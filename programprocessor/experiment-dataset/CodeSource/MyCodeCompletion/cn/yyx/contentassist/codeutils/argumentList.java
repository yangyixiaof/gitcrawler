package cn.yyx.contentassist.codeutils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.yyx.contentassist.codepredict.CodeSynthesisException;
import cn.yyx.contentassist.codesynthesis.CSFlowLineBackTraceGenerationHelper;
import cn.yyx.contentassist.codesynthesis.CSFlowLineQueue;
import cn.yyx.contentassist.codesynthesis.ErrorCheck;
import cn.yyx.contentassist.codesynthesis.data.CSDataMetaInfo;
import cn.yyx.contentassist.codesynthesis.data.CSFlowLineData;
import cn.yyx.contentassist.codesynthesis.data.CSMethodInvocationProperty;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSMethodStatementFirstArgHandler;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSMethodStatementHandler;
import cn.yyx.contentassist.codesynthesis.statementhandler.CSStatementHandler;
import cn.yyx.contentassist.codesynthesis.typeutil.CCType;
import cn.yyx.contentassist.codesynthesis.typeutil.MethodTypeSignature;
import cn.yyx.contentassist.codesynthesis.typeutil.TypeCheckHelper;
import cn.yyx.contentassist.commonutils.CheckUtil;
import cn.yyx.contentassist.commonutils.ListDynamicHeper;
import cn.yyx.contentassist.commonutils.RefAndModifiedMember;
import cn.yyx.contentassist.commonutils.SimilarityHelper;
import cn.yyx.contentassist.specification.SpecificationHelper;

public class argumentList implements OneCode {

	private List<referedExpression> el = new LinkedList<referedExpression>();
	private firstArg fa = null; 

	public argumentList() {
	}

	public void AddToFirst(referedExpression re) {
		getEl().add(0, re);
	}

	public void AddReferedExpression(referedExpression re) {
		getEl().add(re);
	}

	public List<referedExpression> getEl() {
		return el;
	}

	public void setEl(List<referedExpression> el) {
		this.el = el;
	}

