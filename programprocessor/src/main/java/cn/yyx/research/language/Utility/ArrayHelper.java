package cn.yyx.research.language.Utility;

public class ArrayHelper {
	
	public static String JoinArrayInSection(String[] array, int start, int oneafterstop)
	{
		StringBuilder sb = new StringBuilder("");
		for (int i=start;i<oneafterstop;i++)
		{
			sb.append(" " + array[i]);
		}
		return sb.toString();
	}
	
}