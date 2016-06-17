package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class ShearImageFrame extends JFrame {
	private Image img;
	private ShearImagePanel canvasPanel = null;
	public ShearImageFrame() {
        URL imgUrl = ShearImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);  // ��ȡͼƬ��Դ
        canvasPanel = new ShearImagePanel();     // ����������бͼ���������
        this.setBounds(100, 100, 360, 240);                // ���ô����С��λ��
        add(canvasPanel);// �ڴ��������������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("��бͼ��");                    // ���ô������
	}
	public static void main(String[] args) {
		new ShearImageFrame().setVisible(true);
	}
	class ShearImagePanel extends JPanel {// ������бͼ��������
		public void paint(Graphics g) {
			Graphics2D g2=(Graphics2D) g;// ���Graphics2D����
			g2.shear(0.5, 0);// ��бͼ��
			g2.drawImage(img, 10, 20, 220, 160, this);     // ����ָ����С��ͼƬ
		}
	}
}
