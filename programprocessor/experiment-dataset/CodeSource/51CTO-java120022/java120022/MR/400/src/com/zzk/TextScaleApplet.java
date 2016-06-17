package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class TextScaleApplet extends Applet implements Runnable {
    public void start() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    private Image img = null; // ����ͼ�����
    boolean flag = false;// �����Ǳ���,�û�����x��ֵ
    int x = 12;// �����ʾ�����С�ı���x
    Font font = new Font("���Ŀ���", Font.BOLD, 42);// �����������
    public void paint(Graphics g) {
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg");// ��ȡͼƬ��Դ��·��
        Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
        g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
        g2.setFont(font);// ָ������
        g2.setColor(Color.red);// ָ����ɫ
        g2.drawString("ѧ��ֹ��", 30, 120);// �����ı�
    }
    public void run() {
        while (true) {
            if (flag) { // flagΪtrueʱ
                x -= 2; // x���м�1����
                if (x <= 12) {// xС�ڵ���12ʱ
                    x = 12; // x����12
                    flag = false;// Ϊflag��ֵΪfalse
                }
            } else {// flagΪfalseʱ
                x += 2;// x���м�1����
                if (x >= 72) {// x���ڵ���72ʱ
                    x = 72;// x����72
                    flag = true;// Ϊflag��ֵΪtrue
                }
            }
            font = new Font("���Ŀ���", Font.BOLD, x);// ���´����������
            repaint();// ����paint()����
            try {
                Thread.sleep(50);// ����50����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//    public void update(Graphics g) {
//        paint(g);
//    }
}
