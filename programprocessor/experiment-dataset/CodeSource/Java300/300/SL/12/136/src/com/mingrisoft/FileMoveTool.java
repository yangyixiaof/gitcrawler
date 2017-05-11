package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

public class FileMoveTool extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 2023679170797741858L;
    private JPanel contentPane;
    private JTextField sourceTextField;
    private JTextField targetTextField;
    private File[] selectFiles;
    private File targetDirectory;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileMoveTool frame = new FileMoveTool();
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
    public FileMoveTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel sourcePanel = new JPanel();
        panel.add(sourcePanel);

        JLabel sourceLabel = new JLabel("ѡ��Դ�ļ���");
        sourcePanel.add(sourceLabel);

        sourceTextField = new JTextField();
        sourcePanel.add(sourceTextField);
        sourceTextField.setColumns(17);

        JButton sourceButton = new JButton("ѡ���ļ�");
        sourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_sourceButton_actionPerformed(e);
            }
        });
        sourcePanel.add(sourceButton);

        JPanel targetPanel = new JPanel();
        panel.add(targetPanel);

        JLabel targetLabel = new JLabel("ѡ��Ŀ���ļ��У�");
        targetPanel.add(targetLabel);

        targetTextField = new JTextField();
        targetPanel.add(targetTextField);
        targetTextField.setColumns(14);

        JButton targetButton = new JButton("ѡ���ļ���");
        targetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_targetButton_actionPerformed(e);
            }
        });
        targetPanel.add(targetButton);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton moveButton = new JButton("�ƶ�");
        moveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_moveButton_actionPerformed(e);
            }
        });
        buttonPanel.add(moveButton);

        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_closeButton_actionPerformed(e);
            }
        });
        buttonPanel.add(closeButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        model.setColumnIdentifiers(new String[] { "�ƶ��ļ�����", "Ŀ���ļ���" });
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

    protected void do_sourceButton_actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        if (model.getRowCount() != 0) {
            model.setRowCount(0);
        }
        table.setModel(model);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectFiles = chooser.getSelectedFiles();
            if (selectFiles.length != 0) {
                StringBuilder fileNames = new StringBuilder();
                for (File selectFile : selectFiles) {
                    fileNames.append(selectFile.getName() + "��");
                }
                fileNames.deleteCharAt(fileNames.length() - 1);
                sourceTextField.setText(fileNames.toString());
            }
        }
    }

    protected void do_targetButton_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            targetDirectory = chooser.getSelectedFile();
            targetTextField.setText(targetDirectory.getAbsolutePath());
        }
    }

    protected void do_moveButton_actionPerformed(ActionEvent e) {
        if ((selectFiles == null) || (selectFiles.length == 0)) {
            JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�ƶ����ļ���", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (targetDirectory == null) {
            JOptionPane.showMessageDialog(this, "��ѡ��Ŀ���ļ��У�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        for (File selectFile : selectFiles) {
            String fileName = targetDirectory.getAbsolutePath() + File.separator + selectFile.getName();// ������ļ���
            selectFile.renameTo(new File(fileName));// �ƶ��ļ�
            model.addRow(new String[] { selectFile.getName(), targetDirectory.getAbsolutePath() });// ����ģ������������
        }
        table.setModel(model);// ���ñ��ģ��
    }

    protected void do_closeButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
