package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class JCScope {
	
	Integer ID = -1;
	
	Integer Level = -1;
	
	// real data in sequential order.
	TreeMap<String, LinkedList<String>> dataInOrder = new TreeMap<String, LinkedList<String>>();
	
	// records the order of the data. To speed up the search.
	TreeMap<String, TreeMap<String, Integer>> dataOrder = new TreeMap<String, TreeMap<String, Integer>>();
	
	// To speed up search.
	int allDataNum = -1;
	
	public JCScope(int id, int level) {
		this.ID = id;
		this.Level = level;
		allDataNum = 0;
	}
	
	public void PushNewlyAssignedData(String data, String type)
	{
		TreeMap<String, Integer> dataorder = dataOrder.get(type);
		if (dataorder == null)
		{
			dataorder = new TreeMap<String, Integer>();
			dataOrder.put(type, dataorder);
		}
		Integer dorder = dataorder.get(data);
		LinkedList<String> datainorder = dataInOrder.get(type);
		if (datainorder == null)
		{
			datainorder = new LinkedList<String>();
			dataInOrder.put(type, datainorder);
		}
		if (dorder == null)
		{
			// newly added data, not exists before.
			datainorder.add(data);
			dataorder.put(data, allDataNum);
			allDataNum++;
		}
		else
		{
			Iterator<String> itr = datainorder.iterator();
			boolean beginDec = false;
			int idx = -1;
			while (itr.hasNext())
			{
				if (beginDec)
				{
					String idata = itr.next();
					dataorder.put(idata, dataorder.get(idata)-1);
				}
				idx++;
				if (idx == dorder)
				{
					beginDec = true;
				}
			}
			dataorder.put(data, allDataNum-1);
			datainorder.remove(dorder);
			datainorder.add(data);
		}
	}
	
	public Integer GetExactOffset(String data, String type)
	{
		TreeMap<String, Integer> dataorder = dataOrder.get(type);
		if (dataorder == null)
		{
			System.err.println("Warning data: the type of data is not declared or assigned. The system will exit. This has be improved in the future to get better compatibility.");
			return null;
		}
		Integer order = dataorder.get(data);
		if (order == null)
		{
			System.err.println("Warning data: the data is not declared or assigned. The system will exit. This has be improved in the future to get better compatibility.");
			return null;
		}
		int maxOffset = allDataNum-1;
		return order-maxOffset;
	}
	
	public void ClearAll()
	{
		dataInOrder.clear();
		dataOrder.clear();
	}
	
	public void ResetScope(LinkedList<String> orderedData, LinkedList<String> orderedType) {
		ClearAll();
		Iterator<String> itr = orderedData.iterator();
		Iterator<String> typeitr = orderedType.iterator();
		while (itr.hasNext())
		{
			String key = itr.next();
			String type = typeitr.next();
			PushNewlyAssignedData(key, type);
		}
	}
	
}