package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class SlantImageApplet extends Applet {
    public void paint(Graphics g) {
        String value = "��бͼ��";
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD5.jpg");// ���ͼƬ��Ϣ
        Graphics2D g2d = (Graphics2D) g;// ǿ��ת��ΪGraphics2D����
        g2d.drawString(value, 209, 170);// �����ı�
        AffineTransform tr = new AffineTransform();// ����AffineTransform����
        tr.translate(210, 32);// ͼ��λ�õ�ƽ��
        tr.shear(3, 3);// ��бͼ��
        g2d.drawImage(img, tr, this);// ����ͼ��
        AffineTransform tr1 = AffineTransform.getScaleInstance(3.5, 3.5);// ���AffineTransform����
        tr1.translate(15, 13);// ͼ��λ�õ�ƽ��
        g2d.drawImage(img, tr1, this);// ����ͼ��
    }
}
