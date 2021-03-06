package cn.yyx.research.language.simplified.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import cn.yyx.research.language.JDTManager.MethodWindow;
import cn.yyx.research.language.JDTManager.OneJavaFileAnonymousClassesCode;

public class AnonymousClassPoolInOneJavaFile {
	
	OneJavaFileAnonymousClassesCode justleaved = null;
	
	OneJavaFileAnonymousClassesCode recently = null;
	
	int anonymmousLevel = -1;
	
	ArrayList<ArrayList<OneJavaFileAnonymousClassesCode>> ojfacclist = new ArrayList<ArrayList<OneJavaFileAnonymousClassesCode>>();
	
	Stack<OneJavaFileAnonymousClassesCode> ojfaccstack = new Stack<OneJavaFileAnonymousClassesCode>();
	
	public boolean IsInAnonymous()
	{
		return ojfacclist.size() > 0;
	}
	
	public OneJavaFileAnonymousClassesCode EnterAnonymousClass(MethodWindow mw)
	{
		anonymmousLevel++;
		int osize = ojfacclist.size();
		if (anonymmousLevel >= osize)
		{
			int addsize = anonymmousLevel - osize + 1;
			for (int i=0;i<addsize;i++)
			{
				ojfacclist.add(new ArrayList<OneJavaFileAnonymousClassesCode>());
			}
		}
		OneJavaFileAnonymousClassesCode nowanonymous = new OneJavaFileAnonymousClassesCode(anonymmousLevel);
		ojfaccstack.push(nowanonymous);
		nowanonymous.AddPreDeclrations(mw);
		recently = nowanonymous;
		ojfacclist.get(anonymmousLevel).add(nowanonymous);
		return nowanonymous;
	}
	
	public OneJavaFileAnonymousClassesCode ExitAnonymousClass()
	{
		justleaved = ojfaccstack.pop();
		if (!ojfaccstack.isEmpty())
		{
			recently = ojfaccstack.peek();
		}
		else
		{
			recently = null;
		}
		return recently;
	}

	public boolean IsEmpty() {
		return ojfacclist.size() == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Iterator<ArrayList<OneJavaFileAnonymousClassesCode>> itr2 = ojfacclist.iterator();
		while (itr2.hasNext())
		{
			ArrayList<OneJavaFileAnonymousClassesCode> jfs = itr2.next();
			Iterator<OneJavaFileAnonymousClassesCode> itr = jfs.iterator();
			while (itr.hasNext())
			{
				OneJavaFileAnonymousClassesCode ojf = itr.next();
				sb.append(ojf.toString());
			}
		}
		return sb.toString();
	}

	public ArrayList<String> GetRecentAnalyseList() {
		return justleaved.toList();
	}

	public int getAllWords() {
		int totalwords = 0;
		Iterator<ArrayList<OneJavaFileAnonymousClassesCode>> itr2 = ojfacclist.iterator();
		while (itr2.hasNext())
		{
			ArrayList<OneJavaFileAnonymousClassesCode> jfs = itr2.next();
			Iterator<OneJavaFileAnonymousClassesCode> itr = jfs.iterator();
			while (itr.hasNext())
			{
				OneJavaFileAnonymousClassesCode ojf = itr.next();
				totalwords += ojf.getAllWords();
			}
		}
		return totalwords;
	}
	
}