package cn.yyx.research.language.JDTHelper;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
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

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.OffsetLibrary;
import cn.yyx.research.language.JDTManager.OperationType;
import cn.yyx.research.language.JDTManager.VVarObjPoolManager;

public class ForwardMethodPreProcessASTVisitor extends MyPreProcessASTVisitor {
	
	// TODO (later half part not solved.) method invocation not need to set parameters into the generated code but parameters need to have some prefix to indicate this is a parameter of a method.
	// TODO not only method invocation, switch while if for such all need prefix. judgment, update, no-prefix block, and others all need to be prefixed.
	// TODO = + such operations need to change to first order.
	// TODO out of scope variables are thought as First declared.
	// TODO offset still the assignment but method invoke of an object is taken as assignment.
	// TODO record only assignments in a pool. reference offset is the offset to the index of assignment in the poll at reverse order.
	// TODO first used element is also in this pool and taken as first declared or first assignment.
	// TODO In pool, scope is taken into consideration including field. offset must be as this kind: -1/-2#. first part means -1 level, second part means -2 offset in assignment.
	// TODO the offset must record which kind of sub pool it is being offset. sub pool include: final(constant), class(hidden, generated by me), var or obj, or others?.
	// TODO AddEquivalentScope(node, node.get);
	
	// For one visit, there are two tasks: 
	// task 1. raw code. generated raw node code which is stored in NodeCodeManager.
	// task 2. operation & line type. generate codes which have OperationType and line type but not have line offset info.
	
	// For one visit, there are two hidden tasks:
	// 1. set node level and its child's node level indication.
	// 2. for code not put in this line, set HoleManager.
	
	// one line code could offer only one content holder.
	// type a is set, when in first order, the already handled has occupied a line.
	// type c(hole) is set, when the node has occupied a line.
	
	public ForwardMethodPreProcessASTVisitor() {
	}
	
