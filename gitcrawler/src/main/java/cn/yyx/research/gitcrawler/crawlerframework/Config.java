package cn.yyx.research.gitcrawler.crawlerframework;

public class Config {

	private int starmin = -1;
	private int starmax = -1;
	private String language = null;
	
	public Config(int pstarmin, int pstarmax, String planguage) {
		starmin = pstarmin;
		starmax = pstarmax;
		language = planguage;
	}
	
	public int getStarmin() {
		return starmin;
	}
	public void setStarmin(int starmin) {
		this.starmin = starmin;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getStarmax() {
		return starmax;
	}
	public void setStarmax(int starmax) {
		this.starmax = starmax;
	}
	
}