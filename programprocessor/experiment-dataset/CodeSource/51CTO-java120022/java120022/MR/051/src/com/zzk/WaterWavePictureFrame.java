package com.zzk;

import java.awt.*;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WaterWavePictureFrame extends JFrame {
    private WaterWavePicturePanel wavePanel = new WaterWavePicturePanel();
    
    public WaterWavePictureFrame() {
        super();
        setTitle("ˮ��Ч����ͼƬ");
        setBounds(100, 100, 356, 235);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(wavePanel);
    }
    
    public static void main(String[] args) {
        WaterWavePictureFrame frame = new WaterWavePictureFrame();
        frame.setVisible(true);
    }
    
    class WaterWavePicturePanel extends JPanel {
        private Graphics graphics; // Graphics����
        private Graphics waveGraphics; // ����ˮ����Graphics����
        private Image oldImage; // ԭͼ�����
        private Image waveImage; // ������ʾˮ��Ч����ͼ�����
        private int currentImage, imageWidth, imageHeight;
        private boolean isImageLoaded; // ��ʾͼƬ�Ƿ񱻼��صı��
        
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth, 0, this); // ����ͼ��
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight * 2);// �����ʾ�����Ҳ������
        }
        
        public void drawWaterWave() {
            currentImage = 0;
            if (!isImageLoaded) { // ���δ����ͼƬ
                graphics = getGraphics(); // ��û�ͼ�����Ķ���
                MediaTracker mediatracker = new MediaTracker(this); // ����ý����ٶ���
                URL imgUrl = WaterWavePictureFrame.class
                        .getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
                mediatracker.addImage(oldImage, 0); // ���ͼƬ
                try {
                    mediatracker.waitForAll(); // ����ͼƬ
                    isImageLoaded = !mediatracker.isErrorAny(); // �Ƿ��д�����
                } catch (InterruptedException ex) {
                }
                if (!isImageLoaded) { // ͼƬ����ʧ��
                    graphics.drawString("ͼƬ���ش���", 10, 40); // ���ƴ�����Ϣ
                    return;
                }
                imageWidth = oldImage.getWidth(this); // �õ�ͼ����
                imageHeight = oldImage.getHeight(this); // �õ�ͼ��߶�
                createWave(); // ����ˮ��Ч��
            }
        }
        
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight); // ��ͼ���Ⱥ͸߶ȴ���ͼ�����
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics(); // �õ�Image�����Graphics����
                g.drawImage(oldImage, 0, 0, this); // ����ԭͼ�����
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2)); // ����ͼ������
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight); // �õ�ˮ��Ч����ͼ�����
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics(); // �õ�ˮ��Ч���Ļ�ͼ�����Ķ���
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this); // ����ͼ��
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// ���÷�����ģ��ˮ��Ч��
                    j++;
                }
            }
        }
        
        public void simulateWaves(Graphics g, int i) { // ˮ��Ч��ģ��
            int j = (12 - i) * imageWidth;// ���㸴�����ص�ˮƽ����
            int waveHeight = imageHeight / 16;// ����ˮ���߶�
            for (int h = 0; h < imageHeight; h++) {
                int k = (int) ((waveHeight * (h + 28) * Math.sin(waveHeight
                        * (imageHeight - h) / (h + 1))) / imageHeight);// ���㸴�����صĴ�ֱ����
                if (h < -k) {
                    g.copyArea(12 * imageWidth, h, imageWidth, 1, -j, 0); // ����ͼ������,�γ�ˮ��
                } else {
                    g.copyArea(12 * imageWidth, h + k, imageWidth, 1, -j, -k);// ����ͼ������,�γ�ˮ��
                }
            }
        }
    }
}