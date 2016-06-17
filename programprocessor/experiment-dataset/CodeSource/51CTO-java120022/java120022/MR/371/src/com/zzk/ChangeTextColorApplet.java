package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class ChangeTextColorApplet extends Applet implements Runnable {
    Color color = new Color(0, 0, 255);// ������ɫ����
    public void init() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    public void paint(Graphics g) { // ��дpaint()����
        Graphics2D g2 = (Graphics2D) g;// ת��ΪGraphics2D����
        String value = "���ɫ������";// ���Ƶ��ı�
        int x = 10; // �ı�λ�õĺ�����
        int y = 110; // �ı�λ�õ�������
        Font font = new Font("����", Font.BOLD, 40); // �����������
        g2.setFont(font); // ��������
        g2.setColor(color);// ������ɫ
        g2.drawString(value, x, y); // �����ı�
    }
    public void run() {
        Random random = new Random();// �������������
        while (true) {
            int R = random.nextInt(256);// ���������ɫ��Rֵ
            int G = random.nextInt(256);// ���������ɫ��Gֵ
            int B = random.nextInt(256);// ���������ɫ��Bֵ
            color = new Color(R, G, B);// ������ɫ����
            repaint();// ����paint()����
            try {
                Thread.sleep(300);// ����300����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
