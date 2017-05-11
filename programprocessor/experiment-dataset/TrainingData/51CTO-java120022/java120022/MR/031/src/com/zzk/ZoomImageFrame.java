package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ZoomImageFrame extends JFrame {
    private Image img = null;  // ����ͼ�����
    private ZoomImagePanel imagePanel = null;  // ����ͼ��������
    private int imgWidth, imgHeight;// ���ڴ洢ͼƬ�Ŀ�Ⱥ͸߶�
    private int newW, newH;// ���ڴ洢ͼƬ���ź�Ŀ�Ⱥ͸߶�
    private JSlider slider = null;// �����������
    public static void main(String args[]) {
        ZoomImageFrame frame = new ZoomImageFrame();
        frame.setVisible(true);
    }
    public ZoomImageFrame() {
        super();
        URL imgUrl = ZoomImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imagePanel = new ZoomImagePanel();  // ����ͼ��������
        this.setBounds(200, 160, 355, 253); // ���ô����С��λ��
        this.add(imagePanel); // �ڴ����в�λ�����ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("����ͼ��"); // ���ô������
        slider = new JSlider();// �����������
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                imagePanel.repaint();// ���µ���������paint()����
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);// �ڴ���ײ�λ����ӻ������
    }
    // ���������
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �����ͼ�����ĵ�����
            imgWidth = img.getWidth(this); // ��ȡͼƬ���
            imgHeight = img.getHeight(this); // ��ȡͼƬ�߶�
            float value = slider.getValue();// ���������ȡֵ
            newW = (int) (imgWidth * value / 100);// ����ͼƬ���ź�Ŀ��
            newH = (int) (imgHeight * value / 100);// ����ͼƬ���ź�ĸ߶�
            g.drawImage(img, 0, 0, newW, newH, this);// ����ָ����С��ͼƬ
        }
    }
}
