package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NegativeImageFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private NegativeImagePanel negativeImagePanel = null; // ����ͼ��������
    
    public static void main(String args[]) {
        NegativeImageFrame frame = new NegativeImageFrame();
        frame.setVisible(true);
    }
    
    public NegativeImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("ͼƬ������Ч"); // ���ô������
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativeImagePanel = new NegativeImagePanel(); // ����ͼ��������
        this.add(negativeImagePanel); // �ڴ��������ͼ��������
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256*1];// ������ʾ��ɫ����ķ�������
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// Ϊ���鸳ֵ
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// �������ұ����
                LookupOp op = new LookupOp(table,null);// ����ʵ�ִ�Դ��Ŀ����Ҳ�����LookupOp����
                image = op.filter(image, null);// ����LookupOp�����filter()������ʵ��ͼ������
                repaint();  // ����paint()����
            }
        });
        button.setText("��    ��");
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
    
    
    // ���������
    class NegativeImagePanel extends JPanel {
        public NegativeImagePanel(){
            Image img = null;// ��������ͼ�����
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // ����ͼ�����
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0,  null);// �ڻ���ͼ������ϻ���ͼ��
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ���ƻ���ͼ�����
            }
        }
    }
}
