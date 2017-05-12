package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class DeleteFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LocalTableModel model = new LocalTableModel();
	private DeleteUtil util = new DeleteUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		// right 1
		contentPane = new JPanel();
		// right 1
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// right 1
		setContentPane(contentPane);
		// right 1
		contentPane.setLayout(null);
		// right 3
		JPanel content = new JPanel();

		content.setBounds(0, 0, 434, 291);
		contentPane.add(content);
		content.setLayout(null);

		// right 1
		JLabel messageLabel = new JLabel("ɾ������ʱ������ʾ��Ϣ");
		messageLabel.setFont(new Font("��������", Font.PLAIN, 16));
		messageLabel.setBounds(123, 26, 213, 34);
		content.add(messageLabel);

		// right 1
		JScrollPane scrollPane = new JScrollPane();
		// right 2
		scrollPane.setBounds(34, 70, 364, 173);
		content.add(scrollPane);

		// table = new JTable(model);
		// List stu_list = util.selectStu();
		// for (int i = 0; i < stu_list.size(); i++) {
		// Stu stu = (Stu) stu_list.get(i);
		// model.addRow(new Object[] { stu.getId(), stu.getName(),
		// stu.getSex(), stu.getSpecialty(), stu.getGrade() });
		// }
		// scrollPane.setViewportView(table);

		JButton deleteButton = new JButton("ɾ��");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_deleteButton_actionPerformed(arg0);
			}
		});
		deleteButton.setBounds(108, 253, 73, 23);
		content.add(deleteButton);

		JButton closeButton = new JButton("�ر�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_button_1_actionPerformed(arg0);
			}
		});
		closeButton.setBounds(231, 253, 73, 23);
		content.add(closeButton);
	}

	protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
		int row = table.getSelectedRow();
		// right 1
		if (row >= 0) {
			int n = JOptionPane.showConfirmDialog(getContentPane(), "ȷ��ɾ����", "ȷ�϶Ի���",
					JOptionPane.YES_NO_CANCEL_OPTION);
			// wrong of not generalizing.
			if (n == JOptionPane.YES_OPTION) {
				util.deleteStu(row + 1);
			}
			// wrong of not generalizing.
			validate();
		}

	}

	protected void do_button_1_actionPerformed(ActionEvent arg0) {
		// wrong of not fuzzy matching.
		System.exit(0);
	}
}