	@Override
	public boolean CouldThoughtSame(OneCode t) {
		if (t instanceof argumentList) {
			int size = el.size();
			int tsize = ((argumentList) t).el.size();
			int maxsize = Math.max(size, tsize);
			if (maxsize <= 2) {
				return true;
			}
			int minsize = Math.min(size, tsize);
			if (Math.abs(size - tsize) > minsize) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double Similarity(OneCode t) {
		if (t instanceof argumentList) {
			int size = el.size();
			int tsize = ((argumentList) t).el.size();
			if (Math.abs(size - tsize) <= 1) {
				return 1;
			} else {
				return SimilarityHelper.ComputeTwoIntegerSimilarity(size, tsize);
			}
		}
		return 0;
	}

	/*
	 * @Override public boolean HandleCodeSynthesis(CodeSynthesisQueue squeue,
	 * Stack<TypeCheck> expected, SynthesisHandler handler, CSNode result,
	 * AdditionalInfo ai) { // must be in reverse order. expected.add(null);
	 * boolean conflict = false; referedExpression invokerhint = el.get(0);
	 * CSNode invcn = new CSNode(CSNodeType.TempUsed); conflict =
	 * invokerhint.HandleCodeSynthesis(squeue, expected, handler, invcn, ai);
	 * List<CSNode> paramsnode = new LinkedList<CSNode>();
	 * Iterator<referedExpression> itr = el.iterator(); itr.next(); while
	 * (itr.hasNext()) { referedExpression re = itr.next(); CSNode oparam = new
	 * CSNode(CSNodeType.TempUsed); conflict = re.HandleCodeSynthesis(squeue,
	 * expected, handler, oparam, ai); if (conflict) { return true; }
	 * paramsnode.add(oparam); } Map<String, TypeCheck> resdatas = new
	 * TreeMap<String, TypeCheck>(); Map<String, TypeCheck> datas =
	 * invcn.getDatas(); Set<String> precodes = datas.keySet(); Iterator<String>
	 * pcitr = precodes.iterator(); while (pcitr.hasNext()) { String pc =
	 * pcitr.next(); StringBuilder sb = new StringBuilder(pc); sb.append("(");
	 * TypeCheck retandparamstypes = datas.get(pc); if (retandparamstypes ==
	 * null) { // directly add param. Iterator<CSNode> pitr =
	 * paramsnode.iterator(); while (pitr.hasNext()) { CSNode pcn = pitr.next();
	 * sb.append(pcn.GetFirstDataWithoutTypeCheck()); if (pitr.hasNext()) {
	 * sb.append(","); } } } else { List<Boolean> usedparams =
	 * ArrayUtil.InitialBooleanArray(paramsnode.size()); List<Class<?>> tps =
	 * retandparamstypes.getExpargstypesclasses(); Iterator<Class<?>> tpitr =
	 * tps.iterator(); while (tpitr.hasNext()) { Class<?> c = tpitr.next();
	 * String ct = HandleOneClassParamNodes(c, paramsnode, usedparams);
	 * sb.append(ct); if (tpitr.hasNext()) { sb.append(","); } } }
	 * sb.append(")"); resdatas.put(sb.toString(), retandparamstypes); }
	 * result.setDatas(resdatas); expected.pop(); return false; }
	 */
	
	/*private String HandleOneClassParamNodes(LinkedList<CCType> c, List<List<FlowLineNode<CSFlowLineData>>> paramsnode,
			List<Boolean> usedparams) {
		Iterator<List<FlowLineNode<CSFlowLineData>>> pitr = paramsnode.iterator();
		int usedidx = 0;
		String unusedorlatestused = null;
		while (pitr.hasNext()) {
			List<FlowLineNode<CSFlowLineData>> pcn = pitr.next();
			String select = null;
			Iterator<FlowLineNode<CSFlowLineData>> codeitr = pcn.iterator();
			while (codeitr.hasNext()) {
				FlowLineNode<CSFlowLineData> code = codeitr.next();
				
				// debugging code, do not remove.
				if (code == null || code.getData() == null)
				{
					new Exception().printStackTrace();
				}
				
				CCType rtclass = code.getData().getDcls();
				if (TypeCheckHelper.CanBeMutualCast(c, rtclass)) {
					select = code.getData().getData();
					break;
				}
			}
			if (select == null) {
				select = pcn.get(0).getData().getData();
			}
			if (!usedparams.get(usedidx)) {
				usedparams.set(usedidx, true);
				return select;
			} else {
				unusedorlatestused = select;
			}
			usedidx++;
		}
		return unusedorlatestused;
	}*/

	public List<FlowLineNode<CSFlowLineData>> HandleMethodIntegrationCodeSynthesis(CSFlowLineQueue squeue,
			CSStatementHandler smthandler, String methodname, boolean hasem) throws CodeSynthesisException {
		CheckUtil.CheckStatementHandlerIsMethodStatementHandler(smthandler);
		CSMethodStatementHandler realhandler = (CSMethodStatementHandler) smthandler;
		// realhandler.setArgsize(el.size() - 1);
		// change to reverse order list.
		List<referedExpression> reverseel = new ListDynamicHeper<referedExpression>().ReverseList(el);
		List<List<FlowLineNode<CSFlowLineData>>> positiveargs = new LinkedList<List<FlowLineNode<CSFlowLineData>>>();
		Iterator<referedExpression> ritr = reverseel.iterator();
		Map<String, MethodTypeSignature> mts = new TreeMap<String, MethodTypeSignature>();
		while (ritr.hasNext()) {
			referedExpression re = ritr.next();
			// List<FlowLineNode<CSFlowLineData>> oneargpospossibles = ;
			if (!ritr.hasNext()) {
				List<FlowLineNode<CSFlowLineData>> rels = re.HandleCodeSynthesis(squeue, smthandler);
				if (rels == null || rels.size() == 0)
				{
					return null;
				}
				positiveargs.add(rels);
			}
		}
		// handle invoker.
		List<FlowLineNode<CSFlowLineData>> results = new LinkedList<FlowLineNode<CSFlowLineData>>();
		List<FlowLineNode<CSFlowLineData>> invokers = fa.HandleClassOrMethodInvoke(squeue, new CSMethodStatementFirstArgHandler(realhandler), methodname, mts);
		Iterator<FlowLineNode<CSFlowLineData>> itr = invokers.iterator();
		while (itr.hasNext()) {
			FlowLineNode<CSFlowLineData> fln = itr.next();
			CSFlowLineData data = fln.getData();
			MethodTypeSignature msig = mts.get(data.getId());
			StringBuilder sb = new StringBuilder(data.getData());
			if (msig == null) {
				String sepc = data.getData();
				Set<String> specs = new TreeSet<String>();
				specs.add(sepc);
				RefAndModifiedMember ramm = SpecificationHelper.GetMostLikelyRef(
						squeue.GetLastHandler().getContextHandler(), specs, methodname, true, ".");
				if (ramm != null) {
					msig = MethodTypeSignature.GenerateMethodTypeSignature(ramm.getMaxMm(),
							squeue, smthandler);
				}
				if (msig == null) {
					// directly add argument.
					Iterator<List<FlowLineNode<CSFlowLineData>>> pitr = positiveargs.iterator();
					sb.append("(");
					while (pitr.hasNext()) {
						List<FlowLineNode<CSFlowLineData>> pcnls = pitr.next();
						sb.append(pcnls.get(0).getData().getData());
						if (pitr.hasNext()) {
							sb.append(",");
						}
					}
					sb.append(")");
				}
			}
			// msig != null.
			// check and add argument.
			// List<Boolean> usedparams = ListHelper.InitialBooleanArray(positiveargs.size());
			List<LinkedList<CCType>> tps = msig.getArgtypes();
			if (tps.size() != positiveargs.size())
			{
				continue;
			}
			Iterator<LinkedList<CCType>> tpitr = tps.iterator();
			Iterator<List<FlowLineNode<CSFlowLineData>>> pitr = positiveargs.iterator();
			sb.append("(");
			try {
				while (tpitr.hasNext()) {
					LinkedList<CCType> c = tpitr.next();
					List<FlowLineNode<CSFlowLineData>> parg = pitr.next();
					// String ct = HandleOneClassParamNodes(c, positiveargs, usedparams);
					String ct = HandleOneParam(c, parg);
					sb.append(ct);
					if (tpitr.hasNext()) {
						sb.append(",");
					}
				}
			} catch (CodeSynthesisException e) {
				continue;
			}
			sb.append(")");
			data.setData(sb.toString());
			results.add(new FlowLineNode<CSFlowLineData>(data, fln.getProbability()));
			
			// Stack<Integer> signals = new Stack<Integer>();
			// signals.add(DataStructureSignalMetaInfo.MethodInvocation);
			FlowLineNode<CSFlowLineData> mf = realhandler.getMostfar();
			// realhandler.getMostfar();
			if (mf != null) {
				String id = CSFlowLineBackTraceGenerationHelper.GetConcateId(squeue.getLast(), mf) + "." + data.getId();
				mf.getData().getSynthesisCodeManager().AddSynthesisCode(id, fln);
				data.getExtraData().AddExtraData(CSDataMetaInfo.LastNode, data);
				data.getSynthesisCodeManager().setBlockstart(mf, id);
				data.setCsep(new CSMethodInvocationProperty(hasem));
				data.setTck(null);
			}
		}
		return results;
	}
	
	public String HandleOneParam(LinkedList<CCType> c, List<FlowLineNode<CSFlowLineData>> parg) throws CodeSynthesisException
	{
		Iterator<FlowLineNode<CSFlowLineData>> pitr = parg.iterator();
		while (pitr.hasNext())
		{
			FlowLineNode<CSFlowLineData> fln = pitr.next();
			CSFlowLineData flndata = fln.getData();
			if (TypeCheckHelper.CanBeMutualCast(c, flndata.getDcls()))
			{
				return flndata.getData();
			}
		}
		throw new CodeSynthesisException("Argument and param type are not equal.");
	}

	@Override
	@Deprecated
	public List<FlowLineNode<CSFlowLineData>> HandleCodeSynthesis(CSFlowLineQueue squeue, CSStatementHandler smthandler)
			throws CodeSynthesisException {
		ErrorCheck.NoGenerationCheck(
				"argumentList should not invoke HandleCodeSynthesis, should invoke HandleMethodIntegrationCodeSynthesis instead.");
		return null;
	}

	public firstArg getFirstArgument() {
		return fa;
	}

	public void setFirstArgument(firstArg fa) {
		this.fa = fa;
	}

}