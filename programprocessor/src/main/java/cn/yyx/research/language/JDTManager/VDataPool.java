package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

public class VDataPool {
	
	Map<Integer, JCScope> scopes = new TreeMap<Integer, JCScope>();
	
	public void AScopeCreated(OneScope scope)
	{
		int id = scope.hashCode();
		JCScope jscope = new JCScope(id, scope.getLevel());
		scopes.put(id, jscope);
	}
	
	public void AScopeDestroyed(OneScope scope)
	{
		int id = scope.getID();
		scopes.remove(id);
	}
	
	public void NewlyAssignedData(OneScope scope, String data)
	{
		JCScope dataScope = GetDataScope(scope);
		dataScope.PushNewlyAssignedData(data);
	}
	
	public Integer GetExactDataOffsetInDataOwnScope(OneScope scope, String data)
	{
		JCScope dataScope = GetDataScope(scope);
		return dataScope.GetExactOffset(data);
	}
	
	public void ResetClassScope(OneScope scope) {
		JCScope dataScope = GetDataScope(scope);
		if (dataScope instanceof JClassScope)
		{
			JClassScope jcscope = (JClassScope)dataScope;
			jcscope.ResetScope();
		}
	}
	
	private JCScope GetDataScope(OneScope scope)
	{
		int id = scope.getID();
		Integer scopeid = id;
		if (!scopes.containsKey(scopeid))
		{
			System.err.println("Error! no handled scope? The system will exit.");
			System.exit(1);
		}
		return scopes.get(scopeid);
	}
	
}