package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;

public class EnteredMarkerStack {
	
	private ArrayList<Integer> stack = new ArrayList<Integer>();
	
	public EnteredMarkerStack() {
	}
	
	public int peek()
	{
		return getStack().get(getStack().size()-1);
	}
	
	public int pop()
	{
		return getStack().remove(getStack().size()-1);
	}
	
	public void push(int markernodeid)
	{
		// System.out.println("pushed id: block : " + markernodeid);
		getStack().add(markernodeid);
	}
	
	public int getSize()
	{
		return getStack().size();
	}
	
	public ArrayList<Integer> getStack() {
		return stack;
	}
	
	public void setStack(ArrayList<Integer> stack) {
		this.stack = stack;
	}
	
}