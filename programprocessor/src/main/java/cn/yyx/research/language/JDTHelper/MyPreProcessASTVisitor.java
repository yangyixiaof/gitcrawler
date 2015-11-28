package cn.yyx.research.language.JDTHelper;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.ScopeDataManager;

public class MyPreProcessASTVisitor extends ASTVisitor{
	
	// private EnteredLambdaParamFlag lambdaparamstack = new EnteredLambdaParamFlag();
	// private NodeLineManager nlm = new NodeLineManager();
	// private LineManager lm = new LineManager();
	// private LineCodeManager lcm = new LineCodeManager();
	private NodeCodeManager ncm = new NodeCodeManager();
	
	private ScopeDataManager sdm = new ScopeDataManager();
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	private Map<Integer, ASTNode> nodelink = new TreeMap<Integer, ASTNode>();
	// a node is only equivalent to one node.
	private Map<Integer, Integer> equivalentScope = new TreeMap<Integer, Integer>();
	
	@Override
	public void postVisit(ASTNode node) {
		fotp.PreIsOver(node);
		super.postVisit(node);
	}
	
	@Override
	public void preVisit(ASTNode node) {
		fotp.PostIsBegin(node);
		super.preVisit(node);
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		// Do nothing now.
		// System.out.println("TypeDeclaration Begin");
		// System.out.println(node.hashCode());
		// System.out.println("TypeDeclaration End");
		// classstack.push(node.hashCode());
		// blockstack.push(node.hashCode());
		EnterBlock(node, true);
		return super.visit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		ExitBlock();
	}
	
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		// System.out.println("AnonymousClassDeclaration Begin");
		// System.out.println(node);
		// System.out.println("AnonymousClassDeclaration End");
		EnterBlock(node, true);
		return super.visit(node);
	}

	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		ExitBlock();
	}
	
	@Override
	public boolean visit(Block node) {
		// System.out.println("Block:"+node);
		int id = node.hashCode();
		Integer equivalentScopeId = equivalentScope.get(id);
		if (equivalentScopeId == null) // || !ContainsScope(equivalentScopeId) this judgment could not exist.
		{
			EnterBlock(node, false);
		}
		// int line = VisitLineOccupy(node);
		// String code = OperationType.BlockCommand + "#" + "ENTER#";
		// EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(Block node) {
		// System.out.println("node:"+node);
		// System.out.println("node id:"+node.hashCode());
		// OneTextOneLine(OperationType.BlockCommand + "#", "EXIT#");
		int id = node.hashCode();
		Integer equivalentScopeId = equivalentScope.get(id);
		if (equivalentScopeId == null) // || !ContainsScope(equivalentScopeId) this judgment could not exist.
		{
			ExitBlock();
		}
	}
	
	protected void AddEquivalentScope(ASTNode node1, ASTNode node2)
	{
		equivalentScope.put(node2.hashCode(), node1.hashCode());
	}
	
	// If doesn't know the kind, just set one as random. The one must be the big kind you want.
	protected String GetDataOffset(String data, String kind) {
		String code = getSdm().GetDataAssignOffsetInfo(data, ScopeDataManager.GetManagerLevelHintForKind(kind));
		return code;
		/*Integer nline = nlm.GetAstNodeLineInfo(node);
		if (nline != null) {
			code = OffsetLibrary.GetOffsetDescription(nline - currline);
		} else {
			String data = node.toString();
			Integer lline = sdm.GetDataAssignOffsetInfo(data);
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
		return code;*/
	}
	
	/*protected int OneTextOneLine(String commandtype, String nodestr) {
		int line = lm.NewLine();
		lcm.AddLineCode(line, commandtype + nodestr);
		return line;
	}*/
	
	/*protected void UnchangedNode(ASTNode node) {
		getNcm().AddASTNodeCode(node, node.toString());
	}
	
	protected void UnchangedNode(ASTNode node, String nodestr) {
		getNcm().AddASTNodeCode(node, nodestr);
	}*/
	
	/*protected boolean LineOccupied(ASTNode node)
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
	}*/
	
	/*
	 * protected int OneTextNodeOneLine(ASTNode node) { int line = lm.NewLine();
	 * nlm.AddASTNodeLineInfo(node, line); ncm.AddASTNodeCode(node,
	 * OperationType.NearlyCommonText + "#" + node.toString());
	 * lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" +
	 * node.toString()); return line; }
	 */
	
	protected void ResetDLM() {
		getSdm().ResetCurrentClassField();
	}
	
	protected boolean isFirstLevelASTNode(ASTNode node) {
		int classhashcode = getSdm().GetFirstClassId();
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == classhashcode) {
			return true;
		}
		return false;
	}
	
	protected void EnterBlock(ASTNode node, boolean isclass) {
		// System.out.println("Hashcode:"+node.hashCode()+";node:"+node);
		getSdm().EnterBlock(node.hashCode(), isclass);
	}
	
	protected void ExitBlock() {
		getSdm().ExitBlock();
	}
	
	protected boolean ContainsScope(Integer equid) {
		return getSdm().ContainsScope(equid);
	}
	
	/*protected void EnterLambdaParam(LambdaExpression lambda)
	{
		lambdaparamstack.push(lambda.hashCode());
	}
	
	protected void ExitLambdaParam(LambdaExpression lambda)
	{
		lambdaparamstack.pop();
	}*/
	
	protected void GiveLinkBetweenNodes(ASTNode linkingnode, ASTNode nodetobelinked) {
		nodelink.put(linkingnode.hashCode(), nodetobelinked);
	}
	
	protected void AddFirstOrderTask(FirstOrderTask runtask)
	{
		fotp.InfixNodeAddFirstOrderTask(runtask);
	}

	public NodeCodeManager getNcm() {
		return ncm;
	}

	public void setNcm(NodeCodeManager ncm) {
		this.ncm = ncm;
	}

	public ScopeDataManager getSdm() {
		return sdm;
	}

	public void setSdm(ScopeDataManager sdm) {
		this.sdm = sdm;
	}
	
	protected void DataNewlyUsed(String data, String kind, boolean isFieldDeclare, boolean isCommonDeclare)
	{
		sdm.AddDataNewlyUsed(data, kind, isFieldDeclare, isCommonDeclare);
	}
	
	protected void AddNodeCode(ASTNode node, String code)
	{
		ncm.AddASTNodeCode(node, code);
	}
	
	protected String GetNodeCode(ASTNode node)
	{
		return ncm.GetAstNodeCode(node);
	}
	
	protected void AddNodeHasOccupiedOneLine(ASTNode node, Boolean HasOccupiedOneLine)
	{
		ncm.AddASTNodeHasOccupiedOneLine(node, HasOccupiedOneLine);
	}
	
	protected Boolean GetNodeHasOccupiedOneLine(ASTNode node)
	{
		return ncm.GetAstNodeHasOccupiedOneLine(node);
	}
	
	protected void AddNodeHasContentHolder(ASTNode node, Boolean ifHasContentHolder)
	{
		ncm.AddASTNodeIfHasContentHolder(node, ifHasContentHolder);
	}
	
	protected boolean GetNodeHasContentHolder(ASTNode node)
	{
		return ncm.GetAstNodeHasContentHolder(node);
	}
	
	protected String PushBackContentHolder(String code, ASTNode node)
	{
		AddNodeHasContentHolder(node, true);
		return code + GCodeMetaInfo.ContentHolder;
	}
	
}