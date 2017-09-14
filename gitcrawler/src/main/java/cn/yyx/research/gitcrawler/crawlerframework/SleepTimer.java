package cn.yyx.research.gitcrawler.crawlerframework;

public class SleepTimer {
	
	//Milliseconds
	int minTime = -1;
	int maxTime = -1;
	int timegap = -1;
	int nowTime = -1;
	
	public static final int[] minTimeArray = new int[]{15000,15000,110000,110000};
	public static final int[] maxTimeArray = new int[]{125000,150000,180000,1100000};
	public static final int[] gapTimeArray = new int[]{15000,115000,120000,130000};
	
	public static SleepTimer RandomGenerateOne()
	{
		int idx = (int)(Math.random()*4);
		// System.out.println("Random timesleeper type : "+idx);
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