package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

public class NoVisitNodeManager {
	
	Map<Integer, Boolean> mNoVisitNodes = new TreeMap<Integer, Boolean>();
	
	public NoVisitNodeManager() {
		
	}
	
	public void AddNoVisitNode(Integer nodeid)
	{
		mNoVisitNodes.put(nodeid, true);
	}
	
	public boolean NeedVisit(Integer nodeid)
	{
		Boolean novisit = mNoVisitNodes.get(nodeid);
		if (novisit == null)
		{
			return true;
		}
		return false;
	}
	
}