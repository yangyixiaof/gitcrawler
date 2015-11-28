package cn.yyx.research.language.JDTHelper;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.HoleManager;
import cn.yyx.research.language.JDTManager.NodeCode;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.NodeLevelManager;
import cn.yyx.research.language.JDTManager.NodeTypeManager;
import cn.yyx.research.language.JDTManager.OneJavaFileCode;
import cn.yyx.research.language.JDTManager.OtherCodeManager;
import cn.yyx.research.language.JDTManager.ScopeDataManager;
import cn.yyx.research.language.Utility.CorpusContentPair;

public class MyCodeGenerateASTVisitor extends ASTVisitor{
	
	private OtherCodeManager ocm = new OtherCodeManager();
	private NodeCode omc = new NodeCode();
	private OneJavaFileCode ojfc = new OneJavaFileCode();
	
	// node code should be as such System.out.println(STR#,HOLE#)#14#b/
	// if no code, just not set NodeCodeManager.
	// node type and level should be deleted after used immediately.
	private NodeTypeManager ntm = new NodeTypeManager();
	private NodeLevelManager nlm = new NodeLevelManager();
	private HoleManager hm = new HoleManager();
	
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	
	private NodeCodeManager ncm = null;
	private ScopeDataManager sdm = null;
	
	public MyCodeGenerateASTVisitor(MyPreProcessASTVisitor mppast) {
		ncm = mppast.getNcm();
		sdm = mppast.getSdm();
	}
	
	@Override
	public void postVisit(ASTNode node) {
		nlm.PostVisit(node);
		fotp.PreIsOver(node);
		super.postVisit(node);
	}
	
	@Override
	public void preVisit(ASTNode node) {
		nlm.PreVisit(node);
		fotp.PostIsBegin(node);
		super.preVisit(node);
	}
	
	protected boolean isFirstLevelASTNode(ASTNode node) {
		int classhashcode = sdm.GetFirstClassId();
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == classhashcode) {
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
	
	// code has ......#leixing/b, only need to add info of lines.
	protected void TrulyGenerateOneLine(String nodestr, int level, boolean hasContentHolder) {
		// int line = lm.NewLine();
		// lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" + nodestr);
		omc.AddOneLineCode(nodestr, level, hasContentHolder);
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
		omc = new NodeCode();
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
	
	protected void BeginningLevelSet(ASTNode node)
	{
		int level = GetNodeLevel(node);
		omc.setFirstCodeLevel(level);
		omc.setLastCodeLevel(level);
	}
	
	protected int GetNodeLevel(ASTNode node)
	{
		return nlm.GetNodeLevel(node);
	}
	
	protected Character HandleNodeType(ASTNode node)
	{
		if (ntm.IsNewStatement(node))
		{
			ntm.AddNodeType(node, 'b');
		}
		return ntm.GetNodeType(node);
	}
	
}