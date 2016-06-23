package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class FileListFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -615665572894071265L;
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JButton chooseButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JProgressBar progressBar;
    private File chooseFile;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
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
        setTitle("\u7A97\u4F53\u52A8\u6001\u52A0\u8F7D\u78C1\u76D8\u6587\u4EF6");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        chooseTextField = new JTextField();
        panel.add(chooseTextField);
        chooseTextField.setColumns(13);
        
        chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        panel.add(chooseButton);
        
        progressBar = new JProgressBar();
        panel.add(progressBar);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            chooseFile = fileChooser.getSelectedFile();// ����û�ѡ����ļ���
            chooseTextField.setText(chooseFile.getAbsolutePath());// ��ʾ�û�ѡ����ļ���
            progressBar.setIndeterminate(true);// ���ù�������ʼ����
            final File[] subFiles = chooseFile.listFiles();// ����û�ѡ����ļ����е������ļ����У�
            final DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);// ��ձ��
            new Thread() {// ��ʼ�µ��߳�
                public void run() {
                    for (int i = 0; i < subFiles.length; i++) {// �����û�ѡ����ļ���
                        if (subFiles[i].isFile()) {// �ж��Ƿ���һ���ļ�
                            Object[] property = new Object[3];
                            property[0] = i + 1;// �������
                            property[1] = subFiles[i].getName();// �����ļ���
                            property[2] = "";
                            if (subFiles[i].isHidden()) {// �ж��Ƿ���һ�������ļ�
                                property[2] = "�����ļ�";
                            }
                            model.addRow(property);// ��������Ӽ�¼
                            table.setModel(model);// ���±��
                        }
                        try {
                            Thread.sleep(100);// �߳�����0.1��ʵ�ֶ�̬����
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    progressBar.setIndeterminate(false);// ֹͣ����������
                };
            }.start();
            
        }
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[] { "���", "�ļ���", "����" });
    }
    
}
