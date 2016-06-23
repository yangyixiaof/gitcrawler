package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class DrawGrainApplet extends Applet {
    private BufferedImage img;// ����ͼ�����
    public void init() { // ��ʼ������
        img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);// �����������
        Graphics2D g = img.createGraphics();// ����Graphics2D����
        g.setPaint(Color.yellow);// ָ����ɫ
        g.draw(new Rectangle(0, 0, 25, 25));// ���ƾ���
        g.setPaint(Color.red);// ָ����ɫ
        g.fill(new Rectangle(25, 0, 25, 25));// ������
        g.setPaint(Color.green);// ָ����ɫ
        g.fill(new Rectangle(0, 0, 25, 25));// ������
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;// ǿ��ת��ΪGraphics2D����
        g2d.setPaint(new TexturePaint(img, new Rectangle(0, 0, 10, 10)));// �������������ӵ�Graphics��
        g2d.fill(new Ellipse2D.Double(10, 10, 260, 145));// ���ͼ��
    }
}
