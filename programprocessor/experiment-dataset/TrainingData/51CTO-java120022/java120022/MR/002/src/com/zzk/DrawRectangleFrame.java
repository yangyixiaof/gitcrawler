package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectangleFrame extends JFrame {
    DrawRectanglePanel rectPanel = new DrawRectanglePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawRectangleFrame frame = new DrawRectangleFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawRectangleFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ƾ���"); // �������
        setBounds(100, 100, 269, 184); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(rectPanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawRectanglePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {       // ��дpaint()����
            g.drawRect(30, 40, 80, 60);       // ���ƿ��ľ���
            g.fillRect(140, 40, 80, 60);      // ����ʵ�ľ���
        }
    }
}
