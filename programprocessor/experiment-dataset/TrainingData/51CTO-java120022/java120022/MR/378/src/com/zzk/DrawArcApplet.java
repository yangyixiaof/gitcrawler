package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawArcApplet extends Applet {
    public void paint(Graphics g) {
        String value = "����";
        int x = 35;// ��λ�õĺ�����
        int y = 65;// ��λ�õ�������
        int l = 150;// ���ĳ���
        int width = 80;// ���Ŀ��
        int startAngle = 10;// ������ʼ�Ƕ�
        int endAngle = -120;// ��ֹ����ǰɨ���ĽǶ�
        g.setColor(Color.red);// ������ɫ
        g.drawArc(x + 20, y, l, width, startAngle, endAngle);// ���ƻ�
        
        int x0 = 5;// ��λ�õĺ�����
        int y0 = 40;// ��λ�õ�������
        int l0 = 180;// ���ĳ���
        int width0 = 120;// ���Ŀ��
        int startAngle0 = 70;// ������ʼ�Ƕ�
        int endAngle0 = 180;// ��ֹ����ǰɨ���ĽǶ�
        g.setColor(Color.red);// ������ɫ
        g.drawArc(x0 + 20, y0, l0, width0, startAngle0, endAngle0);// ���ƻ�
        
        int x1 = 19;// ��λ�õĺ�����
        int y1 = 90;// ��λ�õ�������
        int l1 = 200;// ���ĳ���
        int width1 = 100;// ���Ŀ��
        int startAngle1 = 5;// ������ʼ�Ƕ�
        int endAngle1 = 300;// ��ֹ����ǰɨ���ĽǶ�
        g.setColor(Color.red);// ������ɫ
        g.drawArc(x1 + 20, y1, l1, width1, startAngle1, endAngle1);// ���ƻ�
        g.drawString(value, 195, 160);
        
    }
}
