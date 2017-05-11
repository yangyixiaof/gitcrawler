package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DynamicDrawTextApplet extends Applet implements Runnable {
    int x = 20;// ��ʼ���x����
    int y = 30;// ��ʼ���y����
    String textStrings = "СӦ�ó���������ͼ��\nСӦ�ó����з�תͼ��\nСӦ�ó�������תͼ��\nСӦ�ó�������бͼ��";// ��Ҫ��̬���Ƶ��ַ���
    String value = "";// �洢�ַ����еĵ����ַ�
    public void init() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    public void paint(Graphics g) {
        Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
        g.setFont(font);// ָ������
        g.setColor(Color.RED);// ָ����ɫ
        g.drawString(value, x, y);// �����ı�
    }
    public void update(Graphics g) {// ��дupdate��������ֹ�޷���ʾ���Ƶ���������
        paint(g);// ����paint()����
    }
    public void run() {
        try {
            for (int i = 0; i < textStrings.length(); i++) {
                Thread.sleep(400); // ��ǰ�߳�����400����
                value = textStrings.substring(i, i + 1);// ��ȡ�ַ����е�һ���ַ�
                if (value.equals("\n")) {// �ǻ��з�
                    x = 20;// ��һ����ʼ���x����
                    y += 30;// ��һ���ı���y����
                } else {// ���ǻس����з�
                    x += 20;// ��ǰ����һ���ֵ�x����
                }
                repaint();// ����paint()����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
