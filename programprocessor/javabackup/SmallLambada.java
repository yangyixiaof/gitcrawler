package cn.yyx.research.language.Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallLambada {
	
	public SmallLambada() {
	}
	
	public void test()
	{
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		CommonLibrary.PrintList(names);
		System.out.println("==================spliter line in legend==================");
		String t = "lab";
		int a = 0;
		Collections.sort(names, (c, b) -> {
			System.out.println(t);
			System.out.println("Collections.sort:" + a);
		    return b.compareTo(c);
		});
		CommonLibrary.PrintList(names);
	}
	
	public static void main(String[] args) {
		SmallLambada sl = new SmallLambada();
		sl.test();
	}
	
}