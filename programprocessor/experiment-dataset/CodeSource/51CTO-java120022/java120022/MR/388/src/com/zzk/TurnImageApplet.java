package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class TurnImageApplet extends Applet {
    public void paint(Graphics g) {
        String value = "��תͼ��";
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD4.jpg");// ���ͼƬ��Ϣ
        int w = img.getWidth(this);// ����ͼ��Ŀ��
        int h = img.getHeight(this);// ����ͼ��ĸ߶�
        Graphics2D g2d = (Graphics2D) g;// ��gת��Ϊ�������õ�Graphics2D
        g2d.drawString(value, 100, 130);// �����ı�
        AffineTransform tr = new AffineTransform(-1, 0, 0, 1, w, 0);// �����任���󲢺���ת
        AffineTransform tr2 = new AffineTransform(1, 0, 0, -1, 0, h);// �����任���󲢴�ֱ��ת
        tr.translate(-20, 40);// ͼ��λ�õ�ƽ��
        tr2.translate(120, -40);// ͼ��λ�õ�ƽ��
        g2d.drawImage(img, tr, this);// ����ͼ��
        g2d.drawImage(img, tr2, this);// ����ͼ��
    }
}
