package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFadeFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private TextFadePanel textFadePanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        TextFadeFrame frame = new TextFadeFrame();
        frame.setVisible(true);
    }
    
    public TextFadeFrame() {
        super();
        URL imgUrl = TextFadeFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        textFadePanel = new TextFadePanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(textFadePanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(textFadePanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("���ֵ��뵭����Ч"); // ���ô������
    }
    
    // ���������
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// �����Ǳ��������ڿ���x��ֵ
        float x = 0.0f;// �����ʾ͸���ȵı���x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// ��ñ�ʾ͸���ȵ�AlphaComposite����
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// ����ͼ��
            Font font = new Font("���Ŀ���", Font.BOLD, 60);// �����������
            g2.setFont(font);// ָ������
            g2.setColor(Color.RED);// ָ����ɫ
            g2.setComposite(alpha);// ָ��AlphaComposite����
            g2.drawString("��̴ʵ�", 30, 120);// �����ı�
        }
        ////// ע�⸡�������������������ȷ�����Լ���if (x <= 0.0f) { x = 0.0f;
        public void run() {
            while (true) {
                if (flag) {        // flagΪtrueʱ
                    x-=0.1f;       // x���м�0.1����
                    if (x <= 0.0f) {// xС�ڵ���0.0fʱ
                        x = 0.0f;   // x����0.0f
                        flag = false;// Ϊflag��ֵΪfalse
                    }
                } else {// flagΪfalseʱ
                    x+=0.1f;// x���м�0.1����
                    if (x >= 1.0f) {// x���ڵ���1.0fʱ
                        x = 1.0f;// x����1.0f
                        flag = true;// Ϊflag��ֵΪtrue
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// ���»�ñ�ʾ͸���ȵ�AlphaComposite����
                repaint();// ����paint()����
                try {
                    Thread.sleep(150);// ����150����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
