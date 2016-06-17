package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureInvertedFrame extends JFrame {
    private PictureInvertedPanel invertedPanel = new PictureInvertedPanel();
    public PictureInvertedFrame() {
        super();
        setTitle("ͼƬ��ӰЧ��");
        setBounds(100, 100, 356, 438);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(invertedPanel);
    }
    
    public static void main(String[] args) {
        PictureInvertedFrame frame = new PictureInvertedFrame();
        frame.setVisible(true);
    }
    
    class PictureInvertedPanel extends JPanel {
        private Graphics graphics; // Graphics����
        private Graphics waveGraphics; // ����ˮ����Graphics����
        private Image oldImage; // ԭͼ�����
        private Image waveImage; // ������ʾˮ��Ч����ͼ�����
        private int currentImage, imageWidth, imageHeight;
        private boolean imageLoaded; // ��ʾͼƬ�Ƿ񱻼��صı��
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth,
                        imageHeight, this); // ����ͼ��
            }
            g.drawImage(oldImage, 0, 1, this);// ����ԭͼƬ
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight*2);// �����ʾ�����Ҳ������
        }
        public void drawWaterWave() {
            currentImage = 0;
            if (!imageLoaded) { // ���δ����ͼƬ
                graphics = getGraphics(); // ��û�ͼ�����Ķ���
                MediaTracker mediatracker = new MediaTracker(this); // ����ý����ٶ���
                URL imgUrl = PictureInvertedFrame.class
                        .getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
                mediatracker.addImage(oldImage, 0); // ���ͼƬ
                try {
                    mediatracker.waitForAll(); // ����ͼƬ
                    imageLoaded = !mediatracker.isErrorAny(); // �Ƿ��д�����
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) { // ͼƬ����ʧ��
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
            for (int l = 0; l < imageHeight; l++) {
                int k = (int) ((waveHeight * (l + 28) * Math.sin(waveHeight
                        * (imageHeight - l) / (l + 1))) / imageHeight);// ���㸴�����صĴ�ֱ����
                if (l < -k){
                    g.copyArea(12 * imageWidth, l, imageWidth, 1, -j, 0); // ����ͼ������,�γ�ˮ��
                } else {
                    g.copyArea(12 * imageWidth, l + k, imageWidth, 1, -j, -k);// ����ͼ������,�γ�ˮ��
                }
            }
        }
    }
}