package cn.yyx.research.language.JDTHelper;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.NodeCode;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.NodeLevelManager;
import cn.yyx.research.language.JDTManager.NodeTypeLibrary;
import cn.yyx.research.language.JDTManager.OneJavaFileCode;
import cn.yyx.research.language.JDTManager.OperationType;
import cn.yyx.research.language.JDTManager.OtherCodeManager;
import cn.yyx.research.language.Utility.CorpusContentPair;

public class MyCodeGenerateASTVisitor extends ASTVisitor{
	
	private OtherCodeManager ocm = new OtherCodeManager();
	// One Method Code.
	private NodeCode omc = new NodeCode();
	private OneJavaFileCode ojfc = new OneJavaFileCode();
	
	// node code should be as such System.out.println(STR#,HOLE#)#14#b/
	// if no code, just not set NodeCodeManager.
	// node type and level should be deleted after used immediately.
	// private NodeTypeManager ntm = new NodeTypeManager();
	private NodeLevelManager nlm = new NodeLevelManager();
	// private HoleManager hm = new HoleManager();
	
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	
	private NodeCodeManager ncm = null;
	
	private Integer FirstLevelClass = null;
	
	public MyCodeGenerateASTVisitor(MyPreProcessASTVisitor mppast) {
		ncm = mppast.getNcm();
	}
	
	@Override
	public void preVisit(ASTNode node) {
		nlm.PreVisit(node);
		fotp.PostIsBegin(node);
		super.preVisit(node);
	}
	
	@Override
	public void postVisit(ASTNode node) {
		nlm.PostVisit(node);
		fotp.PreIsOver(node);
		super.postVisit(node);
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		if (omc == null)
		{
			omc = new NodeCode();
		}
		if (getFirstLevelClass() == null)
		{
			FirstLevelClass = node.hashCode();
		}
		return super.visit(node);
	}
	
	@Override
	public void endVisit(TypeDeclaration node) {
		if (FirstLevelClass == node.hashCode())
		{
			FirstLevelClass = null;			
		}
	}
	
	@Override
	public boolean visit(Initializer node) {
		// Do nothing now.
		// System.out.println("Initializer:"+node);
		// ResetDLM();
		if (isFirstLevelASTNode(node))
		{
			omc = new NodeCode();
		}
		return super.visit(node);
	}
	
