package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureInvertedFrame extends JFrame {
    private PictureInvertedPanel invertedPanel = new PictureInvertedPanel();
    public PictureInvertedFrame() {
        super();
        setTitle("图片倒影效果");
        setBounds(100, 100, 356, 438);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(invertedPanel);
    }
    
    public static void main(String[] args) {
        PictureInvertedFrame frame = new PictureInvertedFrame();
        frame.setVisible(true);
    }
    
    class PictureInvertedPanel extends JPanel {
        private Graphics graphics; // Graphics对象
        private Graphics waveGraphics; // 绘制水波的Graphics对象
        private Image oldImage; // 原图像对象
        private Image waveImage; // 声明表示水波效果的图像对象
        private int currentImage, imageWidth, imageHeight;
        private boolean imageLoaded; // 表示图片是否被加载的标记
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth,
                        imageHeight, this); // 绘制图像
            }
            g.drawImage(oldImage, 0, 1, this);// 绘制原图片
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight*2);// 清除显示区域右侧的内容
        }
        public void drawWaterWave() {
            currentImage = 0;
            if (!imageLoaded) { // 如果未加载图片
                graphics = getGraphics(); // 获得绘图上下文对象
                MediaTracker mediatracker = new MediaTracker(this); // 创建媒体跟踪对象
                URL imgUrl = PictureInvertedFrame.class
                        .getResource("/img/image.jpg");// 获取图片资源的路径
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
                mediatracker.addImage(oldImage, 0); // 添加图片
                try {
                    mediatracker.waitForAll(); // 加载图片
                    imageLoaded = !mediatracker.isErrorAny(); // 是否有错误发生
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) { // 图片加载失败
                    graphics.drawString("图片加载错误", 10, 40); // 绘制错误信息
                    return;
                }
                imageWidth = oldImage.getWidth(this); // 得到图像宽度
                imageHeight = oldImage.getHeight(this); // 得到图像高度
                createWave(); // 创建水波效果
            }
        }
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight); // 以图像宽度和高度创建图像对象
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics(); // 得到Image对象的Graphics对象
                g.drawImage(oldImage, 0, 0, this); // 绘制原图像对象
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2)); // 拷贝图像区域
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight); // 得到水波效果的图像对象
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics(); // 得到水波效果的绘图上下文对象
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this); // 绘制图像
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// 调用方法，模拟水波效果
                    j++;
                }
            }
        }
        public void simulateWaves(Graphics g, int i) { // 水波效果模拟
            int j = (12 - i) * imageWidth;// 计算复制像素的水平距离
            int waveHeight = imageHeight / 16;// 计算水波高度
            for (int l = 0; l < imageHeight; l++) {
                int k = (int) ((waveHeight * (l + 28) * Math.sin(waveHeight
                        * (imageHeight - l) / (l + 1))) / imageHeight);// 计算复制像素的垂直距离
                if (l < -k){
                    g.copyArea(12 * imageWidth, l, imageWidth, 1, -j, 0); // 拷贝图像区域,形成水波
                } else {
                    g.copyArea(12 * imageWidth, l + k, imageWidth, 1, -j, -k);// 拷贝图像区域,形成水波
                }
            }
        }
    }
}