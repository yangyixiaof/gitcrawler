package cn.yyx.research.language.programprocessor;

import junit.framework.TestCase;

public class Test extends TestCase {
	
	public void testApp() {
		String b = "po";
		System.out.println(b);
		PushAdd(b);
		System.out.println(b);
		assertTrue(true);
	}
	
	public void PushAdd(String a)
	{
		a += " test.";
	}
	
}