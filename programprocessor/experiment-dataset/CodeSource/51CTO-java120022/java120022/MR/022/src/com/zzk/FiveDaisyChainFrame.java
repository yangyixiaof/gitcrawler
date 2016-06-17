package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FiveDaisyChainFrame extends JFrame {
    FiveDaisyChainPanel fivePanel = new FiveDaisyChainPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        FiveDaisyChainFrame frame = new FiveDaisyChainFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public FiveDaisyChainFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�����廷ͼ��"); // �������
        setBounds(100, 100, 269, 222); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(fivePanel); // ��������ʵ����ӵ�����������
    }
    
    class FiveDaisyChainPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {     // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            BasicStroke stroke = new BasicStroke(3); // ���������3�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            Color color = new Color(0,162,232);// ������ɫ����
            g2.setColor(color);// ������ɫ
            g2.drawOval(30, 40, 60, 60);     // ���Ƶ�һ��Բ
            color = new Color(233,123,16);   // �����µ���ɫ����
            g2.setColor(color);// ������ɫ
            g2.drawOval(60, 70, 60, 60);     // ���Ƶڶ���Բ
            color = new Color(28,20,100);// �����µ���ɫ����
            g2.setColor(color);// ������ɫ
            g2.drawOval(92, 40, 60, 60);     // ���Ƶ�����Բ
            color = new Color(0,255,0);// �����µ���ɫ����
            g2.setColor(color);// ������ɫ
            g2.drawOval(122, 70, 60, 60);    // ���Ƶ��ĸ�Բ
            color = new Color(255,0,0);// �����µ���ɫ����
            g2.setColor(color);// ������ɫ
            g2.drawOval(154, 40, 60, 60);    // ���Ƶ����Բ
        }
    }
}
