package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawRectApplet extends Applet {
    public void paint(Graphics g) {
        String value = "������";
        int x = 42;// ���εĺ�����
        int y = 27;// ���ε�������
        int width = 122;// ���εĿ��
        int height = 64;// ���εĸ߶�
        g.setColor(Color.BLUE);// ������ɫ��ɫ
        g.drawRect(x, y, width, height);// ���ƾ���
        int x0 = 54;// ���εĺ�����
        int y0 = 37;// ���ε�������
        int width0 = 130;// ���εĿ��
        int height0 = 69;// ���εĸ߶�
        g.setColor(Color.BLUE);// ������ɫ��ɫ
        g.drawRect(x0, y0, width0, height0);// ���ƾ���
        int x1 = 67;// ���εĺ�����
        int y1 = 48;// ���ε�������
        int width1 = 137;// ���εĿ��
        int height1 = 73;// ���εĸ߶�
        g.setColor(Color.BLUE);// ������ɫ��ɫ
        g.drawRect(x1, y1, width1, height1);// ���ƾ���
        g.drawString(value, 185, 143);// �����ı�
    }
}
