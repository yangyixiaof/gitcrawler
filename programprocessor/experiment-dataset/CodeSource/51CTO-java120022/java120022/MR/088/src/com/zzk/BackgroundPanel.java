package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * ±³¾°Ãæ°å
 * @author ÕÅÕñÀ¤
 *
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	// ±³¾°Í¼Æ¬
	private Image image;

	public BackgroundPanel() {
		setOpaque(false);
		setLayout(null);
	}

	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * »­³ö±³¾°
	 */
	protected void paintComponent(Graphics g) {
		if (image != null) {
			// Í¼Æ¬¿í¶È
			int width = getWidth();
			// Í¼Æ¬¸ß¶È
			int height = getHeight();
			// »­³öÍ¼Æ¬
			g.drawImage(image, 0, 0, width, height, this);
		}
		super.paintComponent(g);
	}
}
