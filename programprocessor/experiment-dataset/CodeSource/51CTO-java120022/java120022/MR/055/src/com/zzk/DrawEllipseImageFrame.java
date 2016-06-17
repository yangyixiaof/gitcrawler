package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseImageFrame extends JFrame {
    private Image img = null;  // ����ͼ�����
    private EllipseImagePanel imagePanel = null;  // ����ͼ��������
    public static void main(String args[]) {
        DrawEllipseImageFrame frame = new DrawEllipseImageFrame();
        frame.setVisible(true);
    }
    public DrawEllipseImageFrame() {
        super();
        URL imgUrl = DrawEllipseImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new EllipseImagePanel();  // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("����Բ����ʾͼ��"); // ���ô������
    }
    // ���������
    class EllipseImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(img, 20, 20, 260, 160, this);// ����ͼ��
            Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, getWidth(),getHeight());// �������ζ���
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 20, 260, 160);// ������Բ�ζ���
            Area area1 = new Area(rectangle);   // �����������
            Area area2 = new Area(ellipse);   // ����������Բ
            area1.subtract(area2);// ����������״���м�����
            g2.setColor(getBackground());// ���û�ͼ�����ĵ���ɫΪ���ı�����ɫ
            g2.fill(area1);  // ���Ƽ�������������״
        }
    }
}
