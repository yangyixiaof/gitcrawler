package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    DrawSquarePanel square_panel = new DrawSquarePanel();
    
    public static void main(String args[]) {
        DrawSquareFrame frame = new DrawSquareFrame();
        frame.setVisible(true);
    }
    
    public DrawSquareFrame() {
        super();
        setTitle("绘制正方形");
        setBounds(100, 100, 680, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(square_panel);
    }
    
    class DrawSquarePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawRect(20, 20, 100, 800);
            g.drawRect(40, 40, 60, 30);
            g.drawRect(140, 20, 100, 70);
            g.fillRect(160, 40, 60, 90);
        }
    }
}
