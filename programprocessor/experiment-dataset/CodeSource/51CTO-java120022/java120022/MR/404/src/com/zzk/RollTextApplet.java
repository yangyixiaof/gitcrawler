package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class RollTextApplet extends Applet implements Runnable {
    String value = "����ͼ��������ַ��http://www.mingribook.com";// ��Ż��Ƶ�����
    int x;// ���ú�����
    int y;// ����������
    public void init() { // ��ʼ������
        x = 316;// �洢���Ƶ��x����
        y = 190;// �洢���Ƶ��y����
    }
    public void start() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    public void paint(Graphics g) {
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg");// ��ȡͼƬ��Դ·��
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
        g.clearRect(0, 0, 0, 230);// �����ͼ�����ĵ�����
        g.setColor(Color.BLACK);// ������ɫ
        Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
        g.setFont(font);// ��������
        g.drawString(value, x, y);// �����ı�
    }
    public void run() {
        try {
            while (true) { // ��ȡ����
                Thread.sleep(50); // ��ǰ�߳�����1��
                if (x <= -440) {// ���������Ը�����Ҫ���е���
                    x = 316;// x���궨λ�����Ҳ�
                } else {
                    x -= 2;// x��������
                }
                repaint();// ����paint()����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Graphics g) { // ��дupdate()������ֹ��˸
        paint(g);
    }
}
