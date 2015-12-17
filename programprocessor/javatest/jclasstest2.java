package cn.yyx.research.language.Utility;

public class jclasstest {
	public void TMethod()
	{
		Runnable r = new Runnable() {
			@Override
			public void run() {
				int[] arr = new int[10];
				int[] arr2 = {1,2,3,4,5};
				System.out.println("heihei!" + arr[2] + arr2[3]);
			}
		};
		new Thread(r).start();
		
	}
}