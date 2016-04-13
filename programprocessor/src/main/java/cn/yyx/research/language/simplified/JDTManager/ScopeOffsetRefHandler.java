package cn.yyx.research.language.simplified.JDTManager;

import java.util.List;
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
	
	public String GenerateNewDeclaredVariable(String name, String type, List<String> holderlist)
	{
		String modifiedname = null;
		int gap = 5;
		boolean couldstop = false;
		while (!couldstop && gap <= 20)
		{
			gap += 5;
			modifiedname = cvdp.GenerateModifiedName(classstack.peek(), name, type, gap);
			if (holderlist == null || holderlist.size() == 0)
			{
				return modifiedname;
			}
			if (!(holderlist.contains(modifiedname)))
			{
				return modifiedname;
			}
		}
		return null;
	}
	
	public void NewDeclaredVariable(String name, String type)
	{
		cvdp.NewlyAssignedData(classstack.peek(), name, type);
	}
	
	public void DeleteRecentlyAddedType(String type)
	{
		cvdp.DeleteRecentlyAddedType(classstack.peek(), type);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}