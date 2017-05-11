package com.zzk;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼƬ��Դ
        rotatePanel = new RotatePanel(); // ������תͼ���������
        this.setBounds(150, 120, 380, 277);// ���ô����С��λ��
        add(rotatePanel);// �ڴ����Ϸ���ͼ�����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ��ת����"); // ���ô������
        Thread th = new Thread(rotatePanel);// �����̶߳���
        th.start();// �����߳�
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel implements Runnable {
        int angle = 0;// ��ʼ��ת�Ƕ�
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.translate(190, 120);// ƽ��������
            g2.clearRect(-190, -120, getWidth(), getHeight());// ��������ϵ�����
            g2.rotate(Math.toRadians(angle)); // ��ת����
            g2.drawImage(img, -95, -80, 190, 160, this);// ����ָ����С��ͼƬ
        }
        public void run() {
            while (true) {
                angle = (angle + 10) % 360;// ������ת�Ƕ�
                try {
                    Thread.sleep(200);// ����200����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// ����paint()����
            }
        }
    }
}
