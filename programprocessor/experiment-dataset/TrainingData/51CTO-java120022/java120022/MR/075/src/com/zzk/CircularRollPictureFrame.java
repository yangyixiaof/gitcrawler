package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularRollPictureFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CircularRollPictureFrame frame = new CircularRollPictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CircularRollPictureFrame() {
        super();
        setTitle("ѭ������ͼƬ");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircularRollPicturePanel panel = new CircularRollPicturePanel();// ����������
        getContentPane().add(panel);// �������ӵ���������
        Thread thread = new Thread(panel);// �����̶߳���
        thread.start();// �����̶߳���
    }
    
    /**
     * @author ������
     * ����ʵ��Runnable�ӿڵ��ڲ������
     */
    private class CircularRollPicturePanel extends JPanel implements Runnable {
        int x = 0;// ����ͼƬ�ƶ�λ�õ�x����
        int y = 30;// ����ͼƬ�ƶ�λ�õ�y����
        URL imgUrl = CircularRollPictureFrame.class.getResource("/image/picture.png");// ��ȡͼƬ��Դ��·��
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ�����
        public void paint(Graphics g){
            g.clearRect(0, 0, getWidth(), getHeight());// �������ϵ�����
            g.drawImage(img, x, y, this);// ������ָ��λ�û���ͼ��
        }
        public void run() {
            boolean flag = true;// ������Ǳ���
            while (true){
                if (flag) {// ��Ǳ���Ϊtrue
                    x = x + 10;// ͼƬx����ֵ��10
                    if (x >= getWidth() - img.getWidth(this)) {// ͼƬ��x����ֵ���ڵ��������ͼƬ��ȵĲ�
                        x = getWidth() - img.getWidth(this); // ͼƬ��x����ֵ���������ͼƬ��ȵĲ�
                        flag = false;// ���ñ�Ǳ���Ϊfalse
                    }
                }else {// ��Ǳ���Ϊfalse
                    x = x - 10;// ͼƬx����ֵ��10
                    if ( x <= 0 ) {// ͼƬ��x����ֵС�ڵ���0
                        x = 0;// ͼƬ��x����ֵ����0
                        flag = true;// ���ñ�Ǳ���Ϊtrue
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
