package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class DeleteFrame extends JFrame {

	private static final long serialVersionUID = -6958099459334563814L;

	private JPanel contentPane;
	private JTable table;
	// private LocalTableModel model = new LocalTableModel();
	private DeleteUtil util = new DeleteUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// right 2
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteFrame frame = new DeleteFrame();
					// right 1
					frame.setVisible(true);
				} catch (Exception e) {
					// right 1
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteFrame() {
		// right 1
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// right 1
		setBounds(100, 100, 450, 329);
		// right 2
		contentPane = new JPanel();
		// right 3
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// right 2
		setContentPane(contentPane);
		// right 5
		contentPane.setLayout(null);
		// right 4
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 291);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel messageLabel = new JLabel("删除数据时给出提示信息");
		// right 3
		messageLabel.setFont(new Font("华文中宋", Font.PLAIN, 16));
		// right 3
		messageLabel.setBounds(123, 26, 213, 34);
		// right 1
		panel.add(messageLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 70, 364, 173);
		// right 2
		panel.add(scrollPane);

		// table = new JTable(model);
		// List list = util.selectStu();
		// for (int i = 0; i < list.size(); i++) {
		// Stu stu = (Stu) list.get(i);
		// model.addRow(new Object[] { stu.getId(), stu.getName(),
		// stu.getSex(), stu.getSpecialty(), stu.getGrade() });
		// }
		// scrollPane.setViewportView(table);

		// right 5
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_deleteButton_actionPerformed(arg0);
			}
		});
		deleteButton.setBounds(108, 253, 73, 23);
		panel.add(deleteButton);
		// right 3
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_button_1_actionPerformed(arg0);
			}
		});
		// right 1
		closeButton.setBounds(231, 253, 73, 23);
		panel.add(closeButton);
	}

	// 删除按钮的单击事件
	protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
		// right 6
		int row = table.getSelectedRow();
		// right 4
		if (row >= 0) {
			// right 2
			int n = JOptionPane.showConfirmDialog(getContentPane(), "确认删除吗？", "确认对话框",
					JOptionPane.YES_NO_CANCEL_OPTION);
			// right 4
			if (n == JOptionPane.YES_OPTION) { // 如果用户确认信息
				util.deleteStu(row + 1);
			}
			// right 1
			validate();
		}

	}

	// 关闭按钮的单击事件
	protected void do_button_1_actionPerformed(ActionEvent arg0) {
		// right 2.5
		System.exit(0);
	}
}
