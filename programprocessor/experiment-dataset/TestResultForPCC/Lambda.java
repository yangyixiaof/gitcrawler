package test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;

public class Lambda {

	public void Hoio() {
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
		JButton show = new JButton("Show");
		// right 2
		show.addActionListener((Ia)->{
			// right 1
			System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
		});
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(System.out::println);
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		// right 1
		System.out.println("Languages which starts with J :");
		// right 3
		filter(languages, (str) -> ((String) str).startsWith("J"));
		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> ((String) str).endsWith("a"));
		System.out.println("Print all languages :");
		// right 2
		filter(languages, (str) -> true);
		System.out.println("Print no language : ");
		// right 2
		filter(languages, (str) -> false);
		System.out.println("Print language whose length greater than 4:");
		// right 2
		filter(languages, (str) -> ((String) str).length() > 4);
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		// right 3
		costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
		List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax2.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
		// right 1
		System.out.println("Total : " + bill);
		final int ip = 2;
		// right 3
		List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		List<String> filtered = strList.stream().filter(x -> x.length() > ip).collect(Collectors.toList());
		// right 1
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
	}

	public static void filter(List<String> names, Predicate<String> condition) {
		// right 1
		for (String name : names) {
			// right 1
			if (condition.test(name)) {
				// right 1
				System.out.println(name + " ");
			}
		}
	}

}