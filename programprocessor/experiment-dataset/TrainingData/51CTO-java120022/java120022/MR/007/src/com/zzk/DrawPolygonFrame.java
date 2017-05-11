package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPolygonFrame extends JFrame {
    DrawPolygonPanel polygonPanel = new DrawPolygonPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawPolygonFrame frame = new DrawPolygonFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawPolygonFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ƶ����"); // �������
        setBounds(100, 100, 352, 259); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(polygonPanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawPolygonPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            int[] x1 = { 100,120,180,140,150,100,50,60,20,80 }; // ����εĺ�����
            int[] y1 = { 20,85,90,120,180,140,180,120,90,85 }; // ����ε�������
            int n1 = 10;// ����εı���
            g.fillPolygon(x1, y1, n1);// ���ƶ����
            int[] x2 = { 210, 270, 310, 270, 210, 170 }; // ����εĺ�����
            int[] y2 = { 20, 20, 65, 110, 110, 65 }; // ����ε�������
            int n2 = 6;// ����εı���
            g.fillPolygon(x2, y2, n2);// ����ʵ�Ķ����
            int[] x3 = { 180, 220, 260, 240, 260, 220, 180, 200 }; // ����εĺ�����
            int[] y3 = { 120, 140, 120, 160, 200, 180, 200, 160 }; // ����ε�������
            int n3 = 8;// ����εı���
            g.drawPolygon(x3, y3, n3);// ���ƶ����
        }
    }
}
