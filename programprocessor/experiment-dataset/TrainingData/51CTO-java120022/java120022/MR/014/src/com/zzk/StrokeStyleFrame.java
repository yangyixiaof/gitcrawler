package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeStyleFrame extends JFrame {
    ChangeStrokeStylePanel strokeStylePanel = new ChangeStrokeStylePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        StrokeStyleFrame frame = new StrokeStyleFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public StrokeStyleFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ñʻ���ʽ"); // �������
        setBounds(100, 100, 306, 220); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(strokeStylePanel); // ��������ʵ����ӵ�����������
    }
    
    class ChangeStrokeStylePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // ���������10��ƽͷ�ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            Line2D.Float line = new Line2D.Float(50,50,240,50);// ����ֱ�߶���
            g2.drawString("ƽͷ��ʽ", 120, 40);  // �����ı�
            g2.draw(line);// ����ֱ��
            stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL); // ���������10��Բͷ�ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            line = new Line2D.Float(50,90,240,90);// ����ֱ�߶���
            g2.drawString("Բͷ��ʽ", 120, 80);  // �����ı�
            g2.draw(line);// ����ֱ��
            stroke = new BasicStroke(10,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL); // ���������10�ķ�ͷ�ʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            line = new Line2D.Float(50,130,240,130);// ����ֱ�߶���
            g2.drawString("��ͷ��ʽ", 120, 120);  // �����ı�
            g2.draw(line);// ����ֱ��
        }
    }
}
