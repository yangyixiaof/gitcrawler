package cn.yyx.research.gitcrawler.gitcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.yyx.research.gitcrawler.crawlerframework.Config;
import cn.yyx.research.gitcrawler.crawlerframework.CrawlerWorker;
import cn.yyx.research.gitcrawler.crawlerframework.SleepTimer;
import cn.yyx.research.gitcrawler.crawlerframework.ZipDownloader;

/**
 * Hello world!
 *
 */
public class App
{
	int numberOfThreads = 1;
	int starbegin = 1001;
	int starrange = 1;
	String language = "java";
	String dest = "here";
	// String dest = "/home/yangyixiaof/HomeSpace/AllZipFile";
	
	ArrayList<CrawlerWorker> cwlist = new ArrayList<CrawlerWorker>();
	
	public App() {
	}
	
	public void Initial(int pnumberOfThreads, int pstarbegin, int pstarrange, String planguage, String pdest)
	{
		numberOfThreads = pnumberOfThreads;
		starbegin = pstarbegin; 
		starrange = pstarrange;
		language = planguage;
		dest = pdest;
	}
	
	public void StartAllCrawlers()
	{
		int currstar = starbegin;
		ZipDownloader.setRepodir(dest);
		for (int i=0;i<numberOfThreads;i++)
		{
			Config cfg = new Config(currstar,currstar+starrange-1,language);
			currstar += starrange;
			CrawlerWorker cw = new CrawlerWorker(new GitCrawler(cfg), SleepTimer.RandomGenerateOne(), i);
			cwlist.add(cw);
			cw.RunCrawler();
		}
	}
	
	public void StopAllCrawlers()
	{
		ZipDownloader.setRepodir(null);
		for (int i=0;i<numberOfThreads;i++)
		{
			if (cwlist.size() > 0)
			{
				CrawlerWorker cw = cwlist.get(i);
				cw.StopCrawler();
			}
		}
		cwlist.clear();
	}
	
    public static void main(String[] args)
    {
    	App app = new App();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String cmd = null;
    	boolean stop = false;
    	try {
			while ((cmd = br.readLine()) != null && !stop)
			{
				switch (cmd) {
				case "start":
					app.StartAllCrawlers();
					break;
				case "stop":
					app.StopAllCrawlers();
					stop = true;
					break;
				default:
					cmd = cmd.trim();
					if (cmd.startsWith("initial "))
					{
						String cnt = cmd.substring("initial ".length(), cmd.length());
						String[] cntsps = cnt.split(":");
						if (cntsps.length != 5)
						{
							System.out.println("Invalid initial command.");
						}
						else
						{
							//para1 : numberOfThreads;
							//para2 : starbegin;
							//para3 : starrange;
							//para4 : language;
							//para5 : dest directory;
							// example: initial 10:50:5:java:~/HomeSpace/AllZipFile
							app.Initial(Integer.parseInt(cntsps[0]), Integer.parseInt(cntsps[1]), Integer.parseInt(cntsps[2]), cntsps[3], cntsps[4]);
							System.out.println("The crawler has been initialized successfully.");
						}
						break;
					}
					else
					{
						System.out.println("Unknown command.");
					}
					break;
				}
				if (stop)
				{
					break;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}