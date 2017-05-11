package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 6760471507923160452L;
    private JTextField codeText;
    private JPasswordField pwdText;
    private JTextField nameText;
    ChineseCodePanel imageCode = null;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        setResizable(false);
        setTitle("������֤��");
        setBounds(100, 100, 426, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageCode = new ChineseCodePanel();// �������ʵ��
        imageCode.setBounds(170, 85, 106, 35);// ����λ��
        getContentPane().add(imageCode); // �����֤��
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (imageCode != null) {
                    imageCode.draw(); // ���÷���������֤��
                }
            }
        });
        button.setText("��һ��");
        button.setBounds(301, 90, 94, 28);
        panel.add(button);
        
        final JLabel label = new JLabel();
        label.setText("�û�����");
        label.setBounds(29, 25, 66, 18);
        panel.add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("��   �룺");
        label_1.setBounds(29, 59, 66, 18);
        panel.add(label_1);
        
        nameText = new JTextField();
        nameText.setBounds(85, 23, 310, 22);
        panel.add(nameText);
        
        pwdText = new JPasswordField();
        pwdText.setBounds(85, 57, 310, 22);
        panel.add(pwdText);
        
        final JLabel label_1_1 = new JLabel();
        label_1_1.setText("��֤�룺");
        label_1_1.setBounds(29, 95, 66, 18);
        panel.add(label_1_1);
        
        codeText = new JTextField();
        codeText.setBounds(85, 93, 77, 22);
        panel.add(codeText);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String username = nameText.getText();// ���ı����л�ȡ�û���
                String password = new String(pwdText.getPassword());// ��������л�ȡ����
                String code = codeText.getText();// ����������֤��
                String info = "";// �û���¼��Ϣ
                // �ж��û����Ƿ�Ϊnull��յ��ַ���
                if (username == null || username.isEmpty()) {
                    info = "�û���Ϊ�գ�";
                }
                // �ж������Ƿ�Ϊnull��յ��ַ���
                else if (password == null || password.isEmpty()) {
                    info = "����Ϊ�գ�";
                }
                // �ж���֤���Ƿ�Ϊnull��յ��ַ���
                else if (code == null || code.isEmpty()) {
                    info = "��֤��Ϊ�գ�";
                }
                // �ж� ��֤���Ƿ���ȷ
                else if (!code.equals(imageCode.getNum())) {
                    info = "��֤�����";
                }
                // ����û����������Ϊ"mrsoft"�����¼�ɹ�
                else if (username.equals("mrsoft") && password.equals("mrsoft")) {
                    info = "��ϲ����¼�ɹ�";
                } else {
                    info = "�û������������";
                }
                JOptionPane.showMessageDialog(null, info);// ͨ���Ի��򵯳��û���¼��Ϣ
            }
        });
        button_1.setText("��  ¼");
        button_1.setBounds(42, 134, 106, 28);
        panel.add(button_1);
        
        final JButton button_1_1 = new JButton();
        button_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                nameText.setText("");// ����û����ı�������
                pwdText.setText("");// ��������ı�������
                codeText.setText("");// �����֤���ı�������
            }
        });
        button_1_1.setText("��  ��");
        button_1_1.setBounds(191, 134, 106, 28);
        panel.add(button_1_1);
    }
    
}