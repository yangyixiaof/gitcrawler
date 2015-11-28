package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class NodeTypeManager {
	
	// type should be a / c.
	Map<Integer, Character> nodeTypeMap = new TreeMap<Integer, Character>();
	
	public NodeTypeManager() {
	}
	
	public void AddNodeType(ASTNode node, Character type)
	{
		nodeTypeMap.put(node.hashCode(), type);
	}
	
	public Character GetNodeType(ASTNode node)
	{
		return nodeTypeMap.get(node.hashCode());
	}
	
	// this should be b type.
	public boolean IsNewStatement(ASTNode node)
	{
		return !nodeTypeMap.containsKey(node.hashCode());
	}
	
}