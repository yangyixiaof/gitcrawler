package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCubicCurveFrame extends JFrame {
    DrawCubicCurvePanel cubicCurvePanel = new DrawCubicCurvePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawCubicCurveFrame frame = new DrawCubicCurveFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawCubicCurveFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("������������"); // �������
        setBounds(100, 100, 297, 199); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(cubicCurvePanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawCubicCurvePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2=(Graphics2D)g;// ���Graphics2D����
            // �����������ߣ����е�140,-140�͵�140,300�ǿ��Ƶ㣬��20,80����ʼ�����꣬��260,80���յ�����
            CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double(20,80,140,-140,140,300,260,80);
            g2.draw(cubicCurve); // ������������
        }
    }
}
