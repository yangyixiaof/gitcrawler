package com.zzk;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ShearTextApplet extends Applet {
    public void paint(Graphics g) { // ��дpaint()����
        Graphics2D g2 = (Graphics2D) g;// ת��ΪGraphics2D����
        String value = "��б����";// ���Ƶ��ı�
        int x = 10; // �ı�λ�õĺ�����
        int y = 190; // �ı�λ�õ�������
        Font font = new Font("�����п�", Font.BOLD, 72); // �����������
        g2.setFont(font); // ��������
        g2.shear(0.1, -0.4);// ��б����
        g2.drawString(value, x, y); // �����ı�
    }
}
