package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class RepaintMethodApplet extends Applet {
    static int iFlag = 0;// �����Ǳ���
    public void start() {
        iFlag++;// ������Ǳ�����ֵ
        repaint();// ���µ���paint()����
    }
    public void paint(Graphics g) {
        if (iFlag == 1) {
            g.drawString("����ʹ��paint()�������Ƶ��ı���", 30, 60);// �����ı�
        } else if (iFlag == 2) {
            g.setColor(Color.RED);// ������ɫ
            g.drawString("������ʹ��paint()�������Ƶ�ͼ�Ρ�", 30, 60);// �����ı�
            g.drawRect(30, 80, 50, 40);// ���ƾ���
        } else if (iFlag == 3) {
            g.setColor(Color.BLUE);// ������ɫ
            g.drawString("������ʹ��paint()�������Ƶ�ͼ�Ρ�", 30, 60);// �����ı�
            g.drawRect(30, 80, 50, 40);// ���ƾ���
        } else {
            g.setColor(Color.GREEN);// ������ɫ
            g.drawString("������ʹ��paint()�������Ƶ�ͼ�Ρ�", 30, 60);// �����ı�
            g.drawRect(30, 80, 50, 40);// ���ƾ���
        }
    }
}
