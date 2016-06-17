package com.lzw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lzw.login.LoginPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * @author ����ξ
 */
public class LoginFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private LoginPanel loginPanel = null;
    private JTextField textField;
    private JPasswordField passwordField;
    
    /**
     * �������ڷ���
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             ��֧�ֵ����
     */
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // ���ô������
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // ������¼����
                LoginFrame loginFrame = new LoginFrame();
                // ��ʾ��¼����
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * ��¼����Ĺ��췽��
     */
    public LoginFrame() {
        super();
        setTitle("ʹ��ͼƬ����Ѥ����ť");
        // ���ô����������
        jContentPane = new JPanel();
        // ���ò��ֹ�����
        jContentPane.setLayout(new BorderLayout());
        loginPanel = new LoginPanel();
        loginPanel.setLayout(null);
        JButton loginButton = new JButton();
        loginButton.setBounds(266, 81, 68, 68);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        // ���ð�ťͼ��
        loginButton.setIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut1.png")));
        loginButton.setContentAreaFilled(false);
        // ���ð�ť���¶�����ͼ��
        loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut2.png")));
        // ������꾭����ť��ͼ��
        loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut3.png")));
        // ��Ӱ�ť�¼�������
        loginPanel.add(loginButton);// ��ӵ�¼��ť
        
        textField = new JTextField();// �����ı���
        textField.setBounds(94, 81, 155, 30);
        loginPanel.add(textField);// ����ı��򵽴���
        
        passwordField = new JPasswordField();// ���������
        passwordField.setBounds(94, 113, 155, 30);
        loginPanel.add(passwordField);// �������򵽴���
        // ��ӵ�¼��嵽�������
        jContentPane.add(loginPanel, BorderLayout.CENTER);
        this.setContentPane(jContentPane);
        // ���ô����С
        setSize(new Dimension(513, 248));// ���ó�ʼ������ķ���
        setLocationRelativeTo(null);// �������
    }
}
