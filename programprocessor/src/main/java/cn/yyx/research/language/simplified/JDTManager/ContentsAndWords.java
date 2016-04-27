package cn.yyx.research.language.simplified.JDTManager;

public class ContentsAndWords {
	
	private String content = null;
	private int words = -1;
	
	public ContentsAndWords(String content, int words) {
		this.setContent(content);
		this.setWords(words);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}
	
}