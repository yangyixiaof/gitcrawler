package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawPolylineApplet extends Applet {
    public void paint(Graphics g) {
        String value = "����";
        int[] x = { 30, 60, 60, 20 };// ������ʾ���ߺ����������
        int[] y = { 30, 70, 150, 80 };// ������ʾ���������������
        int num1 = x.length;// ��ȡx,y���������ĳ���
        g.setColor(Color.blue);// ������ɫ
        g.drawPolyline(x, y, num1);// ��������
        int[] x0 = { 80, 110, 65, 80, 200 };// ������ʾ���ߺ����������
        int[] y0 = { 30, 70, 100, 120, 150 };// ������ʾ���������������
        int num2 = x0.length;// ��ȡx0,y0���������ĳ���
        g.drawPolyline(x0, y0, num2);// ��������
        g.drawString(value, 160, 130);// �����ı�
    }
}
