package cn.yyx.research.language.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParentTest {
	
	int u = -1;
	
	public ParentTest() {
	}
	
	public ParentTest(int u) {
		u = 0;
	}
	
	public void ReadFile() throws IOException
	{
		File f = new File("temp.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String a = null;
		while ((a = br.readLine()) != null)
		{
			System.out.println(a);
		}
		br.close();
	}
	
}