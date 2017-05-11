package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawChinesePasswordApplet extends Applet {
    int WIDTH = 120;// ���
    int HEIGHT = 35;// �߶�
    private String num = "";// ��֤��
    Random random = new Random();// ʵ����Random
    public void paint(Graphics g) {
        String hanZi = "��ʵ��ͨ�����ַ������������ĸ��ַ�ʵ����������֤��Ĺ���";// ������֤��ʹ�õĺ���
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);// ʵ����BufferedImage
        Graphics gs = image.getGraphics(); // ��ȡGraphics��Ķ���
        if (!num.isEmpty()) {
            num = "";// �����֤��
        }
        Font font = new Font("����", Font.BOLD, 20); // ͨ��Font��������
        gs.setFont(font);// ��������
        gs.fillRect(0, 0, WIDTH, HEIGHT);// ���һ������
        // ����������֤����
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(hanZi.length());// �����ú��ֵ�����ֵ
            String ctmp = hanZi.substring(index, index + 1);// ���ָ����������һ������
            num += ctmp;// ������֤��
            Color color = new Color(20 + random.nextInt(120), 20 + random
                    .nextInt(120), 20 + random.nextInt(120));// ���������ɫ
            gs.setColor(color); // ������ɫ
            Graphics2D gs2d = (Graphics2D) gs;// ��������תָ���Ƕ�
            AffineTransform trans = new AffineTransform();// ʵ����AffineTransform
            trans.rotate(random.nextInt(45) * 3.14 / 180, 22 * i + 8, 7);
            float scaleSize = random.nextFloat() + 0.8f;// ��������
            if (scaleSize > 1f)
                scaleSize = 1f;// ���scaleSize����1,�����1
            trans.scale(scaleSize, scaleSize); // ��������
            gs2d.setTransform(trans);// ����AffineTransform����
            gs2d.drawString(ctmp, WIDTH / 6 * i + 28, HEIGHT / 2);// ������֤��
        }
        g.drawImage(image, 85, 80, null);// ��СӦ�ó����л�����֤��
    }
}
