package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShutterFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private Image img = null; // ����ͼ�����
    private ShutterPanel shutterPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        ShutterFrame frame = new ShutterFrame();
        frame.setVisible(true);
    }
    
    public ShutterFrame() {
        super();
        URL imgUrl = ShutterFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        shutterPanel = new ShutterPanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(shutterPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ��Ҷ����Ч"); // ���ô������
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// ���� Kernel����
        ConvolveOp op = new ConvolveOp(kernel);// ����ConvolveOp����
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // ���˻���ͼ�����
        repaint();// ����paint()����
    }
    
    // ���������
    class ShutterPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this);// ����ͼ�����
            int y = 5;  // ֱ�߻��Ƶ��y����
            int space = 10;// ��һ��ֱ�ߵ�ƫ����
            Line2D.Float line = null;
            image = new BufferedImage(getWidth() + 10, getHeight(),
                    BufferedImage.TYPE_INT_ARGB);// ��������ͼ�����
            Graphics2D gs2d = (Graphics2D) image.getGraphics();// ��û���ͼ������Graphics2D����
            BasicStroke stroke = new BasicStroke(7); // ���������7�ıʻ�����
            gs2d.setStroke(stroke);// ���ñʻ�����
            gs2d.setColor(Color.WHITE);// ָ����ɫ
            while (y <= getHeight()) {
                line = new Line2D.Float(0, y, getWidth(), y);// ����ֱ�߶���
                gs2d.draw(line);// �ڻ���ͼ������ϻ���ֱ��
                y = y + space;// ������һ��ֱ�ߵ�y����
            }
            for (int i = 0; i < 3; i++) {// ��forѭ����ʵ��3��ģ��
                float[] elements = new float[9];// �����ʾ���ط���������
                for (int j = 0; j < 9; j++) {
                    elements[j] = 0.11f;// Ϊ���鸳ֵ
                }
                convolve(elements);// ���÷�����ʵ��ģ������
            }
            g2.drawImage(image, 0, 0, this);// ���ƻ���ͼ�����
        }
    }
}
