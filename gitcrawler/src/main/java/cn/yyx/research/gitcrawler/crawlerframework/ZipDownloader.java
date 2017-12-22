package cn.yyx.research.gitcrawler.crawlerframework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ZipDownloader {

	private static byte[] buffer = new byte[2048];

	private static String repoDir = "ZIPRepo";
	
	static {
		File f = new File(getRepodir());
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static String downLoadZip(String downloadURL, String filename) {
		int byteread = 0;
		String zipfname = getRepodir() + "/" + filename;
		File zfile = new File(zipfname);
		if (!zfile.exists())
		{
			try {
				zfile.createNewFile();
			} catch (IOException e) {
				System.err.println("Wrong name:" + zipfname);
				e.printStackTrace();
			}
		} else {
			System.out.println("File alreay exist! : " + filename);
			return zipfname;
		}
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			URL url = new URL(downloadURL);
			URLConnection conn = url.openConnection();
			inStream = conn.getInputStream();
			fs = new FileOutputStream(zfile);
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			System.out.println("File " + filename + "Downloaded Successfully.....");
		} catch (Exception e) {
			zfile.delete();
			System.out.println("Download Exception:" + e);
			return "false";
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				inStream = null;
			}
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				fs = null;
			}
		}
		return zipfname;
	}

	public static void downloadFile(String remoteFilePath, String localFilePath) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File f = new File(localFilePath);
		try {
			urlfile = new URL(remoteFilePath);
			httpUrl = (HttpURLConnection) urlfile.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(f));
			int len = -1;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
			bis.close();
			httpUrl.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setRepodir(String prepoDir) {
		repoDir = prepoDir;
	}
	
	public static String getRepodir() {
		return repoDir;
	}
	
}