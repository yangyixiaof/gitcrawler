import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.lzw.ip.IpField;
import javax.swing.UIManager;

public class IPFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    
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
                    IPFrame frame = new IPFrame();
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
    public IPFrame() {
        setTitle("IP�����ı���ؼ�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 355, 222);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel label = new JLabel("���÷�����������IP��ַ");// ���������ǩ
        label.setHorizontalAlignment(SwingConstants.CENTER);// ���ж���
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));// ��������
        label.setBounds(6, 6, 298, 39);
        contentPane.add(label);
        JLabel label_1 = new JLabel("���������ƣ�");// ������ǩ
        label_1.setBounds(6, 57, 83, 18);// ���ñ�ǩ��С
        contentPane.add(label_1);
        JLabel label_2 = new JLabel("���������");// ������ǩ
        label_2.setBounds(6, 95, 83, 18);// ���ñ�ǩ��С
        contentPane.add(label_2);
        textField = new JTextField();// ����������������Ƶ��ı���
        textField.setBounds(82, 51, 251, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        JButton button = new JButton("ȷ��");// ����ȷ����ť
        button.setBounds(54, 132, 90, 30);
        contentPane.add(button);
        JButton button_1 = new JButton("�ر�");// �����رհ�ť
        button_1.setBounds(177, 132, 90, 30);
        contentPane.add(button_1);
        IpField ipField = new IpField();// ����IP�ı���ؼ�
        ipField.setBounds(82, 88, 251, 25);// ���ÿؼ���С
        contentPane.add(ipField);
    }
}
