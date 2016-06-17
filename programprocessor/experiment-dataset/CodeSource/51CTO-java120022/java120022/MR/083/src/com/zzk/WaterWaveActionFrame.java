package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaterWaveActionFrame extends JFrame {
    
    private Thread waveThread; // ����ͼƬ��Ӱ�߳�
    private WaterWaveActionPanel panel = new WaterWaveActionPanel();
    
    public WaterWaveActionFrame() {
        super();
        setBounds(100, 100, 356, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ˮ��������Ч");
        getContentPane().add(panel);
        waveThread = new Thread(panel); // �����̶߳���
        waveThread.start(); // �����߳�
    }
    
    public static void main(String[] args) {
        WaterWaveActionFrame frame = new WaterWaveActionFrame();
        frame.setVisible(true);
    }
    
    class WaterWaveActionPanel extends JPanel implements Runnable {
        private Graphics graphics;// Graphics����
        private Graphics waveGraphics;// ��Ӱ��Graphics����
        private Image image;// ԭImage����
        private Image waveImage;// ��ʾ��Ӱ��Image����
        private int currentIndex;// ��ǰͼ������
        private int imageWidth;// ͼ��Ŀ��
        private int imageHeight;// ͼ��ĸ߶�
        private boolean imageLoaded;// ��ʾͼƬ�Ƿ񱻼��صı��
        
        public void paint(Graphics g) {
            if (waveImage != null) {
                g.drawImage(waveImage, -currentIndex * imageWidth, 0, this);// ����ͼ��
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight);// �����ʾ�����Ҳ������
        }
        
        public void run() {
            currentIndex = 0;
            while (!imageLoaded) {// ���ͼƬδ����
                repaint();// �ػ���Ļ
                graphics = getGraphics();// ���Graphics����
                MediaTracker mediatracker = new MediaTracker(this);// ����ý����ٶ���
                URL imgUrl = WaterWaveActionFrame.class
                        .getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
                image = Toolkit.getDefaultToolkit().getImage(imgUrl);// ��ȡͼ����Դ
                mediatracker.addImage(image, 0);// ���ͼƬ
                try {
                    mediatracker.waitForAll();// ����ͼƬ
                    imageLoaded = !mediatracker.isErrorAny();// �Ƿ��д�����
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) {// ����ͼƬʧ��
                    graphics.drawString("����ͼƬ����", 10, 40);// ���������Ϣ
                    continue;
                }
                imageWidth = image.getWidth(this);// �õ�ͼ����
                imageHeight = image.getHeight(this);// �õ�ͼ��߶�
                createWave();// ���÷���,ʵ�ֶ���Ч��
                break;
            }
            try {
                while (true) {
                    repaint();// �ػ���Ļ
                    currentIndex++;// ������ǰͼ������
                    if (currentIndex == 12) {// �����ǰͼ������Ϊ12
                        currentIndex = 0;// ���õ�ǰͼ������Ϊ0
                    }
                    Thread.sleep(50);// �߳�����
                }
            } catch (InterruptedException ex) {
            }
        }
        
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight);// ��ͼ��߶ȴ���Imageʵ��
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics();// �õ�Image�����Graphics����
                g.drawImage(image, 0, 0, this);// ����Image
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2));// ����ͼ������
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight);// �õ�����Ч����Imageʵ��
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics();// �õ�����Ч����Graphicsʵ��
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this);// ����ͼ��
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// ���÷���
                    j++;
                }
                g.drawImage(image, 0, 0, this);// ����ͼ��
            }
        }
        
        public void simulateWaves(Graphics g, int i) {// ����Ч��ģ��
            double d = (6.0 * i) / 12;
            int j = (12 - i) * imageWidth;// ����ˮƽ�ƶ��ľ���
            int waveHeight = imageHeight / 16;// ���ڼ���ˮ���ĸ߶�
            for (int m = 0; m < imageHeight; m++) {
                int k = (int) ((waveHeight * (m + 28) * Math.sin(waveHeight
                        * (imageHeight - m) / (m + 1) + d)) / imageHeight);// ���ڿ���Ҫ������������Ŀ��
                if (m < -k)
                    g.copyArea(12 * imageWidth, m, imageWidth, 1, -j, 0);// ����ͼ������,�γɲ���
                else
                    g.copyArea(12 * imageWidth, m + k, imageWidth, 1, -j, -k);// ����ͼ������,�γɲ���
            }
        }
    }
}