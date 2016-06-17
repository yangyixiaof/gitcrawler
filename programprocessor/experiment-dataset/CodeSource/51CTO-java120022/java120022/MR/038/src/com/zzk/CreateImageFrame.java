package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateImageFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateImageFrame frame = new CreateImageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CreateImageFrame() {
        super();
        setTitle("ʹ������ֵ����ͼ��");
        setBounds(100, 100, 300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new CreateImagePanel());
    }
    
    class CreateImagePanel extends JPanel {// ����������ֵ����ͼ��������
        public void paint(Graphics g) {
            int w = 300;// ���
            int h = 220;// �߶�
            int pix[] = new int[w * h];// �洢����ֵ������
            int index = 0;// �洢���������
            for (int y = 0; y < h; y++) {// ��������е������Ӻ�ɫ���䵽��ɫ
                int red = (y * 255) / (h - 1);// �����������ɫֵ
                for (int x = 0; x < w; x++) {// �ں�����е������Ӻ�ɫ���䵽��ɫ
                    int blue = (x * 255) / (w - 1);// ����������ɫֵ
                    // ͨ����λ������߼��������������ֵ������ֵ����������
                    pix[index++] = (255 << 24) | (red << 16) | blue;
                }
            }
            // ����ʹ������ΪImage��������ֵ��ImageProducer����
            ImageProducer imageProducer = new MemoryImageSource(w, h, pix, 0, w);
            Image img = createImage(imageProducer);// ����ͼ�����
            g.drawImage(img, 0, 0,getWidth(),getHeight(), this);// ����ͼ��
        }
    }
}
