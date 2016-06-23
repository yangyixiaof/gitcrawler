package com.zzk;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * �������
 * @author ������
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	private Image image;// ����ͼ��
	public BackgroundPanel() {
		setOpaque(false);// ͸��
		setLayout(null);// ���Բ���
	}
	public void setImage(Image image) {
		this.image = image;// ����ͼ��
	}
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, this);// ����ͼ��
		}
		super.paintComponent(g);// ���ó���ķ���
	}
}
