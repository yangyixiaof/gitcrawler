package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class IntersectOperationApplet extends Applet {
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;// ǿ��ת��ΪGraphics2D����
        Rectangle2D.Float rect1 = new Rectangle2D.Float(0, 40, 140, 140);// �������ζ���
        Ellipse2D.Float ellipse1 = new Ellipse2D.Float(80, 40, 140, 140);// ������Բ����
        Area area1 = new Area(rect1);// ������������
        Area area2 = new Area(ellipse1);// ������Բ����
        area1.intersect(area2);// ���������ཻ
        g2d.fill(area1);// �����ཻ�������ͼ��
        Rectangle2D.Float rect2 = new Rectangle2D.Float(240, 0, 160, 160);// �������ζ���
        Ellipse2D.Float ellipse2 = new Ellipse2D.Float(190, 60, 140, 140);// ������Բ����
        Area area3 = new Area(rect2);// ������������
        Area area4 = new Area(ellipse2);// ������Բ����
        area3.intersect(area4);// ���������ཻ
        g2d.fill(area3);// �����ཻ�������ͼ��
    }
}
