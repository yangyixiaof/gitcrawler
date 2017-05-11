package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class SetImageLightenessApplet extends Applet {
    private BufferedImage image;// ��������ͼ�����
    public void paint(Graphics g) {
        Image img = null;// ����ͼ�����
        String value = "����ͼƬ����";
        img = getImage(getCodeBase(), "com/zzk/PPD.jpg");// ���ͼ�����
        int a = img.getWidth(this); // ���ͼƬ��ȸ�������a
        int b = img.getHeight(this);// ���ͼƬ�߶ȸ�������b
        if (a >= 0 || b >= 0) {
            image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0, null);// �ڻ���ͼ������ϻ���ͼ��
            float fa = 2.0f;// ������ʾ���ط���
            float fb = -30.0f;// ������ʾ���ط���
            RescaleOp op = new RescaleOp(fa, fb, null);// ����RescaleOp����
            image = op.filter(image, null); // ���˻���ͼ�����ʵ�ֵ���ͼ�����ȵĹ���
            g.drawImage(img, 30, 40, this);// ����ԭͼ�����
            g.drawImage(image, 220, 40, this);// ���Ƶ������Ⱥ�Ļ���ͼ�����
            g.drawString(value, 265, 188);// �����ı�
        }
    }
}
