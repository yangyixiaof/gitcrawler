package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawLineFrame extends JFrame {
    DrawLinePanel linePanel = new DrawLinePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawLineFrame frame = new DrawLineFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawLineFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("����ֱ��"); // �������
        setBounds(100, 100, 273, 167); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(linePanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawLinePanel extends JPanel {   // �����ڲ������
        public void paint(Graphics g) {    // ��дpaint()����
            g.drawLine(70, 50, 180, 50);   // ���Ƶ�һ��ˮƽ��
            g.drawLine(70, 80, 180, 80);   // ���Ƶڶ���ˮƽ��
            g.drawLine(110, 10, 140, 120); // ����б��
        }
    }
}
