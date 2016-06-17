package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SubtractOperationFrame extends JFrame {
    SubtractOperationPanel subtractOperationPanel = new SubtractOperationPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        SubtractOperationFrame frame = new SubtractOperationFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public SubtractOperationFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("ͼ�εļ�����"); // �������
        setBounds(100, 100, 395, 240); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(subtractOperationPanel); // ��������ʵ����ӵ�����������
    }
    
    class SubtractOperationPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {    // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(20, 20, 160, 160);// ����Բ����
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(90, 20, 160, 160);// ����Բ����
            Area area1 = new Area(ellipse1);   // ��������Բ
            Area area2 = new Area(ellipse2);   // ��������Բ
            area1.subtract(area2);// ��������Բ���м�����
            g2.fill(area1);  // ���Ƽ�����������Բ
            Ellipse2D.Float ellipse3 = new Ellipse2D.Float(200, 70, 160, 60);// ������Բ����
            Ellipse2D.Float ellipse4 = new Ellipse2D.Float(250, 40, 60, 60);// ����Բ����
            Area area3 = new Area(ellipse3);// ����������Բ
            Area area4 = new Area(ellipse4);// ��������Բ
            area3.subtract(area4);// ��������ͼ�ν��м�����
            g2.draw(area3);  // ���Ƽ�����������ͼ��
        }
    }
}
