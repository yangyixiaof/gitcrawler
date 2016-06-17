package com.mingrisoft.codec;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;
import java.awt.Font;

public class MD5Viewer extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8722656519909578699L;
    private JPanel contentPane;
    private JTextField textField;
    private String md5;
    private JLabel messageLabel;
    
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
                    MD5Viewer frame = new MD5Viewer();
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
    public MD5Viewer() {
        setTitle("MD5\u67E5\u770B\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 175);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 5, 5));
        
        JPanel filePanel = new JPanel();
        contentPane.add(filePanel);
        
        JLabel fileLabel = new JLabel("\u9009\u62E9\u6587\u4EF6\uFF1A");
        fileLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        filePanel.add(fileLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        filePanel.add(textField);
        textField.setColumns(10);
        
        JButton fileButton = new JButton("\u6253\u5F00\u6587\u4EF6");
        fileButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_fileButton_actionPerformed(arg0);
            }
        });
        filePanel.add(fileButton);
        
        JPanel messagePanel = new JPanel();
        contentPane.add(messagePanel);
        
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        messagePanel.add(messageLabel);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        
        JButton md5Button = new JButton("\u8BA1\u7B97MD5");
        md5Button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        md5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_md5Button_actionPerformed(arg0);
            }
        });
        buttonPanel.add(md5Button);
    }
    
    protected void do_fileButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();// 创建文件选择器
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 让文件选择器只能选择文件
        fileChooser.setMultiSelectionEnabled(false);// 不能选择多个文件
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = fileChooser.getSelectedFile();// 获得用户选择的文件
            textField.setText(selectFile.getName());// 显示选择文件的绝对路径
            FileInputStream in = null;
            try {
                in = new FileInputStream(selectFile);// 获得文件输入流
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                md5 = DigestUtils.md5Hex(in);// 计算MD5值，并保存在域变量md5中
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void do_md5Button_actionPerformed(ActionEvent arg0) {
        if (md5 == null) {
            JOptionPane.showConfirmDialog(this, "请选择要计算的文件", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        messageLabel.setText(md5);
    }
}
