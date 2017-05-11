package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

/**
 * @author 李钟尉
 */
public class LoginFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JTextField userName = null;
    private JPasswordField password = null;
    private JButton loginButton = null;
    
    /**
     * 创建用户名文本框
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getUserName() {
        if (userName == null) {
            userName = new JTextField();// 创建文本框
            userName.setBackground(new Color(0, 0, 0, 0));// 设置文本框透明背景色
            // 设置文本框位置与大小
            userName.setBounds(new Rectangle(70, 26, 162, 21));// 设置文本框大小
            MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(0, 250,
                    154));// 创建边框
            userName.setBorder(border);// 色绘制边框
        }
        return userName;
    }
    
    /**
     * 创建密码框
     * 
     * @return javax.swing.JPasswordField
     */
    private JPasswordField getPassword() {
        if (password == null) {
            password = new JPasswordField();// 创建密码框
            // 设置密码框位置和大小
            password.setBounds(new Rectangle(71, 57, 159, 22));
            password.setBackground(new Color(0, 0, 0, 0));// 设置透明颜色
            password.setOpaque(false);// 设置透明
            password.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255, 215,
                    0)));// 设置边框
            password.setEchoChar('★');// 设置密码框字符
        }
        return password;
    }
    
    /**
     * 创建登录按钮
     * 
     * @return javax.swing.JButton
     */
    private JButton getLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton("登录");
            // 设置按钮位置与大小
            loginButton.setBounds(new Rectangle(244, 22, 68, 68));
        }
        return loginButton;
    }
    
    /**
     * 主类的入口方法
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             不支持的外观
     */
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 设置窗体外观
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 创建登录窗体
                LoginFrame loginFrame = new LoginFrame();
                // 显示登录窗体
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * 登录窗体的构造方法
     */
    public LoginFrame() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("文本框的下划线边框");
        jContentPane = new JPanel();
        // 设置窗体内容面板
        this.setContentPane(jContentPane);
        // 设置布局管理器
        jContentPane.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(getUserName(), null);// 添加文本框
        panel.add(getPassword(), null);// 添加密码框
        panel.add(getLoginButton(), null);// 添加登录按钮
        jContentPane.add(panel, BorderLayout.CENTER);
        
        JLabel label = new JLabel("用户名：");
        label.setBounds(22, 26, 55, 18);
        panel.add(label);
        
        JLabel label_1 = new JLabel("密　码：");
        label_1.setBounds(22, 59, 55, 18);
        panel.add(label_1);
        // 设置窗体大小
        setSize(new Dimension(375, 143));
        setLocationRelativeTo(null);// 窗体居中
    }
}
