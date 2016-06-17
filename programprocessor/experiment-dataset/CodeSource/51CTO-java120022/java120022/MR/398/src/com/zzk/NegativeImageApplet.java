package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class NegativeImageApplet extends Applet {
    private BufferedImage image;// ��������ͼ�����
    public void paint(Graphics g) {
        String value = "����ͼ��";
        Image img = null;// ����ͼ�����
        img = getImage(getCodeBase(), "com/zzk/PD3.jpg");// ���ͼƬ��Ϣ
        int a = img.getWidth(this); // ���ͼƬ��ȸ�������a
        int b = img.getHeight(this);// ���ͼƬ�߶ȸ�������b
        if (a >= 0 || b >= 0) {
            image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0, null);// �ڻ���ͼ������ϻ���ͼ��
            short[] negative = new short[256 * 1];// ������ʾ��ɫ����ķ�������
            for (int i = 0; i < 256; i++) {
                negative[i] = (short) (255 - i);// Ϊ���鸳ֵ
            }
            ShortLookupTable table = new ShortLookupTable(0, negative);// �������ұ����
            LookupOp op = new LookupOp(table, null);// ����ʵ�ִ�Դ��Ŀ����Ҳ�����LookupOp����
            image = op.filter(image, null);// ����LookupOp�����filter()������ʵ��ͼ������
            if (image != null) {
                g.drawImage(img, 35, 40, null);// ���ƻ���ͼ�����
                g.drawImage(image, 220, 40, null);// ���ƻ���ͼ�����
            }
            g.drawString(value, 265, 175);// �����ı�
        }
    }
}