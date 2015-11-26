package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;

public class EnteredScopeStack {
	
	private ArrayList<OneScope> stack = new ArrayList<OneScope>();
	
	public EnteredScopeStack() {
	}
	
	public OneScope peek()
	{
		return getStack().get(getStack().size()-1);
	}
	
	public OneScope pop()
	{
		return getStack().remove(getStack().size()-1);
	}
	
	public void push(int blockid, boolean isclass, int level)
	{
		// System.out.println("pushed id: block : "+blockid);
		getStack().add(new OneScope(blockid, isclass, level));
	}
	
	public int getSize()
	{
		return getStack().size();
	}
	
	public OneScope getScope(int nowindex)
	{
		return getStack().get(nowindex);
	}

	public ArrayList<OneScope> getStack() {
		return stack;
	}

	public void setStack(ArrayList<OneScope> stack) {
		this.stack = stack;
	}
	
	public boolean isIdContained(int hashcode)
	{
		for (int i=0;i<stack.size();i++)
		{
			if (stack.get(i).getID() == hashcode)
			{
				return true;
			}
		}
		return false;
	}
	
}