package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.yyx.research.language.Utility.CorpusContentPair;
import cn.yyx.research.language.simplified.JDTManager.ContentsAndWords;

public class OtherCodeManager {
	
	private Map<String, ContentsAndWords> othercodemap = new TreeMap<String, ContentsAndWords>();
	// private Map<String, Integer> otherwordsmap = new TreeMap<String, Integer>();
	
	public OtherCodeManager() {
	}
	
	public void AppendOtherCode(String key, String value)
	{
		ContentsAndWords ocode = getOtherCodeMap().get(key);
		// Integer owords = getOtherWordsMap().get(key);
		if (ocode == null)
		{
			ocode = new ContentsAndWords("", 0);
		}
		// ocode.equals("") || 
		String trimvalue = value.trim();
		if (trimvalue.equals("."))
		{
			ocode.setContent(ocode.getContent() + trimvalue);
		}
		else
		{
			ocode.setContent(ocode.getContent() + " " + trimvalue);
			ocode.setWords(ocode.getWords() + 1);
		}
		getOtherCodeMap().put(key, ocode);
	}
	
	public ArrayList<CorpusContentPair> GetOtherGeneratedCode() {
		ArrayList<CorpusContentPair> result = new ArrayList<CorpusContentPair>();
		Set<String> corpuses = getOtherCodeMap().keySet();
		Iterator<String> itr = corpuses.iterator();
		while (itr.hasNext())
		{
			String corpus = itr.next();
			ContentsAndWords ocm = getOtherCodeMap().get(corpus);
			String content = ocm.getContent();
			int words = ocm.getWords();
			CorpusContentPair ccp = new CorpusContentPair(corpus, content, words);
			result.add(ccp);
		}
		return result;
	}

	public Map<String, ContentsAndWords> getOtherCodeMap() {
		return othercodemap;
	}

	/*public Map<String, Integer> getOtherWordsMap() {
		return otherwordsmap;
	}*/

	/*public void setOtherwordsmap(Map<String, Integer> otherwordsmap) {
		this.otherwordsmap = otherwordsmap;
	}*/

	/*public void setOthercodemap(Map<String, String> othercodemap) {
		this.othercodemap = othercodemap;
	}*/
	
}