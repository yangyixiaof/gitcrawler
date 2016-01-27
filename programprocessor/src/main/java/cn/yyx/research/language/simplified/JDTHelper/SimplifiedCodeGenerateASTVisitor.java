package cn.yyx.research.language.simplified.JDTHelper;

import java.lang.reflect.WildcardType;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.CreationReference;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeMethodReference;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.JCScope;
import cn.yyx.research.language.JDTManager.MethodWindow;
import cn.yyx.research.language.JDTManager.NodeCode;
import cn.yyx.research.language.JDTManager.NodeHelpManager;
import cn.yyx.research.language.JDTManager.NodeLevelManager;
import cn.yyx.research.language.JDTManager.NodeTypeLibrary;
import cn.yyx.research.language.JDTManager.OffsetLibrary;
import cn.yyx.research.language.JDTManager.OneJavaFileAnonymousClassesCode;
import cn.yyx.research.language.JDTManager.OneJavaFileCode;
import cn.yyx.research.language.JDTManager.OperationType;
import cn.yyx.research.language.JDTManager.OtherCodeManager;
import cn.yyx.research.language.JDTManager.ReferenceHint;
import cn.yyx.research.language.JDTManager.ReferenceHintLibrary;
import cn.yyx.research.language.JDTManager.ScopeDataManager;
import cn.yyx.research.language.JDTManager.VarOrObjReferenceManager;

public class SimplifiedCodeGenerateASTVisitor extends ASTVisitor {

	private OtherCodeManager ocm = new OtherCodeManager();
	private NodeCode omc = new NodeCode();
	private OneJavaFileCode ojfc = new OneJavaFileCode();
	private OneJavaFileAnonymousClassesCode ojfacc = new OneJavaFileAnonymousClassesCode();
	private MethodWindow mw = new MethodWindow();
	private FirstOrderTaskPool fotp = new FirstOrderTaskPool();
	private ScopeDataManager sdm = new ScopeDataManager();
	private VarOrObjReferenceManager voorm = new VarOrObjReferenceManager();
	private JCScope cjcs = new JCScope();
	private JCScope ljcs = new JCScope();
	private Integer FirstLevelClass = null;
	private String VeryRecentDeclaredType = null;
	private boolean VeryRecentIsFieldDeclared = false;
	private NodeHelpManager<Boolean> berefered = new NodeHelpManager<Boolean>();
	private NodeHelpManager<String> referedcnt = new NodeHelpManager<String>();
	private NodeHelpManager<Integer> referhint = new NodeHelpManager<Integer>();
	private NodeHelpManager<Boolean> runpermit = new NodeHelpManager<Boolean>();
	private NodeHelpManager<Boolean> runforbid = new NodeHelpManager<Boolean>();