	@Override
	public void endVisit(Initializer node) {
		FlushCode();
		// dlm.ClearRawStringDataLineInfo();
	}
	
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		// System.out.println("AnonymousClassDeclaration Begin");
		// System.out.println(node);
		// System.out.println("AnonymousClassDeclaration End");
		nlm.Visit(node);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		nlm.EndVisit(node);
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		if (isFirstLevelASTNode(node))
		{
			omc = new NodeCode();
		}
		return super.visit(node);
	}
	
	@Override
	public void endVisit(MethodDeclaration node) {
		if (isFirstLevelASTNode(node))
		{
			FlushCode();
		}
		else
		{
			OneSentenceEnd();
		}
	}
	
	@Override
	public boolean visit(Block node) {
		// System.out.println("Block:"+node);
		nlm.Visit(node);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(Block node) {
		nlm.EndVisit(node);
	}
	
	@Override
	public boolean visit(ParenthesizedExpression node) {
		nlm.Visit(node);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ParenthesizedExpression node) {
		// System.out.println("ParenthesizedExpression:"+node);
		// System.out.println("ParenthesizedExpression:"+node.getExpression());
		// GiveLinkBetweenNodes(node, node.getExpression());
		nlm.EndVisit(node);
	}
	
	protected boolean isFirstLevelASTNode(ASTNode node) {
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == getFirstLevelClass()) {
			return true;
		}
		return false;
	}
	
	protected void AppendOtherCode(String corpus, String code)
	{
		ocm.AppendOtherCode(corpus, code);
	}
	
	protected void AddFirstOrderTask(FirstOrderTask runtask)
	{
		fotp.InfixNodeAddFirstOrderTask(runtask);
	}
	
	// code has ......#leixing, the rest is %b/ as an example, only need to add info of lines.
	protected void TrulyGenerateOneLine(ASTNode node, int level, boolean hasContentHolder) {
		/*if (omc == null)
		{
			System.err.println(node);
		}*/
		
		String nodecode = GetNodeCode(node) + GCodeMetaInfo.CommonSplitter + OperationType.GetTypeDescriptionId(node.getClass());
		nodecode += "%" + HandleNodeType(node) + "/";
		omc.AddOneLineCode(nodecode, level, hasContentHolder);
	}
	
	protected void TrulyGenerateOneLine(String rawtext, Integer astNodeType, Character relativeNodeType, int level, boolean hasContentHolder) {
		String nodecode = rawtext + GCodeMetaInfo.CommonSplitter + astNodeType;
		nodecode += "%" + relativeNodeType + "/";
		omc.AddOneLineCode(nodecode, level, hasContentHolder);
	}
	
	public ArrayList<CorpusContentPair> GetOtherGeneratedCode() {
		return ocm.GetOtherGeneratedCode();
	}
	
	protected void DecreaseLevel()
	{
		nlm.DecreaseLevel();
	}
	
	protected void IncreaseLevel()
	{
		nlm.IncreaseLevel();
	}
	
	protected void OneSentenceEnd() {
		ojfc.OneSentenceEnd();
	}
	
	protected void ClearMethodNodeCode() {
		omc = null;
	}

	protected void PushMethodNodeCodeToJavaFileCode() {
		ojfc.AddOneMethodNodeCode(omc);
	}
	
	protected void FlushCode()
	{
		OneSentenceEnd();
		PushMethodNodeCodeToJavaFileCode();
		ClearMethodNodeCode();
	}
	
	protected int GetNodeLevel(ASTNode node)
	{
		return nlm.GetNodeLevel(node);
	}
	
	protected Character HandleNodeType(ASTNode node)
	{
		if (ncm.IsNewStatement(node))
		{
			ncm.AddNodeType(node, NodeTypeLibrary.newstart);
		}
		return ncm.GetNodeType(node);
	}
	
	public Map<String, String> GetGeneratedCode()
	{
		Map<String, String> result = new TreeMap<String, String>();
		result.putAll(ocm.getOtherCodeMap());
		result.put(GCodeMetaInfo.LogicCorpus, ojfc.toString());
		return result;
	}
	
	public void RegistFirstNodeAfterDecreasingElement(ASTNode node) {
		nlm.RegistFirstNodeAfterDecreasingElement(node);
	}
	
	public void RegistLastNodeBeforeIncreaseingElement(ASTNode node) {
		nlm.RegistLastNodeBeforeIncreaseingElement(node);
	}

	public Integer getFirstLevelClass() {
		return FirstLevelClass;
	}
	
	protected String GetNodeCode(ASTNode node)
	{
		return ncm.GetAstNodeCode(node);
	}
	
	protected Boolean GetNodeHasOccupiedOneLine(ASTNode node)
	{
		return ncm.GetAstNodeHasOccupiedOneLine(node);
	}
	
	protected boolean GetNodeHasContentHolder(ASTNode node)
	{
		return ncm.GetAstNodeHasContentHolder(node);
	}
	
	protected boolean GetNodeInMultipleLine(ASTNode node)
	{
		return ncm.GetAstNodeInMultipleLine(node);
	}
	
	protected boolean GetNodeHasUsed(ASTNode node)
	{
		return ncm.GetNodeHasUsed(node);
	}
	
	protected char GetNodeType(ASTNode node)
	{
		return ncm.GetNodeType(node);
	}
	
	protected boolean ShouldExecute(ASTNode node)
	{
		if (GetNodeHasUsed(node))
		{
			return false;
		}
		return true;
	}
	
}