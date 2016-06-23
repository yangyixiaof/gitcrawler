package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);   // ��ȡͼƬ��Դ
        rotatePanel = new RotatePanel();  // ������תͼ���������
        this.setBounds(150, 120, 380, 310);                 // ���ô����С��λ��
        add(rotatePanel);// �ڴ����Ϸ���ͼ�����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ���ô���ر�ģʽ
        this.setTitle("��תͼ��");                     // ���ô������
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;         // ���Graphics2D����
            g2.drawImage(img, 80, 10, 260, 150, this);      // ����ָ����С��ͼƬ
            g2.rotate(Math.toRadians(10));                 // ��ͼƬ��ת10��
            g2.drawImage(img, 80, 10, 260, 150, this);      // ����ָ����С��ͼƬ
            g2.rotate(Math.toRadians(10));                // ��ͼƬ��ת10��
            g2.drawImage(img, 80, 10, 260, 150, this);      // ����ָ����С��ͼƬ
        }
    }
}
