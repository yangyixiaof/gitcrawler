package cn.yyx.research.language.Utility;

public class TTest extends ParentTest{
	
	public TTest t = null;
	
	public TTest()
	{
		super(0);
		this.t = null;
	}
	
	public TTest(TTest t) {
		this();
		this.t = t;
	}
	
	public void PrintInfo()
	{
		lab:
		while (true)
		{
			int i=0;
			if (i == 0)
			{
				break lab;
			}
			else
			{
				System.out.println("hahahaha.");
			}
		}
		int p = 0;
		int k = 0;
		lab2:
		for (p=0,k=1;p < 100 && k > -10; p++,k--)
		{
			System.out.println(p+" haha "+k);
			continue lab2;
		}
	}
	
}