

import java.awt.Graphics;
import java.net.URL;
import javax.swing.*;

public class MyJPanel extends JPanel {
	public MyJPanel() {
		super();
	}

	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("registerbg.gif"); // ָ��ͼƬ·��
			ImageIcon image = new ImageIcon(url); // ����ImageIcon����
			g.drawImage(image.getImage(), 0, 0, this); // ����ͼƬ
		} catch (Exception e) {
		}
	}
}
