package com.zzk;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GainAnyPointColorFrame extends JFrame {
    
    private JTextField tf_blue;
    private JTextField tf_green;
    private JTextField tf_red;
    private JTextField tf_y;
    private JTextField tf_x;
    private static final long serialVersionUID = -486745172657329259L;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GainAnyPointColorFrame frame = new GainAnyPointColorFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public GainAnyPointColorFrame() {
        super();
        GainColor gc = new GainColor();// ����GainColor����
        Thread thread = new Thread(gc);// �����̶߳���
        thread.start();// �����̶߳���
        getContentPane().setLayout(null);
        setTitle("��ȡ���������λ�õ���ɫֵ");
        setBounds(100, 100, 300, 207);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("������ڵ��X���꣺");
        label.setBounds(26, 21, 126, 25);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("������ڵ��Y���꣺");
        label_1.setBounds(26, 45, 126, 25);
        getContentPane().add(label_1);
        
        tf_x = new JTextField();
        tf_x.setBounds(155, 22, 103, 22);
        getContentPane().add(tf_x);
        
        tf_y = new JTextField();
        tf_y.setBounds(155, 46, 103, 22);
        getContentPane().add(tf_y);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("��ɫ��Redֵ��");
        label_2.setBounds(26, 79, 97, 18);
        getContentPane().add(label_2);
        
        final JLabel label_3 = new JLabel();
        label_3.setText("��ɫ��Greenֵ��");
        label_3.setBounds(26, 103, 111, 18);
        getContentPane().add(label_3);
        
        final JLabel label_4 = new JLabel();
        label_4.setText("��ɫ��Blueֵ��");
        label_4.setBounds(26, 127, 109, 18);
        getContentPane().add(label_4);
        
        tf_red = new JTextField();
        tf_red.setBounds(155, 77, 103, 22);
        getContentPane().add(tf_red);
        
        tf_green = new JTextField();
        tf_green.setBounds(155, 101, 103, 22);
        getContentPane().add(tf_green);
        
        tf_blue = new JTextField();
        tf_blue.setBounds(155, 125, 103, 22);
        getContentPane().add(tf_blue);
        
    }
    
    class GainColor implements Runnable {
        @Override
        public void run() {
            while (true) {
                PointerInfo mi = MouseInfo.getPointerInfo();// ���ָ�뵱ǰλ�õ�PointerInfo����
                Point p = mi.getLocation();// �����Ļ�ϱ�ʾָ�������Point����
                int x = p.x;// ���X����
                int y = p.y;// ���Y����
                try {
                    Robot robot = new Robot();// ����Robot����
                    Color color = robot.getPixelColor(x, y);// �����Ļָ��λ�õ���ɫ����
                    int r = color.getRed();// �����ɫ��Rֵ
                    int g = color.getGreen();// �����ɫ��Gֵ
                    int b = color.getBlue();// �����ɫ��Bֵ
                    tf_x.setText(String.valueOf(x));// ��ʾX����ֵ
                    tf_y.setText(String.valueOf(y));// ��ʾY����ֵ
                    tf_red.setText(String.valueOf(r));// ��ʾ��ɫ��Rֵ
                    tf_green.setText(String.valueOf(g));// ��ʾ��ɫ��Gֵ
                    tf_blue.setText(String.valueOf(b));// ��ʾ��ɫ��Bֵ
                    Thread.sleep(10);// �߳�����10����
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
