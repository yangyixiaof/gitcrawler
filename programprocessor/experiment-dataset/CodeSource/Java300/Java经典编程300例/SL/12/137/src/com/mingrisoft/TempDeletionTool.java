package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TempDeletionTool extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 891173527384201765L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private File[] tempFiles;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TempDeletionTool frame = new TempDeletionTool();
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
    public TempDeletionTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel discChooseLabel = new JLabel("ѡ���ļ��У�");
        panel.add(discChooseLabel);

        JButton findButton = new JButton("ѡ���ļ���");
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_findButton_actionPerformed(e);
            }
        });

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        panel.add(findButton);

        JButton clearButton = new JButton("����");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_clearButton_actionPerformed(e);
            }
        });
        panel.add(clearButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[] { "�ļ�����", "�ļ���С", "�޸�ʱ��", "�ļ�״̬" });
        scrollPane.setViewportView(table);
    }

    protected void do_findButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File directory = chooser.getSelectedFile();
            textField.setText(directory.getAbsolutePath());
            tempFiles = directory.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    String fileName = pathname.getName();
                    if (fileName.endsWith("tmp") || fileName.endsWith("TMP")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (File tempFile : tempFiles) {
                model.addRow(new Object[] { tempFile.getName(), tempFile.length(), format.format(new Date(tempFile.lastModified())), "δ����" });
            }
            table.setModel(model);
        }
    }

    protected void do_clearButton_actionPerformed(ActionEvent e) {
        if ((tempFiles == null) || (tempFiles.length == 0)) {// �ж��û�ѡ����ļ����Ƿ����temp�ļ�
            JOptionPane.showMessageDialog(this, "ѡ����ļ�����δ����tmp�ļ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        for (int i = 0; i < tempFiles.length; i++) {
            if (tempFiles[i].delete()) { // ɾ���ļ�
                model.setValueAt("�Ѵ���", i, 3);// �޸ı������
            }
        }
    }
}
