package cn.yyx.research.language.Utility;

public class jclasstest {
	int p = 0;
	public jclasstest() {
	}
	class Test
	{
		int j = 0;
	}
}

class ttest {
	int i = 0;
	public void TMethod()
	{
		int[] arr = new int[10];
		int[] arr2 = {1,2,3,4,5};
		i = -i;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("heihei!" + arr[2] + arr2[3]);
			}
		};
		new Thread(r).start();
		System.out.println("haha haha!");
	}
}