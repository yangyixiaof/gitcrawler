package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class DataLineManager {
	
	//LinkedList<Integer> is reverse order.
	Map<String, LinkedList<Integer>> mDataScopeMap = new TreeMap<String, LinkedList<Integer>>();
	//LinkedList<Integer> is reverse order.
	Map<String, LinkedList<Integer>> mDataLineMap = new TreeMap<String, LinkedList<Integer>>();
	//LinkedList<Integer> is formal order.
	Map<Integer, LinkedList<String>> mScopeDataMap = new TreeMap<Integer, LinkedList<String>>();
	
	//No scope data, just record raw string and its corresponding line.
	Map<String, Integer> mRawStringDataLineMap = new TreeMap<String, Integer>();
	
	EnteredBlockStack blockstack = null;
	EnteredClassStack classstack = null;

	public DataLineManager(EnteredBlockStack blockstack, EnteredClassStack classstack) {
		this.blockstack = blockstack;
		this.classstack = classstack;
	}

	public void AddDataLineInfo(String data, int line, boolean isfielddeclare, boolean iscommondeclare) {
		Integer scope = -1;
		Integer sindex = -1;
		boolean newthing = false;
		boolean mustbefield = false;
		if (isfielddeclare)
		{
			scope = classstack.peek();
			newthing = true;
		}
		else
		{
			if (iscommondeclare)
			{
				scope = blockstack.peek();
				newthing = true;
			}
			else
			{
				//is access.
				if (data.startsWith("this."))
				{
					mustbefield = true;
					data = data.substring("this.".length());
				}
				IndexValuePair ivp = null;
				if (mustbefield)
				{
					ivp = GetLastClassIdInList(data);
				}
				else
				{
					ivp = GetLastIdInList(data);
				}
				if (ivp != null)
				{
					scope = ivp.getScope();
					sindex = ivp.getIndex();
				}
				else
				{
					scope = null;
					sindex = null;
				}
			}
		}
		if (newthing)
		{
			if (mScopeDataMap.get(scope) == null)
			{
				mScopeDataMap.put(scope, new LinkedList<String>());
			}
			mScopeDataMap.get(scope).add(data);
			if (mDataScopeMap.get(data) == null)
			{
				mDataScopeMap.put(data, new LinkedList<Integer>());
			}
			mDataScopeMap.get(data).add(0, scope);
			if (mDataLineMap.get(data) == null)
			{
				mDataLineMap.put(data, new LinkedList<Integer>());
			}
			mDataLineMap.get(data).add(0, line);
		}
		else
		{
			if (scope != null)
			{
				mDataLineMap.get(data).set(sindex, line);
			}
			else
			{
				// Other scope declared data such as 'System.in'. Not problem.
				// System.err.println("Warning : Undeclared variable or object. The program will not exit.");
				// System.err.println("Warning : WarningrData:"+data+";isFielddec:"+isfielddeclare+";iscommondec:"+iscommondeclare+";isfield:"+mustbefield);
				// new Exception().printStackTrace();
				// System.exit(1);
				mRawStringDataLineMap.put(data, line);
			}
		}
	}
	
	public int GetDataLineInfo(String data) {
		boolean isfield = false;
		if (data.startsWith("this."))
		{
			isfield = true;
			data = data.substring("this.".length());
		}
		if (isfield)
		{
			IndexValuePair ivp = GetLastClassIdInList(data);
			if (ivp != null)
			{
				int index = ivp.getIndex();
				return mDataLineMap.get(data).get(index);
			}
			else
			{
				System.err.println("Not declared field? The program will exit.");
				System.exit(1);
			}
		}
		else
		{
			Integer line = (mDataLineMap.get(data) == null || mDataLineMap.get(data).size() == 0) ? null : mDataLineMap.get(data).get(0);
			if (line != null)
			{
				return line;
			}
			else
			{
				line = mRawStringDataLineMap.get(data);
				if (line != null)
				{
					return line;
				}
			}
		}
		return GCodeMetaInfo.OutofScopeVarOrObject;
	}
	
	public void EnterBlock(int scopeid, boolean isclass)
	{
		if (isclass)
		{
			classstack.push(scopeid);
		}
		else
		{
			blockstack.push(scopeid);
		}
	}
	
	public void ExitBlock(boolean isclass)
	{
		int scopeid = -1;
		if (isclass)
		{
			scopeid = classstack.pop();
		}
		else
		{
			scopeid = blockstack.pop();
		}
		LinkedList<String> datalist = mScopeDataMap.get(scopeid);
		if (datalist != null)
		{
			Iterator<String> itr = datalist.iterator();
			while (itr.hasNext())
			{
				String data = itr.next();
				mDataLineMap.get(data).removeFirst();
				int storedscopeid = mDataScopeMap.get(data).removeFirst();
				if (storedscopeid != scopeid)
				{
					System.err.println("Inconsist scope id. The program will exit.");
					System.err.println("storedscopeid:"+storedscopeid+";scopeid:"+scopeid+";isclass:"+isclass+";");
					new Exception("Inconsist scope id").printStackTrace();
					System.exit(1);
				}
			}
			mScopeDataMap.remove(scopeid);
		}
	}
	
	public void ResetCurrentClassField()
	{
		int classscopeid = classstack.peek();
		LinkedList<String> fieldslist = mScopeDataMap.get(classscopeid);
		for (String field : fieldslist)
		{
			LinkedList<Integer> samedatas = mDataLineMap.get(field);
			IndexValuePair ivp = GetLastClassIdInList(field);
			if (ivp != null)
			{
				int index = ivp.getIndex();
				samedatas.set(index, GCodeMetaInfo.IsField);
			}
			else
			{
				// this is taken as errors.
				System.err.println("Undeclared variable or object. The program will exit.");
				new Exception("Undeclared variable or object").printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public void ClearRawStringDataLineInfo()
	{
		mRawStringDataLineMap.clear();
	}
	
	private IndexValuePair GetLastIdInList(String data)
	{
		//testing
		/*if (mDataScopeMap.get(data) == null)
		{
			System.out.println("ERROR DATA IN GET LAST ID:"+data);
		}*/
		LinkedList<Integer> list = mDataScopeMap.get(data);
		if (list == null || list.size() == 0)
		{
			return null;
		}
		return new IndexValuePair(0, list.getFirst());
	}
	
	//Due to list is reverse order, so which means first.
	/*private IndexValuePair GetLastBlockIdInList(String data)
	{
		LinkedList<Integer> list = mDataScopeMap.get(data);
		Iterator<Integer> itr = list.iterator();
		int index = -1;
		while (itr.hasNext())
		{
			index++;
			int hashcode = itr.next();
			if (!classstack.isIdClass(hashcode))
			{
				return new IndexValuePair(index, hashcode);
			}
		}
		return null;
	}*/
	
	//Due to list is reverse order, so which means first.
	private IndexValuePair GetLastClassIdInList(String data)
	{
		LinkedList<Integer> list = mDataScopeMap.get(data);
		Iterator<Integer> itr = list.iterator();
		int index = -1;
		while (itr.hasNext())
		{
			index++;
			int hashcode = itr.next();
			if (classstack.isIdClass(hashcode))
			{
				return new IndexValuePair(index, hashcode);
			}
		}
		return null;
	}
	
	private class IndexValuePair {
		private int index = -1;
		private int scope = -1;
		public IndexValuePair(int index, int scope)
		{
			this.index = index;
			this.scope = scope;
		}
		public int getIndex() {
			return index;
		}
		public int getScope() {
			return scope;
		}
	}
	
}