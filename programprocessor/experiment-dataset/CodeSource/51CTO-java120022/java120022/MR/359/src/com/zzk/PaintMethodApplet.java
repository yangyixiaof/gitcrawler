package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class PaintMethodApplet extends Applet {
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);// ������ɫ
        g.drawString("����ʹ��paint()�������Ƶ��ı���", 30, 40);// �����ı�����
        g.setColor(Color.RED);// ������ɫ
        g.drawString("������ʹ��paint()�������Ƶ�ͼ�Ρ�", 30, 80);// �����ı�����
        g.drawRect(30, 100, 50, 40);// ����ͼ��
    }
}
