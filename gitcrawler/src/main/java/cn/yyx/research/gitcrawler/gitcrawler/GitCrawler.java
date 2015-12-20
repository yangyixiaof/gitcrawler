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
	String language = null;
	int page = -1;
	boolean hasnext = false;
	
	public GitCrawler(Config cfg) {
		Initial(cfg);
	}
	
	@Override
	public void Initial(Config cfg) {
		starmin = cfg.getStarmin();
		starmax = cfg.getStarmax();
		language = cfg.getLanguage();
		page = 0;
		hasnext = true;
	}

	@Override
	public String NextProjectPage() {
		if (hasnext)
		{
			page++;
			String nexturl = "https://github.com/search?p="+page+"&q=stars%3A"+starmin+".."+starmax+"+language%3A"+language+"&ref=searchresults&type=Repositories&utf8=%E2%9C%93";
			return nexturl;
		}
		return null;
	}

	@Override
	public ArrayList<String> ExtractProjectZipLinks(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.err.println("wronged url:" + url);
			e.printStackTrace();
			return null;
		}
		hasnext = true;
		Elements next = doc.select("a.next_page");
		if (next == null)
		{
			hasnext = false;
		}
		ArrayList<String> result = new ArrayList<String>();
		Elements plinks = doc.select("h3.repo-list-name > a");
		for (Element link : plinks) {
			result.add(link.attr("abs:href").trim()+"/archive/master.zip");
		}
		//example:
		//https://github.com/kaze0/launchy
		//https://github.com/kaze0/launchy/archive/master.zip
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

}