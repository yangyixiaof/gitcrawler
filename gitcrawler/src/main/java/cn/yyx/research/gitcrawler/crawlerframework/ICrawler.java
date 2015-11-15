package cn.yyx.research.gitcrawler.crawlerframework;

import java.util.ArrayList;

public interface ICrawler {
	public void Initial(Config cfg);
	public String NextProjectPage();
	ArrayList<String> ExtractProjectZipLinks(String url);
}