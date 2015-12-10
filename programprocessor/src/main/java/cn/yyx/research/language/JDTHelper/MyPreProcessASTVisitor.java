package cn.yyx.research.language.JDTHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import cn.yyx.research.language.JDTManager.DebugNodeCorrespondingCode;
import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.KindLibrary;
import cn.yyx.research.language.JDTManager.NoVisitNodeManager;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.NodeTypeLibrary;
import cn.yyx.research.language.JDTManager.ReferenceHintLibrary;
import cn.yyx.research.language.JDTManager.ScopeDataManager;
import cn.yyx.research.language.JDTManager.VarOrObjReferenceManager;

public class MyPreProcessASTVisitor extends ASTVisitor{
	
	// private EnteredLambdaParamFlag lambdaparamstack = new EnteredLambdaParamFlag();
	// private NodeLineManager nlm = new NodeLineManager();
	// private LineManager lm = new LineManager();
	// private LineCodeManager lcm = new LineCodeManager();
	private NodeCodeManager ncm = new NodeCodeManager();
	
	private ScopeDataManager sdm = new ScopeDataManager();
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	
	VarOrObjReferenceManager voorm = new VarOrObjReferenceManager();
	
	NoVisitNodeManager nvnm = new NoVisitNodeManager();
	
	// private Map<Integer, ASTNode> nodelink = new TreeMap<Integer, ASTNode>();
	// a node is only equivalent to one node.
	private Map<Integer, Integer> equivalentScope = new TreeMap<Integer, Integer>();
	
	@Override
	public void postVisit(ASTNode node) {
		fotp.PreIsOver(node);
		super.postVisit(node);
	}
	
