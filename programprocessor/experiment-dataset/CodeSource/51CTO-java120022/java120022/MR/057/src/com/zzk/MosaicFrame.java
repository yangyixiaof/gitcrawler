package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MosaicFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private Image img = null; // ����ͼ�����
    private MosaicPanel mosaicPanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
    
    public MosaicFrame() {
        super();
        URL imgUrl = MosaicFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        mosaicPanel = new MosaicPanel(); // ����ͼ��������
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        this.add(mosaicPanel); // �ڴ��������ͼ��������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ��������Ч"); // ���ô������

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int x = 104;// ���λ��Ƶ��x����
                int y = 60; // ���λ��Ƶ��y����
                Rectangle2D.Float rect = null;
                image = new BufferedImage(getWidth() + 10, getHeight(),
                        BufferedImage.TYPE_INT_ARGB);// ��������ͼ�����
                Graphics2D gs2d = (Graphics2D) image.getGraphics();// ��û���ͼ������Graphics2D����
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.90f);// ��ñ�ʾ͸���ȵ�AlphaComposite����
                gs2d.setComposite(alpha);// ����͸����
                GradientPaint paint = null;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint = new GradientPaint(x, y, Color.white, x + 10,
                                y + 10, Color.gray,true);// ����ѭ�������GraphientPaint����
                        gs2d.setPaint(paint);// ���ý���
                        rect = new Rectangle2D.Float(x, y, 20, 20);// �������ζ���
                        gs2d.fill(rect);// �ڻ���ͼ������ϻ��ƾ���
                        y = y + 20;// ������һ�����ε�y����
                    }
                    y = 60;// ��ԭy����
                    x = x + 20;// ����x����
                }
                
                for (int i = 0; i < 3; i++) {// ��forѭ����ʵ��3��ģ��
                    float[] elements = new float[9];// �����ʾ���ط���������
                    for (int j = 0; j < 9; j++) {
                        elements[j] = 0.11f;// Ϊ���鸳ֵ
                    }
                    convolve(elements);// ���÷�����ʵ��ģ������
                }
                mosaicPanel.repaint();// ����paint()����
            }
        });
        button.setText("���������");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// ���� Kernel����
        ConvolveOp op = new ConvolveOp(kernel);// ����ConvolveOp����
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // ���˻���ͼ�����
    }
    
    // ���������
    class MosaicPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, getWidth(),getHeight(), this);// ����ͼ�����
            g2.drawImage(image, 0, 0, this);// ���ƻ���ͼ�����
        }
    }
}
