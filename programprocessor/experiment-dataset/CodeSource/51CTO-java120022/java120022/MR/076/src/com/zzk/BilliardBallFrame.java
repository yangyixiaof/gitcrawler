package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class BilliardBallFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BilliardBallFrame frame = new BilliardBallFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public BilliardBallFrame() {
        super();
        setTitle("ײ�򶯻�");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BilliardBallPanel panel = new BilliardBallPanel();// ����������
        getContentPane().add(panel);// �������ӵ���������
        Thread thread = new Thread(panel);// �����̶߳���
        thread.start();// �����̶߳���
    }
    
    /**
     * ����ʵ��Runnable�ӿڵ��ڲ������
     */
    private class BilliardBallPanel extends JPanel implements Runnable {
        int x1 = 0;// �����һ�����ƶ�λ�õ�x����
        int y1 = 60;// �����һ�����ƶ�λ�õ�y����
        int x2 = 326 - 30;// ����ڶ������ƶ�λ�õĳ�ʼx����Ϊ�����ȼ���Ŀ��
        int y2 = 60;// ����ڶ������ƶ�λ�õ�y����
        int width = 30;// ������Ŀ��
        int height = 30;// ������ĸ߶�
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �������ϵ�����
            g.setColor(Color.BLUE);// ������ɫ
            g.fillOval(x1, y1, width, height);// ���Ƶ�һ����
            g.setColor(Color.RED);// ������ɫ
            g.fillOval(x2, y2, width, height);// ���Ƶڶ�����
        }
        public void run() {
            boolean flag1 = true;// ������һ����ı�Ǳ���
            boolean flag2 = true;// �����ڶ�����ı�Ǳ���
            while (true) {
                // ��һ�����x����ֵ������Ŀ�ȴ��ڵ��ڵڶ������x����ֵ��1����ʾ��������
                if (x1 + width >= x2 + 1) {// ������ײ
                    x1 = x1 + 5;// �����һ�����x����
                    width = width - 10;// ��Ŀ�ȼ�10
                    x2 = x1 + width;// ����ڶ������x����
                    flag1 = false;// ���õ�һ����ı�Ǳ���Ϊfalse
                    flag2 = false;// ���õڶ�����ı�Ǳ���Ϊfalse
                    repaint();// ����paint()����
                } else {// ����û��ײ
                    if (flag1) {// ��Ǳ���Ϊtrue����һ��������
                        x1 = x1 + 10;// ͼƬx����ֵ��10����һ��������
                        width = 30;// ��Ŀ��
                    } else {// ��Ǳ���Ϊfalse����һ��������
                        x1 = x1 - 10;// ͼƬx����ֵ��10����һ��������
                        width = 30;// ��Ŀ��
                        if (x1 <= 0) {// ͼƬ��x����ֵС�ڵ���0
                            x1 = 0;// ͼƬ��x����ֵ����0
                            flag1 = true;// ���ñ�Ǳ���Ϊtrue
                        }
                    }
                    if (flag2) {// ��Ǳ���Ϊtrue���ڶ���������
                        x2 = x2 - 10;// ͼƬx����ֵ��10�����ڶ���������
                        width = 30;
                    } else {// ��Ǳ���Ϊfalse���ڶ���������
                        x2 = x2 + 10;// ͼƬx����ֵ��10�����ڶ���������
                        width = 30;// ��Ŀ��
                        if (x2 >= getWidth() - width) {// ͼƬ��x����ֵ���ڵ������Ŀ������Ŀ��֮��
                            x2 = getWidth() - width ;// ͼƬ��x����ֵ�������Ŀ������Ŀ��֮��
                            flag2 = true;// ���ñ�Ǳ���Ϊtrue
                        }
                    }
                }
                try {
                    Thread.sleep(200);// ����200����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// ����paint()����
            }
        }
    }
}
