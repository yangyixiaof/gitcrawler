package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RandomMovePictureFrame extends JFrame {
    private final int winWIDTH = 450;
    private final int winHEIGHT = 300;
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RandomMovePictureFrame frame = new RandomMovePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public RandomMovePictureFrame() {
        super();
        setTitle("����ƶ���ͼƬ");
        setBounds(100, 100, winWIDTH, winHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RandomMovePicturePanel panel = new RandomMovePicturePanel();// ����������
        getContentPane().add(panel);// �������ӵ���������
        Thread thread = new Thread(panel);// �����̶߳���
        thread.start();// �����̶߳���
    }
    
    /**
     * @author zzk
     * ����ʵ��Runnable�ӿڵ��ڲ������
     */
    private class RandomMovePicturePanel extends JPanel implements Runnable {
        Random random = new Random();// ����Random����
        int x = 0;// ����ͼƬ�ƶ�λ�õ�x����
        int y = 0;// ����ͼƬ�ƶ�λ�õ�y����
        URL imgUrl = RandomMovePictureFrame.class
                .getResource("/image/picture.png");// ��ȡͼƬ��Դ��·��
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);// ��ȡͼ�����
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �������ϵ�����
            g.drawImage(img, x, y, this);// ������ָ��λ�û���ͼ��
        }
        public void run() {
            while (true) {
                x = random.nextInt(winWIDTH - 110);// ������ͼƬ�ƶ�λ�õ�x����
                y = random.nextInt(winHEIGHT - 140);// ������ͼƬ�ƶ�λ�õ�y����
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
