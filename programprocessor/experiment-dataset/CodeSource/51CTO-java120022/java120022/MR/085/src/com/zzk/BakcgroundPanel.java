package com.zzk;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.*;
/**
 * @author 张振坤
 *
 */
@SuppressWarnings("serial")
public class BakcgroundPanel extends JPanel {
	public BakcgroundPanel() {
		super();
	}
	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("/images/back.jpg"); // 指定图片路径
			ImageIcon image = new ImageIcon(url); // 创建ImageIcon对象
			g.drawImage(image.getImage(), 0, 0, this); // 将图片绘制到面板上
			g.setColor(Color.RED); // 绘制颜色
			g.fillRect(0, 40, 190, 40); // 在面板上绘制长方形
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
