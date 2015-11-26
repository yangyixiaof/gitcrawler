package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;

public class JClassScope extends JCScope {
	
	// Map<String, Boolean> preservedData = new TreeMap<String, Boolean>();
	ArrayList<String> preservedData = new ArrayList<String>();
	
	public JClassScope(int id, int level) {
		super(id, level);
	}
	
	@Override
	public void PushNewlyAssignedData(String data) {
		PreserveData(data);
		super.PushNewlyAssignedData(data);
	}
	
	private void PreserveData(String data) {
		preservedData.add(data);
	}
	
	public void ResetScope() {
		ClearAll();
		Iterator<String> itr = preservedData.iterator();
		while (itr.hasNext())
		{
			String key = itr.next();
			PushNewlyAssignedData(key);
		}
	}
	
}