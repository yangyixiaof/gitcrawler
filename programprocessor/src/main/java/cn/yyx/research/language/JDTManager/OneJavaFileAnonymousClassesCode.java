package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;

import cn.yyx.research.language.simplified.JDTManager.JavaCode;

public class OneJavaFileAnonymousClassesCode extends JavaCode {
	
	int level = -1;
	String mwcontent = null;
	ArrayList<String> codelist = null;
	
	public OneJavaFileAnonymousClassesCode(int level) {
		this.level = level;
	}
	
	public void AddPreDeclrations(MethodWindow mw)
	{
		mwcontent = mw.toString();
		codelist = mw.toList();
	}
	
	public void AddOneMethodNodeCode(NodeCode nc)
	{
		if (mwcontent != null)
		{
			sb.append(mwcontent);
			codes.addAll(codelist);
		}
		AddOneNodeCode(nc);
	}
	
	public ArrayList<String> toList()
	{
		ArrayList<String> result = new ArrayList<String>();
		if (codes.size() == 0 || (codelist.size() > 0 && !codes.get(0).equals(codelist.get(0))))
		{
			result.addAll(codelist);
			result.addAll(codes);
			return result;
		}
		return super.toList();
	}
	
	public void OneSentenceEnd()
	{
		/*System.err.println(sb);
		if (sb.length() == 0 || sb.charAt(sb.length()-1) != '.')
		{
			sb.append(".");
		}*/
	}
	
}