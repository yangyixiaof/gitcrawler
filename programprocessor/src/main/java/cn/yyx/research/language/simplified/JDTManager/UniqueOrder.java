package cn.yyx.research.language.simplified.JDTManager;

public class UniqueOrder {
	
	private static int order = 0;
	
	public static void Reset()
	{
		order = 0;
	}
	
	public static int AllocateOrder()
	{
		order++;
		return order;
	}
	
	public static int CurrentOrder()
	{
		return order;
	}
	
}