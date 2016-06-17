package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CaptionSpecificFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private CaptionSpecificPanel captionSpecificPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        CaptionSpecificFrame frame = new CaptionSpecificFrame();
        frame.setVisible(true);
    }
    
    public CaptionSpecificFrame() {
        super();
        URL imgUrl = CaptionSpecificFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        captionSpecificPanel = new CaptionSpecificPanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(captionSpecificPanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(captionSpecificPanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("��Ļ��ʾ��Ч"); // ���ô������
    }
    
    // ���������
    class CaptionSpecificPanel extends JPanel implements Runnable {
        int x = 50;// �洢���Ƶ��x����
        int y = 216;// �洢���Ƶ��y����
        String value = "���ձ�̴ʵ���ַ";// �洢���Ƶ�����
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
                    Thread.sleep(100); // ��ǰ�߳�����1��
                    if (y <= 216 - 50) {// ����Ѿ������ƶ�50����
                        y = 216;// y���궨λ�����·�
                        if (value.equals("���ձ�̴ʵ���ַ")) {
                            value = "http://www.mrbccd.com";// �ı���Ƶ�����
                        } else {
                            value = "���ձ�̴ʵ���ַ";// �ı���Ƶ�����
                        }
                    } else {// �����û�����ƶ���50����
                        y -= 2;// y��������
                    }
                    repaint();// ����paint()����
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
