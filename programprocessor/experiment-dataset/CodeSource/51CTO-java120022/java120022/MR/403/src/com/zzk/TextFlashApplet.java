package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class TextFlashApplet extends Applet implements Runnable {
    boolean flag = true;// ��Ǳ���
    String value = "";// ��Ż������ݵı���
    public void start() {
        Thread thread = new Thread(this);// �����̶߳���
        thread.start();// �����̶߳���
    }
    public void paint(Graphics g) {
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg");
        g.clearRect(0, 0, 310, 230);// �����ͼ�����ĵ�����
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        Font font = new Font("���Ŀ���", Font.BOLD, 42);// �����������
        g.setFont(font);// ָ������
        g.setColor(Color.red);// ָ����ɫ
        g.drawString(value, 30, 110);// �����ı�
    }
    public void run() {
        try {
            while (true) { // ��ȡ����
                Thread.sleep(150);// ��ǰ�߳�����150��
                if (flag) {// flagΪtrue
                    flag = false; // ��ֵΪfalse
                    value = "JAVA��̴ʵ�";// Ϊvalue��ֵ
                } else {
                    flag = true;// ��ֵΪtrue
                    value = "";// ��ֵΪ���ַ���
                }
                repaint();// ����paint()����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
