package com.zzk;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class SetTextPositionApplet extends Applet {
    public void paint(Graphics g) {
        Font font = new Font("����", Font.PLAIN, 32);// �����������
        g.setFont(font);// ��������
        g.drawString("����", 65, 40);// �����ı�����λ������Ϊ(65��40)
        font = new Font("����", Font.PLAIN, 16);// �����������
        g.setFont(font);// ��������
        g.drawString("����һ������", 50, 80);// �����ı�����λ������Ϊ(50��80)
        g.drawString("���Ķ�������", 50, 120);// �����ı�����λ������Ϊ(50��120)
        g.drawString("������������", 50, 160);// �����ı�����λ������Ϊ(50��160)
    }
}
