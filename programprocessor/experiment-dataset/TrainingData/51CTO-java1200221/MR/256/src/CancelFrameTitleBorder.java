import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class CancelFrameTitleBorder extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancelFrameTitleBorder frame = new CancelFrameTitleBorder();
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
public CancelFrameTitleBorder() {
    // ���ñ���ɫ
    getContentPane().setBackground(new Color(240, 255, 255));
    setUndecorated(true);// ȡ����������Ч��************
    setTitle("���ڽ��������ϵͳ");// ���ñ�����
    getContentPane().setLayout(null);
    setBounds(100, 100, 354, 206);
    setLocationRelativeTo(null);// �������
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final JLabel label = new JLabel();// �ñ�ǩ��ʾlogo
    label.setIcon(new ImageIcon(getClass().getResource("logo.png")));
    label.setBounds(10, 27, 112, 98);
    getContentPane().add(label);
    textArea = new JTextArea();// ���ı�����ʾϵͳ��Ϣ
    textArea.setOpaque(false);// �ؼ�͸��
    textArea.setText("ϵͳ��\n  Microsoft Windows Server 2003\n" +
            "  Standard Editon\n  Service Pack 2\n\n\n" +
            "��������������ϵͳ\n��Ȩ�����տƼ�");
    textArea.setBounds(154, 6, 187, 154);
    getContentPane().add(textArea);// ��ӿؼ�������
    JButton button = new JButton("\u5173\u95ED");// �������رա���ť
    button.addActionListener(new ActionListener() {// ��Ӱ�ť���¼�������
        public void actionPerformed(ActionEvent e) {
            do_button_actionPerformed(e);// ���ð�ť�¼�������
        }
    });
    button.setBounds(230, 172, 90, 30);
    getContentPane().add(button);// ��Ӱ�ť������
}
    
protected void do_button_actionPerformed(ActionEvent e) {
    dispose();// ���ٴ���
}
}
