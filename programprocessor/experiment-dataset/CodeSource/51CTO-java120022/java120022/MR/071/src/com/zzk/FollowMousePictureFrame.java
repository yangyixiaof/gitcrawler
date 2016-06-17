package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FollowMousePictureFrame extends JFrame {
    private Image img = null;
    private ImageIcon icon = null;
    final JLabel lb_move = new JLabel();
    final JLabel lb_tip = new JLabel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FollowMousePictureFrame frame = new FollowMousePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FollowMousePictureFrame() {
        super();
        setTitle("������ƶ���ͼƬ");
        getContentPane().setLayout(null);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = FollowMousePictureFrame.class
                .getResource("/image/coney.png");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        icon = new ImageIcon(img);// ����ͼ��ͼ��
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                int x = e.getX(); // �������ڴ��������еĺ�����ֵ
                int y = e.getY(); // �������ڴ��������е�������ֵ
                int w = lb_move.getWidth(); // ���������ƶ���ͼ�����ڱ�ǩ�Ŀ��
                int h = lb_move.getHeight(); // ���������ƶ���ͼ�����ڱ�ǩ�ĸ߶�
                int x1 = x - w / 2; // �����������ƶ���ͼ�����ڱ�ǩ�ĺ�����ֵ
                int y1 = y - h / 2; // �����������ƶ���ͼ�����ڱ�ǩ��������ֵ
                lb_move.setLocation(x1, y1); // ����������ƶ���ͼ�����ڱ�ǩ����ʾλ��
                int x2 = x - 52; // ������ʾ���ֵı�ǩ�ĺ�����ֵ
                int y2 = y1 + 120; // ������ʾ���ֵı�ǩ��������ֵ
                lb_tip.setLocation(x2, y2); // ������ʾ���ֵı�ǩ����ʾλ��
            }
        });
        lb_move.setIcon(icon);
        lb_move.setBounds(191, 61, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // ��ͼƬ�ϵ�������Ҽ��˳�ϵͳ
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
        lb_tip.setText("�Ҿ͸����㡣����");
        lb_tip.setBounds(180, 191, 104, 18);
        getContentPane().add(lb_tip);
        Thread thread = new Thread(new FlareThread()); // �����̶߳���
        thread.start(); // �����̶߳���
    }
    
    /**
     * @author Administrator
     *         ��̬�ı�������ɫ���߳�
     */
    class FlareThread implements Runnable {
        public void run() {
            while (true) {
                int red = (int) (Math.random() * 256); // �������RGB��ɫ�е�R������ɫ
                int green = (int) (Math.random() * 256); // �������RGB��ɫ�е�G������ɫ
                int blue = (int) (Math.random() * 256); // �������RGB��ɫ�е�B������ɫ
                lb_tip.setForeground(new Color(red, green, blue)); // ���ñ�ǩ�����ֵ�ǰ��ɫ
                try {
                    Thread.sleep(500); // �߳�����500����
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
