package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class HorseRaceLightTextApplet extends Applet implements Runnable {
    public void start() {
        Thread thread = new Thread(this);// �����߳�
        thread.start();// �����̶߳���
    }
    String value = "ӵ�б�̴ʵ䣬ѧϰ��������ɡ�";// �洢���Ƶ�����
    char[] drawChar = value.toCharArray();// ����������ת��Ϊ�ַ�����
    int[] x = new int[drawChar.length];// �洢ÿ���ַ����Ƶ�x���������
    int y = 100;// �洢���Ƶ�y����
    
    public void paint(Graphics g) {
        Image img = null;
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg"); // ����ͼ�����
        g.clearRect(0, 0, getWidth(), getHeight());// �����ͼ����������
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
        Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
        g.setFont(font);// ָ������
        g.setColor(Color.RED);// ָ����ɫ
        for (int j = drawChar.length - 1; j >= 0; j--) {
            g.drawString(drawChar[drawChar.length - 1 - j] + "", x[j], y);// �����ı�
        }
    }
    public void run() {
        boolean flag = false;// Ϊfalseʱ��ʾ��һ��ִ��,x������еȱȵ���,������еȲ�ݼ�
        while (true) {// ��ȡ����
            try {
                for (int i = drawChar.length - 1; i >= 0; i--) {
                    if (!flag) {
                        x[i] = x[i] + 20 * i;// x������еȱȵ���
                    } else {
                        x[i] = x[i] + 20;// x������еȱȵݼ�
                    }
                    if (x[i] >= 360 - 20) {// ���ڴ����ȼ�20��ֵʱ
                        x[i] = 0; // x����Ϊ0
                    }
                }
                repaint();// ���� paint()����
                if (!flag) {
                    flag = true;// ��ֵΪtrue;
                }
                Thread.sleep(1000);// ��ǰ�߳�����300����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(Graphics g) { // ��дupdate()������ֹ��˸
        paint(g);
    }
}
