package cn.yyx.research.gitcrawler.gitcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.yyx.research.gitcrawler.crawlerframework.Config;
import cn.yyx.research.gitcrawler.crawlerframework.CrawlerWorker;
import cn.yyx.research.gitcrawler.crawlerframework.SleepTimer;

/**
 * Hello world!
 *
 */
public class App
{
	int numberOfThreads = 1;
	int starbegin = 134;
	int starrange = 1;
	String language = "java";
	
	ArrayList<CrawlerWorker> cwlist = new ArrayList<CrawlerWorker>();
	
	public App() {
	}
	
	public void Initial(int pnumberOfThreads, int pstarbegin, int pstarrange, String planguage)
	{
		numberOfThreads = pnumberOfThreads;
		starbegin = pstarbegin; 
		starrange = pstarrange;
		language = planguage;
	}
	
	public void StartAllCrawlers()
	{
		int currstar = starbegin;
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
		for (int i=0;i<numberOfThreads;i++)
		{
			
			CrawlerWorker cw = cwlist.get(i);
			cw.StopCrawler();
		}
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
						if (cntsps.length != 4)
						{
							System.out.println("Invalid initial command.");
						}
						else
						{
							//para1 : numberOfThreads;
							//para2 : starbegin;
							//para3 : starrange;
							//para4 : language;
							app.Initial(Integer.parseInt(cntsps[0]), Integer.parseInt(cntsps[1]), Integer.parseInt(cntsps[2]), cntsps[3]);
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