package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class NodeCodeManager {
	
	Map<Integer, String> mNodeCodeMap = new TreeMap<Integer, String>();
	Map<Integer, Boolean> mNodeHasOccupiedOneLineMap = new TreeMap<Integer, Boolean>();
	Map<Integer, Boolean> mNodeInMutipleLineMap = new TreeMap<Integer, Boolean>();
	Map<Integer, Boolean> mNodeHasContentHolder = new TreeMap<Integer, Boolean>();
	// type should be a / c.
	Map<Integer, Character> mNodeTypeMap = new TreeMap<Integer, Character>();
	
	public NodeCodeManager() {
	}
	
	public void AddASTNodeCode(ASTNode astnode, String generatedCode)
	{
		int astnodehashcode = astnode.hashCode();
		mNodeCodeMap.put(astnodehashcode, generatedCode);
	}
	
	public String GetAstNodeCode(ASTNode astnode)
	{
		int astnodehashcode = astnode.hashCode();
		return mNodeCodeMap.get(astnodehashcode);
	}
	
	public void AddASTNodeHasOccupiedOneLine(ASTNode astnode, Boolean HasOccupiedOneLine)
	{
		int astnodehashcode = astnode.hashCode();
		mNodeHasOccupiedOneLineMap.put(astnodehashcode, HasOccupiedOneLine);
	}
	
	public boolean GetAstNodeHasOccupiedOneLine(ASTNode astnode)
	{
		int astnodehashcode = astnode.hashCode();
		Boolean HasOccupiedOneLine = mNodeHasOccupiedOneLineMap.get(astnodehashcode);
		if (HasOccupiedOneLine == null)
		{
			HasOccupiedOneLine = false;
		}
		return HasOccupiedOneLine;
	}
	
	public void AddASTNodeIfHasContentHolder(ASTNode node, Boolean ifHasContentHolder)
	{
		int astnodehashcode = node.hashCode();
		mNodeHasOccupiedOneLineMap.put(astnodehashcode, ifHasContentHolder);
	}
	
	public boolean GetAstNodeHasContentHolder(ASTNode astnode)
	{
		int astnodehashcode = astnode.hashCode();
		Boolean ifHasContentHolder = mNodeHasContentHolder.get(astnodehashcode);
		if (ifHasContentHolder == null)
		{
			ifHasContentHolder = false;
		}
		return ifHasContentHolder;
	}
	
	public void AddASTNodeInMultipleLine(ASTNode node, Boolean inMutipleLine)
	{
		int astnodehashcode = node.hashCode();
		mNodeHasOccupiedOneLineMap.put(astnodehashcode, inMutipleLine);
	}
	
	public boolean GetAstNodeInMultipleLine(ASTNode astnode)
	{
		int astnodehashcode = astnode.hashCode();
		Boolean inMutipleLine = mNodeHasContentHolder.get(astnodehashcode);
		if (inMutipleLine == null)
		{
			inMutipleLine = false;
		}
		return inMutipleLine;
	}
	
	public void AddNodeType(ASTNode node, Character type)
	{
		mNodeTypeMap.put(node.hashCode(), type);
	}
	
	public Character GetNodeType(ASTNode node)
	{
		return mNodeTypeMap.get(node.hashCode());
	}
	
	// this should be b type.
	public boolean IsNewStatement(ASTNode node)
	{
		return !mNodeTypeMap.containsKey(node.hashCode());
	}
	
}