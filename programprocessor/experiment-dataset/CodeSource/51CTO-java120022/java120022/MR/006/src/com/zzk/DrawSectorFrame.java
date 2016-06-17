package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawSectorFrame extends JFrame {
    DrawSectorPanel sectorPanel = new DrawSectorPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        DrawSectorFrame frame = new DrawSectorFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    public DrawSectorFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�����������"); // �������
        setBounds(100, 100, 278, 184); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(sectorPanel); // ��������ʵ����ӵ�����������
    }
    class DrawSectorPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            g.fillArc(40, 20, 80, 80, 0, 150);    // �����������
            g.fillArc(140, 20, 80, 80, 180, -150);// �����������
            g.fillArc(40, 40, 80, 80, 0, -110);   // �����������
            g.fillArc(140, 40, 80, 80, 180, 110); // �����������
        }
    }
}
