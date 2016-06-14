package HTM;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
		
		/*new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
		// case 2.
		JButton show = new JButton("Show");
		show.addActionListener((e) -> {
			System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});*/
		// List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		// IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		/*JButton browseButton = new JButton("\u6D4F\u89C8");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_browseButton_actionPerformed(e);
			}
		});*/
		// String a = null;
		/*int n = 0;
		for (int i = 0;i < n;i++)
		{
			System.err.println("Ha Ha.");
		}*/
		// long a = 0;
		// if (a > 0) a = System.currentTimeMillis();
		/*Process process = getRuntime().exec("rar v -c- \"" + rarFile + "\"");
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		Vector<String> row = new Vector<String>();
		do {
			String line = sc.nextLine();
			if (line.contains("----------------------")) {
			//	count = (count == 0 ? count + 1 : -1);
				continue;
			}
		} while (sc.hasNext());*/
		if (true){
			System.out.println("erw.");
		} else {
			System.err.println("otu.");
		}
	}
	
}