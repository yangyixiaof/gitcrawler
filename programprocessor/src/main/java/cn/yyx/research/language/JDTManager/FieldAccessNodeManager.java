package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class FieldAccessNodeManager {
	
	Map<Integer, Boolean> fieldnodemap = new TreeMap<Integer, Boolean>();
	
	public FieldAccessNodeManager() {
	}
	
	public void AddFieldAccessNode(ASTNode node)
	{
		fieldnodemap.put(node.hashCode(), true);
	}
	
	public boolean GetFieldAccessNode(int hashcode)
	{
		return fieldnodemap.containsKey(hashcode);
	}
	
}
