package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class ZoomImageApplet extends Applet {
    public void paint(Graphics g) {
        String value = "����ͼ��";
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD2.jpg");// ���ͼƬ��Ϣ
        AffineTransform tr = AffineTransform.getScaleInstance(0.25, 0.25);// ���������Ի�����Ŷ���
        tr.translate(120, 100);// ���ö���ƽ��
        AffineTransform tr2 = AffineTransform.getScaleInstance(0.15, 0.15);// ���������Ի�����Ŷ���
        tr2.translate(900, 950);// ���ö���ƽ��
        Graphics2D g2d = (Graphics2D) g;// ��gת����һ�����õ�Graphics2D����
        g2d.drawImage(img, tr, this);// ����ͼ��
        g2d.drawImage(img, tr2, this);// ����ͼ��
        g2d.drawString(value, 60, 150);// �����ı�
    }
}
