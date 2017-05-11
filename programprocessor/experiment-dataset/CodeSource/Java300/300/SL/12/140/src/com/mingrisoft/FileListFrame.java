package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FileListFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3735840332208017268L;
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileListFrame frame = new FileListFrame();
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
    public FileListFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel chooseLabel = new JLabel("\u9009\u62E9\u6587\u4EF6\u5939\uFF1A");
        panel.add(chooseLabel);

        chooseTextField = new JTextField();
        panel.add(chooseTextField);
        chooseTextField.setColumns(15);

        JButton chooseButton = new JButton("\u9009\u62E9");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        panel.add(chooseButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[] { "�ļ���", "�ļ���С", "�޸�ʱ��", });
        scrollPane.setViewportView(table);
    }

    protected void do_chooseButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ���ƽ���ѡ���ļ���
        chooser.setMultiSelectionEnabled(false);// ��ֹ����ѡ��
        int result = chooser.showOpenDialog(this);// ���ļ�ѡ����
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectDirectory = chooser.getSelectedFile();// ���ѡ����ļ���
            chooseTextField.setText(selectDirectory.getAbsolutePath());// ��ʾ�ļ���λ��
            final File[] files = selectDirectory.listFiles();// ����ļ������ļ�����Ŀ¼
            final DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ���ʽ����ʽ
            new Thread() {
                public void run() {
                    for (File file : files) {
                        if (file.isFile()) {// ������ļ�������ģ������������
                            model.addRow(new Object[] { file.getName(), file.length(), format.format(new Date(file.lastModified())) });
                            try {
                                Thread.sleep(1000);// ��ǰ�߳�����1����ʵ�ֶ�̬����
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }.start();
        }
    }
}
