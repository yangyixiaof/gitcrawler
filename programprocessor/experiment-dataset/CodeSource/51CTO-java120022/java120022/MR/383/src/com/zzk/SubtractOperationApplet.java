package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SubtractOperationApplet extends Applet {
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;// ǿ��ת��ΪGraphics2D����
        Rectangle2D.Float rect1 = new Rectangle2D.Float(80, 20, 160, 160);// �������ζ���
        Ellipse2D.Float ellipse1 = new Ellipse2D.Float(130, 80, 140, 140);// ������Բ����
        Area area1 = new Area(rect1);// �����������
        Area area2 = new Area(ellipse1);// ����������Բ
        area1.subtract(area2);// ��������ͼ�����
        g2d.fill(area1);// ��������������ͼ��
        Rectangle2D.Float rect2 = new Rectangle2D.Float(240, -35, 120, 120);// �������ζ���
        Ellipse2D.Float ellipse2 = new Ellipse2D.Float(290, 20, 160, 160);// ������Բ����
        Area area3 = new Area(rect2);// ������������
        Area area4 = new Area(ellipse2);// ������Բ���������ͼ��
        area4.subtract(area3);// ��������ͼ�����
        g2d.fill(area4);// ��������������ͼ��
    }
}
