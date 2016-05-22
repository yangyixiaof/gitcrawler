package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import cn.yyx.research.language.Utility.WarningUtil;

public class JCScope {
	
	// Integer ID = -1;
	// Integer Level = -1;
	String description = null;
	
	// real data in reverse order.
	TreeMap<String, LinkedList<String>> dataInOrder = null;
	
	// records the order of the data. To speed up the search.
	// TreeMap<String, TreeMap<String, Integer>> dataOrder = new TreeMap<String, TreeMap<String, Integer>>();
	
	// To speed up search.
	// TreeMap<String, Integer> allDataNum = new TreeMap<String, Integer>();
	// int allDataNum = -1;
	
	public JCScope() {
		// allDataNum = 0;
		dataInOrder = new TreeMap<String, LinkedList<String>>();
	}
	
	public JCScope(String description, TreeMap<String, LinkedList<String>> dataInOrder) {
		this.description = description;
		this.dataInOrder = dataInOrder;
	}
	
	public JCScope(int id, int level) {
		// this.ID = id;
		// this.Level = level;
		// allDataNum = 0;
		dataInOrder = new TreeMap<String, LinkedList<String>>();
	}
	
	public Map<String, String> GetContentAccordingToOffset(String tempaddtype, int tempalloffset, int offset)
	{
		Map<String, String> result = new TreeMap<String, String>();
		Set<String> types = dataInOrder.keySet();
		Iterator<String> itr = types.iterator();
		while (itr.hasNext())
		{
			String type = itr.next();
			
			LinkedList<String> list = dataInOrder.get(type);
			int idx = offset;
			
			if (type.equals(tempaddtype))
			{
				idx -= tempalloffset;
			}
			
			int lsize = list.size();
			if (idx >= 0 && lsize > idx)
			{
				result.put(type, list.get(idx));
			}
			/*if (lsize >= idx)
			{
				if (lsize == idx)
				{
					idx--;
				}
				result.put(type, list.get(idx));
			}*/
		}
		return result;
	}
	
	public void PushNewlyAssignedData(String data, String type)
	{
		LinkedList<String> datainorder = dataInOrder.get(type);
		if (datainorder == null)
		{
			datainorder = new LinkedList<String>();
			dataInOrder.put(type, datainorder);
		}
		datainorder.add(0, data);
	}
	
	public Integer GetExactOffset(String data, String type)
	{
		LinkedList<String> dataorder = dataInOrder.get(type);
		if (dataorder == null)
		{
			WarningUtil.DataNoRefKindCheckAndPrint(data, description);
			return null;
		}
		int idx = 0;
		Iterator<String> itr = dataorder.iterator();
		while (itr.hasNext())
		{
			String dstr = itr.next();
			if (dstr.equals(data))
			{
				return idx;
			}
			idx++;
		}
		WarningUtil.DataNoRefKindCheckAndPrint(data, description);
		return null;
	}
	
	public void ClearAll()
	{
		dataInOrder.clear();
	}
	
	public void ResetScope(LinkedList<String> orderedData, LinkedList<String> orderedType) {
	}
	
	public void SetDescription(String description)
	{
		this.description = description;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException {
		TreeMap<String, LinkedList<String>> doo = new TreeMap<String, LinkedList<String>>();
		Set<String> keys = dataInOrder.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext())
		{
			String tp = itr.next();
			LinkedList<String> val = dataInOrder.get(tp);
			doo.put(tp, (LinkedList<String>)val.clone());
		}
		return new JCScope(description, doo);
	}

	public void DeleteRecentlyAddedType(String type) {
		dataInOrder.get(type).removeLast();
	}

	public String GenerateModifiedName(String name, String type, int gap) {
		LinkedList<String> names = dataInOrder.get(type);
		if (names == null)
		{
			return name;
		}
		else
		{
			Random ra =new Random();
			boolean couldstop = false;
			String modifiedname = name;
			while (!couldstop)
			{
				modifiedname = name + ra.nextInt(gap);
				if (!names.contains(modifiedname))
				{
					couldstop = true;
				}
				gap+=5;
			}
			return modifiedname;
		}
	}
	
}