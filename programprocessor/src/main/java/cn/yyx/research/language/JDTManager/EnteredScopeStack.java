package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;

import cn.yyx.research.language.simplified.JDTManager.OffsetOutOfScopeException;

public class EnteredScopeStack {
	
	private ArrayList<OneScope> stack = null;
	
	public EnteredScopeStack() {
		stack = new ArrayList<OneScope>();
	}
	
	public EnteredScopeStack(ArrayList<OneScope> stack)
	{
		this.stack = stack;
	}
	
	public OneScope peek()
	{
		return getStack().get(getStack().size()-1);
	}
	
	public OneScope pop()
	{
		return getStack().remove(getStack().size()-1);
	}
	
	public OneScope GetScopeAccordingToScopeOffset(int scope) throws OffsetOutOfScopeException
	{
		int lastidx = stack.size()-1;
		int idx = lastidx - scope;
		if (idx < 0)
		{
			throw new OffsetOutOfScopeException("scope offset out of scope.");
		}
		return stack.get(idx);
	}
	
	public OneScope PushBack(int blockid, int level)
	{
		// System.out.println("pushed id: block : "+blockid);
		OneScope oscope = new OneScope(blockid, level);
		getStack().add(oscope);
		return oscope;
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
	
	public Iterator<OneScope> GetIterator()
	{
		return stack.iterator();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ArrayList<OneScope> oss = new ArrayList<OneScope>();
		Iterator<OneScope> itr = stack.iterator();
		while (itr.hasNext())
		{
			oss.add((OneScope) itr.next().clone());
		}
		return new EnteredScopeStack(oss);
	}
	
}