package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashedModelFrame extends JFrame {
    DashedModelPanel dashedModelPanel = new DashedModelPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DashedModelFrame frame = new DashedModelFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DashedModelFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("��������ģʽ"); // �������
        setBounds(100, 100, 326, 220); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(dashedModelPanel); // ��������ʵ����ӵ�����������
    }
    
    class DashedModelPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            float[] arr = {30.0f,30.0f};  // ��������ģʽ������
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // ���������10��ƽͷ���߱ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            Line2D.Float line = new Line2D.Float(20,50,300,50);// ����ֱ�߶���
            g2.drawString("ƽͷ��ʽ����ģʽ", 110, 40);  // �����ı�
            g2.draw(line);// ����ֱ��
            stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // ���������10��Բͷ���߱ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            line = new Line2D.Float(20,90,300,90);// ����ֱ�߶���
            g2.drawString("Բͷ��ʽ����ģʽ", 110, 80);  // �����ı�
            g2.draw(line);// ����ֱ��
            stroke = new BasicStroke(10,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // ���������10�ķ�ͷ���߱ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            line = new Line2D.Float(20,130,300,130);// ����ֱ�߶���
            g2.drawString("��ͷ��ʽ����ģʽ", 110, 120);  // �����ı�
            g2.draw(line);// ����ֱ��
        }
    }
}
