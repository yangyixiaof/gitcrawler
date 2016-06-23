package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCachetFrame extends JFrame {
    DrawCachetPanel drawCachetPanel = new DrawCachetPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawCachetFrame frame = new DrawCachetFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawCachetFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("���ƹ���"); // �������
        setBounds(100, 100, 340, 240); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(drawCachetPanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawCachetPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D) g; // ���Graphics2D����
            g2.translate(170, 100);// ƽ��������
            BasicStroke stroke = new BasicStroke(6); // ���������6�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            // ����Բ
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, -80, 160, 160);// ����Բ����
            Color color = new Color(255, 0, 0);// ������ɫ����
            g2.setColor(color);// ָ����ɫ
            g2.draw(ellipse);// ����Բ
            // ��������
            int[] x1 = { 0, 8, 30, 16, 25, 0, -25, -16, -30, -8 }; // ����εĺ�����
            int[] y1 = { -35, -10, -15, 5, 28, 10, 28, 5, -15, -10 }; // ����ε�������
            int n1 = 10;// ����εı���
            g2.fillPolygon(x1, y1, n1);// ���ƶ����
            // �����ı�
            g2.scale(1.8, 1.8);// �Ŵ�
            Font font = new Font("����", Font.BOLD, 12);// ��������
            g2.setFont(font);// ��������
            g2.drawString("ר �� ��", -25, 30);// �����ı�
            int width = getWidth();// ��������
            int height = getHeight();// ������߶�
            char[] array = " ���տƼ����޹�˾         ".toCharArray();// ���ַ���ת��Ϊ�ַ�����
            int len = array.length * 2;// ����뾶
            font = new Font("����", Font.BOLD, 10);// ����������
            g2.setFont(font);// ��������
            double angle = 0;// ��ʼ�Ƕ�
            for (int i = 0; i < array.length; i++) {// �����ַ����е��ַ�
                int x = (int) (len * Math.sin(Math.toRadians(angle + 270)));// ����ÿ�����ֵ�λ��
                int y = (int) (len * Math.cos(Math.toRadians(angle + 270)));// ����ÿ�����ֵ�λ��
                g2.drawString(array[i] + "", width / 2 + x - 168, height / 2
                        - y - 95);// ����ÿ���ַ�������168��95������ƽ��ֵ
                angle = angle + 360d / array.length;// �ı�Ƕ�
            }
        }
    }
}
