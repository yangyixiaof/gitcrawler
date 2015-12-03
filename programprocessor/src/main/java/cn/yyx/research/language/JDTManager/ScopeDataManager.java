package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class ScopeDataManager {
	
	//LinkedList<Integer> is reverse order.
	Map<String, LinkedList<OneScope>> mDataScopeMap = new TreeMap<String, LinkedList<OneScope>>();
	//LinkedList<Integer> is reverse order.
	//Map<String, LinkedList<Integer>> mDataLineMap = new TreeMap<String, LinkedList<Integer>>();
	//LinkedList<Integer> is formal order.
	Map<OneScope, LinkedList<String>> mScopeDataMap = new TreeMap<OneScope, LinkedList<String>>();
	TreeMap<OneScope, TreeMap<String, Integer>> mScopeDataKindMap = new TreeMap<OneScope, TreeMap<String, Integer>>();
	
	//No scope data, just record raw string and its corresponding line.
	//Map<String, Integer> mRawStringDataLineMap = new TreeMap<String, Integer>();
	
	EnteredScopeStack blockstack = new EnteredScopeStack();
	EnteredScopeStack classstack = new EnteredScopeStack();
	
	VHiddenClassPoolManager vhcpm = new VHiddenClassPoolManager();
	VLabelPoolManager vlpm = new VLabelPoolManager();
	VVarObjPoolManager vvopm = new VVarObjPoolManager();
	
	public ScopeDataManager() {
	}
	
	public void AddDataNewlyUsed(String data, String kind, boolean isfielddeclare, boolean iscommondeclare) {
		VDataPoolManager vdpm = KindLibrary.ChooseManagerAccordingToKind(kind, vhcpm, vlpm, vvopm);
		OneScope oscope = null;
		boolean newthing = false;
		boolean mustbefield = false;
		if (isfielddeclare)
		{
			oscope = classstack.peek();
			newthing = true;
		}
		else
		{
			if (iscommondeclare)
			{
				oscope = blockstack.peek();
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
					oscope = ivp.getScope();
					// sindex = ivp.getIndex();
				}
				else
				{
					oscope = null;
					// sindex = null;
				}
			}
		}
		if (newthing)
		{
			if (mScopeDataMap.get(oscope) == null)
			{
				mScopeDataMap.put(oscope, new LinkedList<String>());
			}
			mScopeDataMap.get(oscope).add(data);
			if (mScopeDataKindMap.get(oscope) == null)
			{
				mScopeDataKindMap.put(oscope, new TreeMap<String, Integer>());
			}
			Integer kindval = mScopeDataKindMap.get(oscope).get(data);
			if (kindval == null)
			{
				kindval = 0;
			}
			kindval |= KindLibrary.GetKindRepresentation(kind);
			mScopeDataKindMap.get(oscope).put(data, kindval);
			//, kind
			if (mDataScopeMap.get(data) == null)
			{
				mDataScopeMap.put(data, new LinkedList<OneScope>());
			}
			mDataScopeMap.get(data).add(0, oscope);
			//data line map
			vdpm.AddData(kind, oscope, data);
		}
		else
		{
			if (oscope != null)
			{
				vdpm.AddData(kind, oscope, data);
			}
			else
			{
				// Other scope declared data such as 'System.in'. Not problem.
				// System.err.println("Warning : Undeclared variable or object. The program will not exit.");
				// System.err.println("Warning : WarningrData:"+data+";isFielddec:"+isfielddeclare+";iscommondec:"+iscommondeclare+";isfield:"+mustbefield);
				// new Exception().printStackTrace();
				// System.exit(1);
				// mRawStringDataLineMap.put(data, line);
			}
		}
	}
	
	public String GetDataAssignOffsetInfo(String data, KindHint hintkind, String kind) {
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
				OneScope dataScope = ivp.getScope();
				return GetDataExactOffset(dataScope, data, hintkind);
			}
			else
			{
				System.err.println("Not declared field? The program will exit.");
				System.exit(1);
			}
		}
		else
		{
			IndexValuePair ivp = GetLastIdInList(data);
			if (ivp != null)
			{
				OneScope dataScope = ivp.getScope();
				return GetDataExactOffset(dataScope, data, hintkind);
			}
			/*Integer line = (mDataLineMap.get(data) == null || mDataLineMap.get(data).size() == 0) ? null : mDataLineMap.get(data).get(0);
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
			}*/
		}
		AddDataNewlyUsed(data, kind, false, true);
		return GCodeMetaInfo.FirstDeclaredData;
	}
	
	public void EnterBlock(int scopeid, boolean isclass)
	{
		int level = blockstack.getSize();
		blockstack.push(scopeid, isclass, level);
		if (isclass)
		{
			classstack.push(scopeid, isclass, level);
		}
		
		OneScope oscope = blockstack.peek();
		vhcpm.AScopeCreated(oscope);
		vlpm.AScopeCreated(oscope);
		vvopm.AScopeCreated(oscope);
	}
	
	public void ExitBlock()
	{
		OneScope oscope = blockstack.pop();
		int scopeid = oscope.getID();
		boolean isclass = oscope.isIsclass();
		if (isclass)
		{
			classstack.pop();
		}
		
		vhcpm.AScopeDestroyed(oscope);
		vlpm.AScopeDestroyed(oscope);
		vvopm.AScopeDestroyed(oscope);
		
		LinkedList<String> datalist = mScopeDataMap.get(oscope);
		if (datalist != null)
		{
			Iterator<String> itr = datalist.iterator();
			while (itr.hasNext())
			{
				String data = itr.next();
				// mDataLineMap.get(data).removeFirst();
				OneScope storedscope = mDataScopeMap.get(data).removeFirst();
				if (storedscope.getID() != scopeid)
				{
					System.err.println("Inconsist scope id. The program will exit.");
					System.err.println("storedscopeid:"+storedscope.getID()+";scopeid:"+scopeid+";isclass:"+isclass+";");
					new Exception("Inconsist scope id").printStackTrace();
					System.exit(1);
				}
			}
			mScopeDataMap.remove(oscope);
			mScopeDataKindMap.remove(oscope);
		}
	}
	
	public void ResetCurrentClassField()
	{
		OneScope classscope = classstack.peek();
		vvopm.ResetClassScope(classscope);
	}
	
	private String GetDataExactOffset(OneScope dataScope, String data, KindHint hintkind)
	{
		Integer kinds = mScopeDataKindMap.get(dataScope).get(data);
		Integer kind = KindLibrary.ExtractKindAccordingToHint(kinds, hintkind);
		String kindstr = KindLibrary.GetCorrespondKindString(kind);
		VDataPoolManager vdpm = KindLibrary.ChooseManagerAccordingToKind(kindstr, vhcpm, vlpm, vvopm);
		Integer exactoffset = vdpm.GetDataExactOffset(kindstr, dataScope, data);
		if (exactoffset == null || blockstack.getSize() == 0)
		{
			if (blockstack.getSize() == 0)
			{
				System.err.println("What the fuck, data does not have scope? The system will exit.");
				System.exit(1);
			}
			return null;
		}
		OneScope currentscope = blockstack.getScope(blockstack.getSize()-1);
		return (dataScope.getLevel() - currentscope.getLevel())+"/"+OffsetLibrary.GetOffsetDescription(exactoffset);
	}
	
	private IndexValuePair GetLastIdInList(String data)
	{
		//testing
		/*if (mDataScopeMap.get(data) == null)
		{
			System.out.println("ERROR DATA IN GET LAST ID:"+data);
		}*/
		LinkedList<OneScope> list = mDataScopeMap.get(data);
		if (list == null || list.size() == 0)
		{
			return null;
		}
		return new IndexValuePair(list.getFirst());
	}
	
	//Due to list is reverse order, so which means first.
	private IndexValuePair GetLastClassIdInList(String data)
	{
		LinkedList<OneScope> list = mDataScopeMap.get(data);
		Iterator<OneScope> itr = list.iterator();
		while (itr.hasNext())
		{
			OneScope temp = itr.next();
			if (temp.isIsclass())
			{
				return new IndexValuePair(temp);
			}
		}
		return null;
	}
	
	private class IndexValuePair {
		private OneScope scope = null;
		public IndexValuePair(OneScope scope)
		{
			this.scope = scope;
		}
		public OneScope getScope() {
			return scope;
		}
	}

	public boolean ContainsScope(Integer equid) {
		return blockstack.isIdContained(equid);
	}

	public int GetFirstClassId() {
		return classstack.getScope(0).getID();
	}
	
}