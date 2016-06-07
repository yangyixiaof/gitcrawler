package cn.yyx.research.language.simplified.JDTManager;

import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ScopeOffsetResult {
	
	private Map<String, String> sor = null;
	private Map<String, Long> sol = null;
	
	private PriorityQueue<TimeCmp> pqueue = new PriorityQueue<TimeCmp>();
	
	public void BeginIterate()
	{
		pqueue.clear();
		Set<String> keys = sor.keySet();
		Iterator<String> kitr = keys.iterator();
		int i = 0;
		while (kitr.hasNext())
		{
			String key = kitr.next();
			String data = sor.get(key);
			i++;
			Long timestamp = sol.get(key);
			if (timestamp == null)
			{
				timestamp = System.currentTimeMillis()+i;
			}
			pqueue.add(new TimeCmp(new TypeVar(key, data), timestamp));
		}
	}
	
	public boolean HasNext()
	{
		return !pqueue.isEmpty();
	}
	
	public TypeVar Next()
	{
		TimeCmp tt = pqueue.poll();
		return tt.getCnt();
	}
	
	public void SetModifiedVarName(String type, String varname)
	{
		sor.put(type, varname);
	}
	
	public ScopeOffsetResult(Map<String, String> sor, Map<String, Long> sol) {
		this.setSor(sor);
		this.setSol(sol);
	}

	public Map<String, String> getSor() {
		return sor;
	}

	public void setSor(Map<String, String> sor) {
		this.sor = sor;
	}

	public Map<String, Long> getSol() {
		return sol;
	}

	public void setSol(Map<String, Long> sol) {
		this.sol = sol;
	}
	
}