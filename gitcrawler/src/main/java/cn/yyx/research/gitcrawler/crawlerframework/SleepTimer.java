package cn.yyx.research.gitcrawler.crawlerframework;

public class SleepTimer {
	
	//Milliseconds
	int minTime = -1;
	int maxTime = -1;
	int timegap = -1;
	int nowTime = -1;
	
	public static final int[] minTimeArray = new int[]{5000,5000,10000,10000};
	public static final int[] maxTimeArray = new int[]{25000,50000,80000,100000};
	public static final int[] gapTimeArray = new int[]{5000,15000,20000,30000};
	
	public static SleepTimer RandomGenerateOne()
	{
		int idx = (int)(Math.random()*4);
		System.out.println("Random timesleeper type : "+idx);
		return new SleepTimer(minTimeArray[idx], maxTimeArray[idx], gapTimeArray[idx]);
	}
	
	public SleepTimer(int pminTime, int pmaxTime, int ptimegap) {
		minTime = pminTime;
		maxTime = pmaxTime;
		timegap = ptimegap;
		nowTime = minTime;
	}
	
	public void Sleep()
	{
		try {
			Thread.sleep(nowTime);
		} catch (InterruptedException e) {
		}
		nowTime += timegap;
		if (nowTime > maxTime)
		{
			nowTime = minTime;
		}
	}
}