package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntersectOperationFrame extends JFrame {
    IntersectOperationPanel intersectOperationPanel = new IntersectOperationPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        IntersectOperationFrame frame = new IntersectOperationFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public IntersectOperationFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("ͼ�εĽ�����"); // �������
        setBounds(100, 100, 395, 240); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(intersectOperationPanel); // ��������ʵ����ӵ�����������
    }
    
    class IntersectOperationPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {    // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            Rectangle2D.Float rect = new Rectangle2D.Float(30, 30, 160, 120);// �������ζ���
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 30, 180, 180);// ����Բ����
            Area area1 = new Area(rect);   // �����������
            Area area2 = new Area(ellipse);   // ��������Բ
            area1.intersect(area2);// ��������ͼ�ν��н�����
            g2.draw(area1);  // ���ƽ�����������ͼ��
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(190, 20, 100, 140);// ������Բ����
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(240, 20, 100, 140);// ������Բ����
            Area area3 = new Area(ellipse1);// ����������Բ
            Area area4 = new Area(ellipse2);// ����������Բ
            area3.intersect(area4);// ����������Բ���н�����
            g2.fill(area3);  // ���ƽ�������������Բ
        }
    }
}
