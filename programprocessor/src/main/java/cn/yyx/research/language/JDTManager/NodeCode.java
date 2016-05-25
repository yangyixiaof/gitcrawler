package cn.yyx.research.language.JDTManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class NodeCode {
	// once set, no change.
	private int firstCodeLevel = -1;
	private boolean couldAppend = false;
	private boolean mustAppend = false;
	protected Stack<Boolean> argmutiple = new Stack<Boolean>();

	LinkedList<String> codelist = new LinkedList<String>();

	public NodeCode(Stack<Boolean> argmutiple) {
		this.argmutiple = argmutiple;
	}

	public boolean IsEmpty() {
		return codelist.size() == 0;
	}

	public boolean LastCharacterIsDot() {
		if (codelist.size() > 0) {
			String code = codelist.get(codelist.size() - 1);
			if (code.charAt(code.length() - 1) == '.') {
				return true;
			}
		}
		return false;
	}

	public boolean NotInitialize() {
		return getFirstCodeLevel() == -1;
	}
	
	public int RecordCurrentLastIndex()
	{
		return codelist.size()-1;
	}
	
	public void AddOneLineCode(String code, boolean couldappend, boolean mustappend, boolean mustpre, boolean occupyoneline, String preHint) {
		if (code == null || code.equals(""))
		{
			return;
		}
		
		if (codelist.size() > 0)
		{
			if (code.equals("DH@;FI") || code.equals("DH@;FE") || code.equals("DH@;FU"))
			{
				String lastcode = codelist.getLast();
				if (lastcode.equals("DH@for") || lastcode.equals("DH@;FI") || lastcode.equals("DH@;FE") || lastcode.equals("DH@;FU"))
				{
					codelist.add(code);
				}
				else
				{
					if (lastcode.charAt(lastcode.length()-1) == ';')
					{
						lastcode = lastcode.substring(0, lastcode.length()-1);
					}
					lastcode = lastcode + code.substring("DH@".length());
					codelist.removeLast();
					codelist.addLast(lastcode);
				}
				return;
			}
		}
		
		if (codelist.size() > 0)
		{
			boolean shouldreturn = false;
			if (code.equals("DH@" + GCodeMetaInfo.EnterMethodParam))
			{
				shouldreturn = MergeMark(GCodeMetaInfo.EnterMethodParam);
			}
			if (code.equals("DH@{"))
			{
				shouldreturn = MergeMark("{");
			}
			if (code.equals("DH@}"))
			{
				shouldreturn = MergeMark("}");
			}
			if (code.equals("DH@("))
			{
				shouldreturn = MergeMark("(");
			}
			if (code.equals("DH@)"))
			{
				shouldreturn = MergeMark(")");
			}
			if (shouldreturn)
			{
				return;
			}
		}
		
		// debugging
		if (code.trim().equals("'"))
		{
			System.err.println("Wrong error: what is '?");
			System.exit(1);
		}
		if (code.trim().equals("')"))
		{
			System.err.println("Wrong error: what is ')?");
			System.exit(1);
		}
		
		// lastCodeLevel = level;
		/*if (code == null)
		{
			new Exception("AppendOneLineCode Is Null").printStackTrace();
		}
		if (code.equals("null3"))
		{
			System.err.println("codelast:" + codelist.get(codelist.size()-1) + ";1:couldappend:"+couldappend+";mustappend:"+mustappend+";mustpre:"+mustpre+";occupyoneline:"+occupyoneline);
			System.err.println("codelast:" + codelist.get(codelist.size()-1) + ";2:couldappend:"+couldAppend+";mustappend:"+mustAppend);
		}*/
		boolean iscodenewline = false;
		if (couldAppend)
		{
			if (occupyoneline)
			{
				if (mustAppend)
				{
					AppendEndInfoToLast(GCodeMetaInfo.CodeHole);
				}
				if (preHint != null && !preHint.equals(""))
				{
					code = preHint + code;
				}
				codelist.add(code);
				iscodenewline = true;
			}
			else
			{
				AppendEndInfoToLast(code);
			}
		}
		else
		{
			if (mustpre)
			{
				code = GCodeMetaInfo.PreExist + code;
				codelist.add(code);
				iscodenewline = true;
			}
			if (preHint != null && !preHint.equals(""))
			{
				code = preHint + code;
			}
			codelist.add(code);
			iscodenewline = true;
		}
		
		// set couldAppend.
		this.mustAppend = mustappend;
		this.couldAppend = couldappend;
		if (iscodenewline)
		{
			if (argmutiple.size() > 0)
			{
				Boolean mut = argmutiple.pop();
				if (!mut)
				{
					mut = true;
				}
				argmutiple.push(mut);
			}
		}
	}
	
	/**
	 * 
	 * @param mergecontent
	 * @return means whether should return directly in parent.
	 */
	private boolean MergeMark(String mergecontent)
	{
		int idx = codelist.size() - 1;
		String lastcode = codelist.get(idx);
		if (lastcode.startsWith("DH@" + mergecontent))
		{
			lastcode += mergecontent;
			codelist.set(idx, lastcode);
			return true;
		}
		return false;
	}
	
	public void GenerateEndInfo(String lcode)
	{
		if (lcode.charAt(lcode.length()-1) == ';')
		{
			AppendEndInfoToLast(";");
			return;
		}
		final String em = GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EnterMethodParam;
		final String pr = GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfMethodPreRerferedExpression;
		final String ps = GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfMethodArgument;
		String lastcode = GetLastCode();
		if (lcode.equals(pr))
		{
			if (!(lastcode.startsWith(em)))
			{
				AppendEndInfoToLast(GCodeMetaInfo.EndOfMethodPreRerferedExpression);
			}
			return;
		}
		if (lcode.equals(ps))
		{
			if (!(lastcode.startsWith(em)) && !(lastcode.endsWith(GCodeMetaInfo.EndOfMethodPreRerferedExpression)) && !(lastcode.endsWith(GCodeMetaInfo.EndOfMethodArgument)))
			{
				AppendEndInfoToLast(GCodeMetaInfo.EndOfMethodArgument);
			}
			return;
		}
		codelist.add(lcode);
	}

	public void AppendEndInfoToLast(String apdcode) {
		if (apdcode.equals(";"))
		{
			CheckAndDeletePartialEndAndFullEnd();
		}
		AppendToLast(apdcode);
	}
	
	private void CheckAndDeletePartialEndAndFullEnd() {
		int idx = codelist.size() - 1;
		String lastcode = codelist.get(idx);
		if (lastcode.endsWith(",") || lastcode.endsWith(";"))
		{
			lastcode = lastcode.substring(0, lastcode.length()-1);
			codelist.set(idx, lastcode);
		}
	}

	public void AppendToLast(String apdcode)
	{
		int idx = codelist.size() - 1;
		String lastcode = codelist.get(idx) + (apdcode);
		codelist.set(idx, lastcode);
	}

	public int getFirstCodeLevel() {
		return firstCodeLevel;
	}

	public void setFirstCodeLevel(int firstCodeLevel) {
		this.firstCodeLevel = firstCodeLevel;
	}

	public Iterator<String> GetCodeIterator() {
		return codelist.iterator();
	}

	public String GetLastCode() {
		return codelist.get(codelist.size()-1);
	}

	public boolean CheckAppend() {
		if (couldAppend || mustAppend)
		{
			return true;
		}
		return false;
	}

	public void CheckEnterMethodParam() {
		if (codelist.size() > 0)
		{
			int idx = codelist.size() - 1;
			String lastcode = codelist.get(idx);
			if (lastcode.startsWith("DH@" + GCodeMetaInfo.EnterMethodParam))
			{
				int lidx = lastcode.lastIndexOf(GCodeMetaInfo.EnterMethodParam);
				lastcode = lastcode.substring(0, lidx);
				codelist.set(idx, lastcode);
				if (lastcode.equals("DH@"))
				{
					codelist.remove(idx);
				}
			}
		}
	}

	public void MoveLastToSpecificLine(int line) {
		String lastcnt = codelist.removeLast();
		codelist.set(line, lastcnt);
	}

	public void MoveSpecificLineUntilLastToBeforeSpecificLine(int exprstartline, int dowhileline) {
		int csize = codelist.size();
		while (exprstartline < csize)
		{
			String es = codelist.remove(exprstartline);
			codelist.add(dowhileline, es);
			exprstartline++;
			dowhileline++;
		}
	}

	public boolean CheckLastIsSpecific(String checkcode) {
		String code = codelist.getLast();
		if (code.equals(checkcode))
		{
			return true;
		}
		return false;
	}

	public void RemoveLast() {
		codelist.removeLast();
	}

}