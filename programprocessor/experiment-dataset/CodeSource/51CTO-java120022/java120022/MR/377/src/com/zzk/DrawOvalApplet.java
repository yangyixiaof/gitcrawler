package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawOvalApplet extends Applet {
    public void paint(Graphics g) {
        String value = "����Բ";
        int x = 25;// ��Բλ�ú�����
        int y = 40;// ��Բλ��
        int xr = 150;// ��Բ�ĺ�����뾶
        int yr = 150;// ��Բ��������뾶
        g.drawOval(x, y, xr, yr);// ������Բ
        int x0 = 67;// ��Բλ�ú�����
        int y0 = 40;// ��Բλ��
        int xr0 = 65;// ��Բ�ĺ�����뾶
        int yr0 = 150;// ��Բ��������뾶
        g.setColor(Color.blue);// ������ɫ
        g.drawOval(x0, y0, xr0, yr0);// ������Բ
        g.drawString(value, 180, 190);// �����ı�
    }
}
