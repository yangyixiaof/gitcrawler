package cn.yyx.research.language.JDTManager;

import java.util.Iterator;

public class OneJavaFileCode {
	
	StringBuilder sb = new StringBuilder("");
	
	public OneJavaFileCode() {
	}
	
	public void AddOneMethodNodeCode(NodeCode nc)
	{
		Iterator<String> itr = nc.GetCodeIterator();
		while (itr.hasNext())
		{
			sb.append(" " + itr.next());
		}
	}
	
	public void OneSentenceEnd()
	{
		sb.append(".");
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
	
}