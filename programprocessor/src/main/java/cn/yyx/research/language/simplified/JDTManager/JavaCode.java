package cn.yyx.research.language.simplified.JDTManager;

import java.util.Iterator;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.NodeCode;

public interface JavaCode {
	public void AddOneMethodNodeCode(NodeCode nc);
	public void OneSentenceEnd();
	public boolean IsEmpty();
	public default void AddOneNodeCode(NodeCode nc, StringBuilder sb)
	{
		Iterator<String> itr = nc.GetCodeIterator();
		while (itr.hasNext())
		{
			String onesentence = itr.next();
			CheckAllHavePrefixHint(onesentence);
			sb.append(" " + onesentence);
		}
	}
	public default void CheckAllHavePrefixHint(String onesentence)
	{
		int atidx = onesentence.indexOf('@');
		if (atidx < 0)
		{
			System.err.println("There is no @ in str, What is the problem?");
			System.exit(1);
		}
		String prefixhint = onesentence.substring(0, atidx);
		switch (prefixhint)
		{
			case GCodeMetaInfo.AnonymousClassHintStatement:
			case GCodeMetaInfo.ClassDeclarationHint:
			case GCodeMetaInfo.ClassInnerDeclarationHint:
			case GCodeMetaInfo.EnumDeclarationHint:
			case GCodeMetaInfo.MethodDeclarationHint:
			case GCodeMetaInfo.EnumConstantDeclarationHint:
			case GCodeMetaInfo.LabelDeclarationHint:
			case GCodeMetaInfo.VariableDeclarationHint:
			case GCodeMetaInfo.LambdaExpressionHint:
			case GCodeMetaInfo.MethodReferenceHint:
			case GCodeMetaInfo.CastExpressionHint:
			case GCodeMetaInfo.AssignmentHint:
			case GCodeMetaInfo.BreakHint:
			case GCodeMetaInfo.ContinueHint:
			case GCodeMetaInfo.DoWhileHint:
			case GCodeMetaInfo.InfixExpressionHint:
			case GCodeMetaInfo.InstanceofExpressionHint:
			case GCodeMetaInfo.PostfixExpressionHint:
			case GCodeMetaInfo.PrefixExpressionHint:
			case GCodeMetaInfo.ReturnHint:
			case GCodeMetaInfo.SwitchHint:
			case GCodeMetaInfo.CaseHint:
			case GCodeMetaInfo.DefaultHint:
			case GCodeMetaInfo.SynchronizedHint:
			case GCodeMetaInfo.ThrowStatementHint:
			case GCodeMetaInfo.CatchHint:
			case GCodeMetaInfo.WhileStatementHint:
			case GCodeMetaInfo.IfStatementHint:
			case GCodeMetaInfo.MethodInvocationHint:
			case GCodeMetaInfo.ArrayCreationHint:
			case GCodeMetaInfo.LiteralHint:
			case GCodeMetaInfo.NameHint:
			case GCodeMetaInfo.QualifiedNameHint:
			case GCodeMetaInfo.FieldAccessHint:
			case GCodeMetaInfo.QualifiedHint:
			case GCodeMetaInfo.Initializer:
			case GCodeMetaInfo.DescriptionHint:
			case GCodeMetaInfo.VariableDeclarationHolder:
				break;
			default:
				System.err.println("Unrecognized code prefix hint, What is the problem?");
				System.exit(1);
				break;
		}
	}
}