package cn.yyx.research.language.JDTHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.CreationReference;
import org.eclipse.jdt.core.dom.Dimension;
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
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import cn.yyx.research.language.JDTManager.ClassLineManager;
import cn.yyx.research.language.JDTManager.DataLineManager;
import cn.yyx.research.language.JDTManager.EnteredBlockStack;
import cn.yyx.research.language.JDTManager.EnteredClassStack;
import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.LabelLineManager;
import cn.yyx.research.language.JDTManager.LineCodeManager;
import cn.yyx.research.language.JDTManager.LineManager;
import cn.yyx.research.language.JDTManager.NodeCodeManager;
import cn.yyx.research.language.JDTManager.NodeLineManager;
import cn.yyx.research.language.JDTManager.OffsetLibrary;
import cn.yyx.research.language.JDTManager.OperationType;

public class ForwardMethodASTVisitor extends ASTVisitor {

	EnteredBlockStack blockstack = new EnteredBlockStack();
	EnteredClassStack classstack = new EnteredClassStack();
	NodeLineManager nlm = new NodeLineManager();
	LineCodeManager lcm = new LineCodeManager();
	DataLineManager dlm = new DataLineManager(blockstack, classstack);
	NodeCodeManager ncm = new NodeCodeManager();
	LabelLineManager llm = new LabelLineManager();
	// FieldAccessNodeManager fanm = new FieldAccessNodeManager();
	ClassLineManager clm = new ClassLineManager();
	LineManager lm = new LineManager();
	
	FirstOrderTaskPool fotp = new FirstOrderTaskPool();

	Map<Integer, ASTNode> nodelink = new TreeMap<Integer, ASTNode>();
	
	//TODO method invokation not need to set parameters into the generated code.
	//TODO = + such operations need to change to first order.

	public ForwardMethodASTVisitor() {
	}
	
