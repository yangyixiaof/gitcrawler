package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ShadowTextApplet extends Applet {
    public void paint(Graphics g) { // ��дpaint()����
        String value = "��Ӱ����";
        int x = 10; // �ı�λ�õĺ�����
        int y = 120; // �ı�λ�õ�������
        Font font = new Font("�����п�", Font.BOLD, 80); // �����������
        g.setFont(font); // ��������
        g.setColor(Color.GRAY);// ������ɫΪ��ɫ
        g.drawString(value, x, y); // �����ı�
        x += 3;// �������Ƶ�ĺ�����ֵ
        y += 3;// �������Ƶ��������ֵ
        g.setColor(Color.BLACK);// ������ɫ��ɫ
        g.drawString(value, x, y); // �����ı�
    }
}
