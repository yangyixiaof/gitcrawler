package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SolidTextApplet extends Applet {
    public void paint(Graphics g) { // ��дpaint()����
        String value = "��������Ч��";
        int x = 10; // �ı�λ�õĺ�����
        int y = 120; // �ı�λ�õ�������
        Font font = new Font("����", Font.BOLD, 72); // �����������
        g.setFont(font); // ��������
        g.setColor(Color.GRAY);// ������ɫΪ��ɫ
        int i = 0;// ѭ������
        while (i < 8) {
            g.drawString(value, x, y); // �����ı�
            x += 1;// �������Ƶ�ĺ�����ֵ
            y += 1;// �������Ƶ��������ֵ
            i++;// ����ѭ��������ֵ
        }
        g.setColor(Color.BLACK);// ������ɫ��ɫ
        g.drawString(value, x, y); // �����ı�
    }
}
