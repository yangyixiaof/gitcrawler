package com.zzk;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgLabel extends JLabel {
    public ImgLabel() {
        super();
    }
    @Override
    public void paint(Graphics g) {
        try {
            int width = this.getWidth(); // ��ȡͼƬ���
            int height = this.getHeight(); // ��ȡͼƬ�߶�
            ImageIcon icon = (ImageIcon) getIcon(); // ��ȡImageIcon����
            if (icon != null) {// ͼƬ��Ϊ��
                g.drawImage(icon.getImage(), 0, 0, width, height, this); // ����ͼƬ
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
}
