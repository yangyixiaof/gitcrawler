import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;

public class LabelText extends JFrame {
    
    private JPanel contentPane;
    private JLabel label;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LabelText frame = new LabelText();
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
    public LabelText() {
        addWindowListener(new WindowAdapter() {// Ϊ������Ӵ��¼�������
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);// ���ô�����¼�������
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���Ĭ�Ϲرշ�ʽ
        setBounds(100, 100, 450, 179);// ���ô����С
        contentPane = new JPanel();// �����������
        setContentPane(contentPane);// �����������
        contentPane.setLayout(new BorderLayout(0, 0));// ���ô��岼��
        label = new JLabel("");// ������ǩ�ؼ�
        label.setHorizontalAlignment(SwingConstants.RIGHT);// �ı��Ҷ���
        contentPane.add(label);// ��ӱ�ǩ������
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        new Thread() {// �����µ������̶߳���
            @Override
            public void run() {// ��дrun()����
                int len = getWidth() / 12;// ��ȡ�����LED����
                String info = "Java��̴ʵ�";// �������������
                while (true) {// ��������ѭ��
                    String space = "";// �����հ��ַ���
                    for (int i = 0; i < len - info.length() - 2; i++) {// ����LED����
                        len = getWidth() / 12;// ��ȡ�����LED����
                        space += "��";// Ϊ�հ��ַ�����ӿո��ַ�
                        label.setText(info + space);// ���ñ�ǩ�ı�
                        try {
                            sleep(300);// �߳�����
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();// �����߳�
    }
}
