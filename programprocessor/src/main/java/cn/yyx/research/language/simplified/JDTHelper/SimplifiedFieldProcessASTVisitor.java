package cn.yyx.research.language.simplified.JDTHelper;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;

public class SimplifiedFieldProcessASTVisitor extends SimplifiedCodeGenerateASTVisitor {
	
	protected Integer CurrentLevelClass = null;
	
	protected ASTNode atp = null;
	
	public SimplifiedFieldProcessASTVisitor(SimplifiedCodeGenerateASTVisitor scga, ASTNode atp) {
		ocm = scga.ocm;
		ojfc = scga.ojfc;
		jc = scga.jc;
		mw = scga.mw;
		acp = scga.acp;
		fotp = scga.fotp;
		sdm = scga.sdm;
		cjcs = scga.cjcs;
		ljcs = scga.ljcs;
		FirstLevelClass = scga.FirstLevelClass;
		VeryRecentDeclaredType = scga.VeryRecentDeclaredType;
		fielddeclared = scga.fielddeclared;
		berefered = scga.berefered;
		// bereferedAlready = scga.bereferedAlready;
		referedcnt = scga.referedcnt;
		referhint = scga.referhint;
		refernoline = scga.refernoline;
		runpermit = scga.runpermit;
		runforbid = scga.runforbid;
		typesimp = scga.typesimp;
		dostmtln = scga.dostmtln;
		scopeck = scga.scopeck;
		omcanonystack = scga.omcanonystack;
		argmutiple = scga.argmutiple;
		omc = scga.omc;
		this.atp = atp;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		return false;
	}
	
	@Override
	public void endVisit(MethodDeclaration node) {
	}
	
	@Override
	public boolean visit(Initializer node) {
		return false;
	}
	
	@Override
	public void endVisit(Initializer node) {
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		return HandleCurrentLevelControl(node.hashCode());
	}
	
	@Override
	public void endVisit(TypeDeclaration node) {
	}
	
	@Override
	public boolean visit(EnumDeclaration node) {
		return false;
	}
	
	@Override
	public void endVisit(EnumDeclaration node) {
	}
	
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		boolean ifcontinue = HandleCurrentLevelControl(node.hashCode());
		/*if (ifcontinue)
		{
			jc = ojfacc;
		}*/
		return ifcontinue;
	}
	
	@Override
	public void endVisit(AnonymousClassDeclaration node) {
		// jc = ojfc;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(FieldDeclaration node) {
		// System.out.println("FieldDeclaration:" + node + ";typeclass:" + node.getType().getClass());
		String typecode = TypeCode(node.getType(), true);
		SetVeryRecentDeclaredType(typecode);
		// String nodecode = GenerateVariableDeclarationTypeCode(typecode, null);
		// GenerateOneLine(nodecode, false, false, false, true, null);
		List<VariableDeclarationFragment> fs = node.fragments();
		Iterator<VariableDeclarationFragment> itr = fs.iterator();
		while (itr.hasNext())
		{
			VariableDeclarationFragment vdf = itr.next();
			fielddeclared.AddNodeHelp(vdf.getName().hashCode(), true);
		}
		// VeryRecentIsFieldDeclared = true;
		return true;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(FieldDeclaration node) {
		SetVeryRecentDeclaredType(null);
		List<VariableDeclarationFragment> fs = node.fragments();
		Iterator<VariableDeclarationFragment> itr = fs.iterator();
		while (itr.hasNext())
		{
			VariableDeclarationFragment vdf = itr.next();
			fielddeclared.DeleteNodeHelp(vdf.getName().hashCode());
		}
		// VeryRecentIsFieldDeclared = false;
		AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
	}
	
	protected boolean HandleCurrentLevelControl(int classhashcode)
	{
		if (CurrentLevelClass == null)
		{
			CurrentLevelClass = classhashcode;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean preVisit2(ASTNode node) {
		boolean fres = true;
		if (node == atp)
		{
			return fres;
		}
		Boolean forbid = runforbid.GetNodeHelp(node.hashCode());
		if (forbid != null && forbid == true)
		{
			fres = fres && false;
		}
		return fres;
	}

	@Override
	public void postVisit(ASTNode node) {
		if (node == atp)
		{
			return;
		}
		fotp.PreIsOver(node);
		if ((node instanceof AbstractTypeDeclaration) || (node instanceof AnonymousClassDeclaration) || (node instanceof LambdaExpression))
		{
			ExitBlock(node);
		}
	}
	
}