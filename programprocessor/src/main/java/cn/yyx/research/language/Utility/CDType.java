package cn.yyx.research.language.Utility;

public class CDType {
	
	private String simplifiedversion = null;
	private String complexversion = null;
	
	public CDType(String simplifiedversion, String complexversion) {
		this.setSimplifiedversion(simplifiedversion);
		this.setComplexversion(complexversion);
	}
	
	public void appendSomething(String something)
	{
		simplifiedversion += something;
		complexversion += complexversion;
	}

	public String getSimplifiedversion() {
		return simplifiedversion;
	}

	public void setSimplifiedversion(String simplifiedversion) {
		this.simplifiedversion = simplifiedversion;
	}

	public String getComplexversion() {
		return complexversion;
	}

	public void setComplexversion(String complexversion) {
		this.complexversion = complexversion;
	}
	
	@Override
	public String toString() {
		return simplifiedversion;
	}
	
}