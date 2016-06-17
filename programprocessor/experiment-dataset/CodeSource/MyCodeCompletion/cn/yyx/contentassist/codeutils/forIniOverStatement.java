package cn.yyx.contentassist.codeutils;

import java.util.Iterator;
import java.util.List;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineHelper;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.data.CSForIniOverProperty;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;

public class forIniOverStatement extends rawForIniOverStatement implements SWrapper{
	
	statement smt = null;
	
	public forIniOverStatement(statement smt, String smtcode) {
		super(smtcode);
		this.smt = smt;
	}
	
	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof forIniOverStatement)
		{
			return smt.CouldThoughtSame(((forIniOverStatement)t).smt);
		}
		if (t instanceof rawForIniOverStatement)
		{
			return true;
		}
		if (t instanceof statement)
		{
			return smt.CouldThoughtSame(t);
		}
		return false;
	}

	@Override
	public double Similarity(OneCode t) {
		if (t instanceof forIniOverStatement)
		{
			return smt.Similarity(((forIniOverStatement)t).smt);
		}
		if (t instanceof rawForIniOverStatement)
		{
			return 0.5;
		}
		if (t instanceof statement)
		{
			return smt.Similarity(t);
		}
		return 0;
	}
	
	@Override
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		// List<FlowLineNode<CSFlowLineData>> result = new LinkedList<FlowLineNode<CSFlowLineData>>();
		List<FlowLineNode<CSFlowLineData>> smtls = smt.HandleCodeSynthesis(squeue, smthandler);
		smtls = CSFlowLineHelper.ConcateOneFlowLineList(null, smtls, ";");
		if (smtls == null || smtls.size() == 0)
		{
			return null;
		}
		Iterator<FlowLineNode<CSFlowLineData>> itr = smtls.iterator();
		while (itr.hasNext())
		{
			FlowLineNode<CSFlowLineData> smtln = itr.next();
			CSFlowLineData smtdata = smtln.getData();
			smtdata.setCsep(CSForIniOverProperty.GetInstance());
		}
		return smtls;
	}

	@Override
	public statement GetContent() {
		return smt;
	}
	
}