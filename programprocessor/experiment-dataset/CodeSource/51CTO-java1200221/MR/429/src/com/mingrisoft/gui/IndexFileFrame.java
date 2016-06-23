package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IndexFileFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2008614713725440868L;
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JComboBox comboBox;
    private JProgressBar progressBar;
    private JTextArea textArea;
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
                    IndexFileFrame frame = new IndexFileFrame();
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
    public IndexFileFrame() {
        setTitle("\u521B\u5EFA\u78C1\u76D8\u7D22\u5F15\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.NORTH);
        
        chooseTextField = new JTextField();
        chooseTextField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        chooseTextField.setText("\u9009\u62E9\u4FDD\u5B58\u78C1\u76D8\u7D22\u5F15\u7684\u6587\u4EF6");
        choosePanel.add(chooseTextField);
        chooseTextField.setColumns(18);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6");
        chooseButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(chooseButton);
        
        JPanel Panel = new JPanel();
        contentPane.add(Panel, BorderLayout.SOUTH);
        
        comboBox = new JComboBox();
        comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        comboBox.setMaximumRowCount(8);
        Panel.add(comboBox);
        
        File[] files = File.listRoots();
        for (File file : files) {
            comboBox.addItem(file);
        }
        comboBox.setSelectedIndex(0);
        
        JButton createButton = new JButton("\u521B\u5EFA\u7D22\u5F15");
        createButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_createButton_actionPerformed(arg0);
            }
        });
        Panel.add(createButton);
        
        progressBar = new JProgressBar();
        progressBar.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        Panel.add(progressBar);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("�ı��ļ�", "txt"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            chooseFile = fileChooser.getSelectedFile();
            chooseTextField.setText(chooseFile.getAbsolutePath());
        }
    }
    
    protected void do_createButton_actionPerformed(ActionEvent arg0) {
        if (chooseFile == null) {
            JOptionPane.showMessageDialog(this, "��ѡ�񱣴��������ļ�", null, JOptionPane.WARNING_MESSAGE);
            return;
        }
        String disc = comboBox.getSelectedItem().toString();// ����û�ѡ��Ĵ���
        comboBox.setSelectedItem(disc);// ����JComboBox��ʾ�û�ѡ��Ĵ���
        final List<String> list = new ArrayList<String>();// ��list��������
        final File rootFile = new File(disc);// �����û�ѡ��Ĵ��̴���File����
        final StringBuilder sb = new StringBuilder();// ����StringBuilder���󱣴�д�������
        progressBar.setIndeterminate(true);// ���ù�������ʼ����
        new Thread() {// ��һ���µ��߳��д�����������д�������Ĳ���
            @Override
            public void run() {
                getFilePath(list, rootFile);// ��ô����������ļ���·��
                Iterator<String> iterator = list.iterator();// ����������
                while (iterator.hasNext()) {// ����list
                    sb.append(iterator.next());
                    sb.append("\r\n");
                    try {
                        Thread.sleep(100);// �߳�����0.1��
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    textArea.setText(sb.toString());// ���ı�������ʾ�ļ�·��
                }
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(chooseFile);
                    fileWriter.write(textArea.getText());// ���û�ѡ����ı��ļ�д������
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // ʡ���ͷ���Դ�Ĵ���
                progressBar.setIndeterminate(false);// ֹͣ�������Ĺ���
                JOptionPane.showMessageDialog(null, "���������ɹ�");// ��ʾ�û����������ɹ�
            };
        }.start();
    }
    
    private static List<String> getFilePath(List<String> list, File rootFile) {
        File[] files = rootFile.listFiles();
        if (files == null)
            return list;
        for (File file : files) {
            if (file.isDirectory()) {
                getFilePath(list, file);
            } else {
                list.add(file.getAbsolutePath().replace("\\", "/"));
            }
        }
        return list;
    }
    
}
