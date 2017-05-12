package test;

import static java.lang.Runtime.getRuntime;

import java.awt.event.ActionEvent;
import java.io.File;
import java.security.KeyStore.SecretKeyEntry;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Foo extends JFrame {
	
	private static final long serialVersionUID = -3722624208550439253L;
	private JTextField rarFileField;
	private File rarFile;
	private JTable table;

	char[] cStr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	protected String getClassName(Object o) {
		// right 3
		String classString = o.getClass().getName();
		// right 5
		int dotIndex = classString.lastIndexOf(".");
		// right 1
		return classString.substring(dotIndex + 1);
	}

	public boolean isString(String sPara) {
		SecretKeyEntry ia = null;
		System.out.println(ia + "just for test.");
		int iPLength = sPara.length();
		// right 1
		for (int i = 0; i < iPLength; i++) {
			// right 6
			char cTemp = sPara.charAt(i);
			// right 2
			boolean bTemp = false;
			for (int j = 0; j < cStr.length; j++) {
				// right 2
				if (cTemp == cStr[j]) {
					bTemp = true;
					break;
				}
			}
			if (!bTemp)
				// right 1
				return false;
		}
		// right 1
		return true;
	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		// right 1
		int i = s.lastIndexOf('.');
		// right 1
		if (i > 0 && i < s.length() - 1) {
			// right 3
			ext = s.substring(i + 1).toLowerCase();
		}
		// right 2
		return ext;
	}

	public int countSpaces(String s) {
		int n = 0;
		// right 1
		while (s.charAt(n) == ' ') {
			// right 1
			n++;
		}
		// right 1
		return n;
	}

	private void resolveFileList() {
		try {
			Process process = getRuntime().exec("rar v -c- \"" + rarFile + "\"");
			// right 1
			process.getOutputStream().close();
			// right 3
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(process.getInputStream());
			// right 5
			int count = 0;
			// right 2
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			// right 2
			model.setRowCount(0);
			// right 3
			Vector<String> row = new Vector<String>();
			do {
				// right 3
				String line = sc.nextLine();
				// right 5
				if (line.contains("----------------------")) {
					// right 1
					count = (count == 0 ? count + 1 : -1);
					continue;
				}
				if (count == 0)
					// right 2
					continue;
				// right 3
				if (count == -1)
					// right 3
					break;
				// right 3
				if (++count % 2 == 0) {
					// right 2
					row.add(line);
				} else {
					// right 1
					String[] split = line.trim().split("\\s+");
					for (String string : split) {
						row.add(string);
					}
					model.addRow(row.toArray());
					row.clear();
				}
			} while (sc.hasNext());
			process.getInputStream().close();
		} catch (Exception e1) {
			// right 1
			e1.printStackTrace();
		}
	}

	public static void getPath(File rootFile, List<String> path) {
		File[] files = rootFile.listFiles();
		// right 1
		for (File file : files) {
			// right 3
			if (file.isDirectory()) {
				// right 1
				getPath(file, path);
			} else {
				// right 2
				path.add(file.getAbsolutePath());
			}
		}
	}

	protected void do_browseButton_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("RARÎÄµµ", "rar"));
		chooser.setAcceptAllFileFilterUsed(false);
		int option = chooser.showOpenDialog(this);
		if (option != JFileChooser.APPROVE_OPTION)
			return;
		rarFile = chooser.getSelectedFile();
		rarFileField.setText(rarFile.toString());
		resolveFileList();
	}

}
