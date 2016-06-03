package HTM;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class fo {
	
	/*public void haha()
	{
		o().m(a(),p());
	}
	
	public void isString(String sPara){
        int iPLength = sPara.length();
        
	}
	
	protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }*/
	
	/*public void at()
	{
		// int a[] = {1,2};
		// return a[m()];
		A<B> str = null;
		if (str == null)
		{
			System.err.println("haha ererei.");
		}
		List<String> list = new LinkedList<String>();
		Iterator<String> itr = list.iterator();
		// boolean b = false;
		// boolean a = b || m() && f();
	}*/
	
	/*public void show()
	{
		// List<String> list = new LinkedList<String>();
		// Iterator<String> itr = list.iterator();
		int a = 0, b = a + 1;
		boolean c = false;
		while (c == true)
		{
			a = 100;
		}
		int i=0;
		new Runnable() {
			@Override
			public void run() {
				System.out.println(i);
			}
		};
	}*/
	
	public void LambdaTest()
	{
		/*List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
		
		final int ip = 2;
		List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		List<String> filtered = strList.stream().filter(x -> x.length() > ip).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);*/
		
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
		// case 2.
		JButton show = new JButton("Show");
		show.addActionListener((e) -> {
			System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
	}
	
}