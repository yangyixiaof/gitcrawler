package cn.yyx.research.language.JDTHelper;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import cn.yyx.research.language.JDTManager.ClassLineManager;
import cn.yyx.research.language.JDTManager.DataLineManager;
import cn.yyx.research.language.JDTManager.EnteredBlockStack;
import cn.yyx.research.language.JDTManager.EnteredClassStack;
import cn.yyx.research.language.JDTManager.EnteredLambdaParamStack;
import cn.yyx.research.language.JDTManager.EnteredMarkerStack;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.LabelLineManager;
import cn.yyx.research.language.JDTManager.LineCodeManager;
import cn.yyx.research.language.JDTManager.LineManager;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.NodeLineManager;
import cn.yyx.research.language.JDTManager.OffsetLibrary;
import cn.yyx.research.language.JDTManager.OperationType;
import cn.yyx.research.language.JDTManager.OtherCodeManager;
import cn.yyx.research.language.Utility.CorpusContentPair;

public class MyASTVisitor extends ASTVisitor{
	
	private EnteredBlockStack blockstack = new EnteredBlockStack();
	private EnteredClassStack classstack = new EnteredClassStack();
	private EnteredLambdaParamStack lambdaparamstack = new EnteredLambdaParamStack();
	private EnteredMarkerStack markerstack= new EnteredMarkerStack();
	private NodeLineManager nlm = new NodeLineManager();
	private LineCodeManager lcm = new LineCodeManager();
	private DataLineManager dlm = new DataLineManager(blockstack, classstack);
	private NodeCodeManager ncm = new NodeCodeManager();
	private LabelLineManager llm = new LabelLineManager();
	private ClassLineManager clm = new ClassLineManager();
	private LineManager lm = new LineManager();
	private OtherCodeManager ocm = new OtherCodeManager();
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	private Map<Integer, ASTNode> nodelink = new TreeMap<Integer, ASTNode>();
	
	protected String GetRefCode(ASTNode node, int currline) {
		String code = null;
		Integer nline = nlm.GetAstNodeLineInfo(node);
		if (nline != null) {
			code = OffsetLibrary.GetOffsetDescription(nline - currline);
		} else {
			String data = node.toString();
			/*
			 * if (node instanceof FieldAccess && data.startsWith("this.")) {
			 * isfield = true; data = data.substring("this.".length()); }
			 */
			int lline = dlm.GetDataLineInfo(data);
			if (lline == GCodeMetaInfo.OutofScopeVarOrObject) {
				String nodecode = ncm.GetAstNodeCode(node);
				if (nodecode == null) {
					ASTNode linkednode = nodelink.get(node.hashCode());
					if (linkednode != null) {
						return GetRefCode(linkednode, currline);
					}
					nodecode = node.toString();
					 // debuging
					 System.err.println("Warning : No node code, just use the raw string of the node. nodecode is :"+nodecode);
					// new Exception("No node code").printStackTrace();
					// System.exit(1);
				}
				code = nodecode + GCodeMetaInfo.CommonEnd;
			} else {
				if (lline == GCodeMetaInfo.IsField) {
					code = GCodeMetaInfo.IsFieldDesc;
				} else {
					code = OffsetLibrary.GetOffsetDescription(lline - currline);
				}
			}
		}
		return code;
	}
	
	protected int OneTextOneLine(String nodestr) {
		int line = lm.NewLine();
		lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" + nodestr);
		return line;
	}
	
	protected int OneTextOneLine(String commandtype, String nodestr) {
		int line = lm.NewLine();
		lcm.AddLineCode(line, commandtype + nodestr);
		return line;
	}
	
	protected void UnchangedNode(ASTNode node) {
		ncm.AddASTNodeCode(node, node.toString());
	}
	
	protected void UnchangedNode(ASTNode node, String nodestr) {
		ncm.AddASTNodeCode(node, nodestr);
	}
	
	protected boolean LineOccupied(ASTNode node)
	{
		return nlm.GetAstNodeLineInfo(node) != null;
	}
	
	protected int VisitLineOccupy(ASTNode node) {
		int line = lm.NewLine();
		nlm.AddASTNodeLineInfo(node, line);
		return line;
	}
	
	protected Integer GetOccupiedLine(ASTNode node) {
		return nlm.GetAstNodeLineInfo(node);
	}
	
	protected void EndVisitReplaceLineOccupyWithRealContent(int line, ASTNode node, String code) {
		ncm.AddASTNodeCode(node, code);
		lcm.AddLineCode(line, code);
	}
	
	@Override
	public String toString() {
		return lcm.GetGeneratedCode();
	}
	
	public String GetGeneratedCode() {
		return lcm.GetGeneratedCode();
	}
	
	public ArrayList<CorpusContentPair> GetOtherGeneratedCode() {
		return ocm.GetOtherGeneratedCode();
	}
	
	/*
	 * protected int OneTextNodeOneLine(ASTNode node) { int line = lm.NewLine();
	 * nlm.AddASTNodeLineInfo(node, line); ncm.AddASTNodeCode(node,
	 * OperationType.NearlyCommonText + "#" + node.toString());
	 * lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" +
	 * node.toString()); return line; }
	 */
	
	protected void ResetDLM() {
		dlm.ResetCurrentClassField();
	}
	
	protected void OneSentenceEnd() {
		OneTextOneLine(".");
	}
	
	protected boolean isFirstLevelMethod(MethodDeclaration node) {
		int classhashcode = classstack.getClassId(0);
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == classhashcode) {
			return true;
		}
		return false;
	}
	
	protected void EnterBlock(ASTNode node, boolean isclass) {
		// System.out.println("Hashcode:"+node.hashCode()+";node:"+node);
		dlm.EnterBlock(node.hashCode(), isclass);
	}
	
	protected void ExitBlock(boolean isclass) {
		dlm.ExitBlock(isclass);
	}
	
	protected void EnterLambdaParam(LambdaExpression lambda)
	{
		lambdaparamstack.push(lambda.hashCode());
	}
	
	protected void ExitLambdaParam(LambdaExpression lambda)
	{
		lambdaparamstack.pop();
	}
	
	protected void GiveLinkBetweenNodes(ASTNode linkingnode, ASTNode nodetobelinked) {
		nodelink.put(linkingnode.hashCode(), nodetobelinked);
	}
	
	protected void AppendOtherCode(String corpus, String code)
	{
		ocm.AppendOtherCode(corpus, code);
	}
	
}