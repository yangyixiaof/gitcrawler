package LambadaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Utility.CommonLibrary;

public class SmallLambada {
	
	public SmallLambada() {
	}
	
	public void test()
	{
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		CommonLibrary.PrintList(names);
		System.out.println("==================spliter line in legend==================");
		Collections.sort(names, (a, b) -> {
		    return b.compareTo(a);
		});
		CommonLibrary.PrintList(names);
	}
	
	public static void main(String[] args) {
		SmallLambada sl = new SmallLambada();
		sl.test();
	}
	
}