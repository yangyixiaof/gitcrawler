package cn.yyx.research.language.JDTHelper;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.NodeTypeLibrary;
import cn.yyx.research.language.JDTManager.OperationType;

public class ForwardMethodCodeGenerateASTVisitor extends MyCodeGenerateASTVisitor {

	public ForwardMethodCodeGenerateASTVisitor(MyPreProcessASTVisitor mppast) {
		super(mppast);
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
		// OneTextOneLine(node.getName().toString() + "#" +
		// OperationType.EnumConstant + "/0b");
		return super.visit(node);
	}

	@Override
	public boolean visit(StringLiteral node) {
		// System.out.println("StringLiteral:"+node);
		String literal = node.toString().trim();
		AppendOtherCode(GCodeMetaInfo.StringCorpus, literal.substring(1, literal.length()-1));
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
		AppendOtherCode(GCodeMetaInfo.StringCorpus, node.toString());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(ExpressionStatement node) {
		// do nothing.
		return super.visit(node);
	}
	
	@Override
	public boolean visit(SuperFieldAccess node) {
		// do nothing.
		return super.visit(node);
	}

	@Override
	public boolean visit(LambdaExpression node) {
		if (ShouldExecute(node)) {

			RegistFirstNodeAfterDecreasingElement(node.getBody());
			RegistLastNodeBeforeIncreaseingElement(node.getBody());

			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		// System.out.println("MethodDeclarationParent:"+node.getParent().hashCode());
		boolean wcontinue = super.visit(node);
		TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		return wcontinue;
	}

	@Override
	public void endVisit(ExpressionMethodReference node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
	}

	@Override
	public boolean visit(CastExpression node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(Assignment node) {
		if (ShouldExecute(node)) {
			ASTNode expr = node.getLeftHandSide();
			if (GetNodeHasOccupiedOneLine(expr))
			{
				AddFirstOrderTask(new FirstOrderTask(expr, node.getRightHandSide(), node, false) {
					@Override
					public void run() {
						TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
					}
				});
			}
			else
			{
				TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
			}
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ContinueStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ClassInstanceCreation node) {
		// System.out.println("Node Type:"+node.getType());
		// System.out.println("Body:"+node.getAnonymousClassDeclaration());
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ConditionalExpression node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		// Do nothing now.
		// System.out.println("ConstructorInvocation:" + node);
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(DoStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine("do", OperationType.DoRawCode, NodeTypeLibrary.newstart, GetNodeLevel(node), false);
			RegistFirstNodeAfterDecreasingElement(node.getExpression());
			RegistLastNodeBeforeIncreaseingElement(node.getExpression());
			RegistFirstNodeAfterDecreasingElement(node.getBody());
			RegistLastNodeBeforeIncreaseingElement(node.getBody());
			
			AddFirstOrderTask(new FirstOrderTask(node.getBody(), node.getExpression(), node, false) {
				@Override
				public void run() {
					TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
				}
			});
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}
	
	@Override
	public boolean visit(ArrayAccess node) {
		// do nothing.
		// TODO
		return super.visit(node);
	};

	@Override
	public boolean visit(FieldAccess node) {
		// do nothing.
		if (ShouldExecute(node))
		{
			ASTNode expr = node.getExpression();
			if (GetNodeInMultipleLine(expr))
			{
				AddFirstOrderTask(new FirstOrderTask(expr, node.getName(), node, false) {
					@Override
					public void run() {
						TrulyGenerateOneLine(node, GetNodeLevel(node), false);
					}
				});
			}
			else
			{
				TrulyGenerateOneLine(node, GetNodeLevel(node), false);
			}
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(SuperMethodInvocation node) {
		if (ShouldExecute(node)) {
			List<ASTNode> args = node.arguments();
			if (args.size() > 0) {
				RegistFirstNodeAfterDecreasingElement(args.get(0));
				RegistLastNodeBeforeIncreaseingElement(args.get(args.size() - 1));
			}
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(MethodInvocation node) {
		if (ShouldExecute(node)) {
			List<ASTNode> args = node.arguments();
			ASTNode postnode = null;
			boolean afterprerun = false;
			if (args.size() > 0) {
				postnode = args.get(0);
				RegistFirstNodeAfterDecreasingElement(args.get(0));
				RegistLastNodeBeforeIncreaseingElement(args.get(args.size() - 1));
			}
			else
			{
				afterprerun = true;
			}
			ASTNode expr = node.getExpression();
			if (expr != null)
			{
				AddFirstOrderTask(new FirstOrderTask(expr, postnode, node, afterprerun) {
					@Override
					public void run() {
						TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
					}
				});
			}
			else
			{
				TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
			}
		}
		return super.visit(node);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(ForStatement node) {
		if (ShouldExecute(node)) {
			ASTNode first = null;
			ASTNode last = null;
			List<ASTNode> inis = node.initializers();
			ASTNode judge = node.getExpression();
			List<ASTNode> ups = node.updaters();
			if (inis.size() > 0) {
				first = inis.get(0);
			} else {
				if (judge != null) {
					first = judge;
				} else {
					if (ups.size() > 0) {
						first = ups.get(0);
					}
				}
			}

			if (ups.size() > 0) {
				last = ups.get(ups.size() - 1);
			} else {
				if (judge != null) {
					last = judge;
				} else {
					if (inis.size() > 0) {
						last = inis.get(inis.size() - 1);
					}
				}
			}

			if (first != null && last != null) {
				RegistFirstNodeAfterDecreasingElement(first);
				RegistLastNodeBeforeIncreaseingElement(last);
			}

			RegistFirstNodeAfterDecreasingElement(node.getBody());
			RegistLastNodeBeforeIncreaseingElement(node.getBody());

			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationExpression node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(IfStatement node) {
		if (ShouldExecute(node)) {
			RegistFirstNodeAfterDecreasingElement(node.getExpression());
			RegistLastNodeBeforeIncreaseingElement(node.getExpression());

			if (node.getThenStatement() != null) {
				RegistFirstNodeAfterDecreasingElement(node.getThenStatement());
				RegistLastNodeBeforeIncreaseingElement(node.getThenStatement());
			}

			if (node.getElseStatement() != null) {
				RegistFirstNodeAfterDecreasingElement(node.getElseStatement());
				RegistLastNodeBeforeIncreaseingElement(node.getElseStatement());
			}

			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(InfixExpression node) {
		int nodelevel = GetNodeLevel(node);
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, nodelevel, GetNodeHasContentHolder(node));
		}
		ASTNode pre = node.getRightOperand();
		String operatorcode = node.getOperator().toString();
		List<ASTNode> extendops = node.extendedOperands();
		for (ASTNode op : extendops) {
			AddFirstOrderTask(new FirstOrderTask(pre, op, node, false) {
				@Override
				public void run() {
					boolean hasContentHolder = false;
					String postcode = GetNodeCode(getPost());
					if (GetNodeHasOccupiedOneLine(getPost()))
					{
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
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(LabeledStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(PostfixExpression node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(PrefixExpression node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ReturnStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean visit(SuperConstructorInvocation node) {
		if (ShouldExecute(node)) {
			List<ASTNode> args = node.arguments();
			if (args.size() > 0) {
				RegistFirstNodeAfterDecreasingElement(args.get(0));
				RegistLastNodeBeforeIncreaseingElement(args.get(args.size() - 1));
			}
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(ThrowStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(CatchClause node) {
		if (ShouldExecute(node)) {
			RegistFirstNodeAfterDecreasingElement(node.getBody());
			RegistLastNodeBeforeIncreaseingElement(node.getBody());

			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node) {
		if (ShouldExecute(node)) {
			TrulyGenerateOneLine(node, GetNodeLevel(node), GetNodeHasContentHolder(node));
		}
		return super.visit(node);
	}

}