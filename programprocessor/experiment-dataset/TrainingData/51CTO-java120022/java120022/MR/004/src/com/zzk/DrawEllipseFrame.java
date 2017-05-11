package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseFrame extends JFrame {
    DrawEllipsePanel ellipsePanel = new DrawEllipsePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawEllipseFrame frame = new DrawEllipseFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawEllipseFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("������Բ"); // �������
        setBounds(100, 100, 269, 222); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(ellipsePanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawEllipsePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {     // ��дpaint()����
            g.drawOval(30, 20, 80, 50);     // ���ƿ�����Բ
            g.drawOval(150, 10, 50, 80);    // ���ƿ�����Բ
            g.fillOval(40, 90, 50, 80);     // ����ʵ����Բ
            g.fillOval(140, 110, 80, 50);   // ����ʵ����Բ
        }
    }
}
