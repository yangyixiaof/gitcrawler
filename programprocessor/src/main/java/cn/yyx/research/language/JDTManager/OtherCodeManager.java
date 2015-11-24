package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.yyx.research.language.Utility.CorpusContentPair;

public class OtherCodeManager {
	
	Map<String, String> othercodemap = new TreeMap<String, String>();
	
	public OtherCodeManager() {
	}
	
	public void AppendOtherCode(String key, String value)
	{
		String ocode = othercodemap.get(key);
		if (ocode == null)
		{
			ocode = "";
		}
		if (ocode.equals("") || value.trim().equals("."))
		{
			ocode += value.trim();
		}
		else
		{
			ocode += " " + value.trim();
		}
		othercodemap.put(key, ocode);
	}
	
	public ArrayList<CorpusContentPair> GetOtherGeneratedCode() {
		ArrayList<CorpusContentPair> result = new ArrayList<CorpusContentPair>();
		Set<String> corpuses = othercodemap.keySet();
		Iterator<String> itr = corpuses.iterator();
		while (itr.hasNext())
		{
			String corpus = itr.next();
			String content = othercodemap.get(corpus);
			CorpusContentPair ccp = new CorpusContentPair(corpus, content);
			result.add(ccp);
		}
		return result;
	}
	
}