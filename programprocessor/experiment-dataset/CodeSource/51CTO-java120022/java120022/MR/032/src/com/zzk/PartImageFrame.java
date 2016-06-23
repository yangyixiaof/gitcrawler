package com.zzk;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PartImageFrame extends JFrame {
    private Image img = null;  // ����ͼ�����
    private PartImagePanel imagePanel = null;  // ����ͼ��������
    private int dx1, dy1, dx2, dy2;   // Ŀ����ε�һ������ڶ����ǵ�X��Y����
    private int sx1, sy1, sx2, sy2;   // Դ���ε�һ������ڶ����ǵ�X��Y����
    public static void main(String args[]) {
        PartImageFrame frame = new PartImageFrame();
        frame.setVisible(true);
    }
    public PartImageFrame() {
        super();
        URL imgUrl = PartImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        dx2 = sx2 = 340; // ��ʼ��ͼ���С
        dy2 = sy2 = 200; // ��ʼ��ͼ���С
        imagePanel = new PartImagePanel();  // ����ͼ��������
        this.setBounds(200, 160, 355, 276); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ����в�λ�����ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("��תͼ��"); // ���ô������
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton btn_h = new JButton();
        btn_h.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // ����3�д������ڽ���sx1��sx2��ֵ
                int x = sx1;
                sx1 = sx2;
                sx2 = x;
                imagePanel.repaint();  // ���µ���������paint()����
            }
        });
        btn_h.setText("ˮƽ��ת");
        panel.add(btn_h);
        final JButton btn_v = new JButton();
        btn_v.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // ����3�д������ڽ���sy1��sy2��ֵ
                int y = sy1;
                sy1 = sy2;
                sy2 = y;
                imagePanel.repaint();// ���µ���������paint()����
            }
        });
        btn_v.setText("��ֱ��ת");
        panel.add(btn_v);
    }
    // ���������
    class PartImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �����ͼ�����ĵ�����
            g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, this);// ����ͼ��
        }
    }
}
