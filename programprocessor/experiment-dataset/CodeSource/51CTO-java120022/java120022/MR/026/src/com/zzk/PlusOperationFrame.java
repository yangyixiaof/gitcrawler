package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlusOperationFrame extends JFrame {
    PlusOperationPanel plusOperationPanel = new PlusOperationPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        PlusOperationFrame frame = new PlusOperationFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public PlusOperationFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("ͼ�εļ�����"); // �������
        setBounds(100, 100, 395, 240); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(plusOperationPanel); // ��������ʵ����ӵ�����������
    }
    
    class PlusOperationPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {    // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(20, 70, 160, 60);// ������Բ����
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(120, 20, 60, 160);// ������Բ����
            Area area1 = new Area(ellipse1);   // ����������Բ
            Area area2 = new Area(ellipse2);   // ����������Բ
            area1.add(area2);// ����������Բ���м�����
            g2.draw(area1);  // ���Ƽ�������������Բ
            Ellipse2D.Float ellipse3 = new Ellipse2D.Float(200, 70, 160, 60);// ������Բ����
            Ellipse2D.Float ellipse4 = new Ellipse2D.Float(250, 20, 60, 160);// ������Բ����
            Area area3 = new Area(ellipse3);// ����������Բ
            Area area4 = new Area(ellipse4);// ����������Բ
            area3.add(area4);// ����������Բ���м�����
            g2.draw(area3);  // ���Ƽ�������������Բ
        }
    }
}