	@Override
	public boolean visit(CreationReference node) {
		System.out.println("CreationReference:" + node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(Dimension node) {
		// []
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
	public boolean visit(TypeLiteral node) {
		 System.out.println("TypeLiteral:" + node);
		// UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		// Do not know what it is now.
		 System.out.println("TypeDeclarationStatement:"+node);
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(LambdaExpression node) {
		// System.out.println("LambdaExpressionBegin:");
		// System.out.println("LambdaExpression:"+node);
		// System.out.println("LambdaExpressionHasParenthese:"+node.hasParentheses());
		// System.out.println("LambdaExpressionParameters:"+node.parameters());
		// System.out.println("LambdaExpressionBody:"+node.getBody());
		// System.out.println("LambdaExpressionEnd.");
		EnterBlock(node, false);
		// EnterLambdaParam(node);
		// OneTextOneLine(OperationType.LambdaExpression + "#", (node.hasParentheses() ? 1 : 0) + "#");
		/*AddFirstOrderTask(new FirstOrderTask(lastdec, node.getBody(), node, false) {
			@Override
			public void run() {
				// ExitLambdaParam(node);
				int line = VisitLineOccupy(node);
				String code = OperationType.LambdaExpression + "#";
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			}
		});*/
		return super.visit(node);
	}
	
	@Override
	public void endVisit(LambdaExpression node) {
		String pre = "";
		String post = "";
		if (node.hasParentheses())
		{
			pre = "(";
			post = ")";
		}
		StringBuilder cnt = new StringBuilder("");
		List<ASTNode> params = node.parameters();
		if (params!=null && params.size() > 0)
		{
			Iterator<ASTNode> itr = params.iterator();
			while (itr.hasNext())
			{
				ASTNode para = itr.next();
				String str = para.toString();
				String[] decs = str.split(" ");
				if (decs.length == 1)
				{
					cnt.append(GCodeMetaInfo.NoDeclaredType).append(",");
					DataNewlyUsed(decs[0], VVarObjPoolManager.VarOrObjPool, false, true);
				}
				else if (decs.length == 2)
				{
					cnt.append(decs[0]).append(",");
					DataNewlyUsed(decs[1], VVarObjPoolManager.VarOrObjPool, false, true);
				}
				else
				{
					System.err.println("What Lambda Param? Serious error: over two modules in a param. The program will exit.");
					System.exit(1);
				}
			}
			cnt.deleteCharAt(cnt.length()-1);
		}
		String code = pre + cnt.toString() + post + "->";
		code = PushBackContentHolder(code, node);
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
		
		ExitBlock();
	}
	
	@Override
	public boolean visit(ExpressionMethodReference node) {
		// System.out.println("ExpressionMethodReference:"+node);
		// System.out.println("ExpressionMethodReferenceExpr:"+);
		// System.out.println("ExpressionMethodReferenceName:"+node.getName());
		/*AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getName(), node, true) {
			@Override
			public void run() {
				int line = VisitLineOccupy(node);
				String exprcode = LineOccupied(node.getExpression()) ? "" : GetRefCode(node.getExpression(), line);
				String code = OperationType.ExpressionMethodReference + "#" + this.getPost().toString() + "#" + exprcode;
				EndVisitReplaceLineOccupyWithRealContent(line, node, code);
			}
		});*/
		AddNodeCode(node, "::"+node.getName());
		AddNodeHasOccupiedOneLine(node, true);
		return super.visit(node);
	}
	
	// CLASS offset is special, relate to code offset. Solved.
	// Only 'assignment' records the line position of one variable. Solved.

	// Initializers in for is null. Solved.
	// Array initializer is null and there are many other nulls. Solved.

	@Override
	public void endVisit(ArrayInitializer node) {
		// System.out.println("ArrayInitializer:"+node);
		AddNodeCode(node, GCodeMetaInfo.ArrayInitial);
	}
	
	@Override
	public void endVisit(ArrayCreation node) {
		// System.out.println("ArrayCreation:"+node);
		AddNodeCode(node, GCodeMetaInfo.ArrayCreation);
	}
	
	@Override
	public void endVisit(CastExpression node) {
		String code = "(" + node.getType().toString() + ")";
		code = PushBackContentHolder(code, node);
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
		
		/*int line = GetOccupiedLine(node);
		String expr = GetRefCode(node.getExpression(), line);
		Integer typeline = clm.GetClassLineInfo();
		String typestr = null;
		if (typeline == null) {
			typestr = GCodeMetaInfo.OutofScopeDesc;
		} else {
			typestr = OffsetLibrary.GetOffsetDescription(typeline - line);
		}
		String code = OperationType.CastExpression + "#" + typestr + expr;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		clm.AddClassLineInfo(node.getType().toString(), line);*/
	}
	
	@Override
	public void endVisit(ArrayAccess node) {
		/*
		 * String arrstr = node.getArray().toString(); boolean isfield = false;
		 * if (arrstr.startsWith("this.")) { isfield = true; arrstr =
		 * arrstr.substring("this.".length()); } int arrline =
		 * dlm.GetDataLineInfo(arrstr, isfield); (arrline - line) + "#"
		 */
		String code = GetNodeCode(node.getArray());
		ASTNode idx = node.getIndex();
		if (GetNodeHasOccupiedOneLine(idx))
		{
			code+="[" + GCodeMetaInfo.CodeHole + "]";
		}
		else
		{
			code+="[" + GetNodeCode(idx) + "]";
		}
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, false);
	}
	
	@Override
	public void endVisit(Assignment node) {
		/*int line = GetOccupiedLine(node);
		String left = GetRefCode(node.getLeftHandSide(), line);
		String right = GetRefCode(node.getRightHandSide(), line);
		String code = OperationType.Assignment + "#" + node.getOperator() + "#" + left + right;
		EndVisitReplaceLineOccupyWithRealContent(line, node, code);
		String data = node.getLeftHandSide().toString();
		if (node.getLeftHandSide() != null && node.getLeftHandSide() instanceof ArrayAccess) {
			data = ((ArrayAccess) node.getLeftHandSide()).getArray().toString();
		}
		dlm.AddDataLineInfo(data, line, false, false);*/
		String code = "";
		ASTNode left = node.getLeftHandSide();
		if (!GetNodeHasOccupiedOneLine(left))
		{
			code = GetNodeCode(left)+node.getOperator().toString();
		}
		else
		{
			code = node.getOperator().toString();
		}
		code = PushBackContentHolder(code, node);
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
	}

	@Override
	public void endVisit(BooleanLiteral node) {
		// System.out.println("BooleanLiteral:"+node);
		AddNodeCode(node, node.toString());
	}

	@Override
	public void endVisit(BreakStatement node) {
		// System.out.println("BreakStatement:"+node);
		// System.out.println(node.getLabel());
		ASTNode label = node.getLabel();
		String code = "break" + label == null ? "" : GCodeMetaInfo.WhiteSpaceReplacer+GetNodeCode(label);
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
	}

	@Override
	public void endVisit(ContinueStatement node) {
		ASTNode label = node.getLabel();
		String code = "continue" + label == null ? "" : GCodeMetaInfo.WhiteSpaceReplacer+GetNodeCode(label);
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void endVisit(ClassInstanceCreation node) {
		// System.out.println("Node Type:"+node.getType());
		// System.out.println("Body:"+node.getAnonymousClassDeclaration());
		// System.out.println("ClassInstanceCreation:"+node);
		
		String code = "new" + GCodeMetaInfo.WhiteSpaceReplacer + node.getType();
		String pre = "(";
		String post = ")";
		code += pre;
		List<Expression> args = node.arguments();
		for (Expression arg : args) {
			if (GetNodeHasOccupiedOneLine(arg))
			{
				code += GCodeMetaInfo.CodeHole;
			}
			else
			{
				code += GetNodeCode(arg);
			}
			code += ",";
		}
		if (args.size() > 0)
		{
			code.substring(0, code.length()-1);
		}
		code += post;
		AddNodeCode(node, code);
		AddNodeHasOccupiedOneLine(node, true);
		
		int line = GetOccupiedLine(node);
		String code = OperationType.ClassInstanceCreation + "#" + node.getType() + "#";
		
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
	public void endVisit(SuperMethodInvocation node) {
		int line = GetOccupiedLine(node);
		String code = OperationType.MethodInvocation + "#" + "super." + node.getName() + "#";
		/*List<Expression> args = node.arguments();
		for (Expression arg : args) {
			code += GetRefCode(arg, line);
		}*/
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
	public void endVisit(MethodInvocation node) {
		int line = GetOccupiedLine(node);
		String exprcode = (node.getExpression() == null ? "" : GetRefCode(node.getExpression(), line));
		String code = OperationType.MethodInvocation + "#" + exprcode + node.getName() + "#";
		/*List<Expression> args = node.arguments();
		for (Expression arg : args) {
			code += GetRefCode(arg, line);
		}*/
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
		if (isFirstLevelASTNode(node))
		{
			BeginningLevelSet(node);
		}
		return super.visit(node);
	}

	@Override
	public void endVisit(Initializer node) {
		FlushCode();
		// dlm.ClearRawStringDataLineInfo();
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
	public boolean visit(ParenthesizedExpression node) {
		DecreaseLevel();
		return super.visit(node);
	}
	
	@Override
	public void endVisit(ParenthesizedExpression node) {
		// System.out.println("ParenthesizedExpression:"+node);
		// System.out.println("ParenthesizedExpression:"+node.getExpression());
		// GiveLinkBetweenNodes(node, node.getExpression());
		IncreaseLevel();
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
	public boolean visit(PrimitiveType node) {
		// TODO
		// System.out.println("PrimitiveType:" + node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SimpleType node) {
		// TODO
		// System.out.println("SimpleType:" + node);
		UnchangedNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(QualifiedType node) {
		// TODO
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
	public boolean visit(NumberLiteral node) {
		// System.out.println("NumberLiteral:"+node);
		UnchangedNode(node, GCodeMetaInfo.NumberHolder);
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
	public boolean visit(SimpleName node) {
		// System.out.println("SimpleName:" + node);
		UnchangedNode(node);
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
	@SuppressWarnings("unchecked")
	public boolean visit(MethodDeclaration node) {
		// System.out.println("MethodDeclarationParent:"+node.getParent().hashCode());
		ResetDLM();
		List<ASTNode> types = node.typeParameters();
		Iterator<ASTNode> itr = types.iterator();
		StringBuilder sb = new StringBuilder("");
		while (itr.hasNext())
		{
			String type = itr.next().toString();
			sb.append("#" + type);
		}
		OneTextOneLine(node.getName().toString() + sb.toString() + "#" + OperationType.MethodDeclaration + "/b0");
		if (isFirstLevelASTNode(node)) {
			BeginningLevelSet(node);
		}
		EnterBlock(node, false);
		return super.visit(node);
	}
	
	@Override
	public void endVisit(MethodDeclaration node) {
		if (isFirstLevelASTNode(node)) {
			FlushCode();
			// dlm.ClearRawStringDataLineInfo();
		}
		ExitBlock();
	}
	
	@Override
	public boolean visit(EnumDeclaration node) {
		// Do nothing now.
		// System.out.println("EnumDeclaration:"+node);
		AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
		// OneTextOneLine(node.getName().toString());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(EnumConstantDeclaration node) {
		AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
		// OneTextOneLine(node.getName().toString() + "#" + OperationType.EnumConstant + "/0b");
		return super.visit(node);
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
		OneTextOneLine(nodestr, level, hasContentHolder);
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
	
}