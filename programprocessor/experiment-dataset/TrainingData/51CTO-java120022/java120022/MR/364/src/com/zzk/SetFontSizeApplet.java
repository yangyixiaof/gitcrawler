package com.zzk;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class SetFontSizeApplet extends Applet {
    public void paint(Graphics g) {
        Font font = new Font("����", Font.PLAIN, 16);// ����������������СΪ16
        g.setFont(font);// ��������
        g.drawString("�����СΪ16", 30, 20);// �����ı�
        font = new Font("����", Font.PLAIN, 22);// ����������������СΪ22
        g.setFont(font);// ��������
        g.drawString("�����СΪ22", 30, 60);// �����ı�
        font = new Font("����", Font.PLAIN, 36);// ����������������СΪ36
        g.setFont(font);// ��������
        g.drawString("�����СΪ36", 30, 120);// �����ı�
    }
}
