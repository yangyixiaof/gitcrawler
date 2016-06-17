package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class BlurImageApplet extends Applet {
    private BufferedImage image;// ��������ͼ�����
    public void paint(Graphics g) {
        Image img = null;// ��������ͼ�����
        String value = "ģ��ͼ��";
        img = getImage(getCodeBase(), "com/zzk/PPD.jpg");// ���ͼƬ��Ϣ
        int a = img.getWidth(this); // ���ͼƬ��ȸ�������a
        int b = img.getHeight(this);// ���ͼƬ�߶ȸ�������b
        if (a >= 0 || b >= 0) {
            image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0, null);// �ڻ���ͼ������ϻ���ͼ��
            float[] data = { 0.0532f, 0.132f, 0.0532f, 0.132f, 0.19f, 0.138f,
                    0.0532f, 0.132f, 0.0532f };// ������ʾ���ط���������
            Kernel kernel = new Kernel(3, 3, data); // ���� Kernel����
            ConvolveOp op = new ConvolveOp(kernel);// ����ConvolveOp����
            image = op.filter(image, null); // ���˻���ͼ�����
            g.drawImage(img, 25, 35, this);// ���ƻ���ͼ�����
            g.drawImage(image, 215, 35, this);// ���ƻ���ͼ�����
            g.drawString(value, 268, 186);// �����ı�
        }
    }
}