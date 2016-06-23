package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExclusiveOrOperationFrame extends JFrame {
    ExclusiveOrOperationPanel exclusiveOrOperationPanel = new ExclusiveOrOperationPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        ExclusiveOrOperationFrame frame = new ExclusiveOrOperationFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public ExclusiveOrOperationFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("ͼ�ε��������"); // �������
        setBounds(100, 100, 395, 240); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(exclusiveOrOperationPanel); // ��������ʵ����ӵ�����������
    }
    
    class ExclusiveOrOperationPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {    // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(20, 70, 160, 60);// ������Բ����
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(120, 20, 60, 160);// ������Բ����
            Area area1 = new Area(ellipse1);   // ����������Բ
            Area area2 = new Area(ellipse2);   // ����������Բ
            area1.exclusiveOr(area2);// ����������Բ�����������
            g2.fill(area1);  // �������������������Բ
            Ellipse2D.Float ellipse3 = new Ellipse2D.Float(200, 70, 160, 60);// ������Բ����
            Ellipse2D.Float ellipse4 = new Ellipse2D.Float(250, 20, 60, 160);// ������Բ����
            Area area3 = new Area(ellipse3);// ����������Բ
            Area area4 = new Area(ellipse4);// ����������Բ
            area3.exclusiveOr(area4);// ����������Բ�����������
            g2.fill(area3);  // �������������������Բ
        }
    }
}
