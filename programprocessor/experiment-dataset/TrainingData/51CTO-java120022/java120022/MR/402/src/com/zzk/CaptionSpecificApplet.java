package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class CaptionSpecificApplet extends Applet implements Runnable {
    int x = 50;// �洢���Ƶ��x����
    int y = 216;// �洢���Ƶ��y����
    String value = "����ͼ��������ַ";// �洢���Ƶ�����
    public void start() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    public void paint(Graphics g) {
        g.clearRect(0, 0, 316, 237);// �����ͼ����������
        Image img = null;// ����ͼƬ����
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg");
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
        Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
        g.setFont(font);// ָ������
        g.setColor(Color.RED);// ָ����ɫ
        g.drawString(value, x, y);// �����ı�
    }
    public void run() {
        try {
            while (true) { // ��ȡ����
                Thread.sleep(100); // ��ǰ�߳�����1��
                if (y <= 216 - 50) {// ����Ѿ������ƶ�50����
                    y = 216;// y���궨λ�����·�
                    if (value.equals("����ͼ��������ַ")) {
                        value = "http://www.mingribook.com";// �ı���Ƶ�����
                    } else {
                        value = "����ͼ��������ַ";// �ı���Ƶ�����
                    }
                } else {// �����û�����ƶ���50����
                    y -= 2;// y��������
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
