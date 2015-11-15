package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

public class NodeCodeManager {
	
	Map<Integer, String> mNodeCodeMap = new TreeMap<Integer, String>();
	
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
	
}