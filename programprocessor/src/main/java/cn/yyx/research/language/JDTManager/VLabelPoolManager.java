package cn.yyx.research.language.JDTManager;

public class VLabelPoolManager extends VDataPoolManager{
	
	public static final String LabelPool = "label";
	
	public VLabelPoolManager() {
		dataPools.put(LabelPool, new VDataPool());
	}
}
