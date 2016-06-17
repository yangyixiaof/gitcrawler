package com.lzw;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CheckIPAddress extends JFrame {
    
    private JPanel contentPane;
    private JTextField ipField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckIPAddress frame = new CheckIPAddress();
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
    public CheckIPAddress() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 280, 128);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblip = new JLabel("\u8BF7\u8F93\u5165IP\u5730\u5740\uFF1A");
        lblip.setBounds(12, 14, 92, 15);
        contentPane.add(lblip);
        
        ipField = new JTextField();
        ipField.setBounds(100, 10, 141, 25);
        contentPane.add(ipField);
        
        JButton button = new JButton("\u9A8C\u8BC1");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(66, 57, 93, 23);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String text = ipField.getText();// ��ȡ�û�����
        String info = matches(text);// �������ı�����IP��֤
        JOptionPane.showMessageDialog(null, info);// �öԻ��������֤���
    }
    
    /**
     * ��֤ip�Ƿ�Ϸ�
     * 
     * @param text
     *            ip��ַ
     * @return ��֤��Ϣ
     */
    public String matches(String text) {
        if (text != null && !text.isEmpty()) {
            // ����������ʽ
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // �ж�ip��ַ�Ƿ���������ʽƥ��
            if (text.matches(regex)) {
                // �����ж���Ϣ
                return text + "\n��һ���Ϸ���IP��ַ��";
            } else {
                // �����ж���Ϣ
                return text + "\n����һ���Ϸ���IP��ַ��";
            }
        }
        // �����ж���Ϣ
        return "������Ҫ��֤��IP��ַ��";
    }
}
