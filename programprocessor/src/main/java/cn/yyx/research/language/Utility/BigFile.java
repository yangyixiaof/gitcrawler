package cn.yyx.research.language.Utility;

import java.io.File;
import java.io.IOException;

public class BigFile {

	ABigSourceCodeFile mBigFile;
	
	public static final int maxWordsInOneLine = 50000;
	
	/*public BigFile(String prefix) {
		mBigFile = new ABigSourceCodeFile(prefix);
	}*/
	
	public BigFile(File f)
	{
		mBigFile = new ABigSourceCodeFile(f);
	}
	
	public void AppendOneContentToTheBigFile(String onecnt, int words) {
		try {
			mBigFile.Begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if (words > maxWordsInOneLine)
		{
			String[] ocs = onecnt.split(" ");
			int idx = 0;
			while (words > 0)
			{
				int rem = words % maxWordsInOneLine;
				if (rem == 0)
				{
					rem = maxWordsInOneLine;
				}
				words -= rem;
				String tmpcontent = ArrayHelper.JoinArrayInSection(ocs, idx, idx + rem);
				mBigFile.appendContent(tmpcontent + "\n");
				idx += rem;
			}
		}
		else
		{
			mBigFile.appendContent(onecnt + "\n");
		}
		mBigFile.End();
	}
	
	/*public void AppendOneFileToTheBigFile(File onefile) {
		try {
			mBigFile.Begin();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(onefile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				tempString = tempString.trim();
				if (!tempString.equals(""))
				{
					mBigFile.appendContent(tempString); // + "\n"
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		mBigFile.End();
	}*/
	
}