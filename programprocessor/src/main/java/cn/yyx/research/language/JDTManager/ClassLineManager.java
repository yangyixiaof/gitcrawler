package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

public class ClassLineManager {
	
	Map<String, Integer> mClassLineMap = new TreeMap<String, Integer>();
	
	public ClassLineManager() {
	}
	
	public void AddClassLineInfo(String classname, int line)
	{
		mClassLineMap.put(classname, line);
	}
	
	public Integer GetClassLineInfo(String classname)
	{
		return mClassLineMap.get(classname);
	}
	
}