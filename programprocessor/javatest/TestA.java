package cn.yyx.research.language.Utility;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cn.yyx.research.language.programprocessor.Test;

public class TestA<T, M> extends Test {
	Map<Integer, String> a, b;
	T tt;

	{
		a = null;
		b = null;
	} // this is comment

	public TestA(Map<Integer, String> a) {
		this.a = a;
		super.q.b = null;
		super.p.c();
		super.g(foo()).h = null;
		super(a);
		final int d = 3;
	}

	public POI foo() {
		return new POI();
	}

	public int foo(int b, ArrayList<Object> t) {
		a.put(1, "POD");
		Set<?> s;
		int ty = 0;
		synchronized (s) {
			ty--;
		}
		do {
			ty = ty + 1;
		} while (ty < 10);
		while (ty >= 0) {
			ty--;
		}
		for (int xjj = 0; xjj < 1000; xjj++) {
			++ty;
		}
		Map<Integer, String> ppt;
		int[] pt = new int[] { 1, 2, 3 };
		for (int pppt : pt) {
			System.out.println(pppt);
		}
		int d = 3 + 6;
		new Runnable() {
			int a = 0;

			{
				a = 1;
			}

			public void run() {
				System.out.println("hhaahaha !!! wo !!!");
			}
		};
		int[] t = { 1, 2, 3, 4, 5 };
		if (t instanceof int[]) {
			t[0] = 100;
		}
		boolean ga = true;
		if (ga == false) {
			t[2] = t[1] > 0 ? t[3] : t[4];
		}
		PK pk = new PK(1, 2, 3, "dssdsd");
		Object o = new Object();
		Entry e = (Entry) o;
		POI poi = new POI();
		T.c(poi.a.b);
		System.out.println(foi(A.b()).bar().bt);
		poi.abc = 100;
		int y1 = 4;
		int y2 = 4;
		switch (y1) {
		case 1:
			System.exit(1);
			break;
		default:
			System.exit(0);
			break;
		}
		STE.df() = null;
		t[y1 + y2 + y2 * y1 / y2 + y1] += 10;
		int[] p = new int[10];
		char a = 's';
		System.out.println("foo");
		File f = new File("temp.txt");
		try {
			f.createNewFile();
			throw new Exception("This is an exception.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (5 + 6) / 10;
	}
}