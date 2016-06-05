package cn.yyx.research.language.simplified.JDTManager;

public class TimeCmp implements Comparable<TimeCmp> {
	
	private TypeVar cnt = null;
	private Long timestamp = System.currentTimeMillis();
	
	public TimeCmp(TypeVar cnt, Long timestamp) {
		this.setCnt(cnt);
		this.setTimestamp(timestamp);
	}

	@Override
	public int compareTo(TimeCmp o) {
		int cpres = getTimestamp().compareTo(o.getTimestamp());
		if (cpres == 0)
		{
			return getCnt().getType().compareTo(o.getCnt().getType());
		}
		return -cpres;
	}
	
	@Override
	public String toString() {
		return "cnt:" + getCnt() + ";timestamp:" + getTimestamp();
	}

	public TypeVar getCnt() {
		return cnt;
	}

	public void setCnt(TypeVar cnt) {
		this.cnt = cnt;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}