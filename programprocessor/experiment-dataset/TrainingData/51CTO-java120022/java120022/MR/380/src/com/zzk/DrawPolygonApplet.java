package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawPolygonApplet extends Applet {
    public void paint(Graphics g) {
        String value = "���ƶ����";
        int x[] = { 60, 103, 170, 150, 120 };// ��������εĺ���������
        int y[] = { 80, 180, 140, 80, 120 };// ��������ε�����������
        int num = x.length;// ȡ�ö����x,y���������ĳ���
        g.setColor(Color.blue);// ������ɫ
        g.drawPolygon(x, y, num);// // ���ƶ����
        g.drawString(value, 120, 70);// �����ı�
    }
}
