package com.zzk;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class DrawImageApplet extends Applet {
    public void paint(Graphics g) {// ��ʼ������
        String value = "����ͼƬ";
        Image image = null;// ����ͼ�����
        image = getImage(getCodeBase(), "com/zzk/PD2.jpg");// ���ͼƬ��Ϣ
        int x = 10;// ͼ��λ�õĺ�����
        int y = 20;// ͼ��λ�õ�������
        int width = image.getWidth(this);// ���ͼ��Ŀ��
        int height = image.getHeight(this);// ��ȡͼ��ĸ߶�
        g.drawImage(image, x + 150, y + 30, width / 5, height / 5, this);// ����ͼ��
        g.drawImage(image, x + 25, y + 10, (int) (width * 0.2),
                (int) (height * 0.3), this);// ����ͼ��
        g.drawString(value, 140, 170);// �����ı�
    }
}
