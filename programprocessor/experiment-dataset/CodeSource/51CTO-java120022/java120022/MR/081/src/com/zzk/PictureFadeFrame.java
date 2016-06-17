package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private Image fadeImage = null;// �������ֵ�ͼ�����
    private TextFadePanel textFadePanel = null; // ����ͼ��������
    private URL imgUrl = null;// ����URL����
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        imgUrl = PictureFadeFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ�����
        imgUrl = PictureFadeFrame.class.getResource("/img/fade.jpg");// ��ȡͼƬ��Դ��·��
        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ�����
        textFadePanel = new TextFadePanel(); // ����ͼ��������
        this.setBounds(200, 160, 310, 230); // ���ô����С��λ��
        this.add(textFadePanel); // �ڴ��������ͼ��������
        Thread thread = new Thread(textFadePanel);// �����̶߳���
        thread.start();// �����̶߳���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ���ֶ���"); // ���ô������
    }
    
    // ���������
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// ��Ǳ���
        String value = "";// ��Ż������ݵı���
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(),getHeight());// �����ͼ�����ĵ�����
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// ����ͼ��
            g.drawImage(fadeImage,10,10,getWidth()-20,getHeight()-20,this);// �������ֵ�ͼ�����
        }
        public void run() {
            try {
                while (true) { // ��ȡ����
                    Thread.sleep(200); // ��ǰ�߳�����200����
                    if (flag) {// flagΪtrue
                        flag = false;   // ��ֵΪfalse
                        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ�����
                    } else {
                        flag = true;// ��ֵΪtrue
                        fadeImage = Toolkit.getDefaultToolkit().getImage(""); // ��ȡͼ�����
                    }
                    repaint();// ����paint()����
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
