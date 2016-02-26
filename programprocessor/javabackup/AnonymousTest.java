package cn.yyx.research.language.Utility;

public class Empty {
	
	public void HeiHei()
	{
		new Runnable() {
			@Override
			public void run() {
				System.out.println("RT");
				new Runnable() {
					@Override
					public void run() {
						System.out.println("run");
						new Runnable() {
							@Override
							public void run() {
								System.out.println("run12345");
							}
						};
						System.out.println("qwer");
					}
				};
				System.out.println("OIU");
			}
		};
	}
	
}