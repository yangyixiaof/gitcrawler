package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawImageFrame extends JFrame {
    private Image img = null;  // ����ͼ�����
    private DrawImagePanel imagePanel = null;  // ����ͼ��������
    public static void main(String args[]) {
        DrawImageFrame frame = new DrawImageFrame();
        frame.setVisible(true);
    }
    public DrawImageFrame() {
        super();
        URL imgUrl = DrawImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new DrawImagePanel();  // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("����ͼ��"); // ���ô������
    }
    // ���������
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // ����ָ����ͼƬ
        }
    }
}
