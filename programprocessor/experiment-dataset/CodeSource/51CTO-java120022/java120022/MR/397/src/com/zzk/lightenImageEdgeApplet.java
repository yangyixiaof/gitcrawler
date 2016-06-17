package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class lightenImageEdgeApplet extends Applet {
    private BufferedImage image;// ��������ͼ�����
    public void paint(Graphics g) {
        Image img = null;// ��������ͼ�����
        String value = "����ͼ���Ե";
        img = getImage(getCodeBase(), "com/zzk/PPD.jpg");// ���ͼƬ��Ϣ
        int a = img.getWidth(this); // ���ͼƬ��ȸ�������a
        int b = img.getHeight(this);// ���ͼƬ�߶ȸ�������b
        if (a >= 0 || b >= 0) {
            image = new BufferedImage(a, b, BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0, this);// �ڻ���ͼ������ϻ���ͼ��
            float[] f = { 0.0f, -1.5f, 0.0f, -1.5f, 6.0f, -1.5f, 0.0f, -1.5f,
                    0.0f };// ������ʾ���ط���
            Kernel kernel = new Kernel(3, 3, f);// ����Kernel����
            ConvolveOp op = new ConvolveOp(kernel);// ����RescaleOp����
            image = op.filter(image, null); // ���˻���ͼ�����
            g.drawImage(img, 25, 35, this);// ���ƻ���ͼ�����
            g.drawImage(image, 217, 35, this);// ���ƻ���ͼ�����
            g.drawString(value, 258, 186);// �����ı�
        }
    }
}
