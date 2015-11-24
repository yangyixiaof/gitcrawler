package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class VDataPool {
	
	Map<Integer, JCScope> scopes = new TreeMap<Integer, JCScope>();
	
	// a node is only equivalent to one node.
	Map<Integer, Integer> equivalentScope = null;
	
	public VDataPool(Map<Integer, Integer> equivalentScopeParam) {
		this.equivalentScope = equivalentScopeParam;
	}
	
	public void AScopeCreated(ASTNode node, int level)
	{
		int id = node.hashCode();
		JCScope scope = new JCScope(id, level);
		Integer equivalentScopeId = equivalentScope.get(id);
		if (equivalentScopeId == null || !scopes.containsKey(equivalentScopeId))
		{
			scopes.put(id, scope);
		}
	}
	
	public void AScopeDestroyed(ASTNode node)
	{
		equivalentScope.remove(node.hashCode());
		int id = node.hashCode();
		scopes.remove(id);
	}
	
	public void NewlyAssignedData(ASTNode scope, String data)
	{
		JCScope dataScope = GetDataScope(scope);
		dataScope.PushNewlyAssignedData(data);
	}
	
	public int GetExactDataOffsetInDataOwnScope(ASTNode scope, String data)
	{
		JCScope dataScope = GetDataScope(scope);
		return dataScope.GetExactOffset(data);
	}
	
	private JCScope GetDataScope(ASTNode scope)
	{
		int id = scope.hashCode();
		Integer scopeid = id;
		Integer equivalentScopeId = equivalentScope.get(id);
		if (equivalentScopeId != null)
		{
			scopeid = equivalentScopeId;
		}
		if (!scopes.containsKey(scopeid))
		{
			System.err.println("Error! no handled scope? The system will exit.");
			System.exit(1);
		}
		return scopes.get(scopeid);
	}
	
}