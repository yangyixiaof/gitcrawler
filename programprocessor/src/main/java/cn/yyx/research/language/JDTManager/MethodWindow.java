package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MethodWindow {
	
	public static final int WindowSize = 6;
	Queue<String> rawmethodnames = new LinkedList<String>();
	
	public void OneTypeDeclared(String type){
	}
	
	public void PushMethodName(String methodname)
	{
		// System.out.println("methodname:" + methodname);
		//if (methodname.equals(""))
		//{
		//	new Exception().printStackTrace();
		//}
		
		if (rawmethodnames.size() < WindowSize)
		{
			rawmethodnames.add(methodname);
		}
		else
		{
			rawmethodnames.poll();
			rawmethodnames.add(methodname);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append(" " + GCodeMetaInfo.AnonymousClassBegin + "AnonymousBegin");
		Iterator<String> itr = rawmethodnames.iterator();
		while (itr.hasNext())
		{
			String mname = itr.next();
			sb.append(" " + GCodeMetaInfo.AnonymousClassPreHint + mname);
		}
		return sb.toString();
	}

	public ArrayList<String> toList() {
		ArrayList<String> codes = new ArrayList<String>();
		codes.add(GCodeMetaInfo.AnonymousClassBegin + "AnonymousBegin");
		Iterator<String> itr = rawmethodnames.iterator();
		while (itr.hasNext())
		{
			String mname = itr.next();
			codes.add(GCodeMetaInfo.AnonymousClassPreHint + mname);
		}
		return codes;
	}

	public void Clear() {
		rawmethodnames.clear();
	}
	
	/*public static void main(String[] args) {
		MethodWindow mw = new MethodWindow();
		mw.PushMethodName("sddas1");
		mw.PushMethodName("sddas2");
		mw.PushMethodName("sddas3");
		System.err.println(mw);
	}*/
	
}