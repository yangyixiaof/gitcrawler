package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextAndShapeColorFrame extends JFrame {
    TextAndShapeColorPanel colorPanel = new TextAndShapeColorPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        TextAndShapeColorFrame frame = new TextAndShapeColorFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public TextAndShapeColorFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�����ı���ͼ�ε���ɫ"); // �������
        setBounds(100, 100, 300, 193); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(colorPanel); // ��������ʵ����ӵ�����������
    }
    
    class TextAndShapeColorPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            String value = "ֻҪŬ����������";
            int x = 60;    // �ı�λ�õĺ�����
            int y = 60;     // �ı�λ�õ�������
            Color color = new Color(255,0,0); // ������ɫ����
            g.setColor(color); // ������ɫ
            g.drawString(value, x, y);   // �����ı�
            value = "һ�н��п���";
            x = 140;    // �ı�λ�õĺ�����
            y = 100;     // �ı�λ�õ�������
            color = new Color(0,0,255);// ������ɫ����
            g.setColor(color);// ������ɫ
            g.drawString(value, x, y);   // �����ı�
            color = Color.ORANGE; // ͨ��Color����ֶλ����ɫ����
            g.setColor(color);// ������ɫ
            g.drawRoundRect(40,30,200,100,40,30);// ����Բ�Ǿ���
            g.drawRoundRect(45,35,190,90,36,26);// ����Բ�Ǿ���
        }
    }
}
