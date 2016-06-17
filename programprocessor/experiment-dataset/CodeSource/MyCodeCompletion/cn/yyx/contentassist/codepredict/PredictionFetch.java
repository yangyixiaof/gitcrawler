package cn.yyx.contentassist.codepredict;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;

import cn.yyx.contentassist.aerospikehandle.AeroLifeCycle;
import cn.yyx.contentassist.aerospikehandle.PredictProbPair;
import cn.yyx.contentassist.codecompletion.AeroMetaData;
import cn.yyx.contentassist.codecompletion.PredictMetaInfo;
import cn.yyx.contentassist.codepredict.aerohandle.PredictInfer;
import cn.yyx.contentassist.codesynthesis.flowline.CodeSynthesisFlowLines;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineHelper;
import cn.yyx.contentassist.codesynthesis.flowline.FlowLineNode;
import cn.yyx.contentassist.codesynthesis.flowline.PreTryFlowLineNode;
import cn.yyx.contentassist.codesynthesis.flowline.PreTryFlowLines;
import cn.yyx.contentassist.codeutils.methodInvocationStatement;
import cn.yyx.contentassist.codeutils.statement;
import cn.yyx.contentassist.commonutils.ASTOffsetInfo;
import cn.yyx.contentassist.commonutils.ClassInstanceOfUtil;
import cn.yyx.contentassist.commonutils.ContextHandler;
import cn.yyx.contentassist.commonutils.ProbabilityComputer;
import cn.yyx.contentassist.commonutils.SimilarityHelper;
import cn.yyx.contentassist.commonutils.StatementsMIs;
import cn.yyx.contentassist.commonutils.SynthesisHandler;
import cn.yyx.research.language.simplified.JDTManager.ScopeOffsetRefHandler;

public class PredictionFetch {
	
	public List<String> FetchPrediction(JavaContentAssistInvocationContext javacontext, ScopeOffsetRefHandler handler, List<String> analist, ArrayList<String> result, ASTOffsetInfo aoi)
	{
		AeroLifeCycle alc = AeroLifeCycle.GetInstance();
		//alc.Initialize();
		
		int size = analist.size();
		if (size > PredictMetaInfo.PrePredictWindow) {
			analist = analist.subList(size - PredictMetaInfo.PrePredictWindow, size);
			size = PredictMetaInfo.PrePredictWindow;
		}
		
		LinkedList<Sentence> setelist = SentenceHelper.TranslateStringsToSentences(analist);
		final Class<?> lastkind = setelist.getLast().getSmt().getClass();
		StatementsMIs smtmis = SentenceHelper.TranslateSentencesToStatements(setelist);
		List<statement> smtlist = smtmis.getSmts();
		List<statement> smilist = smtmis.getSmis();
		PreTryFlowLines<Sentence> fls = new PreTryFlowLines<Sentence>();
		DoPreTrySequencePredict(alc, fls, setelist, smtlist, smilist, PredictMetaInfo.PreTryNeedSize, lastkind);
		
		ContextHandler ch = new ContextHandler(javacontext);
		SynthesisHandler sh = new SynthesisHandler(handler, ch);
		// List<CodeSynthesisFlowLines> csfll = DoRealCodePredictAndSynthesis(sh, alc, fls, aoi);
		List<CodeSynthesisFlowLines> csfll = DoRealCodePredictAndSynthesisInSerial(sh, alc, fls, aoi);
		
		// alc.Destroy();
		// alc = null;
		
		List<String> list = new LinkedList<String>();
		Iterator<CodeSynthesisFlowLines> itr = csfll.iterator();
		while (itr.hasNext())
		{
			CodeSynthesisFlowLines csfl = itr.next();
			list.addAll(0, csfl.GetSynthesisOverCode());
		}
		return list;
	}
	
