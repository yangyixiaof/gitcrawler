package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class UpdateMethodApplet extends Applet {
    boolean flag = false;// �����Ǳ���
    public void start() {
        repaint();// ���µ���paint����
    }
    public void paint(Graphics g) {
        g.setColor(Color.RED);// ������ɫ
        g.drawString("����ʹ��paint()�������Ƶ��ı���", 30, 120);// �����ı�
        g.setColor(Color.BLUE);// ������ɫ
        g.drawString("������ʹ��paint()�������Ƶ�ͼ�Ρ�", 30, 140);// �����ı�
        g.drawRect(30, 150, 50, 40);// ���ƾ���
        update(g);// ����updatae����
    }
    public void update(Graphics g) {
        if (flag) {
            g.clearRect(0, 0, 300, 220);// ��Ǳ���Ϊtrueʱ,�������
            flag = false;// ���ñ�Ǳ���Ϊfalse
        } else {
            flag = true;// ���ñ�Ǳ���Ϊtrue
        }
        g.setColor(Color.BLUE);// ������ɫ
        g.drawString("����ʹ��updatePaint()�������Ƶ��ı���", 30, 20);// �����ı�
        g.setColor(Color.RED);// ������ɫ
        g.drawString("������ʹ��updatePaint()�������Ƶ�ͼ�Ρ�", 30, 40);// �����ı�
        g.drawRect(30, 50, 50, 40);// ���ƾ���
    }
}
