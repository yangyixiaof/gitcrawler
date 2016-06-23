package cn.yyx.contentassist.codeutils;

import java.util.LinkedList;
import java.util.List;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.data.CSLeftParenInfoProperty;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineStack;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;
import cn.yyx.contentassist.commonutils.StringUtil;

public class leftParentheseStatement extends statement{
	
	int times = 0;
	
	public leftParentheseStatement(String smtcode, int count) {
		super(smtcode);
		this.times = count;
	}

	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof leftParentheseStatement)
		{
			return true;
		}
		return false;
	}

	@Override
	public double Similarity(OneCode t) {
		if (t instanceof leftParentheseStatement)
		{
			return 1;
		}
		return 0;
	}

	/*@Override
	public boolean HandleOverSignal(Stack<Integer> cstack) {
		cstack.add(ComplicatedSignal.GenerateComplicatedSignal(StructureSignalMetaInfo.ParentheseBlock, times));
		return false;
	}

	@Override
	public boolean HandleCodeSynthesis(CodeSynthesisQueue squeue, Stack<TypeCheck> expected, SynthesisHandler handler,
			CSNode result, AdditionalInfo ai) {
		CSLeftParenInfoData cpin = new CSLeftParenInfoData(times);
		squeue.add(cpin);
		return false;
	}*/

	@Override
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		List<FlowLineNode<CSFlowLineData>> result = new LinkedList<FlowLineNode<CSFlowLineData>>();
		result.add(new FlowLineNode<CSFlowLineData>(new CSFlowLineData(squeue.GenerateNewNodeId() + "", smthandler.getSete(), StringUtil.GenerateDuplicates("(", times), null, null, squeue.GetLastHandler(), new CSLeftParenInfoProperty(times)), smthandler.getProb()));
		return result;
	}

	@Override
	public boolean HandleOverSignal(FlowLineStack cstack) throws CodeSynthesisException {
		return false;
	}
	
}