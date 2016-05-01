package cn.yyx.research.language.Utility;

public class StringUtil {
	
	public static String GenerateDuplicates(String onepiece, int times)
	{
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<times;i++)
		{
			sb.append(onepiece);
		}
		return sb.toString();
	}
	
}