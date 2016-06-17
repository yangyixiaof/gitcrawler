package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img1 = null; // ����ͼ�����
    private Image img2 = null; // ����ͼ�����
    private PictureFadePanel pictureFadePanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        URL imgUrl = PictureFadeFrame.class.getResource("/img/img.jpg");// ��ȡͼƬ��Դ��·��
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imgUrl = PictureFadeFrame.class.getResource("/img/imag.jpg");// ��ȡͼƬ��Դ��·��
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        pictureFadePanel = new PictureFadePanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(pictureFadePanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(pictureFadePanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ���뵭����Ч"); // ���ô������
    }
    
    // ���������
    class PictureFadePanel extends JPanel implements Runnable {
        boolean flag = true;// �����Ǳ��������ڿ���x��ֵ
        float x = 0.0f;// �����ʾ͸���ȵı���x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// ��ñ�ʾ͸���ȵ�AlphaComposite����
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.clearRect( 0, 0, getWidth(), getHeight());// ����ͼ��
            g2.drawImage(img1, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
            g2.setComposite(alpha);// ָ��AlphaComposite����
            g.drawImage(img2, 50, 40, getWidth() - 100, getHeight() - 80, this);// ���Ƶ���͸���Ⱥ��ͼƬ��ʵ��ͼƬ���뵭����Ч
        }
        public void run() {
            while (true) {
                if (flag) { // flagΪtrueʱ
                    x -= 0.1f; // x���м�0.1����
                    if (x <= 0.0f) {// xС�ڵ���0.0fʱ
                        x = 0.0f; // x����0.0f
                        flag = false;// Ϊflag��ֵΪfalse
                    }
                } else {// flagΪfalseʱ
                    x += 0.1f;// x���м�0.1����
                    if (x >= 1.0f) {// x���ڵ���1.0fʱ
                        x = 1.0f;// x����1.0f
                        flag = true;// Ϊflag��ֵΪtrue
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// ���»�ñ�ʾ͸���ȵ�AlphaComposite����
                repaint();// ����paint()����
                try {
                    Thread.sleep(200);// ����200����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
