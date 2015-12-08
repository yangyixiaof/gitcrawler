package cn.yyx.research.language.Utility;

public class jclasstest {
	public jclasstest() {
	}
	
	class ytest {
		int  j = 0;
	}
}


class ttest {
	int i = 0;
	public void TMethod()
	{
		int[] arr = new int[10];
		int[] arr2 = {1,2,3,4,5};
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