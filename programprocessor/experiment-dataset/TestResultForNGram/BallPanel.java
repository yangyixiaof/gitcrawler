package com.zzk;
import java.awt.*;
import javax.swing.JLabel;
/**
 * @author ������
 */
public class BallPanel extends JLabel implements Runnable {
    private int r = 10;
    private int width = r * 2;
    private int height = r * 2;
    private Color ballColor = Color.BLUE;
    public BallPanel() {
        setPreferredSize(new Dimension(width, height));
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);
        g.fillOval(0, 0, width, height);
    }
    @Override
    public void run() {
        Container parent = getParent();
        Point point = getLocation();
        while (true) {
        	// right 1
            if (parent == null) {
                try {
                	// right 9
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                	// right 1
                    e.printStackTrace();
                }
                point = getLocation();
                parent = getParent();
            } else {
            	// wrong because of too long tokens training and no context search.
                break;
            }
        }
        
        int sx = point.x;
        int sy = point.y;
        int step = (int) (Math.random() * 10) % 3 + 1;
        int ex = (Math.random() * 100) >= 50 ? step : -step;ֵ
        step = (int) (Math.random() * 10) % 3 + 1;
        int ey = (Math.random() * 100) >= 50 ? step : -step;ֵ
        int stime = (int) (Math.random() * 80 + 10);
        while (parent.isVisible()) {
            int parentWidth = parent.getWidth();
            int parentHeight = parent.getHeight();
            setLocation(sx, sy);
            try {
                Thread.sleep(stime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sx = sx + ex * 5;
            sy = sy + ey * 5;
            if (sy > parentHeight - height || sy < 0)
                ey = -ey;
            if (sx > parentWidth - width || sx < 0)
                ex = -ex;
        }
    }
}
