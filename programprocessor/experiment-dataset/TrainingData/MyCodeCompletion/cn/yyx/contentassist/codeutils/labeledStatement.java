package cn.yyx.contentassist.codeutils;

import java.util.List;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineHelper;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineStack;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;

public class labeledStatement extends statement{
	
	identifier id = null;
	
	public labeledStatement(String smtcode, identifier name) {
		super(smtcode);
		this.id = name;
	}
	
	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof labeledStatement)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public double Similarity(OneCode t) {
		if (t instanceof labeledStatement)
		{
			return 1;
		}
		return 0;
	}
	
	/*@Override
	public boolean HandleCodeSynthesis(CodeSynthesisQueue squeue, Stack<TypeCheck> expected, SynthesisHandler handler,
			CSNode result, AdditionalInfo ai) {
		CSNode cs = new CSNode(CSNodeType.HalfFullExpression);
		boolean conflict = id.HandleCodeSynthesis(squeue, expected, handler, cs, ai);
		cs.setPostfix(":");
		squeue.add(cs);
		return conflict;
	}*/

	@Override
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		List<FlowLineNode<CSFlowLineData>> idls = id.HandleCodeSynthesis(squeue, smthandler);
		return CSFlowLineHelper.ConcateOneFlowLineList(null, idls, ":");
	}

	@Override
	public boolean HandleOverSignal(FlowLineStack cstack) throws CodeSynthesisException {
		return false;
	}
	
}