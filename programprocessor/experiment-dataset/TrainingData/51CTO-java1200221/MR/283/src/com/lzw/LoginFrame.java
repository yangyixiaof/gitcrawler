package com.lzw;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.lzw.login.ProgressPanel;
import javax.swing.UIManager;

/**
 * @author ����ξ
 */
public class LoginFrame extends JFrame {
    
    /**
     * ��¼��ť���¼�������
     * 
     * @author ����ξ
     */
    private final class LoginActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // ��ʾ����ĵ�¼�������
            getGlassPane().setVisible(true);
        }
    }
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private ProgressPanel panel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton button;
    
    /**
     * �������ڷ���
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             ��֧�ֵ����
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * ��¼����Ĺ��췽��
     */
    public LoginFrame() {
        super();
        // ������¼�������
        panel = new ProgressPanel();
        // �ѵ�¼�����������Ϊ���嶥��
        setGlassPane(panel);
        jContentPane = new JPanel();
        this.setContentPane(jContentPane);
        jContentPane.setLayout(null);
        
        JLabel label = new JLabel("XX��¼����");
        label.setFont(new Font("SansSerif", Font.PLAIN, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(6, 6, 386, 44);
        jContentPane.add(label);
        
        JLabel label_1 = new JLabel("�û�����");
        label_1.setBounds(38, 75, 55, 18);
        jContentPane.add(label_1);
        
        JLabel label_2 = new JLabel("�ܡ��룺");
        label_2.setBounds(38, 115, 55, 18);
        jContentPane.add(label_2);
        
        textField = new JTextField();
        textField.setBounds(105, 69, 168, 30);
        jContentPane.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(105, 109, 168, 30);
        jContentPane.add(passwordField);
        
        button = new JButton("��¼");
        button.setBounds(287, 62, 90, 71);
        button.addActionListener(new LoginActionListener());
        jContentPane.add(button);
        // ���ô����С
        setSize(new Dimension(414, 243));
        setLocationRelativeTo(null);// �������
    }
}
