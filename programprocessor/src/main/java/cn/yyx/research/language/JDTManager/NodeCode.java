package cn.yyx.research.language.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeCode {

	// private int lastCodeLevel = -1;

	// once set, no change.
	private int firstCodeLevel = -1;
	private boolean couldAppend = false;
	private boolean mustAppend = false;
	// private boolean lastHasContentHolder = false;

	ArrayList<String> codelist = new ArrayList<String>();

	public NodeCode() {
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
	
	public void AddOneLineCode(String code, boolean couldappend, boolean mustappend, boolean mustpre, boolean occupyoneline) {
		// lastCodeLevel = level;
		if (couldAppend)
		{
			if (occupyoneline)
			{
				if (mustAppend)
				{
					AppendLast(GCodeMetaInfo.CodeHole);
				}
				codelist.add(code);
			}
			else
			{
				AppendLast(code);
			}
		}
		else
		{
			if (mustpre)
			{
				code = GCodeMetaInfo.PreExist + code;
				codelist.add(code);
			}
			else
			{
				codelist.add(code);
			}
		}
		
		// set couldAppend.
		this.mustAppend = mustappend;
		this.couldAppend = couldappend;
		if (occupyoneline)
		{
			couldAppend = false;
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

	public void AppendLast(String apdcode) {
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

}