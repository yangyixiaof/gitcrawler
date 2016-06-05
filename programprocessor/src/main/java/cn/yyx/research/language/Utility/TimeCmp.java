package cn.yyx.research.language.Utility;

public class TimeCmp implements Comparable<TimeCmp> {
	
	String cnt = null;
	Long timestamp = System.currentTimeMillis();
	
	public TimeCmp(String cnt) {
		this.cnt = cnt;
	}
	
	public TimeCmp(String cnt, Long timestamp) {
		this.cnt = cnt;
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(TimeCmp o) {
		int cpres = timestamp.compareTo(o.timestamp);
		if (cpres == 0)
		{
			return cnt.compareTo(o.cnt);
		}
		return cpres;
	}
	
	@Override
	public String toString() {
		return "cnt:" + cnt + ";timestamp:" + timestamp;
	}
	
}