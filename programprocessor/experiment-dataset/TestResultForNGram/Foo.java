package HTM;

import static java.lang.Runtime.getRuntime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.KeyStore.SecretKeyEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Foo extends JFrame {

	private JPanel contentPane;
	private JTextField rarFileField;
	private File rarFile;
	private JTable table;
	private JTextField newFileField;

	char[] cStr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		// wrong of fused.
		int dotIndex = classString.lastIndexOf(".");
		// wrong of fused.
		return classString.substring(dotIndex + 1);
	}

	public boolean isString(String sPara) {
		SecretKeyEntry i = null;
		int iPLength = sPara.length();
		for (int i7 = 0; i7 < iPLength; i7++) {
			// right 8
			char c = sPara.charAt(i7);
			boolean b = false;
			for (int i1 = 0; i1 < cStr.length; i1++) {
				// right 1
				if (!b) {
					// wrong of not handling variable names.
					return false;
				}
			}
		}
		// right 1
		return true;
	}

	public static String getExtension(File f) {
		String ext = null;
		// wrong of chaos context.
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		// right 2
		return ext;
	}

	private int countSpaces(String s) {
		int n = 0;
		// wrong of not learning from others.
		while (s.charAt(n) == ' ') {
			// wrong of too easily fused.
			n++;
		}
		// right 1
		return n;
	}

	private void resolveFileList() {
		try {
			Process process = getRuntime().exec("rar v -c- \"" + rarFile + "\"");
			process.getOutputStream().close();
			Scanner sc = new Scanner(process.getInputStream());
			int count = 0;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			// right 1
			model.setRowCount(0);
			Vector<String> row = new Vector<String>();
			do {
				String line = sc.nextLine();
				if (line.contains("----------------------")) {
					count = (count == 0 ? count + 1 : -1);
					continue;
				}
				if (count == 0)
					continue;
				if (count == -1)
					// right 2
					break;
				if (++count % 2 == 0) {
					row.add(line);
				} else {
					String[] split = line.trim().split("\\s+");
					// wrong of not learning from other places.
					for (String string : split) {
						row.add(string);
					}
					model.addRow(row.toArray());
					row.clear();
				}
			} while (sc.hasNext());
			// context such as ()); has no meanings.
			process.getInputStream().close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private static void getPath(File rootFile, List<String> path) {
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getPath(file, path);
			} else {
				path.add(file.getAbsolutePath());
			}
		}
	}

}
