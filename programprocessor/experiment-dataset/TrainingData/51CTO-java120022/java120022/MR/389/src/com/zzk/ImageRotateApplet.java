package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageRotateApplet extends Applet {
    public void paint(Graphics g) {
        String value = "��תͼ��";
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD2.jpg");// ���ͼƬ��Ϣ
        Graphics2D g2d = (Graphics2D) g; // ǿ��ת��ΪGraphics2D����
        g2d.drawString(value, 180, 150);// �����ı�
        int x = 50;// ͼ��λ�õĺ�����
        int y = -10;// ͼ��λ�õ�������
        int w = img.getWidth(this);// ���ͼƬ�Ŀ��
        int h = img.getHeight(this);// ���ͼƬ�ĸ߶�
        g2d.drawImage(img, x, y + 50, w / 5, h / 5, this);// ����ͼ��
        g2d.drawImage(img, x + 150, y + 50, w / 5, h / 5, this);// ����ͼ��
        AffineTransform tr = new AffineTransform();// �������ζ���
        tr.rotate(90, 15, 15, 65);// ������ת�Ƕ�
        g2d.setTransform(tr);// ִ����ת
        g2d.drawImage(img, x + 150, y + 20, w / 5, h / 5, this);// ����ͼ��
        tr.rotate(35, 15, 30, 65);// ������ת�Ƕ�
        g2d.setTransform(tr);// ִ����ת
        g2d.drawImage(img, x + 120, y - 60, w / 5, h / 5, this);// ����ͼ��
    }
}