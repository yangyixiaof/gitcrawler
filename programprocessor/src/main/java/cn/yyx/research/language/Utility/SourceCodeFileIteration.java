package cn.yyx.research.language.Utility;

import java.io.File;
import java.util.ArrayList;

import cn.yyx.research.language.JDTHelper.ProgramProcessor;

public class SourceCodeFileIteration {
	
	private static boolean canrun = false;
	
	public static void Initial() {
		canrun = true;
	}

	public static void IterateAllFilesAndWriteToOneBigFile(File f) {
		if (!canrun)
		{
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
					System.out.println("Handling file : " + f.getAbsolutePath());
					ArrayList<CorpusContentPair> corpus = ProgramProcessor.ProcessOneJavaFile(f);
					BigDirectoryManager.WriteCorpus(corpus);
				}
			}
		}
	}

	public static void StopIterate() {
		canrun = false;
	}

}