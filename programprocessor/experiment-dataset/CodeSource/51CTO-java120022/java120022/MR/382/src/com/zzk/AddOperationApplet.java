package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class AddOperationApplet extends Applet {
    public void paint(Graphics g) {
        String value = "ͼ���������";
        Graphics2D g2d = (Graphics2D) g;// pת��Ϊ���õ�Graphics2D����
        Rectangle2D.Float rect1 = new Rectangle2D.Float(20, 70, 185, 60);// �������ζ���
        Rectangle2D.Float rect2 = new Rectangle2D.Float(120, 20, 65, 160);// �������ζ���
        Area area1 = new Area(rect1);// �����������
        Area area2 = new Area(rect2);// �����������
        area1.add(area2);// ��������������
        g2d.draw(area1);// ������Ӻ���������
        Rectangle2D.Float rect3 = new Rectangle2D.Float(230, 70, 185, 60);// �������ζ���
        Rectangle2D.Float rect4 = new Rectangle2D.Float(290, 20, 65, 160);// �������ζ���
        Area area3 = new Area(rect3);// �����������
        Area area4 = new Area(rect4);// �����������
        area3.add(area4);// ��������������
        g2d.draw(area3);// ������Ӻ���������
        g2d.drawString(value, 25, 56);// �����ı�
    }
}
