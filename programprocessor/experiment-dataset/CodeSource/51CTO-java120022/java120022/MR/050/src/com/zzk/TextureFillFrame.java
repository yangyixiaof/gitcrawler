package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null;  // ����������
    public static void main(String args[]) {
        TextureFillFrame frame = new TextureFillFrame();
        frame.setVisible(true);
    }
    public TextureFillFrame() {
        super();
        textureFillPanel = new TextureFillPanel();  // ����ͼ��������
        this.setBounds(200, 160, 308, 230); // ���ô����С��λ��
        this.add(textureFillPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("���������Ч"); // ���ô������
    }
    // ���������
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            Graphics2D g2 = image.createGraphics();// ��û���ͼ�����Ļ�ͼ�����Ķ���
            g2.setColor(Color.BLUE);// ������ɫ
            g2.fillRect(0,0,90,90);// ����������
            g2.setColor(Color.RED);// ������ɫ
            g2.fillOval(95,95,90,90);// ���ƴ����ɫ��Բ��
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// ����Rectangle2D����
            TexturePaint textPaint = new TexturePaint(image,rect);// ��������������
            Graphics2D graphics2 = (Graphics2D)g;// ת��paint()�����Ļ�ͼ�����Ķ���
            graphics2.setPaint(textPaint);// Ϊ��ͼ�����Ķ�����������������
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(45, 25, 200, 140);// �������ζ���
            graphics2.fill(ellipse2);// �����������ľ���
        }
    }
}
