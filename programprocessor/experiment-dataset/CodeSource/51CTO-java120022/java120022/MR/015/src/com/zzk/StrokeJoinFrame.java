package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeJoinFrame extends JFrame {
    ChangeStrokeJoinPanel strokeJoinPanel = new ChangeStrokeJoinPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        StrokeJoinFrame frame = new StrokeJoinFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public StrokeJoinFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�������ӷ�ʽ"); // �������
        setBounds(100, 100, 338, 198); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(strokeJoinPanel); // ��������ʵ����ӵ�����������
    }
    
    class ChangeStrokeJoinPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // ���������10��ƽͷб�����ӱʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            Rectangle2D.Float rect = new Rectangle2D.Float(20,60,80,50);// �������ζ���
            g2.drawString("б������", 35, 50);  // �����ı�
            g2.draw(rect);// ���ƾ���
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER); // ���������10��ƽͷ������ӱʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            rect = new Rectangle2D.Float(120,60,80,50);// �������ζ���
            g2.drawString("�������", 135, 50);  // �����ı�
            g2.draw(rect);// ���ƾ���
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND); // ���������10��ƽͷԲ�����ӱʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            rect = new Rectangle2D.Float(220,60,80,50);// �������ζ���
            g2.drawString("Բ������", 235, 50);  // �����ı�
            g2.draw(rect);// ���ƾ���
        }
    }
}
