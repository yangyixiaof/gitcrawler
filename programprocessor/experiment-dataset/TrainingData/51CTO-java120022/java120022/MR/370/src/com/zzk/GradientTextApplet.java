package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GradientTextApplet extends Applet {
    public void paint(Graphics g) { // ��дpaint()����
        Graphics2D g2 = (Graphics2D) g;// ת��ΪGraphics2D����
        String value = "����ɫ����";// ���Ƶ��ı�
        int x = 15; // �ı�λ�õĺ�����
        int y = 120; // �ı�λ�õ�������
        Font font = new Font("����", Font.BOLD, 60); // �����������
        g2.setFont(font); // ��������
        GradientPaint paint = new GradientPaint(20, 20, Color.BLUE, 100, 120,
                Color.RED, true);// ����ѭ�������GradientPaint����
        g2.setPaint(paint);// ���ý���
        g2.drawString(value, x, y); // �����ı�
    }
}
