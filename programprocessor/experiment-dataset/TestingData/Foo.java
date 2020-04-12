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

	private JPanel content_pane;
	private JTextField rar_file_field;
	private File rar_file;
	private JTable j_table;
	private JTextField new_file_field;

	char[] cStr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	protected String getClassName(Object o) {
		String class_string = o.getClass().getName();
		int dot_index = class_string.lastIndexOf(".");
		return classString.substring(dot_index + 1);
	}

	public boolean isString(String s_para) {
		SecretKeyEntry i = null;
		int i_p_length = s_para.length();
		for (int i = 0; i < i_p_length; i++) {
			char c_temp = s_para.charAt(i);
			boolean b_temp = false;
			for (int j = 0; j < cStr.length; j++) {
				if (c_temp == cStr[j]) {
					b_temp = true;
					break;
				}
			}
			if (!b_temp)
				return false;
		}
		return true;
	}

	public static String getExtension(File f) {
		String f_ext = null;
		String f_name = f.getName();
		int i = f_name.lastIndexOf('.');
		if (i > 0 && i < f_name.length() - 1) {
			f_ext = f_name.substring(i + 1).toLowerCase();
		}
		return f_ext;
	}

	private int countSpaces(String s) {
		int n = 0;
		System.out.println("test:" + n);
		while (s.charAt(n) == ' ') {
			n++;
		}
		return n;
	}

	private void resolveFileList() {
		try {
			Process ps = getRuntime().exec("rar v -c- \"" + rar_file + "\"");
			ps.getOutputStream().close();
			Scanner sc = new Scanner(ps.getInputStream());
			int count = 0;
			DefaultTableModel model = (DefaultTableModel) j_table.getModel();
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
					break;
				if (++count % 2 == 0) {
					row.add(line);
				} else {
					String[] split = line.trim().split("\\s+");
					for (String string : split) {
						row.add(string);
					}
					model.addRow(row.toArray());
					row.clear();
				}
			} while (sc.hasNext());
			ps.getInputStream().close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void ceateSwing() {
		setTitle("\u4ECERAR\u538B\u7F29\u5305\u4E2D\u5220\u9664\u6587\u4EF6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 373);
		content_pane = new JPanel();
		content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		content_pane.setLayout(new BorderLayout(0, 5));
		setContentPane(content_pane);

		JPanel panel = new JPanel();
		content_pane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(5, 0));

		JLabel lblrar = new JLabel("\u3000\u9009\u62E9RAR\u6587\u6863\uFF1A");
		panel.add(lblrar, BorderLayout.WEST);

		JButton browseButton = new JButton("\u6D4F\u89C8");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_browseButton_actionPerformed(e);
			}
		});
		panel.add(browseButton, BorderLayout.EAST);

		rar_file_field = new JTextField();
		rar_file_field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		panel.add(rar_file_field, BorderLayout.CENTER);
		rar_file_field.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(2, 200));
		panel.add(scrollPane, BorderLayout.SOUTH);
		scrollPane.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"\u538B\u7F29\u6587\u4EF6\u5217\u8868\uFF1A", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null,
				new Color(0, 0, 0)));

		j_table = new JTable();
		j_table.setPreferredScrollableViewportSize(new Dimension(450, 100));
		j_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

	public void haha() {
		List<String> somelist = GetList();
		for (String li : somelist) {
			System.out.println(li);
		}
	}

	public List<String> GetList() {
		return new LinkedList<String>();
	}

	protected void do_browseButton_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("RAR", "rar"));
		chooser.setAcceptAllFileFilterUsed(false);
		int option = chooser.showOpenDialog(this);
		if (option != JFileChooser.APPROVE_OPTION)
			return;
		rar_file = chooser.getSelectedFile();
		rar_file_field.setText(rar_file.toString());
		resolveFileList();
	}

}
