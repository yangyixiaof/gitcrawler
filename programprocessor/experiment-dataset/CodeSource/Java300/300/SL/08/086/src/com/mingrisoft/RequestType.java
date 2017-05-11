package com.mingrisoft;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class RequestType extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7402676201978279765L;
    private JPanel contentPane;
    private JTextField requestField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RequestType frame = new RequestType();
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
    public RequestType() {
        setTitle("\u5224\u65AD\u7F51\u9875\u8BF7\u6C42\u4E0EFTP\u8BF7\u6C42");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 423, 125);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u8F93\u5165\u8BF7\u6C42\u7F51\u5740\uFF1A");
        label.setBounds(10, 18, 84, 15);
        contentPane.add(label);
        
        requestField = new JTextField();
        requestField.setBounds(93, 10, 304, 30);
        contentPane.add(requestField);
        requestField.setColumns(10);
        
        JButton button = new JButton("\u9A8C\u8BC1");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(93, 50, 90, 30);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u5173\u95ED");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(240, 50, 90, 30);
        contentPane.add(button_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String request = requestField.getText();// ��ȡ�û�����
        if (request.startsWith("http")) {// �ж������Ƿ���http��ͷ
            JOptionPane.showMessageDialog(null, "�����������ҳ��ַ��ϣ�����ĳ����վ��");
        } else if (request.startsWith("ftp")) {// �ж������Ƿ���ftp��ͷ
            JOptionPane.showMessageDialog(null, "���������FTP��ַ��ϣ������FTP��������");
        } else {// �����ַ�����ͷ��Ϊ��Ϣ������
            JOptionPane.showMessageDialog(null, "�������������Ϣ��������");
        }
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        dispose();
    }
}
