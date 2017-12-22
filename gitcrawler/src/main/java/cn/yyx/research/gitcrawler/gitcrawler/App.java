package cn.yyx.research.gitcrawler.gitcrawler;

import java.io.File;
import java.util.ArrayList;

import cn.yyx.research.gitcrawler.crawlerframework.*;

/**
 * Hello world!
 */
public class App {
    int numberOfThreads = 1;
    int starbegin = 1005;
    int starrange = 16;
    // int starbegin = 100;
    // int starrange = 1000;
    String language = "java";
    String dest = "here";
    // String dest = "/home/yangyixiaof/HomeSpace/AllZipFile";
    // String dest = "/home/yyx/HomeSpace/AllZipPool";

    ArrayList<CrawlerWorker> cwlist = new ArrayList<CrawlerWorker>();

    public App() {
    }

    public void Initial(int pnumberOfThreads, int pstarbegin, int pstarrange, String planguage, String pdest) {
        numberOfThreads = pnumberOfThreads;
        starbegin = pstarbegin;
        starrange = pstarrange;
        language = planguage;
        dest = pdest;
    }

    public void StartAllCrawlers() {
        int currstar = starbegin;
        ZipDownloader.setRepodir(dest);
        for (int i = 0; i < numberOfThreads; i++) {
            Config cfg = new Config(currstar, currstar + starrange - 1, language);
            currstar += starrange;
            CrawlerWorker cw = new CrawlerWorker(new GitCrawler(cfg, 1), SleepTimer.RandomGenerateOne(), i);
            cwlist.add(cw);
            //cw.RunCrawler();
        }
    }

    public void StopAllCrawlers() {
        ZipDownloader.setRepodir(null);
        for (int i = 0; i < numberOfThreads; i++) {
            if (cwlist.size() > 0) {
                CrawlerWorker cw = cwlist.get(i);
                cw.StopCrawler();
            }
        }
        cwlist.clear();
    }



    public static void main(String[] args) {
        ZipDownloader.setRepodir(args[0]);
        while (true) {
            int star = FileUtility.getNumber(FileUtility.starFilePath);
            int page = FileUtility.getNumber(FileUtility.pageFilePath);
            int range = FileUtility.getProperRange(star);
            if (star >= 30000) break;

            Config cfg = new Config(star, star + range - 1, "java");
            CrawlerWorker cw = new CrawlerWorker(new GitCrawler(cfg, page), SleepTimer.RandomGenerateOne(), 1234);
            cw.run();
        }
    }

	/*
    public static void main(String[] args)
    {
    	App app = new App();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String cmd = null;
    	boolean stop = false;
    	try {
			while ((cmd = br.readLine()) != null && !stop)
			{
				if ("start".equals(cmd)) {
					app.StartAllCrawlers();

				} else if ("stop".equals(cmd)) {
					app.StopAllCrawlers();
					stop = true;

				} else {
					cmd = cmd.trim();
					if (cmd.startsWith("initial ")) {
						String cnt = cmd.substring("initial ".length(), cmd.length());
						String[] cntsps = cnt.split(":");
						if (cntsps.length != 5) {
							System.out.println("Invalid initial command.");
						} else {
							//para1 : numberOfThreads;
							//para2 : starbegin;
							//para3 : starrange;
							//para4 : language;
							//para5 : dest directory;
							// example: initial 10:50:5:java:~/HomeSpace/AllZipFile
							app.Initial(Integer.parseInt(cntsps[0]), Integer.parseInt(cntsps[1]), Integer.parseInt(cntsps[2]), cntsps[3], cntsps[4]);
							System.out.println("The crawler has been initialized successfully.");
						}
						//break;
					} else {
						System.out.println("Unknown command.");
					}

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
    */
}