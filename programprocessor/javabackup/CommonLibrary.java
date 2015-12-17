package cn.yyx.research.language.Utility;

import java.util.List;

public class CommonLibrary {

	public static void PrintList(List<String> names) {
		TTest t1 = new TTest(null);
		TTest t2 = new TTest(t1);
		TTest t3 = new TTest(t2);
		for (String name : names)
		{
			t3.t.t.PrintInfo();
			System.out.println(name);
		}
	}
	
}