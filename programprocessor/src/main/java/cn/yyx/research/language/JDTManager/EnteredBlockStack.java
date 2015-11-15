package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;

public class EnteredBlockStack {
	
	private ArrayList<Integer> stack = new ArrayList<Integer>();
	
	public EnteredBlockStack() {
	}
	
	public int peek()
	{
		return getStack().get(getStack().size()-1);
	}
	
	public int pop()
	{
		return getStack().remove(getStack().size()-1);
	}
	
	public void push(int blockid)
	{
		// System.out.println("pushed id: block : "+blockid);
		getStack().add(blockid);
	}
	
	public int getSize()
	{
		return getStack().size();
	}
	
	public int getBlockId(int nowindex)
	{
		return getStack().get(nowindex);
	}

	public ArrayList<Integer> getStack() {
		return stack;
	}

	public void setStack(ArrayList<Integer> stack) {
		this.stack = stack;
	}
	
}