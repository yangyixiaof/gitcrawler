package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeWidthFrame extends JFrame {
    ChangeStrokeWidthPanel strokeWidthPanel = new ChangeStrokeWidthPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        StrokeWidthFrame frame = new StrokeWidthFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public StrokeWidthFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ñʻ��Ĵ�ϸ"); // �������
        setBounds(100, 100, 300, 220); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(strokeWidthPanel); // ��������ʵ����ӵ�����������
    }
    
    class ChangeStrokeWidthPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            BasicStroke stroke = new BasicStroke(1); // ���������1�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20,20,100,60);// ������Բ����
            g2.draw(ellipse);// ������Բ
            stroke = new BasicStroke(4);// ���������4�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            ellipse = new Ellipse2D.Float(160,20,100,60);// ������Բ����
            g2.draw(ellipse);// ������Բ
            stroke = new BasicStroke(6);// ���������6�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            ellipse = new Ellipse2D.Float(20,100,100,60);// ������Բ����
            g2.draw(ellipse);// ������Բ
            stroke = new BasicStroke(8);// ���������8�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            ellipse = new Ellipse2D.Float(160,100,100,60);// ������Բ����
            g2.draw(ellipse);// ������Բ
        }
    }
}
