package com.zzk;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * 背景面板
 * @author 张振坤
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	private Image image;// 背景图像
	public BackgroundPanel() {
		setOpaque(false);// 透明
		setLayout(null);// 绝对布局
	}
	public void setImage(Image image) {
		this.image = image;// 设置图像
	}
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, this);// 绘制图像
		}
		super.paintComponent(g);// 调用超类的方法
	}
}
