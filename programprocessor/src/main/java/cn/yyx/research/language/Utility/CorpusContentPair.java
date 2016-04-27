package cn.yyx.research.language.Utility;

public class CorpusContentPair {
	private String Corpus = null;
	private String Content = null;
	private int words = -1;
	
	public CorpusContentPair(String pCorpus, String pContent, int words) {
		setCorpus(pCorpus);
		setContent(pContent);
		this.setWords(words);
	}

	public String getCorpus() {
		return Corpus;
	}

	public void setCorpus(String corpus) {
		Corpus = corpus;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}
	
}