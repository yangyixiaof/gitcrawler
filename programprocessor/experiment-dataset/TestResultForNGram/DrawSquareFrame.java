package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    DrawSquarePanel squarePanel = new DrawSquarePanel();
    
    public static void main(String args[]) {
        DrawSquareFrame frame = new DrawSquareFrame();
        frame.setVisible(true);
    }
    
    public DrawSquareFrame() {
        super();
        // right 1
        setTitle("����������");
        setBounds(100, 100, 280, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // wrong because of not learning from other places.
        add(squarePanel);
    }
    
    class DrawSquarePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawRect(20, 20, 100, 100);
            // wrong because of too easily fused.
            g.drawRect(40, 40, 60, 60);
            g.drawRect(140, 20, 100, 100);
            g.fillRect(160, 40, 60, 60);
        }
    }
}
