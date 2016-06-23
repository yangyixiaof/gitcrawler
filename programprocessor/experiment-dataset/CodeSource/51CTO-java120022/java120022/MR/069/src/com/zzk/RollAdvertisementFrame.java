package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RollAdvertisementFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private RollAdvertisementPanel rollAdvertisementPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        RollAdvertisementFrame frame = new RollAdvertisementFrame();
        frame.setVisible(true);
    }
    
    public RollAdvertisementFrame() {
        super();
        URL imgUrl = RollAdvertisementFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        rollAdvertisementPanel = new RollAdvertisementPanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(rollAdvertisementPanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(rollAdvertisementPanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("���������Ļ"); // ���ô������
    }
    
    // ���������
    class RollAdvertisementPanel extends JPanel implements Runnable {
        int x = 316;// �洢���Ƶ��x����
        int y = 190;// �洢���Ƶ��y����
        String value = "���ձ�̴ʵ���ַ��http://www.mrbccd.com";// �洢���Ƶ�����
        public void paint(Graphics g) {
            g.clearRect(0, 0, 316, 237);// �����ͼ�����ĵ�����
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
            Font font = new Font("���Ŀ���", Font.BOLD, 20);// �����������
            g.setFont(font);// ָ������
            g.setColor(Color.RED);// ָ����ɫ
            g.drawString(value, x, y);// �����ı�
        }
        public void run() {
            try {
                while (true) { // ��ȡ����
                    Thread.sleep(50); // ��ǰ�߳�����1��
                    if (x <= -400) {// ���������Ը�����Ҫ���е���
                        x = 316;// x���궨λ�����Ҳ�
                    } else {
                        x -= 2;// x��������
                    }
                    repaint();// ����paint()����
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
