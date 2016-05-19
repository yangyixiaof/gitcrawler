package cn.yyx.research.language.simplified.JDTManager;

public class ForBlockState {
	
	private boolean oneempty = false;
	private boolean twoempty = false;
	private boolean threeempty = false;
	
	public ForBlockState(boolean oneempty, boolean twoempty, boolean threeempty) {
		this.setOneempty(oneempty);
		this.setTwoempty(twoempty);
		this.setThreeempty(threeempty);
	}
	
	public boolean isOneempty() {
		return oneempty;
	}
	
	public void setOneempty(boolean oneempty) {
		this.oneempty = oneempty;
	}
	
	public boolean isTwoempty() {
		return twoempty;
	}
	
	public void setTwoempty(boolean twoempty) {
		this.twoempty = twoempty;
	}
	
	public boolean isThreeempty() {
		return threeempty;
	}
	
	public void setThreeempty(boolean threeempty) {
		this.threeempty = threeempty;
	}
	
}