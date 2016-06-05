package cn.yyx.research.language.Utility;

import java.util.Map;

public class ScopeOffsetResult {
	
	private Map<String, String> sor = null;
	private Map<String, Long> sol = null;
	
	public ScopeOffsetResult(Map<String, String> sor, Map<String, Long> sol) {
		this.setSor(sor);
		this.setSol(sol);
	}

	public Map<String, String> getSor() {
		return sor;
	}

	public void setSor(Map<String, String> sor) {
		this.sor = sor;
	}

	public Map<String, Long> getSol() {
		return sol;
	}

	public void setSol(Map<String, Long> sol) {
		this.sol = sol;
	}
	
}