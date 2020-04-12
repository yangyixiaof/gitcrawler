package com.zzk;
import java.awt.*;
import javax.swing.JLabel;

public class BallPanel extends JLabel implements Runnable {
    private int x_r = 10;
    private int x_width = x_r * 2;
    private int x_height = x_r * 2;
    private Color ball_color = Color.BLUE;
    public BallPanel() {
        setPreferredSize(new Dimension(x_width, x_height));
        new Thread(this).start();
    }
    @Override
    protected void paintComponent(Graphics b_g) {
        super.paintComponent(b_g);
        b_g.setColor(ball_color);
        b_g.fillOval(0, 0, x_width, x_height);
    }
    
    @Override
    public void run() {
        Container parent = getParent();
        Point my_point = getLocation();
        while (true) {
            if (parent == null) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                my_point = getLocation();
                parent = getParent();
            } else {
                break;
            }
        }
        int sx = my_point.x;
        int sy = my_point.y;
        int step = (int) (Math.random() * 10) % 3 + 1;
        int dx = (Math.random() * 100) >= 50 ? step : -step;
        step = (int) (Math.random() * 10) % 3 + 1;
        int dy = (Math.random() * 100) >= 50 ? step : -step;
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
            sx = sx + dx * 5;
            sy = sy + dy * 5;
            
            if (sy > parentHeight - x_height || sy < 0)
                dy = -dy;
            if (sx > parentWidth - x_width || sx < 0)
                dx = -dx;
        }
    }
}
