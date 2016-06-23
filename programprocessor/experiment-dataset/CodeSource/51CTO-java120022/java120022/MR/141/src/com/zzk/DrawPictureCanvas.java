package com.zzk;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author ������
 *         ����������
 */
public class DrawPictureCanvas extends Canvas {
    private Image image = null; // ����Image���������
    public DrawPictureCanvas() {
        super();
    }
    public void setImage(Image image) {
        this.image = image; // Ϊ��Ա������ֵ
    }
    /*
     * ��дpaint()����,�ڻ����ϻ���ͼ��
     */
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null); // �ڻ����ϻ���ͼ��
    }
    /*
     * ��дupdate()�������������Խ����Ļ��ҫ������
     */
    public void update(Graphics g) {
        paint(g); // ����paint����
    }
}
