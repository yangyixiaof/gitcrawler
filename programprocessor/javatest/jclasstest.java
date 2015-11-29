package cn.yyx.research.language.Utility;

import cn.yyx.research.language.JDTHelper.var;

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
		
		
		Runnable test = new Runnable("foo", function(done){
            process.nextTick(done);
            process.nextTick(done);
            process.nextTick(done);
            process.nextTick(done);
        });
		test.start();
		
		Runnable test = new Runnable("foo", System::function);
		
		Runnable = new Runnable("foo", <HOLE>)#21/b1
		System::function#25/c0
		
		(Test)node.getExpression()#25/c0#21/c0
		
		test.start();
	}
}