	@Override
	public void preVisit(ASTNode node) {
		// debuging
		DebugNodeCorrespondingCode.AddIdNodePair(node);
		
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
		NoVisit(node.getName());
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
	
	@Override
	public boolean visit(ParenthesizedExpression node) {
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ParenthesizedExpression node) {
		// System.out.println("ParenthesizedExpression:"+node);
		// System.out.println("ParenthesizedExpression:"+node.getExpression());
		// GiveLinkBetweenNodes(node, node.getExpression());
	}
	
	protected void AddEquivalentScope(ASTNode node1, ASTNode node2)
	{
		equivalentScope.put(node2.hashCode(), node1.hashCode());
	}
	
	// If doesn't know the kind, just set one as random. The one must be the big kind you want.
	protected String GetDataOffset(String data, String kind) {
		String code = getSdm().GetDataAssignOffsetInfo(data, KindLibrary.GetManagerLevelHintForKind(kind), kind);
		DataNewlyUsed(data, kind, false, false);
		return code;
	}
	
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
	
	/*protected void AddLinkBetweenNodes(ASTNode linkingnode, ASTNode nodetobelinked) {
		nodelink.put(linkingnode.hashCode(), nodetobelinked);
	}*/
	
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
	
	protected void AddNodeInMultipleLine(ASTNode node, Boolean inMultipleLine)
	{
		ncm.AddASTNodeInMultipleLine(node, inMultipleLine);
	}
	
	protected boolean GetNodeInMultipleLine(ASTNode node)
	{
		return ncm.GetAstNodeInMultipleLine(node);
	}
	
	public void AddNodeHasUsed(ASTNode node, boolean used)
	{
		ncm.AddNodeHasUsed(node, used);
	}
	
	public boolean GetNodeHasUsed(ASTNode node)
	{
		return ncm.GetNodeHasUsed(node);
	}
	
	public void AddNodeType(ASTNode node, char type)
	{
		ncm.AddNodeType(node, type);
	}
	
	public char GetNodeType(ASTNode node)
	{
		return ncm.GetNodeType(node);
	}
	
	protected String PushBackContentHolder(String code, ASTNode node)
	{
		AddNodeHasContentHolder(node, true);
		return code + GCodeMetaInfo.ContentHolder;
	}
	
	protected void AddReferenceUpdateHint(ASTNode node, Integer hint)
	{
		voorm.AddReferenceUpdateHint(node, hint);
	}
	
	protected void DeleteReferenceUpdateHint(ASTNode node)
	{
		voorm.DeleteReferenceUpdateHint(node);
	}
	
	protected Integer GetReferenceUpdateHint(ASTNode node)
	{
		return voorm.GetReferenceUpdateHint(node);
	}
	
	protected void AddNodeInMultipleLineWhenRemainIsContentHolder(ASTNode remain, ASTNode container)
	{
		if (GetNodeInMultipleLine(remain) || GetNodeHasContentHolder(remain))
		{
			AddNodeInMultipleLine(container, true);
		}
	}
	
	// v means virtual, r means real.
	protected void DirectLinkCode(ASTNode vnode, ASTNode rnode)
	{
		ncm.AddNodeLink(vnode, rnode);
		/*AddNodeCode(vnode, GetNodeCode(rnode));
		AddNodeHasUsed(rnode, true);
		if (GetNodeHasContentHolder(rnode))
		{
			AddNodeHasContentHolder(rnode, true);
		}
		if (GetNodeHasOccupiedOneLine(rnode))
		{
			AddNodeHasOccupiedOneLine(rnode, true);
		}
		if (GetNodeInMultipleLine(rnode))
		{
			AddNodeInMultipleLine(rnode, true);
		}*/
	}
	
	protected void MethodInvocationAddHint(List<ASTNode> args)
	{
		for (ASTNode arg : args) {
			AddReferenceUpdateHint(arg, ReferenceHintLibrary.DataUpdate);
		}
	}
	
	protected void MethodInvocationDeleteHint(List<ASTNode> args)
	{
		for (ASTNode arg : args) {
			DeleteReferenceUpdateHint(arg);
		}
	}
	
	protected Boolean MethodInvocationCode(String methodName, List<ASTNode> args, StringBuilder code)
	{
		// "new" + GCodeMetaInfo.WhiteSpaceReplacer + 
		code.append(methodName);
		String pre = "(";
		String post = ")";
		code.append(pre);
		boolean inOneLine = true;
		for (ASTNode arg : args) {
			if (GetNodeHasOccupiedOneLine(arg))
			{
				code.append(GCodeMetaInfo.CodeHole);
				AddNodeType(arg, NodeTypeLibrary.comphole);
				inOneLine = false;
			}
			else
			{
				code.append(GetNodeCode(arg));
				AddNodeHasUsed(arg, true);
			}
			code.append(",");
		}
		if (args.size() > 0)
		{
			code.deleteCharAt(code.length()-1);
		}
		code.append(post);
		return inOneLine;
	}
	
	protected void VariableDeclarationReferenceHint(List<VariableDeclarationFragment> fs, int overAllHint)
	{
		for (VariableDeclarationFragment vdf : fs) {
			AddReferenceUpdateHint(vdf.getName(), overAllHint);
		}
	}
	
	protected void VariableDeclarationCode(ASTNode node, List<VariableDeclarationFragment> fs, String type)
	{
		StringBuilder code = new StringBuilder("");
		if (fs != null && fs.size() > 0)
		{
			for (VariableDeclarationFragment vdf : fs) {
				code.append(type);
				Expression iniexpr = vdf.getInitializer();
				if (iniexpr != null) {
					code.append("=");
					if (!GetNodeHasOccupiedOneLine(iniexpr))
					{
						code.append(GetNodeCode(iniexpr));
						AddNodeHasUsed(iniexpr, true);
					}
					else
					{
						if (GetNodeInMultipleLine(iniexpr) || fs.size() > 1)
						{
							code.append(GCodeMetaInfo.CodeHole);
							AddNodeType(iniexpr, NodeTypeLibrary.comphole);
						}
						else
						{
							code.append(GCodeMetaInfo.ContentHolder);
							AddNodeHasContentHolder(node, true);
						}
					}
				}
				code.append(",");
			}
			code.deleteCharAt(code.length()-1);
		}
		AddNodeCode(node, code.toString());
		
		for (VariableDeclarationFragment vdf : fs) {
			DeleteReferenceUpdateHint(vdf);
		}
	}
	
	protected void NoVisit(ASTNode node)
	{
		nvnm.AddNoVisitNode(node.hashCode());
	}
	
	protected boolean ShouldVisit(ASTNode node)
	{
		return nvnm.NeedVisit(node.hashCode());
	}
	
	protected void DeleteNoVisit(ASTNode node)
	{
		nvnm.DeleteNoVisit(node.hashCode());
	}
	
}