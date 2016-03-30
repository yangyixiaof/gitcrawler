package cn.yyx.research.language.simplified.JDTManager;

import java.util.Map;
import java.util.Set;

import cn.yyx.research.language.JDTManager.EnteredScopeStack;
import cn.yyx.research.language.JDTManager.JCScope;
import cn.yyx.research.language.JDTManager.OneScope;
import cn.yyx.research.language.JDTManager.VDataPool;

public class ScopeOffsetRefHandler {
	
	EnteredScopeStack classstack = null;
	
	VDataPool fvdp = null;
	VDataPool cvdp = null;
	
	JCScope cjcs = null;
	JCScope ljcs = null;
	
	public ScopeOffsetRefHandler(EnteredScopeStack classstack, VDataPool fvdp, VDataPool cvdp, JCScope cjcs, JCScope ljcs)
	{
		this.classstack = classstack;
		this.fvdp = fvdp;
		this.cvdp = cvdp;
		this.cjcs = cjcs;
		this.ljcs = ljcs;
	}
	
	public String HandleTypeRef(int offset)
	{
		Map<String, String> res = cjcs.GetContentAccordingToOffset(offset);
		Set<String> keys = res.keySet();
		String key = keys.iterator().next();
		return res.get(key);
	}
	
	public String HandleLabelRef(int offset)
	{
		Map<String, String> res = ljcs.GetContentAccordingToOffset(offset);
		Set<String> keys = res.keySet();
		String key = keys.iterator().next();
		return res.get(key);
	}
	
	public Map<String, String> HandleFieldVariableRef(int scope, int offset)
	{
		OneScope sp = classstack.GetScopeAccordingToScopeOffset(scope);
		JCScope fcs = fvdp.GetJCScope(sp.getID());
		return fcs.GetContentAccordingToOffset(offset);
	}
	
	public Map<String, String> HandleCommonVariableRef(int scope, int offset)
	{
		OneScope sp = classstack.GetScopeAccordingToScopeOffset(scope);
		JCScope ccs = cvdp.GetJCScope(sp.getID());
		return ccs.GetContentAccordingToOffset(offset);
	}
	
	public String GenerateNewDeclaredVariable(String name, String type)
	{
		String modifiedname = cvdp.GenerateModifiedName(classstack.peek(), name, type);
		cvdp.NewlyAssignedData(classstack.peek(), modifiedname, type);
		return modifiedname;
	}
	
	public void DeleteRecentlyAddedType(String type)
	{
		cvdp.DeleteRecentlyAddedType(classstack.peek(), type);
	}
	
}