import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class ShowBevelBorder extends JFrame {
    
    private JPanel contentPane;
    
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
                    ShowBevelBorder frame = new ShowBevelBorder();
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
    public ShowBevelBorder() {
        setTitle("ʵ�ֱ�ǩ�ؼ�������߿�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 132);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("���ݵ�����߿�");
        label.setBounds(150, 18, 100, 22);// ���ñ�ǩλ�����С
        contentPane.add(label);// ��ӱ�ǩ���������
        label.setHorizontalAlignment(SwingConstants.CENTER);// �ı�������ʾ
        label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
                null));// ���ñ�ǩ�ı߿�
        
        JLabel label_1 = new JLabel("ͻ�������߿�");
        label_1.setBounds(150, 52, 100, 22);// ���ñ�ǩλ�����С
        contentPane.add(label_1);// ��ӱ�ǩ���������
        label_1.setHorizontalAlignment(SwingConstants.CENTER);// �ı�������ʾ
        label_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
                null));// ���ñ�ǩ�ı߿�
        
        JLabel label_2 = new JLabel("���ǿɲ��ǰ�ť�����Ǳ�ǩ");
        label_2.setBounds(262, 20, 166, 54);// ���ñ�ǩλ�����С
        contentPane.add(label_2);// ��ӱ�ǩ���������
        
        JLabel label_3 = new JLabel("ָ���߿���ɫ");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);// �ı�������ʾ
        label_3.setBounds(6, 17, 124, 55);// ���ñ�ǩλ�����С
        contentPane.add(label_3);// ��ӱ�ǩ���������
        Color highlightOuter = new Color(255, 255, 0);// �߿�ĸ�����ɫ����
        Color highlightInner = new Color(255, 175, 175);
        label_3.setBorder(new BevelBorder(BevelBorder.LOWERED, highlightOuter,
                highlightInner, Color.BLUE, Color.RED));// ���ñ�ǩ�ı߿�
    }
}