	@Override
	public boolean visit(ImportDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		return false;
	}

	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return false;
	}

	@Override
	public boolean visit(NormalAnnotation node) {
		return false;
	}

	@Override
	public boolean visit(MarkerAnnotation node) {
		return false;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		if (omc == null) {
			omc = (new NodeCode());
			GenerateOneLine(node.getName().toString() + GCodeMetaInfo.ClassDeclarationHint, false, false, false, true);
		}
		if (FirstLevelClass == null) {
			FirstLevelClass = node.hashCode();
		}
		SimplifiedFieldProcessASTVisitor sfpa = new SimplifiedFieldProcessASTVisitor(this);
		node.accept(sfpa);
		return super.visit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		if (FirstLevelClass == node.hashCode()) {
			FirstLevelClass = null;
		}
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		// System.out.println("AnonymousClassDeclaration Begin");
		// System.out.println(node);
		// System.out.println("AnonymousClassDeclaration End");
		JustBeforeAnonymousClassDeclaration();
		SimplifiedFieldProcessASTVisitor sfpa = new SimplifiedFieldProcessASTVisitor(this);
		node.accept(sfpa);
		return super.visit(node);
	}

	@Override
	public boolean visit(Initializer node) {
		// Do nothing now.
		// System.out.println("Initializer:"+node);
		if (isFirstLevelASTNode(node)) {
			if (omc != null && !omc.IsEmpty()) {
				PushMethodNodeCodeToJavaFileCode();
			}
			omc = new NodeCode();
		}
		return super.visit(node);
	}

	@Override
	public void endVisit(Initializer node) {
		if (isFirstLevelASTNode(node)) {
			FlushCode();
		} else {
			OneSentenceEnd();
		}
	}
	
	@Override
	public boolean visit(Block node) {
		GenerateOneLine("{", false, false, false, true);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(Block node) {
		GenerateOneLine("}", false, false, false, true);
	}
	
	@Override
	public void endVisit(ExpressionStatement node) {
		Expression expr = node.getExpression();
		if (expr != null)
		{
			AppendToLast(GCodeMetaInfo.EndOfAStatement);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(LambdaExpression node) {
		List<ASTNode> params = node.parameters();
		StringBuffer nodecode = new StringBuffer("(");
		Iterator<ASTNode> itr = params.iterator();
		while (itr.hasNext())
		{
			ASTNode para = itr.next();
			nodecode.append(para.toString());
			nodecode.append(',');
		}
		if (params.size() > 0)
		{
			nodecode.deleteCharAt(nodecode.length()-1);
		}
		nodecode.append(")->{}");
		GenerateOneLine(nodecode.toString(), false, false, false, true);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(ExpressionMethodReference node) {
		Integer hint = referhint.GetNodeHelp(node.hashCode());
		CheckHint(hint);
		ExpressionReferPreHandle(node.getExpression(), hint);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ExpressionMethodReference node) {
		ExpressionReferPostHandle(node, node.getExpression(), "::", node.getName().toString(), false, false);
	}	
	
	@Override
	public boolean visit(CreationReference node) {
		Type type = node.getType();
		String nodecode = "new::" + TypeCode(type, true);
		GenerateOneLine(nodecode, false, false, false, false);
		return false;
	}
	
	@Override
	public boolean visit(SuperMethodReference node) {
		// System.out.println("SuperMethodReference:"+node);
		QualifiedPreHandle(node, node.getQualifier());
		return super.visit(node);
	}
	
	@Override
	public void endVisit(SuperMethodReference node) {
		QualifiedPostHandle(node, node.getQualifier(), node.getName(), "super", "::");
	}
	
	@Override
	public boolean visit(TypeMethodReference node) {
		// System.out.println("TypeMethodReference:"+node);
		Type type = node.getType();
		String nodecode = node.getName().toString() + "::" + TypeCode(type, true);
		GenerateOneLine(nodecode, false, false, false, false);
		return false;
	}

	@Override
	public boolean visit(CastExpression node) {
		Integer hint = referhint.GetNodeHelp(node.hashCode());
		CheckHint(hint);
		ExpressionReferPreHandle(node.getExpression(), hint);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(CastExpression node) {
		ExpressionReferPostHandle(node, node.getExpression(), "", "(" + TypeCode(node.getType(), true) + ")", false, false);
	}
	
	@Override
	public boolean visit(Assignment node) {
		ExpressionReferPreHandle(node.getLeftHandSide(), ReferenceHintLibrary.DataUpdate);
		int nodehashcode = node.hashCode();
		berefered.AddNodeHelp(nodehashcode, true);
		AddFirstOrderTask(new FirstOrderTask(node.getLeftHandSide(), node.getRightHandSide(), node, false) {
			@Override
			public void run() {
				ExpressionReferPostHandle(node, node.getLeftHandSide(), node.getOperator().toString(), "", true, false);
				String code = referedcnt.GetNodeHelp(nodehashcode);
				if (code == null)
				{
					code = GCodeMetaInfo.PreExist;
				}
				GenerateOneLine(code, true, true, false, false);
				berefered.DeleteNodeHelp(nodehashcode);
				referedcnt.DeleteNodeHelp(nodehashcode);
			}
		});
		return super.visit(node);
	}
	
	protected void LabelReferPreHandle(SimpleName label)
	{
		if (label != null)
		{
			int namehashcode = label.hashCode();
			runpermit.AddNodeHelp(namehashcode, true);
			referhint.AddNodeHelp(namehashcode, ReferenceHintLibrary.LabelUse);
			berefered.AddNodeHelp(namehashcode, true);	
		}
	}
	
	protected void LabelReferPostHandle(String prefix, SimpleName label)
	{
		String labelcode = null;
		if (label != null)
		{
			int namehashcode = label.hashCode();
			labelcode = referedcnt.GetNodeHelp(namehashcode);
			
			runpermit.DeleteNodeHelp(namehashcode);
			referhint.DeleteNodeHelp(namehashcode);
			berefered.DeleteNodeHelp(namehashcode);
			referedcnt.DeleteNodeHelp(namehashcode);
		}
		String nodecode = prefix + (labelcode != null ? GCodeMetaInfo.CommonSplitter + labelcode : "");
		GenerateOneLine(nodecode, false, false, false, true);
	}
	
	@Override
	public boolean visit(BreakStatement node) {
		LabelReferPreHandle(node.getLabel());
		return super.visit(node);
	}
	
	@Override
	public void endVisit(BreakStatement node) {
		LabelReferPostHandle("break", node.getLabel());
	}

	@Override
	public boolean visit(ContinueStatement node) {
		LabelReferPreHandle(node.getLabel());
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ContinueStatement node) {
		LabelReferPostHandle("continue", node.getLabel());
	}

	@Override
	public boolean visit(DoStatement node) {
		GenerateOneLine("do" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
		Statement body = node.getBody();
		Expression expr = node.getExpression();
		AddFirstOrderTask(new FirstOrderTask(body, expr, node, false) {
			@Override
			public void run() {
				ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
			}
		});
		return super.visit(node);
	}
	
	@Override
	public void endVisit(DoStatement node) {
		ExpressionReferPostHandle(node, node.getExpression(), "while", "", false, true);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		int nodehashcode = node.hashCode();
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		berefered.AddNodeHelp(nodehashcode, true);
		runforbid.AddNodeHelp(node.getParameter().hashCode(), true);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(EnhancedForStatement node) {
		int nodehashcode = node.hashCode();
		ExpressionReferPostHandle(node, node.getExpression(), "", "", false, false);
		String code = referedcnt.GetNodeHelp(nodehashcode);
		if (code == null)
		{
			code = GCodeMetaInfo.PreExist;
		}
		String nodecode = "for(" + TypeCode(node.getParameter().getType(),true) + ":" + code + ")";
		GenerateOneLine(nodecode, false, false, false, true);
		
		runforbid.DeleteNodeHelp(node.getParameter().hashCode());
		berefered.DeleteNodeHelp(nodehashcode);
		referedcnt.DeleteNodeHelp(nodehashcode);
	}
	
	@Override
	public boolean visit(ArrayAccess node) {
		// do nothing.
		// TODO
		return super.visit(node);
	};
	
	// TODO tonight over here.
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(InfixExpression node) {
		int nodelevel = GetNodeLevel(node);
		ASTNode left = node.getLeftOperand();
		ASTNode right = node.getRightOperand();
		if (ShouldExecute(node)) {
			AddFirstOrderTask(new FirstOrderTask(left, right, node, false) {
				@Override
				public void run() {
					TrulyGenerateOneLine(node, nodelevel, GetNodeHasContentHolder(node));
				}
			});
		}
		ASTNode pre = right;
		String operatorcode = node.getOperator().toString();
		List<ASTNode> extendops = node.extendedOperands();
		for (ASTNode op : extendops) {
			AddFirstOrderTask(new FirstOrderTask(pre, op, node, false) {
				@Override
				public void run() {
					boolean hasContentHolder = false;
					String postcode = GetNodeCode(getPost());
					if (GetNodeHasOccupiedOneLine(getPost())) {
						postcode = GCodeMetaInfo.ContentHolder;
						hasContentHolder = true;
					}
					String code = operatorcode + postcode;
					TrulyGenerateOneLine(code, OperationType.InfixExpression, NodeTypeLibrary.adjacent, nodelevel,
							hasContentHolder);
				}
			});
			pre = op;
		}
		return super.visit(node);
	}
	
	@Override
	public boolean visit(InstanceofExpression node) {
		ExpressionReferPreHandle(node.getLeftOperand(), ReferenceHintLibrary.DataUse);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(InstanceofExpression node) {
		ExpressionReferPostHandle(node, node.getLeftOperand(), "instanceof", TypeCode(node.getRightOperand(), true), true, true);
		Expression expr = node.getLeftOperand();
		int exprhashcode = expr.hashCode();
		String leftcode = referedcnt.GetNodeHelp(exprhashcode);
		if (leftcode == null)
		{
			leftcode = GCodeMetaInfo.PreExist;
		}
		String nodecode = leftcode + GCodeMetaInfo.WhiteSpaceReplacer + "instanceof" + GCodeMetaInfo.WhiteSpaceReplacer + TypeCode(node.getRightOperand(), true);
		int nodehashcode = node.hashCode();
		if (NodeIsRefered(nodehashcode))
		{
			referedcnt.AddNodeHelp(nodehashcode, nodecode);
		}
		else
		{
			GenerateOneLine(nodecode, false, false, false, true);
		}
		
		referhint.DeleteNodeHelp(exprhashcode);
		referedcnt.DeleteNodeHelp(exprhashcode);
		berefered.DeleteNodeHelp(exprhashcode);
	}
	
	@Override
	public boolean visit(LabeledStatement node) {
		String nodecode = node.getLabel().toString() + GCodeMetaInfo.LabelDeclarationHint;
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}

	@Override
	public boolean visit(PostfixExpression node) {
		ExpressionReferPreHandle(node.getOperand(), ReferenceHintLibrary.DataUpdate);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(PostfixExpression node) {
		ExpressionReferPostHandle(node, node.getOperand(), node.getOperator().toString(), "", true, false);
	}

	@Override
	public boolean visit(PrefixExpression node) {
		ExpressionReferPreHandle(node.getOperand(), ReferenceHintLibrary.DataUpdate);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(PrefixExpression node) {
		ExpressionReferPostHandle(node, node.getOperand(), node.getOperator().toString(), "", false, false);
	}

	@Override
	public boolean visit(ReturnStatement node) {
		Expression expr = node.getExpression();
		if (expr != null)
		{
			ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
		}
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ReturnStatement node) {
		Expression expr = node.getExpression();
		if (expr != null)
		{
			ExpressionReferPostHandle(node, expr, "return", "", false, true);
		}
	}

	@Override
	public boolean visit(SwitchStatement node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(SwitchStatement node) {
		ExpressionReferPostHandle(node, node.getExpression(), "switch", "", false, true);
	}

	@Override
	public boolean visit(SwitchCase node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(SwitchCase node) {
		ExpressionReferPostHandle(node, node.getExpression(), "case", "", false, true);
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getBody(), node, false) {
			@Override
			public void run() {
				ExpressionReferPostHandle(node, node.getExpression(), "synchronized", "", false, true);
			}
		});
		return super.visit(node);
	}

	@Override
	public boolean visit(ThrowStatement node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ThrowStatement node) {
		ExpressionReferPostHandle(node, node.getExpression(), "throw", "", false, true);
	}
	
	@Override
	public boolean visit(CatchClause node) {
		String nodecode = "catch" + GCodeMetaInfo.WhiteSpaceReplacer + TypeCode(node.getException().getType(), true);
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}

	// below are judge and loop related.
	
	@Override
	public boolean visit(WhileStatement node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(WhileStatement node) {
		ExpressionReferPostHandle(node, node.getExpression(), "while", "", false, true);
	}
	
	@Override
	public boolean visit(IfStatement node) {
		ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
		AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getThenStatement(), node, true) {
			@Override
			public void run() {
				ExpressionReferPostHandle(node, node.getExpression(), "if", "", false, true);
			}
		});
		AddFirstOrderTask(new FirstOrderTask(node.getThenStatement(), node.getElseStatement(), node, true) {
			@Override
			public void run() {
				if (getPost() != null) {
					GenerateOneLine("else" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
				}
			}
		});
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(ForStatement node) {
		GenerateOneLine("for" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
		List<ASTNode> inis = node.initializers();
		boolean oneempty = false;
		boolean twoempty = false;
		boolean threeempty = false;
		if (inis == null || inis.size() == 0)
		{
			oneempty = true;
			GenerateOneLine("forIniOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
		}
		Expression expr = node.getExpression();
		if (expr == null)
		{
			twoempty = true;
			GenerateOneLine("forExpOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
		}
		List<ASTNode> ups = node.updaters();
		if (ups == null || ups.size() == 0)
		{
			threeempty = true;
			GenerateOneLine("forUpdOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
		}
		if (!oneempty)
		{
			AddFirstOrderTask(new FirstOrderTask(inis.get(inis.size()-1), null, node, true) {
				@Override
				public void run() {
					GenerateOneLine("forIniOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
				}
			});
		}
		if (!twoempty)
		{
			AddFirstOrderTask(new FirstOrderTask(expr, null, node, true) {
				@Override
				public void run() {
					GenerateOneLine("forExpOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
				}
			});
		}
		if (!threeempty)
		{
			AddFirstOrderTask(new FirstOrderTask(ups.get(ups.size()-1), null, node, true) {
				@Override
				public void run() {
					GenerateOneLine("forUpdOver" + GCodeMetaInfo.DescriptionHint, false, false, false, true);
				}
			});
		}
		return super.visit(node);
	}
	
	// below are VariableDeclarations
	
	@Override
	public boolean visit(VariableDeclarationExpression node) {
		SetVeryRecentDeclaredType(TypeCode(node.getType(), true));
		String nodecode = GenerateVariableDeclarationTypeCode(node.getType(), null);
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(VariableDeclarationExpression node) {
		SetVeryRecentDeclaredType(null);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(SingleVariableDeclaration node) {
		Boolean forbid = runforbid.GetNodeHelp(node.hashCode());
		if (forbid != null && forbid == true)
		{
			return false;
		}
		SetVeryRecentDeclaredType(TypeCode(node.getType(), true));
		String nodecode = GenerateVariableDeclarationTypeCode(node.getType(), node.extraDimensions());
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(SingleVariableDeclaration node) {
		int nodehashcode = node.hashCode();
		Boolean forbid = runforbid.GetNodeHelp(nodehashcode);
		if (forbid != null && forbid == true)
		{
			return;
		}
		SetVeryRecentDeclaredType(null);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		SetVeryRecentDeclaredType(TypeCode(node.getType(), true));
		String nodecode = GenerateVariableDeclarationTypeCode(node.getType(), null);
		GenerateOneLine(nodecode, false, false, false, true);
		VeryRecentIsFieldDeclared = true;
		return super.visit(node);
	}
	
	@Override
	public void endVisit(FieldDeclaration node) {
		SetVeryRecentDeclaredType(null);
		VeryRecentIsFieldDeclared = false;
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		SetVeryRecentDeclaredType(TypeCode(node.getType(), true));
		String nodecode = GenerateVariableDeclarationTypeCode(node.getType(), null);
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(VariableDeclarationStatement node) {
		SetVeryRecentDeclaredType(null);
	}
	
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		VariableDeclarationFragmentPreHandle(node);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(VariableDeclarationFragment node) {
		VariableDeclarationFragmentPostHandle(node);
	}
	
	protected void VariableDeclarationFragmentPreHandle(VariableDeclarationFragment fs)
	{
		int hint = ReferenceHintLibrary.DataDeclare;
		if (VeryRecentIsFieldDeclared)
		{
			hint = ReferenceHintLibrary.FieldDeclare;
		}
		referhint.AddNodeHelp(fs.getName().hashCode(), hint);
		Expression iniexpr = fs.getInitializer();
		if (iniexpr != null)
		{
			berefered.AddNodeHelp(iniexpr.hashCode(), true);	
		}
	}
	
	protected void VariableDeclarationFragmentPostHandle(VariableDeclarationFragment fs)
	{
		StringBuilder code = new StringBuilder("");
		Expression iniexpr = fs.getInitializer();
		int iniexprhashcode = iniexpr.hashCode();
		code.append(GCodeMetaInfo.VariableDeclarationHolder + "=");
		if (iniexpr != null) {
			String exprcnt = referedcnt.GetNodeHelp(iniexprhashcode);
			if (exprcnt != null)
			{
				code.append(exprcnt);
			}
			else
			{
				code.append(GCodeMetaInfo.PreExist);
			}
		}
		code.append(GCodeMetaInfo.EndOfAPartialStatement);		
		GenerateOneLine(code.toString(), false, false, false, true);
		
		// delete hint
		referhint.DeleteNodeHelp(fs.getName().hashCode());
		if (iniexpr != null)
		{
			berefered.DeleteNodeHelp(iniexpr.hashCode());	
		}
	}
	
	@Override
	public boolean visit(ConditionalExpression node) {
		GenerateOneLine("CondExpBegin"+GCodeMetaInfo.DescriptionHint, false, false, false, true);
		AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getThenExpression(), node, false) {
			@Override
			public void run() {
				GenerateOneLine("CondExpQM"+GCodeMetaInfo.DescriptionHint, false, false, false, true);
			}
		});
		AddFirstOrderTask(new FirstOrderTask(node.getThenExpression(), node.getElseExpression(), node, false) {
			@Override
			public void run() {
				GenerateOneLine("CondExpCM"+GCodeMetaInfo.DescriptionHint, false, false, false, true);
			}
		});
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ConditionalExpression node) {
		GenerateOneLine("CondExpEnd"+GCodeMetaInfo.DescriptionHint, false, false, false, true);
	}

	// field access is highly related to the below.

	@Override
	public boolean visit(FieldAccess node) {
		// System.out.println("FieldAccess:"+node);
		// System.out.println("FieldAccessName:"+node.getName());
		// System.out.println("FieldAccessExpr:"+node.getExpression());
		int nodehashcode = node.hashCode();
		Integer hint = referhint.GetNodeHelp(nodehashcode);
		ASTNode expr = node.getExpression();
		int exprhashcode = expr.hashCode();
		if (expr instanceof FieldAccess) {
			berefered.AddNodeHelp(exprhashcode, true);
			referhint.AddNodeHelp(exprhashcode, hint);
		}
		if (expr instanceof ThisExpression)
		{
			int namehashcode = node.getName().hashCode();
			runpermit.AddNodeHelp(namehashcode, true);
			Integer changedhint = ReferenceHintLibrary.ChangeHintHighByteToField(hint);
			referhint.AddNodeHelp(namehashcode, changedhint);
			berefered.AddNodeHelp(namehashcode, true);
		}
		return super.visit(node);
	}
	
	@Override
	public void endVisit(FieldAccess node) {
		String nodecode = "";
		ASTNode expr = node.getExpression();
		if (!(expr instanceof FieldAccess)) {
			if (expr instanceof ThisExpression)
			{
				int namehashcode = node.getName().hashCode();
				nodecode = referedcnt.GetNodeHelp(namehashcode);
			}
			else
			{
				nodecode = node.getName().toString() + "." + GCodeMetaInfo.PreExist;
			}
		}
		else
		{
			nodecode = node.getName().toString() + "." + referedcnt.GetNodeHelp(expr.hashCode());
		}
		int nodehashcode = node.hashCode();
		if (NodeIsRefered(nodehashcode)) {
			referedcnt.AddNodeHelp(nodehashcode, nodecode);
		} else {
			GenerateOneLine(nodecode, true, false, false, false);
		}
		
		// delete hint.
		int exprhashcode = expr.hashCode();
		if (expr instanceof FieldAccess) {
			berefered.DeleteNodeHelp(exprhashcode);
			referhint.DeleteNodeHelp(exprhashcode);
		}
		if (expr instanceof ThisExpression)
		{
			int namehashcode = node.getName().hashCode();
			runpermit.DeleteNodeHelp(namehashcode);
			referhint.DeleteNodeHelp(namehashcode);
			berefered.DeleteNodeHelp(namehashcode);
		}
	}
	
	// below are most important method related : MethodDeclaration.

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(MethodDeclaration node) {
		// System.out.println("MethodDeclarationParent:"+node.getParent().hashCode());
		if (isFirstLevelASTNode(node)) {
			if (omc != null) {
				FlushCode();
				ClearClassAndLabelInfo();
			}
			omc = new NodeCode();
		}
		String nodecode = node.getName().toString() + GCodeMetaInfo.MethodDeclarationHint;
		nodecode = nodecode + "(";
		List<SingleVariableDeclaration> types = node.parameters();
		Iterator<SingleVariableDeclaration> itr = types.iterator();
		while (itr.hasNext()) {
			SingleVariableDeclaration t = itr.next();
			nodecode = nodecode + t.getType().toString() + ",";
		}
		if (types.size() > 0) {
			nodecode = nodecode.substring(0, nodecode.length() - 1);
		}
		nodecode = nodecode + ")";
		GenerateOneLine(nodecode, false, false, false, true);
		return super.visit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		if (isFirstLevelASTNode(node)) {
			FlushCode();
		} else {
			OneSentenceEnd();
		}
	}

	// below are all method invocations.
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(ClassInstanceCreation node) {
		// System.out.println("Node Type:"+node.getType());
		// System.out.println("Body:"+node.getAnonymousClassDeclaration());
		MethodPushReferRequest(node.getExpression(), node.arguments());
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(ClassInstanceCreation node) {
		// System.out.println("Node Type:"+node.getType());
		// System.out.println("Body:"+node.getAnonymousClassDeclaration());
		Expression expr = node.getExpression();
		String invoker = "new";
		if (expr != null)
		{
			int exprhashcode = expr.hashCode();
			String refercnt = referedcnt.GetNodeHelp(exprhashcode);
			if (refercnt != null)
			{
				invoker += ("." + refercnt);
			}
		}
		MethodInvocationCode(node.getType().toString(), invoker, node.arguments());
		MethodDeleteReferRequest(expr, node.arguments());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(ConstructorInvocation node) {
		MethodPushReferRequest(null, node.arguments());
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(ConstructorInvocation node) {
		// Do nothing now.
		// System.out.println("ConstructorInvocation:" + node);
		MethodInvocationCode("this", "this", node.arguments());
		MethodDeleteReferRequest(null, node.arguments());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(SuperConstructorInvocation node) {
		MethodPushReferRequest(node.getExpression(), node.arguments());
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(SuperConstructorInvocation node) {
		Expression expr = node.getExpression();
		String invoker = "this";
		if (expr != null)
		{
			int exprhashcode = expr.hashCode();
			String refercnt = referedcnt.GetNodeHelp(exprhashcode);
			if (refercnt != null)
			{
				invoker = refercnt;
			}
		}
		MethodInvocationCode("super", invoker, node.arguments());
		MethodDeleteReferRequest(expr, node.arguments());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(SuperMethodInvocation node) {
		MethodPushReferRequest(null, node.arguments());
		berefered.AddNodeHelp(node.getQualifier().hashCode(), true);
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(SuperMethodInvocation node) {
		String invoker = "super";
		int qualifierhashcode = node.getQualifier().hashCode();
		berefered.DeleteNodeHelp(qualifierhashcode);
		String qualifier = referedcnt.GetNodeHelp(qualifierhashcode);
		referedcnt.DeleteNodeHelp(qualifierhashcode);
		if (qualifier != null)
		{
			invoker += "." + qualifier;
		}
		MethodInvocationCode(node.getName().toString(), invoker, node.arguments());
		MethodDeleteReferRequest(null, node.arguments());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(MethodInvocation node) {
		MethodPushReferRequest(node.getExpression(), node.arguments());
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(MethodInvocation node) {
		Expression expr = node.getExpression();
		String invoker = "this";
		if (expr != null)
		{
			int exprhashcode = expr.hashCode();
			String refercnt = referedcnt.GetNodeHelp(exprhashcode);
			if (refercnt != null)
			{
				invoker = refercnt;
			}
		}
		MethodInvocationCode(node.getName().toString(), invoker, node.arguments());
		MethodDeleteReferRequest(expr, node.arguments());
	}
	
	protected void MethodPushReferRequest(Expression expr, List<ASTNode> args)
	{
		if (expr != null)
		{
			int exprhashcode = expr.hashCode();
			referhint.AddNodeHelp(exprhashcode, ReferenceHintLibrary.DataUpdate);
			berefered.AddNodeHelp(exprhashcode, true);
		}
		if (args != null && args.size() > 0)
		{
			Iterator<ASTNode> itr = args.iterator();
			while (itr.hasNext()) {
				ASTNode arg = itr.next();
				int arghashcode = arg.hashCode();
				referhint.AddNodeHelp(arghashcode , ReferenceHintLibrary.DataUse);
				berefered.AddNodeHelp(arghashcode, true);
			}
		}
	}
	
	protected void MethodDeleteReferRequest(Expression expr, List<ASTNode> args)
	{
		if (expr != null)
		{
			int exprhashcode = expr.hashCode();
			berefered.DeleteNodeHelp(exprhashcode);
			referedcnt.DeleteNodeHelp(exprhashcode);
			referhint.DeleteNodeHelp(exprhashcode);
		}
		if (args != null && args.size() > 0)
		{
			Iterator<ASTNode> itr = args.iterator();
			while (itr.hasNext()) {
				ASTNode arg = itr.next();
				int arghashcode = arg.hashCode();
				berefered.DeleteNodeHelp(arghashcode);
				referedcnt.DeleteNodeHelp(arghashcode);
				referhint.DeleteNodeHelp(arghashcode);
			}
		}
	}

	protected void MethodInvocationCode(String methodName, String invoker, List<ASTNode> args) {
		StringBuilder nodecode = new StringBuilder("");
		nodecode.append(methodName);
		String pre = "(";
		String post = ")";
		nodecode.append(pre);
		nodecode.append(invoker);
		Iterator<ASTNode> itr = args.iterator();
		while (itr.hasNext()) {
			ASTNode arg = itr.next();
			nodecode.append(",");
			String argcnt = referedcnt.GetNodeHelp(arg.hashCode());
			if (argcnt == null)
			{
				nodecode.append(GCodeMetaInfo.PreExist);
			}
			else
			{
				nodecode.append(argcnt);
			}
		}
		nodecode.append(post);
		GenerateOneLine(nodecode.toString(), false, false, false, true);
	}

	// raw names : handle types : SuperFieldAccess or Name
	
	@Override
	public boolean visit(SuperFieldAccess node) {
		// System.out.println("SuperFieldAccess:" + node);
		// System.out.println("SuperFieldAccess Qualifier:" +
		// System.out.println("SuperFieldAccess Name:" + node.getName());
		Name qualifier = node.getQualifier();
		return QualifiedPreHandle(node, qualifier) && super.visit(node);
	}
	
	@Override
	public void endVisit(SuperFieldAccess node) {
		Name qualifier = node.getQualifier();
		Name name = node.getName();
		QualifiedPostHandle(node, qualifier, name, "super", ".");
	}

	@Override
	public boolean visit(QualifiedName node) {
		Name qualifier = node.getQualifier();
		return QualifiedPreHandle(node, qualifier) && super.visit(node);
	}
	
	@Override
	public void endVisit(QualifiedName node) {
		Name qualifier = node.getQualifier();
		Name name = node.getName();
		QualifiedPostHandle(node, qualifier, name, null, null);
	}
	
	protected boolean QualifiedPreHandle(ASTNode node, Name qualifier)
	{
		int nodehashcode = node.hashCode();
		Boolean canrun = runpermit.GetNodeHelp(nodehashcode);
		if (canrun == null || canrun == false)
		{
			return false;
		}
		int qualifierhashcode = qualifier.hashCode();
		Integer hint = referhint.GetNodeHelp(node.hashCode());
		if (hint != null)
		{
			referhint.AddNodeHelp(qualifierhashcode, hint);
		}
		if (qualifier != null)
		{
			berefered.AddNodeHelp(qualifierhashcode, true);
		}
		return true;
	}
	
	protected void QualifiedPostHandle(ASTNode node, Name qualifier, Name name, String additional, String additionalprefixoperator)
	{
		int nodehashcode = node.hashCode();
		Boolean canrun = runpermit.GetNodeHelp(nodehashcode);
		if (canrun == null || canrun == false)
		{
			return;
		}
		String nodecode = name.toString() + (additional != null ? additionalprefixoperator + additional : "") + (qualifier != null ? "." + referedcnt.GetNodeHelp(qualifier.hashCode()) : "");
		if (NodeIsRefered(nodehashcode))
		{
			referedcnt.AddNodeHelp(nodehashcode, nodecode);
		}
		else
		{
			GenerateOneLine(nodecode, true, false, false, false);
		}
		// delete qualifier refer
		int qualifierhashcode = qualifier.hashCode();
		Integer hint = referhint.GetNodeHelp(node.hashCode());
		if (hint != null)
		{
			referhint.DeleteNodeHelp(qualifierhashcode);
			referhint.DeleteNodeHelp(nodehashcode);
		}
		if (qualifier != null)
		{
			berefered.DeleteNodeHelp(qualifierhashcode);
			referedcnt.DeleteNodeHelp(qualifierhashcode);
		}
	}
	
	@Override
	public boolean visit(SimpleName node) {
		int nodehashcode = node.hashCode();
		Boolean canrun = runpermit.GetNodeHelp(node.hashCode());
		if (canrun == null || canrun == false)
		{
			return false;
		}
		Integer hint = referhint.GetNodeHelp(node.hashCode());
		boolean isfield = false;
		String result = null;
		if (hint != ReferenceHintLibrary.NoHint) {
			String code = null;
			boolean hasCorrespond = false;
			String data = node.toString();
			switch (hint) {
			case ReferenceHintLibrary.DataUse:
				/*
				 * if (data.equals("a")) { System.out.println("DataUse"); }
				 */
				code = GetDataOffset(data, false, false);
				break;
			case ReferenceHintLibrary.FieldUse:
				/*
				 * if (data.equals("a")) { System.out.println("FieldUse"); }
				 */
				isfield = true;
				code = GetDataOffset(data, true, false);
				break;
			case ReferenceHintLibrary.DataUpdate:
				/*
				 * if (data.equals("a")) { System.out.println("DataUpdate");
				 * }
				 */
				code = GetDataOffset(data, false, false);
				DataNewlyUsed(data, null, false, false, false, false, false);
				break;
			case ReferenceHintLibrary.FieldUpdate:
				/*
				 * if (data.equals("a")) {
				 * System.out.println("FieldUpdate"); }
				 */
				isfield = true;
				code = GetDataOffset(data, true, false);
				DataNewlyUsed(data, null, false, false, false, true, false);
				break;
			case ReferenceHintLibrary.DataDeclare:
				/*
				 * if (data.equals("a")) {
				 * System.out.println("DataDeclare"); }
				 */
				String declaredtype = GetVeryRecentDeclaredType();
				// System.out.println("common declaredtype:" + declaredtype
				// + "; and data is:" + data + "; and is final:" +
				// GetVeryRecentDeclaredFinal());
				CheckVeryRecentDeclaredTypeMustNotNull(declaredtype);
				// System.err.println("data is:" + data + "
				// declaredtype:"+declaredtype);
				DataNewlyUsed(data, declaredtype, false, false, true, false, false);
				hasCorrespond = true;
			case ReferenceHintLibrary.FieldDeclare:
				/*
				 * if (data.equals("a")) {
				 * System.out.println("FieldDeclare"); }
				 */
				isfield = true;
				String declaredtype2 = GetVeryRecentDeclaredType();
				CheckVeryRecentDeclaredTypeMustNotNull(declaredtype2);
				DataNewlyUsed(data, declaredtype2, false, true, false, false, false);
				hasCorrespond = true;
			default:
				break;
			}
			if (code != null) {
				result = code;
			} else {
				if (!hasCorrespond) {
					String nodestr = node.toString();
					String pre = (isfield ? "this." : "");
					if (Character.isLowerCase(nodestr.charAt(0)) == true) {
						System.err.println("Debugging Data: " + node
								+ "; No corresponding data offset. Maybe data use or others.");
					}
					result = pre + nodestr;
				}
			}
		} else {
			String nodestr = node.toString();
			if (Character.isLowerCase(nodestr.charAt(0)) == true) {
				System.err.println("Warning Data: " + node
						+ "; just for debugging and testing. The simple name does not have hint.");
			}
			result = nodestr;
		}
		if (NodeIsRefered(nodehashcode))
		{
			referedcnt.AddNodeHelp(nodehashcode, result);
		}
		else
		{
			GenerateOneLine(result, true, false, false, false);
		}
		return super.visit(node);
	}

	// the handle of the following types should use the helper function
	// TypeCode(...).

	/*protected String RawTypesHandle(ASTNode node, boolean simplified) {
		if (node instanceof Type) {
			return TypeCode((Type) node, simplified);
		}
		return null;
	}*/

	@SuppressWarnings("unchecked")
	protected String TypeCode(Type node, boolean simplified) {
		if (node instanceof PrimitiveType) {
			return node.toString();
		}
		String type = node.toString();
		String typecode = GetClassOffset(type);
		ClassNewlyAssigned(type);
		if (node instanceof SimpleType || node instanceof QualifiedType || node instanceof SimpleType
				|| node instanceof NameQualifiedType || node instanceof WildcardType) {
			if (typecode == null) {
				typecode = node.toString();
			}
		}
		if (node instanceof ArrayType) {
			if (typecode == null) {
				if (simplified) {
					int dimens = ((ArrayType) node).dimensions().size();
					String dimenstr = "";
					for (int i = 0; i < dimens; i++) {
						dimenstr += "[]";
					}
					typecode = "Object" + dimenstr;
				} else {
					typecode = node.toString();
				}
			}
		}
		if (node instanceof ParameterizedType) {
			if (typecode == null) {
				if (simplified) {
					typecode = ((ParameterizedType) node).getType().toString();
				} else {
					typecode = node.toString();
				}
			}
		}
		if (node instanceof IntersectionType) {
			IntersectionType tnode = (IntersectionType) node;
			List<Type> types = tnode.types();
			Iterator<Type> itr = types.iterator();
			typecode = "";
			while (itr.hasNext()) {
				Type t = itr.next();
				String tstr = TypeCode(t, simplified);
				typecode = typecode + "&" + tstr;
			}
			if (types.size() > 0) {
				typecode = typecode.substring(1);
			}
		}
		if (node instanceof UnionType) {
			UnionType tnode = (UnionType) node;
			List<Type> types = tnode.types();
			Iterator<Type> itr = types.iterator();
			typecode = "";
			while (itr.hasNext()) {
				Type t = itr.next();
				String tstr = TypeCode(t, simplified);
				typecode = typecode + "|" + tstr;
			}
			if (types.size() > 0) {
				typecode = typecode.substring(1);
			}
		}
		return typecode;
	}

	@Override
	public boolean visit(IntersectionType node) {
		// type & type
		// System.out.println("IntersectionType:" + node);
		// do nothing.
		return super.visit(node);
	}

	@Override
	public boolean visit(UnionType node) {
		// type | type
		// System.out.println("UnionType:" + node);
		// do nothing.
		return super.visit(node);
	}

	@Override
	public boolean visit(PrimitiveType node) {
		// System.out.println("PrimitiveType:" + node);
		// do nothing. but this is different.
		return false;
	}

	public boolean visit(WildcardType node) {
		return false;
	};

	@Override
	public boolean visit(SimpleType node) {
		// System.out.println("SimpleType:" + node);
		// do nothing.
		return false;
	}

	@Override
	public boolean visit(QualifiedType node) {
		// System.out.println("QualifiedType:"+node);
		// do nothing.
		return false;
	}

	@Override
	public boolean visit(ArrayType node) {
		// System.out.println("ArrayType:"+node);
		// do nothing.
		return false;
	}

	@Override
	public boolean visit(ParameterizedType node) {
		// System.out.println("ParameterizedType:"+node);
		// do nothing.
		return false;
	}

	@Override
	public boolean visit(NameQualifiedType node) {
		// System.out.println("NameQualifiedType:"+node);
		// do nothing.
		return false;
	}

	// raw string
	@Override
	public boolean visit(EnumDeclaration node) {
		// Do nothing now.
		// System.out.println("EnumDeclaration:"+node);
		AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(StringLiteral node) {
		// System.out.println("StringLiteral:"+node);
		String literal = node.toString().trim();
		AppendOtherCode(GCodeMetaInfo.StringCorpus, literal.substring(1, literal.length() - 1));
		return super.visit(node);
	}

	@Override
	public boolean visit(NumberLiteral node) {
		// System.out.println("NumberLiteral:"+node);
		AppendOtherCode(GCodeMetaInfo.NumberCorpus, node.toString());
		return super.visit(node);
	}

	@Override
	public boolean visit(CharacterLiteral node) {
		// System.out.println("CharacterLiteral:"+node);
		AppendOtherCode(GCodeMetaInfo.StringCorpus, node.charValue() + "");
		AppendOtherCode(GCodeMetaInfo.CharCorpus, node.charValue() + "");
		return super.visit(node);
	}

	// helper functions
	protected void JustBeforeAnonymousClassDeclaration() {
		ojfacc.AddPreDeclrations(mw);
	}

	protected void OneMethodInvocationOccurs(String rawmethodname) {
		mw.PushMethodName(rawmethodname);
	}

	protected boolean isFirstLevelASTNode(ASTNode node) {
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == FirstLevelClass) {
			return true;
		}
		return false;
	}

	protected void PushMethodNodeCodeToJavaFileCode() {
		ojfc.AddOneMethodNodeCode(omc);
	}

	protected void OneSentenceEnd() {
		ojfc.OneSentenceEnd();
	}

	protected void FlushCode() {
		if (!omc.IsEmpty()) {
			PushMethodNodeCodeToJavaFileCode();
			OneSentenceEnd();
		}
		omc = null;
	}

	protected void AppendOtherCode(String corpus, String code) {
		ocm.AppendOtherCode(corpus, code);
	}

	protected void AddFirstOrderTask(FirstOrderTask runtask) {
		fotp.InfixNodeAddFirstOrderTask(runtask);
	}

	protected void GenerateOneLine(String nodecode, boolean couldappend, boolean mustappend, boolean mustpre, boolean occupyoneline) {
		omc.AddOneLineCode(nodecode, couldappend, mustappend, mustpre, occupyoneline);
	}

	protected void AppendToLast(String ncode) {
		omc.AppendLast(ncode);
	}

	// If doesn't know the kind, just set one as random. The one must be the big
	// kind you want.
	protected String GetDataOffset(String data, boolean isFieldUseOrUpdate, boolean isCommonUseOrUpdate) {
		String code = sdm.GetDataOffsetInfo(data, isFieldUseOrUpdate, isCommonUseOrUpdate);
		return code;
	}

	protected void ClassNewlyAssigned(String type) {
		cjcs.PushNewlyAssignedData(type, GCodeMetaInfo.HackedNoType);
	}

	protected String GetClassOffset(String type) {
		Integer offset = cjcs.GetExactOffset(type, GCodeMetaInfo.HackedNoType);
		if (offset == null) {
			return null;
		}
		return "$K" + 0 + GCodeMetaInfo.OffsetSpiliter + OffsetLibrary.GetOffsetDescription(offset);
	}

	protected void LabelNewlyAssigned(String label) {
		ljcs.PushNewlyAssignedData(label, GCodeMetaInfo.HackedNoType);
	}

	protected String GetLabelOffset(String label) {
		Integer offset = ljcs.GetExactOffset(label, GCodeMetaInfo.HackedNoType);
		if (offset == null) {
			return null;
		}
		return "$L" + 0 + GCodeMetaInfo.OffsetSpiliter + OffsetLibrary.GetOffsetDescription(offset);
	}

	protected Integer GetReferenceUpdateHint(ASTNode node) {
		return voorm.GetReferenceUpdateHint(node);
	}

	protected void AddReferenceUpdateHint(ASTNode node, Integer hint) {
		voorm.AddReferenceUpdateHint(node, hint);
	}

	protected void DataNewlyUsed(String data, String type, boolean isfianl, boolean isFieldDeclare,
			boolean isCommonDeclare, boolean isFieldUseOrDeclare, boolean isCommonUseOrDeclare) {
		sdm.AddDataNewlyUsed(data, type, isfianl, isFieldDeclare, isCommonDeclare, isFieldUseOrDeclare,
				isCommonUseOrDeclare);
	}

	protected String GetVeryRecentDeclaredType() {
		return VeryRecentDeclaredType;
	}

	protected void CheckVeryRecentDeclaredTypeMustNotNull(String declaredtype) {
		if (declaredtype == null) {
			System.err.println("No Declared Type? The system will exit.");
			System.exit(1);
		}
	}

	protected void ClearClassAndLabelInfo() {
		cjcs.ClearAll();
		ljcs.ClearAll();
	}
	
	protected boolean NodeIsRefered(int nodehashcode)
	{
		Boolean isrefered = berefered.GetNodeHelp(nodehashcode);
		if (isrefered != null && isrefered == true)
		{
			return true;
		}
		return false;
	}
	
	protected void ExpressionReferPreHandle(Expression expr, int referenceHint)
	{
		int exprhashcode = expr.hashCode();
		referhint.AddNodeHelp(exprhashcode, referenceHint);
		berefered.AddNodeHelp(exprhashcode, true);
	}
	
	protected void ExpressionReferPostHandle(ASTNode node, Expression expr, String operator, String addedcnt, boolean exprisleft, boolean needaddsplitter)
	{
		int exprhashcode = expr.hashCode();
		
		String exprcode = referedcnt.GetNodeHelp(exprhashcode);
		if (exprcode == null)
		{
			exprcode = GCodeMetaInfo.PreExist;
		}
		String nodecode = "";
		String splitter = (needaddsplitter ? GCodeMetaInfo.CommonSplitter : "");
		operator = (operator == null || operator.equals("")) ? "" : (splitter + operator);
		addedcnt = (addedcnt == null || addedcnt.equals("")) ? "" : (splitter + addedcnt);
		if (exprisleft)
		{
			nodecode = exprcode + operator + addedcnt;
		}
		else
		{
			nodecode = addedcnt + operator + exprcode;
		}
		int nodehashcode = node.hashCode();
		if (NodeIsRefered(nodehashcode))
		{
			referedcnt.AddNodeHelp(nodehashcode, nodecode);
		}
		else
		{
			GenerateOneLine(nodecode, false, false, false, true);
		}
		
		referhint.DeleteNodeHelp(exprhashcode);
		berefered.DeleteNodeHelp(exprhashcode);
		referedcnt.DeleteNodeHelp(exprhashcode);
	}
	
	protected void SetVeryRecentDeclaredType(String veryRecentDeclaredType) {
		VeryRecentDeclaredType = veryRecentDeclaredType;
	}
	
	protected String GenerateVariableDeclarationTypeCode(Type type, List<ASTNode> dimens)
	{
		String dimenstr = "";
		if (dimens != null && dimens.size() > 0)
		{
			Iterator<ASTNode> itr = dimens.iterator();
			while (itr.hasNext())
			{
				dimenstr += itr.next().toString();
			}
		}
		return TypeCode(type, true) + dimenstr + GCodeMetaInfo.VariableDeclarationHint;
	}
	
	protected void CheckHint(Integer hint)
	{
		if (hint == null || hint == ReferenceHintLibrary.NoHint)
		{
			System.err.println("There is no hint in this node handle. The system will exit.");
			new Exception("No hint exception").printStackTrace();
			System.exit(1);
		}
	}
	
	protected void CheckCode(String code) {
		if (code == null)
		{
			System.err.println("There is no code in this node handle. The system will exit.");
			new Exception("No code exception").printStackTrace();
			System.exit(1);
		}
	}

}