import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class IconTitledBorder extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IconTitledBorder frame = new IconTitledBorder();
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
    public IconTitledBorder() {
        setTitle("��ͼ��߿�ı���߿�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 398, 271);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 239, 213));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));// ����ͼ�����
        JPanel panel_12 = new JPanel();// ����������
        panel_12.setOpaque(false);// ���͸��
        MatteBorder matteBorder = new MatteBorder(16, 16, 16, 16, icon);// ����MatteBorder�߿�
        Font font = new Font("����", Font.ITALIC | Font.BOLD, 24);// ��������
        // ����TitledBorder�߿򲢰�MatteBorder�߿���Ϊ���췽���Ĳ�������Ƕ��
        TitledBorder titledBorder = new TitledBorder(matteBorder, "ͼ��߿�ı���",
                TitledBorder.LEADING, TitledBorder.ABOVE_TOP, font, Color.BLACK);
        panel_12.setBorder(titledBorder);// �����������ʹ��TitledBorder�߿�
        contentPane.add(panel_12);
    }
}
