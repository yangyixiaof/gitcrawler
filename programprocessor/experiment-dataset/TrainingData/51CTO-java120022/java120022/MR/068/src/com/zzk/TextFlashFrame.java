package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFlashFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private TextFlashPanel textFlashPanel = null; // ����ͼ��������
    public static void main(String args[]) {
        TextFlashFrame frame = new TextFlashFrame();
        frame.setVisible(true);
    }
    
    public TextFlashFrame() {
        super();
        URL imgUrl = TextFlashFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        textFlashPanel = new TextFlashPanel(); // ����ͼ��������
        this.setBounds(200, 160, 310, 230); // ���ô����С��λ��
        this.add(textFlashPanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(textFlashPanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("����������Ч"); // ���ô������
    }
    
    // ���������
    class TextFlashPanel extends JPanel implements Runnable {
        boolean flag = true;// ��Ǳ���
        String value = "";// ��Ż������ݵı���
        public void paint(Graphics g) {
            g.clearRect(0, 0, 310, 230);// �����ͼ�����ĵ�����
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// ����ͼ��
            Font font = new Font("���Ŀ���", Font.BOLD, 42);// �����������
            g.setFont(font);// ָ������
            g.setColor(Color.RED);// ָ����ɫ
            g.drawString(value, 10, 110);// �����ı�
            
        }
        public void run() {
            try {
                while (true) { // ��ȡ����
                    Thread.sleep(150); // ��ǰ�߳�����1��
                    if (flag) {// flagΪtrue
                        flag = false;   // ��ֵΪfalse
                        value="���ձ�̴ʵ�";// Ϊvalue��ֵ
                    } else {
                        flag = true;// ��ֵΪtrue
                        value="";// ��ֵΪ���ַ���
                    }
                    repaint();// ����paint()����
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
