package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class JCScope {
	
	Integer ID = -1;
	
	Integer Level = -1;
	
	// real data in sequential order.
	LinkedList<String> dataInOrder = new LinkedList<String>();
	
	// records the order of the data. To speed up the search.
	Map<String, Integer> dataOrder = new TreeMap<String, Integer>();
	
	// To speed up search.
	int allDataNum = -1;
	
	public JCScope(int id, int level) {
		this.ID = id;
		this.Level = level;
		allDataNum = 0;
	}
	
	public void PushNewlyAssignedData(String data)
	{
		Integer dorder = dataOrder.get(data);
		if (dorder == null)
		{
			// newly added data, not exists before.
			dataInOrder.add(data);
			dataOrder.put(data, allDataNum);
			allDataNum++;
		}
		else
		{
			Iterator<String> itr = dataInOrder.iterator();
			boolean beginDec = false;
			int idx = -1;
			while (itr.hasNext())
			{
				if (beginDec)
				{
					String idata = itr.next();
					dataOrder.put(idata, dataOrder.get(idata)-1);
				}
				idx++;
				if (idx == dorder)
				{
					beginDec = true;
				}
			}
			dataOrder.put(data, allDataNum-1);
			dataInOrder.remove(dorder);
			dataInOrder.add(data);
		}
	}
	
	public int GetExactOffset(String data)
	{
		Integer order = dataOrder.get(data);
		if (order == null)
		{
			System.err.println("Wrong data: the data is not declared or assigned. The system will exit. This may be improved in the future to get better compatibility.");
			System.exit(1);
		}
		int maxOffset = allDataNum-1;
		return order-maxOffset;
	}
	
}