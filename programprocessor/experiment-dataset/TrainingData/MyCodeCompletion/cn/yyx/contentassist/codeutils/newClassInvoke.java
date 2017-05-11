package cn.yyx.contentassist.codeutils;

import java.util.List;
import java.util.Map;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.CodeSynthesisHelper;
import cn.yyx.contentassist.codesynthesis.ErrorCheck;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;
import cn.yyx.contentassist.codesynthesis.typeutil.MethodTypeSignature;

public class newClassInvoke extends firstArg {
	
	firstArgReferedExpression rexp = null; // warning: rexp could be null.
	
	public newClassInvoke(firstArgReferedExpression rexp) {
		this.rexp = rexp;
	}

	/*@Override
	public boolean HandleCodeSynthesis(CodeSynthesisQueue squeue, Stack<TypeCheck> expected, SynthesisHandler handler,
			CSNode result, AdditionalInfo ai) {
		String mcode = ai.getMethodName();
		CSNode recs = new CSNode(CSNodeType.HalfFullExpression);
		if (rexp != null)
		{
			boolean conflict = rexp.HandleCodeSynthesis(squeue, expected, handler, recs, ai);
			if (conflict)
			{
				return true;
			}
		}
		CSNode spec = new CSNode(CSNodeType.HalfFullExpression);
		boolean conflict = CodeSynthesisHelper.HandleMethodSpecificationInfer(squeue, expected, handler, spec, ai, "new "+ mcode);
		if (conflict)
		{
			return true;
		}
		result.SetCSNodeContent(CSNodeHelper.ConcatTwoNodes(recs, spec, ".", -1));
		// result.AddOneData(mcode, null);
		return false;
	}*/

	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof newClassInvoke)
		{
			return true;
		}
		return false;
	}

	@Override
	public double Similarity(OneCode t) {
		if (t instanceof newClassInvoke)
		{
			return 0.3+0.7*(rexp.Similarity(((newClassInvoke) t).rexp));
		}
		return 0;
	}

	@Override
	@Deprecated
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		ErrorCheck.NoGenerationCheck("commonClassMemberInvoke should not invoke HandleCodeSynthesis, but HandleClassOrMethodInvoke instead.");
		// return CodeSynthesisHelper.HandleClassInvokeCodeSynthesis(squeue, smthandler, rexp, "new ");
		return null;
	}

	@Override
	public List<FlowLineNode<CSFlowLineData>> HandleClassOrMethodInvoke(CSFlowLineQueue squeue,
			CSStatementHandler smthandler, String methodname, Map<String, MethodTypeSignature> mts)
			throws CodeSynthesisException {
		return CodeSynthesisHelper.HandleClassInvokeCodeSynthesis(squeue, smthandler, rexp, "new ", methodname, mts);
	}
	
}