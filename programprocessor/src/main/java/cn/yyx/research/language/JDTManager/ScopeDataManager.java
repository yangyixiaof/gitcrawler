package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class ScopeDataManager {
	
	//LinkedList<Integer> is reverse order.
	Map<String, LinkedList<DataScopeInfo>> mDataScopeMap = new TreeMap<String, LinkedList<DataScopeInfo>>();
	//LinkedList<Integer> is reverse order.
	//Map<String, LinkedList<Integer>> mDataLineMap = new TreeMap<String, LinkedList<Integer>>();
	//LinkedList<Integer> is formal order.
	Map<OneScope, LinkedList<String>> mFieldScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
	Map<OneScope, LinkedList<String>> mFinalFieldScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
	
	Map<OneScope, LinkedList<String>> mCommonScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
	Map<OneScope, LinkedList<String>> mFinalCommonScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
	
	Map<OneScope, LinkedList<String>> mFieldScopeTypeMap = new TreeMap<OneScope, LinkedList<String>>();
	Map<OneScope, LinkedList<String>> mFinalFieldScopeTypeMap = new TreeMap<OneScope, LinkedList<String>>();
	
	Map<OneScope, LinkedList<String>> mCommonScopeTypeMap = new TreeMap<OneScope, LinkedList<String>>();
	Map<OneScope, LinkedList<String>> mFinalCommonScopeTypeMap = new TreeMap<OneScope, LinkedList<String>>();
	// TreeMap<OneScope, TreeMap<String, Integer>> mScopeDataKindMap = new TreeMap<OneScope, TreeMap<String, Integer>>();
	
	//No scope data, just record raw string and its corresponding line.
	// Map<String, Integer> mRawStringDataLineMap = new TreeMap<String, Integer>();
	
	EnteredScopeStack classstack = new EnteredScopeStack();
	
	// VHiddenClassPoolManager vhcpm = new VHiddenClassPoolManager();
	// VLabelPoolManager vlpm = new VLabelPoolManager();
	// VVarObjPoolManager vvopm = new VVarObjPoolManager();
	
	VDataPool fvdp = new VDataPool();
	VDataPool cvdp = new VDataPool();
	
	VDataPool ffvdp = new VDataPool();
	VDataPool fcvdp = new VDataPool();
	
	public ScopeDataManager() {
	}
	
	private void CheckTypeNotNull(String type)
	{
		if (type == null)
		{
			System.err.println("The type in AddDataNewlyUsed must not be null, serious error. The system will exit.");
			System.exit(1);
		}
	}
	
	private void CheckTypeMustNull(String type)
	{
		if (type != null)
		{
			System.err.println("The type in AddDataNewlyUsed must be null, serious error. The system will exit.");
			System.exit(1);
		}
	}
	
	private void DataScopeAndScopeDataMapCode(String data, String type, boolean isfinal, OneScope oscope, boolean isfield, Map<OneScope, LinkedList<String>> fieldScopeDataMap, Map<OneScope, LinkedList<String>> fieldScopeTypeMap)
	{
		LinkedList<DataScopeInfo> list = mDataScopeMap.get(data);
		if (list == null)
		{
			list = new LinkedList<DataScopeInfo>();
			mDataScopeMap.put(data, list);
		}
		list.add(new DataScopeInfo(oscope, data, type, isfinal, isfield));
		LinkedList<String> datalist = fieldScopeDataMap.get(oscope);
		if (datalist == null)
		{
			datalist = new LinkedList<String>();
			fieldScopeDataMap.put(oscope, datalist);
		}
		datalist.add(data);
		LinkedList<String> typelist = fieldScopeTypeMap.get(oscope);
		if (typelist == null)
		{
			typelist = new LinkedList<String>();
			fieldScopeTypeMap.put(oscope, typelist);
		}
		typelist.add(type);
	}
	
	public void AddDataNewlyUsed(String data, String type, boolean isfinal, boolean isfielddeclare, boolean iscommondeclare, boolean isFieldUpdate, boolean isCommonUpdate) {
		// VDataPoolManager vdpm = KindLibrary.ChooseManagerAccordingToKind(kind, vhcpm, vlpm, vvopm);
		OneScope oscope = null;
		VDataPool use = null;
		if (isfinal)
		{
			if (isfielddeclare)
			{
				oscope = classstack.peek();
				CheckTypeNotNull(type);
				use = ffvdp;
				DataScopeAndScopeDataMapCode(data, type, isfinal, oscope, isfielddeclare, mFinalFieldScopeDataMap, mFinalFieldScopeTypeMap);
			}
			if (iscommondeclare)
			{
				oscope = classstack.peek();
				CheckTypeNotNull(type);
				use = fcvdp;
				DataScopeAndScopeDataMapCode(data, type, isfinal, oscope, isfielddeclare, mFinalCommonScopeDataMap, mFinalCommonScopeTypeMap);
			}
			if (isFieldUpdate)
			{
				use = ffvdp;
			}
			if (isCommonUpdate)
			{
				use = fcvdp;
			}
		}
		else
		{
			if (isfielddeclare)
			{
				oscope = classstack.peek();
				CheckTypeNotNull(type);
				use = fvdp;
				DataScopeAndScopeDataMapCode(data, type, isfinal, oscope, isfielddeclare, mFieldScopeDataMap, mFieldScopeTypeMap);
			}
			if (iscommondeclare)
			{
				oscope = classstack.peek();
				CheckTypeNotNull(type);
				use = cvdp;
				DataScopeAndScopeDataMapCode(data, type, isfinal, oscope, isfielddeclare, mCommonScopeDataMap, mCommonScopeTypeMap);
			}
			if (isFieldUpdate)
			{
				use = fvdp;
			}
			if (isCommonUpdate)
			{
				use = cvdp;
			}
		}
		if (use == null)
		{
			LinkedList<DataScopeInfo> list = mDataScopeMap.get(data);
			if (list == null || list.size() == 0)
			{
				System.err.println("Not declared field? The program will exit.");
				System.exit(1);
			}
			else
			{
				Iterator<DataScopeInfo> itr = list.iterator();
				while (itr.hasNext())
				{
					DataScopeInfo dscopeinfo = itr.next();
					if (dscopeinfo.isFinal() != isfinal)
					{
						continue;
					}
					use = GetRealUseDataPool(dscopeinfo);
					oscope = dscopeinfo.getOscope();
					CheckTypeMustNull(type);
					type = dscopeinfo.getType();
					break;
				}
			}
		}
		use.NewlyAssignedData(oscope, data, type);
	}
	
	public String GetDataOffsetInfo(String data, boolean isFieldUseOrUpdate, boolean isCommonUseOrUpdate) {
		DataScopeInfo firstinfo = GetLastClassIdInList(data);
		if (firstinfo != null)
		{
			LinkedList<DataScopeInfo> list = mDataScopeMap.get(data);
			VDataPool use = null;
			DataScopeInfo nowinfo = null;
			Iterator<DataScopeInfo> itr = list.iterator();
			while (itr.hasNext())
			{
				nowinfo = itr.next();
				if (isFieldUseOrUpdate)
				{
					if (nowinfo.isField() == false)
					{
						continue;
					}
				}
				if (isCommonUseOrUpdate)
				{
					if (nowinfo.isField() == true)
					{
						continue;
					}
				}
				use = GetRealUseDataPool(nowinfo);
				break;
			}
			OneScope dataScope = nowinfo.getOscope();
			String type = nowinfo.getType();
			Integer exactoffset = use.GetExactDataOffsetInDataOwnScope(dataScope, data, type);
			if (exactoffset == null || classstack.getSize() == 0)
			{
				if (classstack.getSize() == 0)
				{
					System.err.println("What the fuck, data does not have scope? The system will exit.");
					System.exit(1);
				}
				return null;
			}
			OneScope currentscope = classstack.getScope(classstack.getSize()-1);
			return "$" + Math.abs(dataScope.getLevel() - currentscope.getLevel()) + GCodeMetaInfo.OffsetSpiliter + OffsetLibrary.GetOffsetDescription(exactoffset);
		}
		else
		{
			System.err.println("Not declared field? The program will exit.");
			System.exit(1);
		}
		return null;
	}
	
	private VDataPool GetRealUseDataPool(DataScopeInfo firstinfo)
	{
		VDataPool use = null;
		if (firstinfo.isField() && firstinfo.isFinal())
		{
			use = ffvdp;
		}
		if (firstinfo.isField() && !firstinfo.isFinal())
		{
			use = fvdp;
		}
		if (!firstinfo.isField() && firstinfo.isFinal())
		{
			use = fcvdp;
		}
		if (!firstinfo.isField() && !firstinfo.isFinal())
		{
			use = cvdp;
		}
		return use;
	}
	
	public void EnterBlock(int scopeid)
	{
		int level = classstack.getSize();
		OneScope oscope = classstack.PushBack(scopeid, level);
		
		fvdp.AScopeCreated(oscope);
		cvdp.AScopeCreated(oscope);
		
		ffvdp.AScopeCreated(oscope);
		fcvdp.AScopeCreated(oscope);
	}
	
	public void ExitBlock()
	{
		// Map<OneScope, LinkedList<String>> mFieldScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
		// Map<OneScope, LinkedList<String>> mFinalFieldScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
		
		// Map<OneScope, LinkedList<String>> mCommonScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
		// Map<OneScope, LinkedList<String>> mFinalCommonScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
		
		OneScope oscope = classstack.pop();
		
		fvdp.AScopeDestroyed(oscope);
		cvdp.AScopeDestroyed(oscope);
		
		ffvdp.AScopeDestroyed(oscope);
		fcvdp.AScopeDestroyed(oscope);
		
		LinkedList<String> datalist = null;
		datalist = mFieldScopeDataMap.remove(oscope);
		DeleteDataScopeOfScope(oscope, datalist);
		datalist = mFinalFieldScopeDataMap.remove(oscope);
		DeleteDataScopeOfScope(oscope, datalist);
		datalist = mCommonScopeDataMap.remove(oscope);
		DeleteDataScopeOfScope(oscope, datalist);
		datalist = mFinalCommonScopeDataMap.remove(oscope);
		DeleteDataScopeOfScope(oscope, datalist);
	}
	
	private void DeleteDataScopeOfScope(OneScope oscope, LinkedList<String> datalist)
	{
		Iterator<String> itr = datalist.iterator();
		while (itr.hasNext())
		{
			String data = itr.next();
			LinkedList<DataScopeInfo> list = mDataScopeMap.get(data);
			while (list.size() > 0 && list.get(0).getOscope().getLevel() == oscope.getLevel())
			{
				list.removeFirst();
			}
		}
	}
	
	public void ResetCurrentClassField()
	{
		OneScope classscope = classstack.peek();
		
		//testing
		//TestUtil.PrintEnteredScopeStack(classstack);
		
		VDataPool fvdp = new VDataPool();
		VDataPool ffvdp = new VDataPool();
		
		fvdp.ResetClassScope(classscope, mFieldScopeDataMap.get(classscope), mFieldScopeTypeMap.get(classscope));
		ffvdp.ResetClassScope(classscope, mFinalFieldScopeDataMap.get(classscope), mFieldScopeTypeMap.get(classscope));
	}
	
	//Due to list is reverse order, so which means first.
	private DataScopeInfo GetLastClassIdInList(String data)
	{
		LinkedList<DataScopeInfo> list = mDataScopeMap.get(data);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public boolean ContainsScope(Integer equid) {
		return classstack.isIdContained(equid);
	}
	
	public int GetFirstClassId() {
		return classstack.getScope(0).getID();
	}
	
}