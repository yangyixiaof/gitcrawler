package com.mingrisoft.time;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class SimulatedClock extends JFrame {
    
    /**
     * �����޸ĳ�ʹ��Rectangle2D
     */
    private static final long serialVersionUID = 1557083478271086551L;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimulatedClock frame = new SimulatedClock();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public SimulatedClock() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setTitle("\u6A21\u62DF\u65F6\u949F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
    }
    
    public void paint(Graphics g) {
        super.paint(g);// ���ø����paint()�������������ڻ�ͼʱ�ܱ������
        Rectangle rectangle = getBounds();// ������������
        Insets insets = getInsets();// �������ı߿�
        int radius = 120;// ����Բ�İ뾶��120px
        int x = (rectangle.width - 2 * radius - insets.left - insets.right) / 2 + insets.left;
        int y = (rectangle.height - 2 * radius - insets.top - insets.bottom) / 2 + insets.top;
        Point2D.Double center = new Point2D.Double(x + radius, y + radius);// ���Բ������
        g.drawOval(x, y, 2 * radius, 2 * radius);// ����Բ��
        Point2D.Double[] scales = new Point2D.Double[60];// ��60���㱣����̵Ŀ̶�
        double angle = Math.PI / 30;// ������������֮��ļн���PI/30
        for (int i = 0; i < scales.length; i++) {// ������п̶ȵ�����
            scales[i] = new Point2D.Double();// ��ʼ�������
            scales[i].setLocation(x + radius + radius * Math.sin(angle * i), y + radius - radius * Math.cos(angle * i));// �������Ǻ�������������
        }
        for (int i = 0; i < scales.length; i++) {// �����п̶�
            if (i % 5 == 0) {// ��������5�򻭳ɴ�㣬��Щ���൱��ʯӢ���ϵ�����
                g.setColor(Color.RED);
                g.fillOval((int) scales[i].x - 4, (int) scales[i].y - 4, 8, 8);
            } else {// �����Ų���5�򻭳�С�㣬��Щ���൱��ʯӢ���ϵ�С�̶�
                g.setColor(Color.CYAN);
                g.fillOval((int) scales[i].x - 2, (int) scales[i].y - 2, 4, 4);
            }
        }
        Calendar calendar = new GregorianCalendar();// �������ڶ���
        int hour = calendar.get(Calendar.HOUR);// ��õ�ǰСʱ��
        int minute = calendar.get(Calendar.MINUTE);// ��õ�ǰ������
        int second = calendar.get(Calendar.SECOND);// ��õ�ǰ����
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);// ����ɫ���óɺ�ɫ
        g2d.draw(new Line2D.Double(center, scales[second]));// ��������
        BasicStroke bs = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs);
        g2d.setColor(Color.blue);// ����ɫ���ó���ɫ
        g2d.draw(new Line2D.Double(center, scales[minute]));// ���Ʒ���
        bs = new BasicStroke(6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs);
        g2d.setColor(Color.green);// ����ɫ���ó���ɫ
        g2d.draw(new Line2D.Double(center, scales[hour * 5 + minute / 12]));// ����ʱ��
    }
    
    private class ClockRunnable implements Runnable {
        
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        new Thread(new ClockRunnable()).start();
    }
}