	protected List<CodeSynthesisFlowLines> DoRealCodePredictAndSynthesis(SynthesisHandler sh, AeroLifeCycle alc, PreTryFlowLines<Sentence> fls, ASTOffsetInfo aoi) {
		List<CodeSynthesisFlowLines> csfll = new LinkedList<CodeSynthesisFlowLines>();
		List<CodeSynthesisPredictTask> csptl = new LinkedList<CodeSynthesisPredictTask>();
		List<PreTryFlowLineNode<Sentence>> ots = fls.getOvertails();
		Iterator<PreTryFlowLineNode<Sentence>> otsitr = ots.iterator();
		int alen = AeroMetaData.codengram.length;
		int aidx = 0;
		while (otsitr.hasNext())
		{
			PreTryFlowLineNode<Sentence> fln = otsitr.next();
			aidx++;
			if (aidx > alen)
			{
				break;
			}
			CodeSynthesisFlowLines csfl = new CodeSynthesisFlowLines();
			csptl.add(new CodeSynthesisPredictTask(fln, sh, alc, csfl, aoi, AeroMetaData.codengram[aidx-1]));
			csfll.add(csfl);
		}
		List<Thread> tl = new LinkedList<Thread>();
		Iterator<CodeSynthesisPredictTask> csptlitr = csptl.iterator();
		while (csptlitr.hasNext())
		{
			CodeSynthesisPredictTask cspt = csptlitr.next();
			Thread t = new Thread(cspt);
			tl.add(t);
			t.start();
		}
		Iterator<Thread> tlitr = tl.iterator();
		while (tlitr.hasNext())
		{
			Thread t = tlitr.next();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return csfll;
	}
	
	/**
	 * just for test use.
	 * @param sh
	 * @param alc
	 * @param fls
	 * @param aoi
	 * @return
	 */
	protected List<CodeSynthesisFlowLines> DoRealCodePredictAndSynthesisInSerial(SynthesisHandler sh, AeroLifeCycle alc, PreTryFlowLines<Sentence> fls, ASTOffsetInfo aoi) {
		List<CodeSynthesisFlowLines> csfll = new LinkedList<CodeSynthesisFlowLines>();
		List<CodeSynthesisPredictTask> csptl = new LinkedList<CodeSynthesisPredictTask>();
		List<PreTryFlowLineNode<Sentence>> ots = fls.getOvertails();
		Iterator<PreTryFlowLineNode<Sentence>> otsitr = ots.iterator();
		int alen = AeroMetaData.codengram.length;
		int aidx = 0;
		while (otsitr.hasNext())
		{
			PreTryFlowLineNode<Sentence> fln = otsitr.next();
			aidx++;
			if (aidx > alen)
			{
				break;
			}
			CodeSynthesisFlowLines csfl = new CodeSynthesisFlowLines();
			csptl.add(new CodeSynthesisPredictTask(fln, sh, alc, csfl, aoi, AeroMetaData.codengram[aidx-1]));
			csfll.add(csfl);
		}
		Iterator<CodeSynthesisPredictTask> csptlitr = csptl.iterator();
		while (csptlitr.hasNext())
		{
			CodeSynthesisPredictTask cspt = csptlitr.next();
			cspt.run();
		}
		return csfll;
	}
	
	// split line, below are pre try predict.
	
	private void DoPreTrySequencePredict(AeroLifeCycle alc, PreTryFlowLines<Sentence> fls, List<Sentence> setelist,
			List<statement> smtlist, final List<statement> smtmilist, int needsize, final Class<?> lastkind) {
		PredictInfer pi = new PredictInfer(AeroMetaData.codengram[0]);
		// Map<String, Boolean> keynull = new TreeMap<String, Boolean>();
		
		Iterator<Sentence> itr = setelist.iterator();
		while (itr.hasNext())
		{
			Sentence ons = itr.next();
			DoOnePreTrySequencePredict(alc, fls, ons, smtlist, smtmilist, (int)(needsize), 2*needsize, lastkind, pi);
		}
		int size = fls.GetValidOveredSize();
		// int overtailsize = fls.getOvertails().size();
		int turn = 0;
		while ((size == 0) && turn < PredictMetaInfo.PreTryMaxStep)
		{
			turn++;
			DoOnePreTrySequencePredict(alc, fls, null, smtlist, smtmilist, (int)((needsize-size)), 2*(needsize-size), lastkind, pi);
			size = fls.GetValidOveredSize();
		}
		fls.TrimOverTails(needsize);
		
		// keynull.clear();
		pi.Clear();
		pi = null;
	}
	
	private void DoOnePreTrySequencePredict(AeroLifeCycle alc, PreTryFlowLines<Sentence> fls, Sentence ons, final List<statement> oraclelist, final List<statement> oraclemilist, int neededsize, int maxparsize, final Class<?> lastkind, PredictInfer pi)
	{
		if (fls.IsEmpty())
		{
			if (ons == null)
			{
				System.err.println("fls isEmpty and ons == null? What the fuck, the system will exit.");
				System.exit(1);
			}
			fls.InitialSeed(ons);
		}
		else {
			fls.BeginOperation();
			pi.BeginOperation();
			
			Queue<PreTryFlowLineNode<Sentence>> pppqueue = new PriorityQueue<PreTryFlowLineNode<Sentence>>();
			
			// exact match.
			double maxexactmatchsimilarity = -100000;
			boolean exactmatchhandled = false;
			double tempexactmatchprob = 0;
			
			List<FlowLineNode<Sentence>> tails = fls.getTails();
			List<Integer> sizes = ComputeInferSizes(tails, neededsize, maxparsize);
			Iterator<FlowLineNode<Sentence>> itr = tails.iterator();
			Iterator<Integer> sizeitr = sizes.iterator();
			while (itr.hasNext())
			{
				PreTryFlowLineNode<Sentence> fln = (PreTryFlowLineNode<Sentence>) itr.next();
				List<PredictProbPair> pps = pi.InferNextGeneration(alc, sizeitr.next(), fln, null);
				Iterator<PredictProbPair> ppsitr = pps.iterator();
				StatementsMIs smtmis = FlowLineHelper.LastToFirstStatementQueueWithMethodInvocationExtracted(fln);
				List<statement> triedcmp = smtmis.getSmts();
				List<statement> triedcmpsmi = smtmis.getSmis();
				while (ppsitr.hasNext())
				{
					PredictProbPair ppp = ppsitr.next();
					Sentence pred = ppp.getPred();
					statement predsmt = pred.getSmt();
					triedcmp.add(predsmt);
					if (ClassInstanceOfUtil.ObjectInstanceOf(predsmt, methodInvocationStatement.class))
					{
						triedcmpsmi.add(predsmt);
					}
					double mtsim = LCSComparison.LCSSimilarity(oraclelist, triedcmp);
					
					if (pred.getSentence().equals("MI@getActionCommand(@PE);"))
					{
						System.err.println("Debugging Sentence.");
					}
					
					double misim = LCSComparison.LCSSimilarity(oraclemilist, triedcmpsmi);
					double sim = 0.5*mtsim + 0.5*misim;
					PreTryFlowLineNode<Sentence> nf = new PreTryFlowLineNode<Sentence>(pred, ppp.getProb() + fln.getProbability(), sim, fln, ppp.getKeylen());
					// fls.AddToNextLevel(nf, fln);
					pppqueue.add(nf);
					((LinkedList<statement>)triedcmp).removeLast();
					if (ClassInstanceOfUtil.ObjectInstanceOf(predsmt, methodInvocationStatement.class))
					{
						((LinkedList<statement>)triedcmpsmi).removeLast();
					}
					
					// record information which ons and exact match need.
					if (ons != null)
					{
						double similarity = ons.getSmt().Similarity(pred.getSmt());
						if (maxexactmatchsimilarity < similarity)
						{
							maxexactmatchsimilarity = similarity;
							tempexactmatchprob = ppp.getProb();
						}
					}
					
				}
				// HandleOneInOneTurnPreTrySequencePredict(alc, fls, fln, oraclelist, handledkey, neededsize);
			}
			
			// sort and put the all one turn infers, exact match handled here.
			int ndsize = neededsize;
			while (ndsize > 0 && (!pppqueue.isEmpty()))
			{
				PreTryFlowLineNode<Sentence> nf = pppqueue.poll();
				
				boolean inexactmatchseq = tails.get(0) == nf.getParent();
				boolean isexactmatch = false;
				
				// judge if exact match is handled.
				if (inexactmatchseq && ons != null && ons.getSentence().equals(nf.getData().getSentence()))
				{
					isexactmatch = true;
					exactmatchhandled = true;
					fls.setExactmatchtail(nf);
				}
				
				boolean couldterminate = TerminationHelper.couldTerminate(nf.getData(), lastkind, nf.getParent().getLength()+1, oraclelist.size(), isexactmatch);
				if (couldterminate)
				{
					fls.AddOverFlowLineNode(nf, nf.getParent());
				}
				else
				{
					fls.AddToNextLevel(nf, nf.getParent());
					ndsize--;
				}
				
				if (isexactmatch && !couldterminate)
				{
					fls.MoveTempTailSpecificToFirst(nf);
				}
				
			}
			
			if (ons != null && !exactmatchhandled)
			{
				if (ndsize == 0 && neededsize > 0)
				{
					// delete the least probability node.
					fls.DeleteLastAddedNode();
				}
				double enhancedenergy = ProbabilityComputer.ComputeProbability(maxexactmatchsimilarity);
				PreTryFlowLineNode<Sentence> fln = fls.getExactmatchtail();
				PreTryFlowLineNode<Sentence> nf = new PreTryFlowLineNode<Sentence>(ons, tempexactmatchprob + enhancedenergy + fln.getProbability(), ((fln.getLength()+1)*1.0)/(oraclelist.size()*1.0), fln, 0);
				fls.AddToNextLevel(nf, nf.getParent());
				fls.setExactmatchtail(nf);
				fls.MoveTempTailLastToFirst();
			}
			
			fls.EndOperation();
			pi.EndOperation();
		}
	}
	
	private List<Integer> ComputeInferSizes(List<FlowLineNode<Sentence>> tails, int neededsize, int maxparsize)
	{
		ArrayList<Integer> diffs = new ArrayList<Integer>();
		Iterator<FlowLineNode<Sentence>> itr = tails.iterator();
		ComputeDiffs(itr, null, diffs, 0);
		ArrayList<Integer> sizes = ComputeSizes(diffs, neededsize, maxparsize);
		return sizes;
	}
	
	private ArrayList<Integer> ComputeSizes(ArrayList<Integer> diffs, int neededsize, int maxparsize) {
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		int all = SumIntegerList(diffs);
		Iterator<Integer> itr = diffs.iterator();
		while (itr.hasNext())
		{
			int df = itr.next();
			int sz = (int)((df*1.0)/(all*1.0)*(maxparsize*1.0));
			if (sz == 0)
			{
				sz = 1;
			}
			if (sz > neededsize)
			{
				sz = neededsize;
			}
			sizes.add(sz);
		}
		int szall = SumIntegerList(sizes);
		if (szall > maxparsize)
		{
			int pregap = 0;
			int gap = szall - maxparsize;
			while (gap > 0 && pregap != gap)
			{
				int sslen = sizes.size();
				for (int i=0;i<sslen-1 && gap > 0;i++)
				{
					if (sizes.get(i) > sizes.get(i+1))
					{
						sizes.set(i, sizes.get(i)-1);
						gap--;
					}
					if (gap > 0 && i == sslen-2)
					{
						if (sizes.get(i+1) > 1)
						{
							sizes.set(i+1, sizes.get(i+1)-1);
							gap--;
						}
					}
				}
				pregap = gap;
			}
		} else {
			int pregap = 0;
			int gap = maxparsize - szall;
			while (gap > 0 && pregap != gap)
			{
				int sslen = sizes.size();
				for (int i=0;i<sslen-1 && gap > 0;i++)
				{
					if (sizes.get(i) < neededsize)
					{
						sizes.set(i, sizes.get(i)+1);
						gap--;
					}
				}
				pregap = gap;
			}
		}
		return sizes;
	}
	
	private int SumIntegerList(List<Integer> ils)
	{
		int total = 0;
		Iterator<Integer> itr = ils.iterator();
		while (itr.hasNext())
		{
			total += itr.next();
		}
		return total;
	}
	
	private int ComputeDiffs(Iterator<FlowLineNode<Sentence>> itr, PreTryFlowLineNode<Sentence> flnpre, ArrayList<Integer> diffs, int idx)
	{
		int onediff = 0;
		PreTryFlowLineNode<Sentence> fln = null;
		if (itr.hasNext())
		{
			fln = (PreTryFlowLineNode<Sentence>) itr.next();
			diffs.add(1);
			onediff = ComputeDiffs(itr, fln, diffs, idx+1);
			diffs.set(idx, 1+onediff);
		}
		else {
			return 0;
		}
		int rtdiff = onediff;
		if (flnpre != null)
		{
			if (!SimilarityHelper.CouldThoughtTwoDoubleSame(flnpre.getSeqencesimilarity(), fln.getSeqencesimilarity()))
			{
				rtdiff++;
			}
		}
		return rtdiff;
	}
	
}