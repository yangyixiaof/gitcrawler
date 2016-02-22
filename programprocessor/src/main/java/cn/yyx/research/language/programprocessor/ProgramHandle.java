package cn.yyx.research.language.programprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.yyx.research.language.Utility.BigDirectory;
import cn.yyx.research.language.Utility.SourceCodeFileIteration;

/**
 * Hello world!
 *
 */
public class ProgramHandle {

	// command: start /home/yyx/HomeSpace/UnzipPool /home/yyx/HomeSpace/UnzipAllFiles/TransformedData
	
	String processdir = null;
	Thread runThread = null;
	File dir = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public ProgramHandle(String processdir) {
		this.processdir = processdir;
	}

	public boolean StartProcessJavaProjects() {
		if (processdir.equals("")) {
			File f = new File("temp.txt");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Create temp.txt fails, the system will exit.");
					System.exit(1);
				}
			}
			dir = new File(f.getAbsolutePath()).getParentFile();
			if (!dir.exists()) {
				System.err.println("The directory which contains this jar doesn't exist??????");
				return false;
			}
			f.delete();
		} else {
			dir = new File(processdir);
		}
		if (!dir.exists()) {
			System.err.println("Directory not exists. Dir : " + dir.getAbsolutePath());
			return false;
		}
		runThread = new Thread(new Runnable() {
			@Override
			public void run() {
				SourceCodeFileIteration.Initial();
				SourceCodeFileIteration.IterateAllFilesAndWriteToOneBigFile(dir);
				System.out.println("All Done......");
			}
		});
		runThread.start();
		return true;
	}

	public void StopProcessJavaProjects() {
		SourceCodeFileIteration.StopIterate();
	}

	public static void main(String[] args) {
		String processdir = "";
		ProgramHandle app = null;
		String cmd = null;
		boolean stop = false;
		try {
			System.out.println("Input your command:");
			while ((cmd = br.readLine()) != null && !stop) {
				switch (cmd.toLowerCase()) {
				case "start":
					System.out.println("Java processor uses the current directory.");
					app = new ProgramHandle(processdir);
					// stop = !...
					app.StartProcessJavaProjects();
					break;
				case "exit":
				case "stop":
					if (app != null)
					{
						app.StopProcessJavaProjects();
					}
					stop = true;
					break;
				default:
					if (cmd.startsWith("start ")) {
						processdir = cmd.substring("start ".length(), cmd.length());
						String[] ps = processdir.split(" ");
						if (ps.length == 2)
						{
							processdir = ps[0];
							BigDirectory.PrefixDirectory = ps[1];
						}
						System.out.println("Java processor uses the directory : " + processdir + ";Writing Directory : " + BigDirectory.PrefixDirectory);
						app = new ProgramHandle(processdir);
						// stop = !
						app.StartProcessJavaProjects();
					} else {
						System.out.println(
								"Unrecoginze parameters. There should be only one parameter represents the directory which contains java projects which are after decompression.");
					}
					break;
				}
				if (stop) {
					break;
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Input closed and the program exits.");
		}
	}
}