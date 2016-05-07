package cn.yyx.research.language.Utility;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class NormalLibrary {

	public static String normalize(String input) {
		String output = Normalizer.normalize(input, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(output).replaceAll("");
	}
	
}