package com.zzk;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class SetFontStyleApplet extends Applet {
    public void paint(Graphics g) {
        Font font = new Font("����", Font.PLAIN, 16);// �����������������ʽΪ��ͨ����
        g.setFont(font);// ��������
        g.drawString("��ͨ����", 30, 20);// �����ı�
        font = new Font("����", Font.ITALIC, 22);// �����������������ʽΪ��б����
        g.setFont(font);// ��������
        g.drawString("��б����", 30, 60);// �����ı�
        font = new Font("����", Font.BOLD, 28);// �����������������ʽΪ�Ӵ�����
        g.setFont(font);// ��������
        g.drawString("�Ӵ�����", 30, 120);// �����ı�
    }
}
