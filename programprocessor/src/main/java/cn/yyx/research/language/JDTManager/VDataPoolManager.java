package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VDataPoolManager {
	
	Map<String, VDataPool> dataPools = new TreeMap<String, VDataPool>();
	
	public VDataPoolManager() {
	}
	
	public void AddData(String kind, OneScope dataScope, String data)
	{
		VDataPool dpool = GetDataPool(kind);
		dpool.NewlyAssignedData(dataScope, data);
	}
	
	public Integer GetDataExactOffset(String kind, OneScope dataScope, String data)
	{
		VDataPool dpool = GetDataPool(kind);
		return dpool.GetExactDataOffsetInDataOwnScope(dataScope, data);
	}
	
	//dpool.AScopeCreated(node, level);
	//dpool.AScopeDestroyed(node);
	//dpool.AddEquivalentScope(node1, node2);
	public void AScopeCreated(OneScope scope)
	{
		Set<String> kinds = dataPools.keySet();
		Iterator<String> itr = kinds.iterator();
		while (itr.hasNext())
		{
			String kind = itr.next();
			VDataPool dpool = dataPools.get(kind);
			dpool.AScopeCreated(scope);
		}
	}
	
	public void AScopeDestroyed(OneScope scope)
	{
		Set<String> kinds = dataPools.keySet();
		Iterator<String> itr = kinds.iterator();
		while (itr.hasNext())
		{
			String kind = itr.next();
			VDataPool dpool = dataPools.get(kind);
			dpool.AScopeDestroyed(scope);
		}
	}
	
	public void ResetClassScope(OneScope scope) {
		// before invoke this function, we have ensure the scope is really scope of classed.
		Set<String> kinds = dataPools.keySet();
		Iterator<String> itr = kinds.iterator();
		while (itr.hasNext())
		{
			String kind = itr.next();
			VDataPool dpool = dataPools.get(kind);
			dpool.ResetClassScope(scope);
		}
	}
	
	private VDataPool GetDataPool(String kind)
	{
		if (!dataPools.containsKey(kind))
		{
			dataPools.put(kind, new VDataPool());
		}
		return dataPools.get(kind);
	}
	
}