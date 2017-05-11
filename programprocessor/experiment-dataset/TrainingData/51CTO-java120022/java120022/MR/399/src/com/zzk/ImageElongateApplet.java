package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class ImageElongateApplet extends Applet implements Runnable {
    private boolean flag = true;// ������Ǳ���
    private static float xw;// �������ͼ���ȵı���
    public void init(){
        xw = 0.5f;// ��ʼ��ͼ����
    }
    public void start(){
        Thread th = new Thread(this);// �����̶߳���
        th.start();// �����̶߳���
    }
    public void paint(Graphics g) {
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD4.jpg");// ���ͼƬ��Ϣ
        int w = img.getWidth(this);// ����ͼ��Ŀ��
        int h = img.getHeight(this);// ����ͼ��ĸ߶�
        Graphics2D g2d = (Graphics2D) g;// ��gת��Ϊ�������õ�Graphics2D
        g2d.drawImage(img, w - 50, h, this);// ����ͼ��
        AffineTransform tr = new AffineTransform(xw, 0, 0, 1, 150, h);// ��������任����������ñ任����һ��������
        g2d.drawImage(img, tr, this);// ����ͼ��
        
    }
    @Override
    public void run() {
        while (true) {
            if (flag) {// ��Ǳ���Ϊtrueʱִ��
                xw += 0.1f;// ʹ��ȱ��
                if (xw > 2.0f) {// ��ȴ���2.0ʱ
                    flag = false;// ��Ǳ���Ϊfalse
                }
            } else {// ��Ǳ���Ϊfalseʱִ��
                xw -= 0.1f;// ʹ��ȱ�С
                if (xw < 0.5f) {// ���С��2.0ʱ
                    flag = true;// ��Ǳ���Ϊtrue
                }
            }
            try {
                Thread.sleep(200);// ����200����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();// ���µ���paint()����
        }
    }
}
