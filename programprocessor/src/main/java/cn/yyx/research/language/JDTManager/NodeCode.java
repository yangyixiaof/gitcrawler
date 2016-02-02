package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class NodeCode {

	// private int lastCodeLevel = -1;

	// once set, no change.
	private int firstCodeLevel = -1;
	private boolean couldAppend = false;
	private boolean mustAppend = false;
	protected Stack<Boolean> argmutiple = new Stack<Boolean>();
	// private boolean lastHasContentHolder = false;

	ArrayList<String> codelist = new ArrayList<String>();

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

	// code has b/leixing#......, only need to add info of lines.
	/*public void AddOneLineCode(String code, int level, boolean hasContentHolder) {
		// code = code + (IsEmpty() ? 0 : (level - lastCodeLevel));
		lastCodeLevel = level;
		if (lastHasContentHolder) {
			AppendLast(code);
		} else {
			codelist.add(code);
		}
		lastHasContentHolder = hasContentHolder;
	}*/
	
	public void AddOneLineCode(String code, boolean couldappend, boolean mustappend, boolean mustpre, boolean occupyoneline, String preHint) {
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
			else
			{
				if (preHint != null && !preHint.equals(""))
				{
					code = preHint + code;
				}
				codelist.add(code);
				iscodenewline = true;
			}
		}
		
		// set couldAppend.
		this.mustAppend = mustappend;
		this.couldAppend = couldappend;
		/*if (occupyoneline)
		{
			couldAppend = false;
		}*/
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
	
	// in first line, only level is not sure.
	/*public void BeAddedToNodeCode(NodeCode anc) {
		if (codelist.size() > 0) {
			String firstcode = codelist.get(0);
			// firstcode = (getFirstCodeLevel() - anc.getLastCodeLevel()) + firstcode;
			if (anc.isLastHasContentHolder()) {
				anc.AppendLast(firstcode);
			} else {
				anc.PushOneLineCode(firstcode);
			}
			int len = codelist.size();
			for (int i = 2; i < len; i++) {
				anc.PushOneLineCode(codelist.get(i));
			}
		}
	}*/

	public void AppendEndInfoToLast(String apdcode) {
		/*if (couldAppend || mustAppend)
		{
			AppendToLast(GCodeMetaInfo.CodeHole);
		}*/
		AppendToLast(apdcode);
	}
	
	public void AppendToLast(String apdcode)
	{
		int idx = codelist.size() - 1;
		String lastcode = codelist.get(idx) + (apdcode);
		codelist.set(idx, lastcode);
	}
	
	/*public void AppendLast(String firstcode) {
		int idx = codelist.size() - 1;
		String lastcode = codelist.get(idx).replace(GCodeMetaInfo.ContentHolder, firstcode);
		codelist.set(idx, lastcode);
	}*/

	/*public boolean isLastHasContentHolder() {
		return lastHasContentHolder;
	}

	public void setLastHasContentHolder(boolean lastHasContentHolder) {
		this.lastHasContentHolder = lastHasContentHolder;
	}*/

	public int getFirstCodeLevel() {
		return firstCodeLevel;
	}

	public void setFirstCodeLevel(int firstCodeLevel) {
		this.firstCodeLevel = firstCodeLevel;
	}

	/*public int getLastCodeLevel() {
		return lastCodeLevel;
	}

	public void setLastCodeLevel(int lastCodeLevel) {
		this.lastCodeLevel = lastCodeLevel;
	}*/

	public Iterator<String> GetCodeIterator() {
		return codelist.iterator();
	}

	public String GetLastCode() {
		return codelist.get(codelist.size()-1);
	}

}