package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class ExclusiveOrOperationApplet extends Applet {
    public void paint(Graphics g) { // ��дpaint()����
        Graphics2D g2d = (Graphics2D) g; // ǿ��ת��ΪGraphics2D����
        Ellipse2D.Float ellipse1 = new Ellipse2D.Float(30, 80, 180, 80);// ������Բ����
        Ellipse2D.Float ellipse2 = new Ellipse2D.Float(80, 30, 80, 180);// ������Բ����
        Area area1 = new Area(ellipse1);// ������Բ����
        Area area2 = new Area(ellipse2);// ������Բ����
        area1.exclusiveOr(area2);// ��������ͼ�ν����������
        g2d.fill(area1);// �����������������ͼ��
        Ellipse2D.Float ellipse3 = new Ellipse2D.Float(270, 80, 180, 80);// ������Բ����
        Ellipse2D.Float ellipse4 = new Ellipse2D.Float(270, 30, 80, 180);// ������Բ����
        Area area3 = new Area(ellipse3);// ������Բ����
        Area area4 = new Area(ellipse4);// ������Բ����
        area3.exclusiveOr(area4);// �����������������ͼ��
        g2d.fill(area3);// �����������������ͼ��
    }
}
