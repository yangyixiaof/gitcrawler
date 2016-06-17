package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFlowerFrame extends JFrame {
    DrawFlowerPanel drawFlowerPanel = new DrawFlowerPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        DrawFlowerFrame frame = new DrawFlowerFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawFlowerFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ƻ���"); // �������
        setBounds(100, 100, 338, 230); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(drawFlowerPanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawFlowerPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {     // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            g2.translate(drawFlowerPanel.getWidth() / 2, drawFlowerPanel.getHeight() / 2);// ƽ��������
            // ������ɫ����
            Ellipse2D.Float ellipse = new Ellipse2D.Float(30, 0, 70, 20);// ������Բ����
            Color color = new Color(0,255,0);//������ɫ����
            g2.setColor(color);//ָ����ɫ
            g2.fill(ellipse);// ������Բ
            int i=0;
            while (i<8){
                g2.rotate(30);// ��ת����
                g2.fill(ellipse);// ������Բ
                i++;
            }
            // ���ƺ�ɫ����
            ellipse = new Ellipse2D.Float(20, 0, 60, 15);// ������Բ����
            color = new Color(255,0,0);//������ɫ����
            g2.setColor(color);//ָ����ɫ
            g2.fill(ellipse);// ������Բ
            i=0;
            while (i<15){
                g2.rotate(75);// ��ת����
                g2.fill(ellipse);// ������Բ
                i++;
            }
            // ���ƻ�ɫ����
            ellipse = new Ellipse2D.Float(10, 0, 50, 15);// ������Բ����
            color = new Color(255,255,0);//������ɫ����
            g2.setColor(color);//ָ����ɫ
            g2.fill(ellipse);// ������Բ
            i=0;
            while (i<8){
                g2.rotate(30);// ��ת����
                g2.fill(ellipse);// ������Բ
                i++;
            }
            // ���ƺ�ɫ���ĵ�
            color = new Color(255, 0, 0);// ������ɫ����
            g2.setColor(color);// ָ����ɫ
            ellipse = new Ellipse2D.Float(-10, -10, 20, 20);// ������Բ����
            g2.fill(ellipse);// ������Բ
        }
    }
}
