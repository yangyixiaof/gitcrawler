package com.zzk;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.*;
/**
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class BakcgroundPanel extends JPanel {
	public BakcgroundPanel() {
		super();
	}
	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("/images/back.jpg"); // ָ��ͼƬ·��
			ImageIcon image = new ImageIcon(url); // ����ImageIcon����
			g.drawImage(image.getImage(), 0, 0, this); // ��ͼƬ���Ƶ������
			g.setColor(Color.RED); // ������ɫ
			g.fillRect(0, 40, 190, 40); // ������ϻ��Ƴ�����
			g.setColor(Color.yellow);
			g.fillRect(190, 40, 40, 240);
			g.setColor(Color.pink);
			g.fillRect(190, 180, 230, 40);
			g.setColor(Color.cyan);
			g.fillRect(300, 180, 40, 140);

		} catch (Exception e) {
		}
	}
}
