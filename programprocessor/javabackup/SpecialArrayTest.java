package cn.yyx.research.language.Utility;

public class SpecialArrayTest {
	public SpecialArrayTest() {
	}
	
	public void test()
	{
		int[][][] a = new int[23][54][78];
		a[GetNum()][GetNum()][GetNum()] = 100;
	}
	
	public int GetNum()
	{
		return 1;
	}
	
}
