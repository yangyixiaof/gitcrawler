package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicFlexImageFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private DynamicFlexPanel imagePanel = null; // ����ͼ��������
    private Thread thread = null;// �����̶߳���
    
    public static void main(String args[]) {
        DynamicFlexImageFrame frame = new DynamicFlexImageFrame();
        frame.setVisible(true);
    }
    
    public DynamicFlexImageFrame() {
        super();
        URL imgUrl = DynamicFlexImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new DynamicFlexPanel(); // ����ͼ��������
        this.setBounds(200, 160, 340, 200); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ��������ͼ��������
        thread = new Thread(imagePanel);// �����̶߳���
        thread.start();// �����߳�
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ��̬����"); // ���ô������
    }
    
    // ���������
    class DynamicFlexPanel extends JPanel implements Runnable {
        private boolean flag = true;// ��Ǳ���
        int width = 0;// ����ͼ���ȵı���
        int height = 0;// ����ͼ��߶ȵı���
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �����ͼ�����ĵ�����
            g.drawImage(img, 0, 0, width, height, this); // ����ָ����С��ͼƬ
        }
        public void run() {
            while (true) {
                if (flag) {
                    width+=2;// �������ֵ
                    height++;// �����߶�ֵ
                    if (width >= getWidth() || height >= getHeight()) {
                        flag = false;// �ﵽͼ��Ŀ�Ȼ�߶�ʱ���ı��Ǳ�����ֵ
                    }
                } else {
                    width -= 2;// �������ֵ
                    height--;// �����߶�ֵ
                    if (width <= 0 || height <= 0) {
                        flag = true;// ͼ��Ŀ�Ȼ�߶�С�ڵ���0ʱ���ı��Ǳ�����ֵ
                    }
                }
                repaint();// ����paint()����
                try {
                    Thread.sleep(20);// �߳�����20����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
