package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class DrawRoundRectApplet extends Applet {
    public void paint(Graphics g) {
        String value = "��Բ�Ǿ���";
        int x = 20;// Բ�Ǿ���λ�õĺ�����
        int y = 20;// Բ�Ǿ���λ�õ�������
        int width = 70;// Բ�Ǿ��ο��
        int height = 129;// Բ�Ǿ��θ߶�
        int xr = 5;                         // Բ�Ǿ���Բ�ǵ�ˮƽ����
        int yr = 7;                         // Բ�Ǿ���Բ�ǵĴ�ֱ����

        g.setColor(Color.blue);// ������ɫ
        g.drawRoundRect(x, y, width, height, xr, yr); // ����ͼ��
        
        int x0 = 35; // Բ�Ǿ���λ�õĺ�����
        int y0 = 35; // Բ�Ǿ���λ�õ�������
        int width0 = 82; // Բ�Ǿ��ο��
        int height0 = 139; // Բ�Ǿ��θ߶�
        int xr0 = 10; // Բ�Ǿ���Բ�ǵ�ˮƽ����
        int yr0 = 12; // Բ�Ǿ���Բ�ǵĴ�ֱ����
        g.setColor(Color.blue);// ������ɫ
        g.drawRoundRect(x0, y0, width0, height0, xr0, yr0);// ����ͼ��
        
        int x1 = 59;// Բ�Ǿ���λ�õĺ�����
        int y1 = 59;// Բ�Ǿ���λ�õ�������
        int width1 = 92;// Բ�Ǿ��ο��
        int height1 = 151;// Բ�Ǿ��θ߶�
        int xr1 = 20;// Բ�Ǿ���Բ�ǵ�ˮƽ����
        int yr1 = 22;// Բ�Ǿ���Բ�ǵĴ�ֱ����
        g.setColor(Color.blue);// ������ɫ
        g.drawRoundRect(x1, y1, width1, height1, xr1, yr1);// ����ͼ��
        g.drawString(value, 165, 30);// �����ı�
    }
}
