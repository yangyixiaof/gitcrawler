package test;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    
	private static final long serialVersionUID = 8270477245019271761L;
	DrawSquarePanel squarePanel = new DrawSquarePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        DrawSquareFrame frame = new DrawSquareFrame(); // �����������ʵ��
        // right 2
        frame.setVisible(true); // ��ʾ����
    }
    
    public DrawSquareFrame() {
        super(); // ���ó���Ĺ��췽��
        // right 1
        setTitle("����������"); // �������
        // right 1
        setBounds(100, 100, 280, 180); // �������ʾλ�úʹ�С
        // right 1
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        // right 5
        add(squarePanel); // ��������ʵ����ӵ�����������
    }
    
    class DrawSquarePanel extends JPanel {// �����ڲ������
		private static final long serialVersionUID = 5267218279398988766L;

		public void paint(Graphics g) {   // ��дpaint()����
            g.drawRect(20, 20, 100, 100); // ���ƿ���������
            // right 1
            g.drawRect(40, 40, 60, 60);   // ���ƿ���������
            // right 1
            g.drawRect(140, 20, 100, 100);   // ���ƿ���������
            // right 1
            g.fillRect(160, 40, 60, 60);  // ����ʵ��������
        }
    }
}
