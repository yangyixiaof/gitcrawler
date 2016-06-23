package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawArcFrame extends JFrame {
    DrawArcPanel arcPanel = new DrawArcPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        DrawArcFrame frame = new DrawArcFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    public DrawArcFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("����Բ��"); // �������
        setBounds(100, 100, 269, 184); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(arcPanel); // ��������ʵ����ӵ�����������
    }
    class DrawArcPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            g.drawArc(20, 20, 80, 80, 0, 120);    // ����Բ��
            g.drawArc(20, 40, 80, 80, 0, -120);   // ����Բ��
            g.drawArc(150, 20, 80, 80, 180, -120);// ����Բ��
            g.drawArc(150, 40, 80, 80, 180, 120); // ����Բ��
        }
    }
}
