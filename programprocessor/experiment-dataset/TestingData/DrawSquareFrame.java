package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    DrawSquarePanel square_panel = new DrawSquarePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawSquareFrame frame = new DrawSquareFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawSquareFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("����������"); // �������
        setBounds(100, 100, 680, 580); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(square_panel); // ��������ʵ����ӵ�����������
    }
    
    class DrawSquarePanel extends JPanel {// �����ڲ������
        public void paint(Graphics g) {   // ��дpaint()����
            g.drawRect(20, 20, 100, 100); // ���ƿ���������
            g.drawRect(40, 40, 60, 60);   // ���ƿ���������
            g.drawRect(140, 20, 100, 100);   // ���ƿ���������
            g.fillRect(160, 40, 60, 60);  // ����ʵ��������
        }
    }
}
