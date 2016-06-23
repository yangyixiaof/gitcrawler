package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawQuadCurveFrame extends JFrame {
    DrawQuadCurvePanel quadCurvePanel = new DrawQuadCurvePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawQuadCurveFrame frame = new DrawQuadCurveFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawQuadCurveFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ƶ�������"); // �������
        setBounds(100, 100, 269, 175); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(quadCurvePanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawQuadCurvePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2=(Graphics2D)g;// ���Graphics2D����
            // �����������ߣ����е�120,100�ǿ��Ƶ㣬��60,20����ʼ�����꣬��180,20���յ�����
            QuadCurve2D.Double quadCurve1 = new QuadCurve2D.Double(60,20,120,100,180,20);
            g2.draw(quadCurve1); // ���ƶ�������
            // �����������ߣ����е�120,40�ǿ��Ƶ㣬��60,120����ʼ�����꣬��180,120���յ�����
            QuadCurve2D.Double quadCurve2 = new QuadCurve2D.Double(60,120,120,40,180,120);
            g2.draw(quadCurve2); // ���ƶ�������
        }
    }
}
