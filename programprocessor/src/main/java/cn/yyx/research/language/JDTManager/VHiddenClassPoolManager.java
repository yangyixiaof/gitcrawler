package cn.yyx.research.language.JDTManager;

public class VHiddenClassPoolManager extends VDataPoolManager{
	
	public static final String ClassHiddenPool = "classHidden";
	
	public VHiddenClassPoolManager() {
		dataPools.put(ClassHiddenPool, new VDataPool());
	}
}