package cn.yyx.research.language.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cn.yyx.research.language.JDTHelper.ProgramProcessor;

public class SourceCodeFileIteration {
	
	private static boolean canrun = false;
	
	private static int level = 0;
	private static int num = 0;
	private static int dnum = 0;
	
	public static void Initial() {
		canrun = true;
	}

	public static void IterateAllFilesAndWriteToOneBigFile(File f) {
		if (!canrun)
		{
			return;
		}
		level++;
		if (level >= 80)
		{
			System.err.println("What the fuck, the depth of directory is more than 80？");
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		if (f.exists()) {
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				for (File a : files)
				{
					IterateAllFilesAndWriteToOneBigFile(a);
				}
			} else {
				String fname = f.getName();
				//  || fname.endsWith(".c") || fname.endsWith(".cpp") || fname.endsWith(".cc")
				if (fname.endsWith(".java"))
				{
					num++;
					System.out.println("Handling file : " + f.getAbsolutePath() + ";CurrentNum:"+num);
					try {
						long begin = System.currentTimeMillis();
						ArrayList<CorpusContentPair> corpus = ProgramProcessor.ProcessOneJavaFile(f);
						long end = System.currentTimeMillis();
						try {
							SleepTimeController.TImeSleep(begin, end);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						BigDirectoryManager.WriteCorpus(corpus);
						corpus = null;
					} catch (Exception e) {
						System.err.println("Not Parsed File or wrong parsed file, ignore it. It is exception.");
						RecordWrongFile(f.getAbsolutePath());
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} catch (Error e) {
						System.err.println("Not Parsed File or wrong parsed file, ignore it. It is error.");
						RecordWrongFile(f.getAbsolutePath());
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
		level--;
	}
	
	private static void RecordWrongFile(String filepath)
	{
		File errorfile = new File("errorJavaFile.txt");
		if (!errorfile.exists())
		{
			try {
				errorfile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(errorfile, true));
			bw.write("Wrong File Path:" + filepath);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public static void StopIterate() {
		canrun = false;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		SourceCodeFileIteration.level = level;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		SourceCodeFileIteration.num = num;
	}

	public static int getDnum() {
		return dnum;
	}

	public static void setDnum(int dnum) {
		SourceCodeFileIteration.dnum = dnum;
	}

}