	@Override
	public boolean visit(CreationReference node) {
		System.out.println("CreationReference:" + node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(Dimension node) {
		System.out.println("Dimension:" + node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(IntersectionType node) {
		System.out.println("IntersectionType:" + node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(UnionType node) {
		System.out.println("UnionType:" + node);
		return super.visit(node);
	}
	
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
	public boolean visit(LambdaExpression node) {
		// TODO
		 System.out.println("LambdaExpressionBegin:");
		 System.out.println("LambdaExpression:"+node);
		 System.out.println("LambdaExpressionHasParenthese:"+node.hasParentheses());
		 System.out.println("LambdaExpressionParameters:"+node.parameters());
		 System.out.println("LambdaExpressionBody:"+node.getBody());
		 System.out.println("LambdaExpressionEnd.");
		return super.visit(node);
	}
	
	@Override
	public boolean visit(ExpressionMethodReference node) {
		// System.out.println("ExpressionMethodReference:"+node);
		// System.out.println("ExpressionMethodReferenceExpr:"+);
		// System.out.println("ExpressionMethodReferenceName:"+node.getName());
		fotp.InfixNodeAddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getName(), node, true) {
			@Override
			public void run() {
				int line = VisitLineOccupy(node);
				String code = OperationType.ExpressionMethodReference + "#" + this.getPost().toString();
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			}
		});
		return super.visit(node);
	}
	
	// CLASS offset is special, relate to code offset. Solved.
	// Only 'assignment' records the line position of one variable. Solved.

	// Initializers in for is null. Solved.
	// Array initializer is null and there are many other nulls. Solved.

	@Override
	public void endVisit(ArrayInitializer node) {
		UnchangedNode(node, GCodeMetaInfo.ArrayInitial);
	}
	
	@Override
	public boolean visit(ArrayCreation node) {
		// System.out.println("ArrayCreation:"+node);
		UnchangedNode(node, GCodeMetaInfo.ArrayInitial);
		return false;
	}
	
	@Override
	public boolean visit(TypeLiteral node) {
		System.out.println("TypeLiteral:" + node);
		// UnchangedNode(node);
		return super.visit(node);
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
		// blockstack.pop();
		// classstack.pop();
		ExitBlock(true);
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
		ExitBlock(true);
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		// Do nothing now.
		// System.out.println("TypeDeclarationStatement:"+node);
		return super.visit(node);
	}

	@Override
	public boolean visit(CastExpression node) {
		// System.out.println("CastExpression:"+node);
		// System.out.println("CastExpressionType:"+node.getType());
		// System.out.println("CastExpressionExpression:"+node.getExpression());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(CastExpression node) {
		int line = GetOccupiedLine(node);
		// ((ncm.GetAstNodeCode(node.getExpression()))
		String expr = GetRefCode(node.getExpression(), line);
		Integer typeline = clm.GetClassLineInfo(node.getType().toString());
		String typestr = null;
		if (typeline == null) {
			typestr = GCodeMetaInfo.OutofScopeDesc;
		} else {
			typestr = OffsetLibrary.GetOffsetDescription(typeline - line);
		}
		String code = OperationType.CastExpression + "#" + typestr + expr;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		clm.AddClassLineInfo(node.getType().toString(), line);
	}

	@Override
	public boolean visit(ArrayAccess node) {
		// System.out.println("ArrayAccessArray:"+node.getArray());
		// System.out.println("ArrayAccessIndex:"+node.getIndex());
		// System.out.println("ArrayAccess:"+node);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(ArrayAccess node) {
		/*
		 * String arrstr = node.getArray().toString(); boolean isfield = false;
		 * if (arrstr.startsWith("this.")) { isfield = true; arrstr =
		 * arrstr.substring("this.".length()); } int arrline =
		 * dlm.GetDataLineInfo(arrstr, isfield); (arrline - line) + "#"
		 */
		int line = GetOccupiedLine(node);
		String code = OperationType.ArrayAccess + "#" + GetRefCode(node.getArray(), line)
				+ GetRefCode(node.getIndex(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(Assignment node) {
		// System.out.println("AssignmentLeftHand:"+node.getLeftHandSide());
		// System.out.println("Assignment:"+node);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(Assignment node) {
		int line = GetOccupiedLine(node);
		String left = GetRefCode(node.getLeftHandSide(), line);
		String right = GetRefCode(node.getRightHandSide(), line);
		String code = OperationType.Assignment + "#" + node.getOperator() + "#" + left + right;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		String data = node.getLeftHandSide().toString();
		if (node.getLeftHandSide() != null && node.getLeftHandSide() instanceof ArrayAccess) {
			data = ((ArrayAccess) node.getLeftHandSide()).getArray().toString();
		}
		dlm.AddDataLineInfo(data, line, false, false);
	}

	@Override
	public boolean visit(BooleanLiteral node) {
		// System.out.println("BooleanLiteral:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node) {
		// System.out.println("BreakStatement:"+node);
		// System.out.println(node.getLabel());
		int line = lm.NewLine();
		String labelstr = "";
		if (node.getLabel() != null && !node.getLabel().equals("")) {
			int lline = llm.GetLabelLine(node.getLabel().toString());
			labelstr = OffsetLibrary.GetOffsetDescription(lline - line);
		}
		lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" + "break#" + labelstr);
		return super.visit(node);
	}

	@Override
	public boolean visit(ContinueStatement node) {
		int line = lm.NewLine();
		String labelstr = "";
		if (node.getLabel() != null && !node.getLabel().equals("")) {
			int lline = llm.GetLabelLine(node.getLabel().toString());
			labelstr = OffsetLibrary.GetOffsetDescription(lline - line);
		}
		lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" + "continue#" + labelstr);
		return super.visit(node);
	}

	@Override
	public boolean visit(CharacterLiteral node) {
		// System.out.println("CharacterLiteral:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		// System.out.println("ClassInstanceCreation:"+node);
		// System.out.println("ClassInstanceCreationType:"+node.getType());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(ClassInstanceCreation node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.ClassInstanceCreation + "#" + node.getType() + "#";
		List<Expression> args = node.arguments();
		for (Expression arg : args) {
			String argcode = GetRefCode(arg, line);
			code += argcode;
		}
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(ConditionalExpression node) {
		// System.out.println("ConditionalExpression:"+node);
		// System.out.println("ConditionalExpressionExpr:"+node.getExpression());
		// System.out.println("ConditionalExpressionThenExpr:"+node.getThenExpression());
		// System.out.println("ConditionalExpressionElseExpr:"+node.getElseExpression());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(ConditionalExpression node) {
		int line = GetOccupiedLine(node);
		String codeexpr = GetRefCode(node.getExpression(), line);
		String codethen = GetRefCode(node.getThenExpression(), line);
		String codeelse = GetRefCode(node.getElseExpression(), line);
		String code = OperationType.ConditionalExpression + "#" + codeexpr + codethen + codeelse;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		// Do nothing now.
		System.out.println("ConstructorInvocation:" + node);
		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node) {
		// System.out.println("Do statement begin:");
		// System.out.println("DoStatement:"+node);
		// System.out.println("Do statement end.");
		OneTextOneLine("do");
		return super.visit(node);
	}

	@Override
	public void endVisit(DoStatement node) {
		VisitLineOccupy(node);
		int line = GetOccupiedLine(node);
		String code = OperationType.DoStatement + "#" + GetRefCode(node.getExpression(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		// System.out.println("EnhancedForStatementParameter:"+node.getParameter());
		// System.out.println("EnhancedForStatementExpr:"+node.getExpression());
		// System.out.println("EnhancedForStatementBody:"+node.getBody());
		EnterBlock(node, false);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(EnhancedForStatement node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.EnhancedForStatement + "#" + GetRefCode(node.getParameter(), line)
				+ GetRefCode(node.getExpression(), line) + GetRefCode(node.getBody(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		dlm.ExitBlock(false);
	}

	@Override
	public boolean visit(ExpressionStatement node) {
		// This method is too high level, almost useless.
		// System.out.println("ExpressionStatement:"+node);
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldAccess node) {
		// System.out.println("FieldAccess:"+node);
		// System.out.println("FieldAccessName:"+node.getName());
		// System.out.println("FieldAccessExpr:"+node.getExpression());
		return super.visit(node);
	}

	@Override
	public void endVisit(FieldAccess node) {
		String preexpr = node.getExpression().toString();
		String preexprcode = "";
		String code = "";
		if (preexpr.equals("this")) {
			code = GCodeMetaInfo.IsFieldDesc;
			UnchangedNode(node, code);
			// fanm.AddFieldAccessNode(node);
		} else {
			if (nlm.GetAstNodeLineInfo(node.getExpression()) == null) {
				// EXPR doesn't create a new line.
				UnchangedNode(node);
			} else {
				int line = VisitLineOccupy(node);
				preexprcode = GetRefCode(node.getExpression(), line);
				if (!preexprcode.endsWith("#")) {
					preexprcode = preexprcode + "#";
				}
				code = OperationType.FieldDotSequence + "#" + preexprcode + node.getName() + "#";
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			}
		}
	}

	@Override
	public boolean visit(SuperFieldAccess node) {
		// System.out.println("SuperFieldAccess:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		// System.out.println("SuperMethodInvocation:"+node);
		// System.out.println("SuperMethodInvocationName:"+node.getName());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(SuperMethodInvocation node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.MethodInvocation + "#" + "super." + node.getName() + "#";
		List<Expression> args = node.arguments();
		for (Expression arg : args) {
			code += GetRefCode(arg, line);
		}
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(MethodInvocation node) {
		/*
		 * System.out.println("MethodInvocation:"+node);
		 * System.out.println("MethodInvocationName:"+node.getName()); String
		 * argscnt = ""; List<Expression> args = node.arguments(); for
		 * (Expression arg : args) { argscnt += arg + "#"; }
		 * System.out.println("MethodInvocationArguments:"+argscnt);
		 * System.out.println("MethodInvocationExpr:"+node.getExpression());
		 */
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(MethodInvocation node) {
		int line = GetOccupiedLine(node);
		String exprcode = (node.getExpression() == null ? "" : GetRefCode(node.getExpression(), line));
		String code = OperationType.MethodInvocation + "#" + exprcode + node.getName() + "#";
		List<Expression> args = node.arguments();
		for (Expression arg : args) {
			code += GetRefCode(arg, line);
		}
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(ForStatement node) {
		/*
		 * System.out.println("ForStatement:"+node); String inilizers = "";
		 * List<Expression> inis = node.initializers(); for (Expression ini :
		 * inis) { inilizers += (String) (ini + ";"); } String updaters = "";
		 * List<Expression> ups = node.updaters(); for (Expression up : ups) {
		 * updaters += up + ";"; }
		 * System.out.println("ForStatementInitializer:"+inilizers);
		 * System.out.println("ForStatementJudge:"+node.getExpression());
		 * System.out.println("ForStatementUpdater:"+updaters);
		 */
		EnterBlock(node, false);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(ForStatement node) {
		int line = GetOccupiedLine(node);
		String inicode = GCodeMetaInfo.NoStatement;
		List<Expression> inis = node.initializers();
		if (inis != null && inis.size() > 0) {
			inicode = GetRefCode(inis.get(0), line);
		}
		String judgecode = GCodeMetaInfo.NoStatement;
		Expression judge = node.getExpression();
		if (judge != null) {
			judgecode = GetRefCode(judge, line);
		}
		String updatecode = GCodeMetaInfo.NoStatement;
		List<Expression> ups = node.updaters();
		if (ups != null && ups.size() > 0) {
			updatecode = GetRefCode(ups.get(0), line);
		}
		String bodycode = GetRefCode(node.getBody(), line);
		String code = OperationType.ForStatement + "#" + inicode + judgecode + updatecode + bodycode;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		ExitBlock(false);
	}

	@Override
	public boolean visit(IfStatement node) {
		// System.out.println("IfStatement:"+node);
		// System.out.println("IfStatementExpr:"+node.getExpression());
		// System.out.println("IfStatementThen:"+node.getThenStatement());
		// System.out.println("IfStatementElse:"+node.getThenStatement());
		EnterBlock(node, false);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(IfStatement node) {
		// blockstack.pop();
		int line = GetOccupiedLine(node);
		String thencode = node.getThenStatement() == null ? GCodeMetaInfo.NoStatement
				: GetRefCode(node.getThenStatement(), line);
		String elsecode = node.getElseStatement() == null ? GCodeMetaInfo.NoStatement
				: GetRefCode(node.getElseStatement(), line);
		String code = OperationType.IfStatement + "#" + GetRefCode(node.getExpression(), line) + thencode + elsecode;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		ExitBlock(false);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(InfixExpression node) {
		VisitLineOccupy(node);
		List<Expression> ops = node.extendedOperands();
		for (int i = 0; i < ops.size(); i++) {
			lm.NewLine();
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(InfixExpression node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.InfixExpression + "#" + node.getOperator().toString() + "#"
				+ GetRefCode(node.getLeftOperand(), line) + GetRefCode(node.getRightOperand(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		List<Expression> ops = node.extendedOperands();
		int opline = line;
		for (Expression op : ops) {
			opline++;
			String opcode = OperationType.InfixExpression + "#" + node.getOperator().toString() + "#"
					+ OffsetLibrary.GetOffsetDescription(line - opline) + GetRefCode(op, opline);
			lcm.AddLineCode(opline, opcode);
			// code += GetRefCode(op, line);
		}
		/*
		 * System.out.println("=========传说中的分割线==开始==========");
		 * System.out.println("InfixExpression:"+node);
		 * System.out.println(node.getOperator());
		 * System.out.println(node.getLeftOperand());
		 * System.out.println(node.getRightOperand());
		 * System.out.println("=========传说中的分割线==中间==========");
		 * List<Expression> opso = node.extendedOperands(); for (Expression op :
		 * opso) { System.out.println(op); }
		 * System.out.println("=========传说中的分割线==结束==========");
		 */
	}

	@Override
	public boolean visit(Initializer node) {
		// Do nothing now.
		// System.out.println("Initializer:"+node);
		ResetDLM();
		return super.visit(node);
	}

	@Override
	public void endVisit(Initializer node) {
		OneSentenceEnd();
		dlm.ClearRawStringDataLineInfo();
	}

	@Override
	public boolean visit(InstanceofExpression node) {
		// System.out.println("InstanceofExpression:"+node);
		// System.out.println("InstanceofExpressionLeft:"+node.getLeftOperand());
		// System.out.println("InstanceofExpressionRight:"+node.getRightOperand());
		return super.visit(node);
	}

	@Override
	public void endVisit(InstanceofExpression node) {
		int line = VisitLineOccupy(node);
		String itype = "";
		String type = node.getRightOperand().toString();
		Integer idxline = clm.GetClassLineInfo(type);
		if (idxline == null) {
			itype = OffsetLibrary.GetOffsetDescription(1);
			int newiline = OneTextOneLine(type);
			clm.AddClassLineInfo(type, newiline);
		} else {
			itype = OffsetLibrary.GetOffsetDescription(idxline - line);
			clm.AddClassLineInfo(type, line);
		}
		String code = OperationType.InstanceofExpression + "#" + GetRefCode(node.getLeftOperand(), line) + itype;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(LabeledStatement node) {
		// System.out.println("LabeledStatement:"+node);
		int line = OneTextOneLine("LAB" + "#" + node.getLabel() + "#");
		llm.AddLabelLine(node.getLabel().toString(), line);
		return super.visit(node);
	}

	@Override
	public boolean visit(NullLiteral node) {
		// System.out.println("NullLiteral:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(NumberLiteral node) {
		// System.out.println("NumberLiteral:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(ParenthesizedExpression node) {
		// System.out.println("ParenthesizedExpression:"+node);
		// System.out.println("ParenthesizedExpression:"+node.getExpression());
		GiveLinkBetweenNodes(node, node.getExpression());
	}

	@Override
	public boolean visit(PostfixExpression node) {
		/*
		 * System.out.println("PostfixExpression:"+node);
		 * System.out.println("PostfixExpressionOperator:"+node.getOperator());
		 * System.out.println("PostfixExpressionOperand:"+node.getOperand());
		 */
		return super.visit(node);
	}

	@Override
	public void endVisit(PostfixExpression node) {
		int line = VisitLineOccupy(node);
		String code = OperationType.PostfixExpression + "#" + node.getOperator() + "#"
				+ GetRefCode(node.getOperand(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		if (node.getOperator().toString().length() > 1) {
			dlm.AddDataLineInfo(node.getOperand().toString(), line, false, false);
		}
	}

	@Override
	public boolean visit(PrefixExpression node) {
		/*
		 * System.out.println("PrefixExpression:"+node);
		 * System.out.println("PrefixExpressionOperator:"+node.getOperator());
		 * System.out.println("PrefixExpressionOperand:"+node.getOperand());
		 */
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(PrefixExpression node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.PrefixExpression + "#" + node.getOperator().toString() + "#"
				+ GetRefCode(node.getOperand(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		if (node.getOperator().toString().length() > 1) {
			dlm.AddDataLineInfo(node.getOperand().toString(), line, false, false);
		}
	}

	@Override
	public boolean visit(QualifiedName node) {
		// System.out.println("QualifiedName:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(ReturnStatement node) {
		// System.out.println("ReturnStatement:"+node);
		// System.out.println("ReturnStatementExpr:"+node.getExpression());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(ReturnStatement node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.ReturnStatement + "#" + GetRefCode(node.getExpression(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(SimpleName node) {
		// System.out.println("SimpleName:" + node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(PrimitiveType node) {
		// System.out.println("PrimitiveType:" + node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SimpleType node) {
		// System.out.println("SimpleType:" + node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedType node) {
		// System.out.println("QualifiedType:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(StringLiteral node) {
		// System.out.println("StringLiteral:"+node);
		UnchangedNode(node, GCodeMetaInfo.StringHolder);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		// System.out.println("SuperConstructorInvocation:"+node);
		// System.out.println("SuperConstructorInvocationExpr:"+node.getExpression());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(SuperConstructorInvocation node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.SuperConstructorInvocation + "#";
		List<Expression> args = node.arguments();
		for (Expression arg : args) {
			code += GetRefCode(arg, line);
		}
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(SwitchStatement node) {
		// System.out.println("SwitchStatement:"+node);
		// System.out.println("SwitchStatementExpr:"+node.getExpression());
		// blockstack.push(node.hashCode());
		EnterBlock(node, false);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(SwitchStatement node) {
		// blockstack.pop();
		int line = GetOccupiedLine(node);
		String code = OperationType.SwitchStatement + "#" + GetRefCode(node.getExpression(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		ExitBlock(false);
	}

	@Override
	public boolean visit(SwitchCase node) {
		// System.out.println("SwitchCase:"+node);
		// System.out.println("SwitchCaseExpr:"+node.getExpression());
		if (node.getExpression() != null) {
			VisitLineOccupy(node);
		}
		return super.visit(node);
	}

	@Override
	public void endVisit(SwitchCase node) {
		if (node.getExpression() == null) {
			OneTextOneLine("default");
		} else {
			int line = GetOccupiedLine(node);
			String code = OperationType.SwitchCase + "#" + GetRefCode(node.getExpression(), line);
			EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		}
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		// System.out.println("SynchronizedStatement:"+node);
		// System.out.println("SynchronizedStatementExpr:"+node.getExpression());
		// System.out.println("SynchronizedStatementBlock:"+node.getBody());
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(SynchronizedStatement node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.SynchronizedStatement + "#" + GetRefCode(node.getExpression(), line)
				+ GetRefCode(node.getBody(), line);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
	}

	@Override
	public boolean visit(ThisExpression node) {
		// System.out.println("ThisExpression:"+node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(ThrowStatement node) {
		// System.out.println("ThrowStatement:"+node);
		OneTextOneLine(OperationType.ThrowException + "#");
		return super.visit(node);
	}

	@Override
	public boolean visit(TryStatement node) {
		// System.out.println("TryStatement:"+node);
		// System.out.println("TryStatementBody:"+node.getBody());
		OneTextOneLine("try");
		return super.visit(node);
	}

	@Override
	public boolean visit(CatchClause node) {
		// System.out.println("CatchClause:"+node);
		int line = VisitLineOccupy(node);
		String code = OperationType.CatchClause + "#" + OffsetLibrary.GetAdjacentRefCode(true);
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		return super.visit(node);
	}

	@Override
	public boolean visit(Block node) {
		// Do nothing now.
		// System.out.println("Block:"+node);
		// blockstack.push(node.hashCode());
		EnterBlock(node, false);
		int line = VisitLineOccupy(node);
		String code = OperationType.BlockCommand + "#" + "ENTER#";
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		return super.visit(node);
	}

	@Override
	public void endVisit(Block node) {
		// blockstack.pop();
		// System.out.println("node:"+node);
		// System.out.println("node id:"+node.hashCode());
		OneTextOneLine(OperationType.BlockCommand + "#", "EXIT#");
		ExitBlock(false);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		// System.out.println("SingleVariableDeclaration:"+node);
		// System.out.println("SingleVariableDeclarationType:"+node.getType());
		int line = VisitLineOccupy(node);
		dlm.AddDataLineInfo(node.getName().toString(), line, false, true);
		String code = OperationType.SingleVariableDeclaration + "#" + node.getType();
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(FieldDeclaration node) {
		// System.out.println("FieldDeclaration:"+node);
		// System.out.println("FieldDeclarationParentHashcode:"+node.getParent().hashCode());
		List<VariableDeclarationFragment> fs = node.fragments();
		for (VariableDeclarationFragment f : fs) {
			// System.out.println("FieldDeclarationFragmentName:"+f.getName());
			dlm.AddDataLineInfo(f.getName().toString(), GCodeMetaInfo.IsField, true, false);
		}
		return false;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		// System.out.println("MethodDeclarationParent:"+node.getParent().hashCode());
		ResetDLM();
		OneTextOneLine(node.getName().toString());
		EnterBlock(node, false);
		return super.visit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		if (isFirstLevelMethod(node)) {
			OneSentenceEnd();
			dlm.ClearRawStringDataLineInfo();
		}
		ExitBlock(false);
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		// Do nothing now.
		// System.out.println("EnumDeclaration:"+node);
		OneTextOneLine(node.getName().toString());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(EnumConstantDeclaration node) {
		OneTextOneLine(node.getName().toString());
		return super.visit(node);
	}
	
	@Override
	public void endVisit(EnumDeclaration node) {
		OneTextOneLine(".");
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationExpression node) {
		// System.out.println("VariableDeclarationExpression:" + node);
		String type = node.getType().toString();
		List<VariableDeclarationFragment> fs = node.fragments();
		boolean first = true;
		int nodeline = -1;
		for (VariableDeclarationFragment vdf : fs) {
			// System.out.println(vdf.getInitializer());
			int line = VisitLineOccupy(vdf);
			if (first) {
				nodeline = line;
			}
			String code = OperationType.VariableDeclarationStatement + "#" + type + "#";
			clm.AddClassLineInfo(type, line);
			EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			if (vdf.getInitializer() != null) {
				lm.NewLine();
			}
			dlm.AddDataLineInfo(vdf.getName().toString(), line, false, true);
			first = false;
		}
		if (nodeline > -1) {
			nlm.AddASTNodeLineInfo(node, nodeline);
		}
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(VariableDeclarationExpression node) {
		List<VariableDeclarationFragment> fs = node.fragments();
		for (VariableDeclarationFragment vdf : fs) {
			if (vdf.getInitializer() != null) {
				int templine = GetOccupiedLine(vdf);
				int line = templine + 1;
				String left = OffsetLibrary.GetAdjacentRefCode(false);
				String right = GetRefCode(vdf.getInitializer(), line);
				String code = OperationType.Assignment + "#" + "=" + "#" + left + right;
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
				dlm.AddDataLineInfo(vdf.getName().toString(), line, false, false);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationStatement node) {
		// System.out.println("VariableDeclarationStatement:"+node);
		// System.out.println("VariableDeclarationStatementType:"+node.getType());
		String type = node.getType().toString();
		List<VariableDeclarationFragment> fs = node.fragments();
		boolean first = true;
		int nodeline = -1;
		for (VariableDeclarationFragment vdf : fs) {
			// System.out.println(vdf.getInitializer());
			int line = VisitLineOccupy(vdf);
			if (first) {
				nodeline = line;
			}
			String code = OperationType.VariableDeclarationStatement + "#" + type + "#";
			clm.AddClassLineInfo(type, line);
			EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			if (vdf.getInitializer() != null) {
				lm.NewLine();
			}
			dlm.AddDataLineInfo(vdf.getName().toString(), line, false, true);
			first = false;
		}
		if (nodeline > -1) {
			nlm.AddASTNodeLineInfo(node, nodeline);
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(VariableDeclarationStatement node) {
		List<VariableDeclarationFragment> fs = node.fragments();
		for (VariableDeclarationFragment vdf : fs) {
			if (vdf.getInitializer() != null) {
				int templine = GetOccupiedLine(vdf);
				int line = templine + 1;
				String left = OffsetLibrary.GetAdjacentRefCode(false);
				String right = GetRefCode(vdf.getInitializer(), line);
				String code = OperationType.Assignment + "#" + "=" + "#" + left + right;
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
				dlm.AddDataLineInfo(vdf.getName().toString(), line, false, false);
			}
		}
	}

	@Override
	public boolean visit(WhileStatement node) {
		// System.out.println("WhileStatement:"+node);
		// System.out.println("WhileStatementExpr:"+node.getExpression());
		// System.out.println("WhileStatementBody:"+node.getBody());
		EnterBlock(node, false);
		VisitLineOccupy(node);
		return super.visit(node);
	}

	@Override
	public void endVisit(WhileStatement node) {
		int line = GetOccupiedLine(node);
		String exprcode = GetRefCode(node.getExpression(), line);
		String bodycode = GetRefCode(node.getBody(), line);
		String code = OperationType.WhileStatement + "#" + exprcode + bodycode;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		ExitBlock(false);
	}

	private String GetRefCode(ASTNode node, int currline) {
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

	private int OneTextOneLine(String nodestr) {
		int line = lm.NewLine();
		lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" + nodestr);
		return line;
	}

	private int OneTextOneLine(String commandtype, String nodestr) {
		int line = lm.NewLine();
		lcm.AddLineCode(line, commandtype + nodestr);
		return line;
	}

	private void UnchangedNode(ASTNode node) {
		ncm.AddASTNodeCode(node, node.toString());
	}

	private void UnchangedNode(ASTNode node, String nodestr) {
		ncm.AddASTNodeCode(node, nodestr);
	}

	private int VisitLineOccupy(ASTNode node) {
		int line = lm.NewLine();
		nlm.AddASTNodeLineInfo(node, line);
		return line;
	}

	private Integer GetOccupiedLine(ASTNode node) {
		return nlm.GetAstNodeLineInfo(node);
	}

	private void EndVisitReplaceLineOccupyWithRealContent(int line, ASTNode node, String code) {
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

	/*
	 * private int OneTextNodeOneLine(ASTNode node) { int line = lm.NewLine();
	 * nlm.AddASTNodeLineInfo(node, line); ncm.AddASTNodeCode(node,
	 * OperationType.NearlyCommonText + "#" + node.toString());
	 * lcm.AddLineCode(line, OperationType.NearlyCommonText + "#" +
	 * node.toString()); return line; }
	 */

	private void ResetDLM() {
		dlm.ResetCurrentClassField();
	}

	private void OneSentenceEnd() {
		OneTextOneLine(".");
	}

	private boolean isFirstLevelMethod(MethodDeclaration node) {
		int classhashcode = classstack.getClassId(0);
		int parenthashcode = node.getParent().hashCode();
		if (parenthashcode == classhashcode) {
			return true;
		}
		return false;
	}

	private void EnterBlock(ASTNode node, boolean isclass) {
		// System.out.println("Hashcode:"+node.hashCode()+";node:"+node);
		dlm.EnterBlock(node.hashCode(), isclass);
	}

	private void ExitBlock(boolean isclass) {
		dlm.ExitBlock(isclass);
	}

	private void GiveLinkBetweenNodes(ASTNode linkingnode, ASTNode nodetobelinked) {
		nodelink.put(linkingnode.hashCode(), nodetobelinked);
	}

}