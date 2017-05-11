package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FilesList extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -2029566581047632579L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FilesList frame = new FilesList();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FilesList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel label = new JLabel("�����ļ���չ����");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        panel.add(label);

        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        panel.add(textField);
        textField.setColumns(12);

        JButton button = new JButton("ѡ���ļ���");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        panel.add(button);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 25));// �޸ı�ͷ�ĸ߶�
        table.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        table.setRowHeight(25);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[] { "�ļ���", "�ļ���С", "�޸�ʱ��" });
        scrollPane.setViewportView(table);
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        final String fileType = textField.getText();// ����û�������ļ�����
        if (fileType.isEmpty()) {// ��ʾ�û������ļ�����
            JOptionPane.showMessageDialog(this, "�������ļ����ͣ�", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ���ý���ѡ���ļ���
        chooser.setMultiSelectionEnabled(false);// ��ֹѡ�����ļ���
        int result = chooser.showOpenDialog(this);// ���ļ�ѡ����
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] listFiles = chooser.getSelectedFile().listFiles(new java.io.FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(fileType)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });// ��÷����������ļ�
            DefaultTableModel model = (DefaultTableModel) table.getModel();// ���Ĭ�ϱ��ģ��
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ָ�����ڸ�ʽ
            for (File file : listFiles) {
                String name = file.getName();// ����ļ���
                long size = file.length();// ����ļ���С
                String modifyDate = format.format(new Date(file.lastModified()));// ����ļ��޸�����
                model.addRow(new String[] { name, "" + size, modifyDate });// ��������������
            }
            table.setModel(model);// ���±��ģ��
        }

    }
}
