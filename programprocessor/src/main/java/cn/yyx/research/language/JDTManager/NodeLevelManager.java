package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class NodeLevelManager {
	
	int level = 0;
	
	Map<Integer, Integer> nodeLevelMap = new TreeMap<Integer, Integer>();
	
	Map<Integer, Boolean> firstNodeAfterDecreasingElement = new TreeMap<Integer, Boolean>();
	
	Map<Integer, Boolean> lastNodeBeforeIncreaseingElement = new TreeMap<Integer, Boolean>();
	
	public NodeLevelManager() {
	}
	
	public void PreVisit(ASTNode node)
	{
		int nid = node.hashCode();
		if (firstNodeAfterDecreasingElement.containsKey(nid))
		{
			firstNodeAfterDecreasingElement.remove(nid);
			level--;
		}
		nodeLevelMap.put(nid, level);
	}
	
	public void PostVisit(ASTNode node)
	{
		int nid = node.hashCode();
		// nodeLevelMap.put(nid, level);
		if (lastNodeBeforeIncreaseingElement.containsKey(nid))
		{
			lastNodeBeforeIncreaseingElement.remove(nid);
			level++;
		}
	}
	
	// The following two functions should be called by ast visit method.
	public void RegistFirstNodeAfterDecreasingElement(ASTNode node) {
		firstNodeAfterDecreasingElement.put(node.hashCode(), true);
	}
	
	public void RegistLastNodeBeforeIncreaseingElement(ASTNode node) {
		lastNodeBeforeIncreaseingElement.put(node.hashCode(), true);
	}

	public void DecreaseLevel() {
		level--;
	}

	public void IncreaseLevel() {
		level++;
	}
	
	public int GetNodeLevel(ASTNode node)
	{
		return nodeLevelMap.get(node.hashCode());
	}
	
}