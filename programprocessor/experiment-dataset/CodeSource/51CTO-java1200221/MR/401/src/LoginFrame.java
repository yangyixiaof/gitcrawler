import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class LoginFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton button;
    
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
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 329, 185);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("����ϵͳ��¼����"); // ������ǩ�ؼ�
        label.setHorizontalAlignment(SwingConstants.CENTER);// ��ǩ�ı����ж���
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));// ���ñ�ǩ�ؼ�����
        label.setBounds(6, 6, 309, 51);
        contentPane.add(label);
        JLabel label_1 = new JLabel("�û�����"); // ������ǩ�ؼ�
        label_1.setBounds(16, 69, 55, 18);
        contentPane.add(label_1);
        JLabel label_2 = new JLabel("�ܡ��룺"); // ������ǩ�ؼ�
        label_2.setBounds(16, 103, 55, 18);
        contentPane.add(label_2);
        textField = new JTextField();// �����ı���
        textField.setBounds(65, 63, 242, 30);
        contentPane.add(textField);
        textField.setColumns(10);// �����ı�������
        passwordField = new JPasswordField();// ���������
        passwordField.setBounds(65, 99, 143, 30);
        contentPane.add(passwordField);
        button = new JButton("�ǡ�¼");// ������¼��ť��û�ж�λ
        contentPane.add(button);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {// ���������¼�������
        new Thread() {// ���������߳�
            @Override
            public void run() {
                for (int i = 0; i < 217; i++) {// ѭ�����ư�ť���ƶ�
                    button.setBounds(i, i > 99 ? 99 : i, 90, 30);// �ƶ���ť
                    getRootPane().setComponentZOrder(button, 0);// �Ѱ�ť�ö���ʾ
                    try {
                        sleep(1);// �߳�����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();// �����߳�
    }
}
