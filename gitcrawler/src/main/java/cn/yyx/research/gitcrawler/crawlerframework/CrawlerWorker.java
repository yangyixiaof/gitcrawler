package cn.yyx.research.gitcrawler.crawlerframework;

import java.util.ArrayList;

public class CrawlerWorker implements Runnable {
	
	Thread selfThread = null;
	ICrawler onecrawler = null;
	boolean running = false;
	boolean stopped = false;
	SleepTimer sleeptimer = null;
	int ID = -1;
	
	int downloadcount = 0;
	final int downloadmax = 50;
	final int longsleeptime = 2800000;
	
	public CrawlerWorker(ICrawler crawler, SleepTimer stimer, int pID) {
		onecrawler = crawler;
		sleeptimer = stimer;
		ID = pID;
	}
	
	public void RunCrawler()
	{
		running = true;
		stopped = false;
		selfThread = new Thread(this);
		selfThread.start();
	}

	@Override
	public void run() {
		String nexturl = null;
		while ((nexturl = onecrawler.NextProjectPage()) != null && running)
		{
			ArrayList<String> ziplinks = onecrawler.ExtractProjectZipLinks(nexturl);
			if (ziplinks != null)
			{
				for (String zlink : ziplinks)
				{
					String filename = ExtractFileNameFromLink(zlink);
					System.out.println("Downloading file : "+filename);
					ZipDownloader.downLoadZip(zlink, filename);
					downloadcount++;
					if (!running)
					{
						break;
					}
					sleeptimer.Sleep();
					if (!running)
					{
						break;
					}
				}
			}
			if (downloadcount >= downloadmax)
			{
				downloadcount = 0;
				try {
					Thread.sleep(longsleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		stopped = true;
	}
	
	private String ExtractFileNameFromLink(String link)
	{
		int pos1 = link.lastIndexOf('/', link.length());
		int pos2 = link.lastIndexOf('/', pos1-1);
		int pos3 = link.lastIndexOf('/', pos2-1);
		int pos4 = link.lastIndexOf('/', pos3-1);
		String name1 = link.substring(pos4+1, pos3);
		String name2 = link.substring(pos3+1, pos2);
		return name1+"_"+name2+".zip";
	}
	
	public void StopCrawler()
	{
		running = false;
		selfThread.interrupt();
		while (!stopped)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread " + ID + " stopped.");
		selfThread = null;
	}
}