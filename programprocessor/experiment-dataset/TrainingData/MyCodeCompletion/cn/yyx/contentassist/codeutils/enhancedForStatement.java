package cn.yyx.contentassist.codeutils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineHelper;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.CodeSynthesisHelper;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineStack;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSInnerLevelPreHandler;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;
import cn.yyx.contentassist.codesynthesis.typeutil.CCType;
import cn.yyx.contentassist.commonutils.StringUtil;

public class enhancedForStatement extends statement {

	type tp = null;
	referedExpression rexp = null;

	public enhancedForStatement(String smtcode, type rt, referedExpression rexp) {
		super(smtcode);
		this.tp = rt;
		this.rexp = rexp;
	}

	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof enhancedForStatement) {
			return true;
		}
		return false;
	}

	@Override
	public double Similarity(OneCode t) {
		if (t instanceof enhancedForStatement) {
			return 0.4 + 0.6 * (0.6 * tp.Similarity(((enhancedForStatement) t).tp)
					+ 0.4 * (rexp.Similarity(((enhancedForStatement) t).rexp)));
		}
		return 0;
	}
	
	@Override
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		List<FlowLineNode<CSFlowLineData>> tpls = tp.HandleCodeSynthesis(squeue, smthandler);
		CSInnerLevelPreHandler csilp = new CSInnerLevelPreHandler("enhanced-for", smthandler);
		List<FlowLineNode<CSFlowLineData>> rels = rexp.HandleCodeSynthesis(squeue, csilp);
		List<FlowLineNode<CSFlowLineData>> result = new LinkedList<FlowLineNode<CSFlowLineData>>();
		if (rels != null && rels.size() > 0) {
			Iterator<FlowLineNode<CSFlowLineData>> itr = rels.iterator();
			while (itr.hasNext()) {
				FlowLineNode<CSFlowLineData> fln = itr.next();
				CCType cls = fln.getData().getDcls();
				if (cls != null) {
					String handledclass = null;
					if (cls.HasProperty(Collection.class)) {
						handledclass = StringUtil.ExtractParameterizedFromRawType(cls.getClstr());
					}
					if (cls.getCls().isArray()) {
						handledclass = cls.getCls().getComponentType().toString();
						if (handledclass.startsWith("class "))
						{
							handledclass = handledclass.substring("class ".length());
						}
					}
					if (handledclass != null)
					{
						result.add(new FlowLineNode<CSFlowLineData>(new CSFlowLineData(squeue.GenerateNewNodeId(), smthandler.getSete(), "for (" + handledclass + " et:" + fln.getData().getData() + ") " + CodeSynthesisHelper.GenerateBlockCode(smthandler), null, null, squeue.GetLastHandler()), smthandler.getProb()));
					}
				}
			}
		} else {
			return CSFlowLineHelper.ForwardConcate("for (", tpls, " et: ", rels, ") " + CodeSynthesisHelper.GenerateBlockCode(smthandler), squeue, smthandler, null);
		}
		return result;
		// return CSFlowLineHelper.ConcateOneFlowLineList("for (" + handledclass + " et:", rels, "){\n\n}");
	}

	@Override
	public boolean HandleOverSignal(FlowLineStack cstack) throws CodeSynthesisException {
		cstack.EnsureAllSignalNull();
		return true;
	}

}