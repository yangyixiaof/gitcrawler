package cn.yyx.research.language.JDTManager;

public class VVarObjPoolManager extends VDataPoolManager{
	
	public static final String ConstantPool = "constant";
	public static final String VarOrObjPool = "varOrObj";
	
	public VVarObjPoolManager() {
		dataPools.put(ConstantPool, new VDataPool());
		dataPools.put(VarOrObjPool, new VDataPool());
	}
}