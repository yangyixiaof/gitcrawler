package com.mingrisoft.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class DownloadFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4265931991434336626L;
    private JPanel contentPane;
    private JTextField urlTextField;
    private JTextField pathTextField;
    
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
                    DownloadFrame frame = new DownloadFrame();
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
    public DownloadFrame() {
        setTitle("\u63D0\u53D6\u6280\u672F\u7F51\u7AD9\u6570\u636E\u5230\u6587\u4EF6\u5939");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 0, 0));
        
        JPanel urlPanel = new JPanel();
        contentPane.add(urlPanel);
        
        JLabel urlLabel = new JLabel("\u4E0B\u8F7D\u5730\u5740\uFF1A");
        urlLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        urlPanel.add(urlLabel);
        
        urlTextField = new JTextField();
        urlTextField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        urlPanel.add(urlTextField);
        urlTextField.setColumns(15);
        
        JPanel pathPanel = new JPanel();
        contentPane.add(pathPanel);
        
        JLabel pathLabel = new JLabel("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
        pathLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        pathPanel.add(pathLabel);
        
        pathTextField = new JTextField();
        pathTextField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        pathPanel.add(pathTextField);
        pathTextField.setColumns(15);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        
        JButton button = new JButton("\u5F00\u59CB\u4E0B\u8F7D");
        button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        
        JButton chooseButton = new JButton("\u9009\u62E9\u8DEF\u5F84");
        chooseButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(chooseButton);
        buttonPanel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        String address = urlTextField.getText();// ����û��������ַ
        String path = pathTextField.getText();// ����û�ѡ��ı��������ļ��ĵ�ַ
        if (address.length() == 0) {
            JOptionPane.showMessageDialog(this, "���������ص�ַ", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (path.length() == 0) {
            JOptionPane.showMessageDialog(this, "��ѡ�񱣴�·��", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(address);// �����û��������ַ����URL����
            URLConnection conn = url.openConnection();// ����URL������URLConnection����
            in = conn.getInputStream();// ���InputStream����
            out = new FileOutputStream(path + "download.html");// �������ص��ļ��������
            int data;
            while ((data = in.read()) != -1) {
                out.write(data);// д��Ҫ���ص��ļ�������
            }
            JOptionPane.showMessageDialog(this, "�������");// ��ʾ�û��������
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            pathTextField.setText(path);
        }
    }
}
