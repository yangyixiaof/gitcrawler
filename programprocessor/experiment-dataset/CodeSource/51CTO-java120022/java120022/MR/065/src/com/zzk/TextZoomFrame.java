package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextZoomFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private TextZoomPanel textZoomPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        TextZoomFrame frame = new TextZoomFrame();
        frame.setVisible(true);
    }
    
    public TextZoomFrame() {
        super();
        URL imgUrl = TextZoomFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        textZoomPanel = new TextZoomPanel(); // ����ͼ��������
        this.setBounds(200, 160, 376, 237); // ���ô����С��λ��
        this.add(textZoomPanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(textZoomPanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("����������Ч"); // ���ô������
    }
    
    // ���������
    class TextZoomPanel extends JPanel implements Runnable {
        boolean flag = false;// �����Ǳ��������ڿ���x��ֵ
        int x = 12;// �����ʾ�����С�ı���x
        Font font = new Font("���Ŀ���", Font.BOLD, x);// �����������
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ����ͼ��
            g2.setFont(font);// ָ������
            g2.setColor(Color.RED);// ָ����ɫ
            g2.drawString("��̴ʵ�", 30, 120);// �����ı�
        }
        public void run() {
            while (true) {
                if (flag) {        // flagΪtrueʱ
                    x-=1;       // x���м�1����
                    if (x <= 12) {// xС�ڵ���12ʱ
                        x = 12;   // x����12
                        flag = false;// Ϊflag��ֵΪfalse
                    }
                } else {// flagΪfalseʱ
                    x+=1;// x���м�1����
                    if (x >= 72) {// x���ڵ���72ʱ
                        x = 72;// x����72
                        flag = true;// Ϊflag��ֵΪtrue
                    }
                }
                font = new Font("���Ŀ���", Font.BOLD, x);// ���´����������
                repaint();// ����paint()����
                try {
                    Thread.sleep(50);// ����50����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
