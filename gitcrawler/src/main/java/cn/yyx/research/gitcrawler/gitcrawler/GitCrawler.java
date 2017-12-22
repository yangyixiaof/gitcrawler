package cn.yyx.research.gitcrawler.gitcrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.yyx.research.gitcrawler.crawlerframework.Config;
import cn.yyx.research.gitcrawler.crawlerframework.ICrawler;

public class GitCrawler implements ICrawler{

	int starmin = -1;
	int starmax = -1;
	
	int startbegin = -1;
	int startend = -1;
	
	final int STARRANGE = 16;
	
	String language = null;
	int page = -1;
	boolean hasnext = false;
	
	int sleepidx = 0;
	final int[] sleeps = {5000,10000,15000};
	final int sidxmax= sleeps.length;
	
	public GitCrawler(Config cfg) {
		Initial(cfg);
	}
	
	@Override
	public void Initial(Config cfg) {
		starmin = cfg.getStarmin();
		starmax = cfg.getStarmax();
		
		startbegin = starmin;
		startend = startbegin + STARRANGE - 1;
		
		language = cfg.getLanguage();
		page = 0;
		hasnext = true;
	}

	@Override
	public String NextProjectPage() {
		if (hasnext)
		{
			page++;
			String nexturl = "https://github.com/search?p="+page+"&q=stars%3A"+startbegin+".."+startend+"+language%3A"+language+"&ref=searchresults&type=Repositories&utf8=%E2%9C%93";
			
			System.out.println("hasnext page?" + hasnext);
			System.out.println("download url:" + nexturl);
			
			return nexturl;
		}
		else
		{
			page = 1;
			startbegin = startbegin + STARRANGE;
			
			if (startbegin > starmax)
			{
				System.out.println("The whole process should be over: startbegin:" + startbegin + ";starmax:" + starmax);
				return null;
			}
			
			startend = startbegin + STARRANGE - 1;
			String nexturl = "https://github.com/search?p="+page+"&q=stars%3A"+startbegin+".."+startend+"+language%3A"+language+"&ref=searchresults&type=Repositories&utf8=%E2%9C%93";
			
			System.out.println("hasnext page?" + hasnext);
			System.out.println("download url:" + nexturl);
			
			return nexturl;
		}
	}

	@Override
	public ArrayList<String> ExtractProjectZipLinks(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.err.println("wronged url:" + url);
			System.err.println("May be error 429.");
			try {
				Thread.sleep(600000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
		hasnext = true;
		Elements next = doc.select("a.next_page");
		if (next == null || next.size() == 0)
		{
			hasnext = false;
		}
		ArrayList<String> result = new ArrayList<String>();
		Elements plinks = doc.select("a[class*=v-align-middle]");
		for (Element link : plinks) {
			result.add(link.attr("abs:href").trim()+"/archive/master.zip");
		}
		//example:
		//https://github.com/kaze0/launchy
		//https://github.com/kaze0/launchy/archive/master.zip
		try {
			Thread.sleep(sleeps[sleepidx]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sleepidx++;
		if (sleepidx >= sidxmax)
		{
			sleepidx = 0;
		}
		return result;
	}
	
}