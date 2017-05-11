package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class UnZipTextFileFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7525621255251725313L;
    private JPanel contentPane;
    private JTextField sourceTextField;
    private JTable table;
    private File zipFile;
    private JTextField targetTextField;
    private File targetFile;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UnZipTextFileFrame frame = new UnZipTextFileFrame();
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
    public UnZipTextFileFrame() {
        setTitle("\u538B\u7F29\u5305\u89E3\u538B\u5230\u6307\u5B9A\u6587\u4EF6\u5939");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.NORTH);
        
        sourceTextField = new JTextField();
        choosePanel.add(sourceTextField);
        sourceTextField.setColumns(10);
        
        JButton sourceButton = new JButton("Zip\u6587\u4EF6");
        sourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_sourceButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(sourceButton);
        
        targetTextField = new JTextField();
        choosePanel.add(targetTextField);
        targetTextField.setColumns(10);
        
        JButton targetButton = new JButton("\u89E3\u538B\u5230");
        targetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_targetButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(targetButton);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton unzipButton = new JButton("\u5F00\u59CB\u89E3\u538B\u7F29");
        unzipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_unzipButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(unzipButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
    }
    
    protected void do_sourceButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("�ı��ļ�", "zip"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            zipFile = fileChooser.getSelectedFile();
            sourceTextField.setText(zipFile.getAbsolutePath());
        }
    }
    
    protected void do_targetButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            targetFile = fileChooser.getSelectedFile();
            targetTextField.setText(targetFile.getAbsolutePath());
        }
    }
    
    @SuppressWarnings("rawtypes")
    protected void do_unzipButton_actionPerformed(ActionEvent arg0) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        model.setColumnIdentifiers(new Object[] { "���", "�ļ���" });// ���ñ�ͷ
        int id = 1;// ������ű���
        ZipFile zf = null;
        try {
            zf = new ZipFile(zipFile);// �����û�ѡ���ZIP�ļ�����ZipFile����
            Enumeration e = zf.entries();// ����ö�ٱ���
            while (e.hasMoreElements()) {// ����ö�ٱ���
                ZipEntry entry = (ZipEntry) e.nextElement();// ���ZipEntry����
                if (!entry.getName().endsWith(".txt")) {// ��������ı��ļ��Ͳ����н�ѹ��
                    continue;
                }
                // �����û�ѡ����ļ��к�ZipEntry�������ƴ�����ѹ����ļ�
                File currentFile = new File(targetFile + File.separator + entry.getName());
                FileOutputStream out = new FileOutputStream(currentFile);
                InputStream in = zf.getInputStream(entry);// ���û�õ�ZipEntry�����������
                int buffer = 0;
                while ((buffer = in.read()) != -1) {// ��������д�뵽�����ļ�
                    out.write(buffer);
                }
                model.addRow(new Object[] { id++, currentFile.getName() });// ����һ�б������
                in.close();// �ͷ���Դ
                out.close();
            }
            table.setModel(model);// ���±��
            JOptionPane.showMessageDialog(this, "��ѹ�����");// ��ʾ�û���ѹ�����
        } catch (ZipException e) {// �����쳣
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zf != null) {
                try {
                    zf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
