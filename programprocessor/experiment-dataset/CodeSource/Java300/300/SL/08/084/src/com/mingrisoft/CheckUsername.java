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
import javax.swing.border.EmptyBorder;

public class CheckUsername extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8690277454603699636L;
    private JPanel contentPane;
    private JTextField usernameField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckUsername frame = new CheckUsername();
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
    public CheckUsername() {
        setTitle("\u5BC6\u7801\u627E\u56DE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 312, 181);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
        label.setBounds(22, 54, 58, 17);
        contentPane.add(label);
        
        usernameField = new JTextField();
        usernameField.setBounds(77, 51, 180, 23);
        contentPane.add(usernameField);
        usernameField.setColumns(10);
        
        JButton button = new JButton("\u63D0\u4EA4");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(67, 88, 71, 25);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u5173\u95ED");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(165, 88, 71, 25);
        contentPane.add(button_1);
        
        JLabel label_1 = new JLabel(
                "<html>\u8BF7\u8F93\u5165\u4F60\u8981<font size=5 color=red>\u627E\u56DE\u5BC6\u7801</font>\u7684\u7528\u6237\u540D\u79F0\u3002</html>");
        label_1.setBounds(12, 14, 343, 25);
        contentPane.add(label_1);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String name = usernameField.getText();// ��ȡ�û�����
        if (name.equals("admin")) {// �ж��Ƿ����Ա�˺�
            JOptionPane.showMessageDialog(null, "�Բ�������û����ǹ���Ա�ģ��������");
        } else if (name.equals("mingri")) {// �ж��Ƿ�ע���û�
            JOptionPane.showMessageDialog(null, "���û�����Ӧ�������Ѿ����͵�ע��ʱ�����䣬�����");
        } else {// �������û�������ʾ�Ի���
            JOptionPane.showMessageDialog(null, "��������û��������ڣ�����Caps Lock���Ƿ��¡�");
        }
    }
    protected void do_button_1_actionPerformed(ActionEvent e) {
        dispose();
    }
}
