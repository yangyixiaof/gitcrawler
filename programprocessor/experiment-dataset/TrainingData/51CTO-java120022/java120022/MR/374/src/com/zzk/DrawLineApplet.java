package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawLineApplet extends Applet {
    public void paint(Graphics g) {
        String value = "��ֱ��";
        int x = 215;// ֱ�ߵĺ�����(��)
        int y = 45;// ֱ�ߵ�������(��)
        int x1 = 15;// ֱ�ߵĺ�����(��)
        int y1 = 45;// ֱ�ߵ�������(��)
        int x2 = 300;// ֱ�ߵĺ�����(��)
        int y2 = 100;// ֱ�ߵ�������(��)
        int x3 = 60;// ֱ�ߵĺ�����(��)
        int y3 = 100;// ֱ�ߵ�������(��)
        g.setColor(Color.blue);// ������ɫ��ɫ
        g.drawLine(x, y, x1, y1);// ����ֱ��
        g.drawLine(x2, y2, x3, y3);// ����ֱ��
        g.drawString(value, 120, 75);// �����ı�
    }
}
