package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class PictureMixFrame extends JFrame {
    private Image img1 = null; // ����ͼ�����
    private Image img2 = null; // ����ͼ�����
    private JSlider slider = null;
    private PictureMixPanel pictureMixPanel = null; // ����ͼ��������
    public static void main(String args[]) {
        PictureMixFrame frame = new PictureMixFrame();
        frame.setVisible(true);
    }
    
    public PictureMixFrame() {
        super();
        URL imgUrl = PictureMixFrame.class.getResource("/img/img.jpg");// ��ȡͼƬ��Դ��·��
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        imgUrl = PictureMixFrame.class.getResource("/img/imag.jpg");// ��ȡͼƬ��Դ��·��
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        pictureMixPanel = new PictureMixPanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(pictureMixPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ�ܺ���Ч"); // ���ô������

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                pictureMixPanel.repaint();// ���µ���������paint()����
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);
    }
    
    // ���������
    class PictureMixPanel extends JPanel {
        boolean flag = true;// �����Ǳ��������ڿ���x��ֵ
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D����
            g2.drawImage(img1, 0, 0,  getWidth(), getHeight(), this);// ����ͼ��
            float value = slider.getValue();// ���������ȡֵ
            float alphaValue = value / 100;// ����͸���ȵ�ֵ
            alpha = AlphaComposite.SrcOver.derive(alphaValue);// ��ñ�ʾ͸���ȵ�AlphaComposite����
            g2.setComposite(alpha);// ָ��AlphaComposite����
            g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);// ���Ƶ���͸���Ⱥ��ͼƬ
        }
        
    }
}
