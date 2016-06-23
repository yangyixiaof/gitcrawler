package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTextStringFrame extends JFrame {
    DrawTextStringPanel textStringPanel = new DrawTextStringPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawTextStringFrame frame = new DrawTextStringFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawTextStringFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�����ı�"); // �������
        setBounds(100, 100, 308, 186); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(textStringPanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawTextStringPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            String value = "��ҹ˼";
            int x = 120;  // �ı�λ�õĺ�����
            int y = 30;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
            value = "���";
            x = 170;  // �ı�λ�õĺ�����
            y = 50;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
            value = "��ǰ���¹⣬";
            x = 70;  // �ı�λ�õĺ�����
            y = 80;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
            value = "���ǵ���˪��";
            x = 145;  // �ı�λ�õĺ�����
            y = 80;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
            value = "��ͷ�����£�";
            x = 70;  // �ı�λ�õĺ�����
            y = 110;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
            value = "��ͷ˼���硣";
            x = 145;  // �ı�λ�õĺ�����
            y = 110;  // �ı�λ�õ�������
            g.drawString(value, x, y);   // �����ı�
        }
    }
}
