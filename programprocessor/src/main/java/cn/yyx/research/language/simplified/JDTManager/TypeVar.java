package cn.yyx.research.language.simplified.JDTManager;

public class TypeVar {
	
	private String type = null;
	private String name = null;
	
	public TypeVar(String type, String name) {
		this.setType(type);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}