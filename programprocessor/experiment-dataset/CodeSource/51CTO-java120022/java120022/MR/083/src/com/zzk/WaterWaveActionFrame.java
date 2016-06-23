package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaterWaveActionFrame extends JFrame {
    
    private Thread waveThread; // 声明图片倒影线程
    private WaterWaveActionPanel panel = new WaterWaveActionPanel();
    
    public WaterWaveActionFrame() {
        super();
        setBounds(100, 100, 356, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("水波动画特效");
        getContentPane().add(panel);
        waveThread = new Thread(panel); // 创建线程对象
        waveThread.start(); // 启动线程
    }
    
    public static void main(String[] args) {
        WaterWaveActionFrame frame = new WaterWaveActionFrame();
        frame.setVisible(true);
    }
    
    class WaterWaveActionPanel extends JPanel implements Runnable {
        private Graphics graphics;// Graphics对象
        private Graphics waveGraphics;// 倒影的Graphics对象
        private Image image;// 原Image对象
        private Image waveImage;// 表示倒影的Image对象
        private int currentIndex;// 当前图像索引
        private int imageWidth;// 图像的宽度
        private int imageHeight;// 图像的高度
        private boolean imageLoaded;// 表示图片是否被加载的标记
        
        public void paint(Graphics g) {
            if (waveImage != null) {
                g.drawImage(waveImage, -currentIndex * imageWidth, 0, this);// 绘制图像
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight);// 清除显示区域右侧的内容
        }
        
        public void run() {
            currentIndex = 0;
            while (!imageLoaded) {// 如果图片未加载
                repaint();// 重绘屏幕
                graphics = getGraphics();// 获得Graphics对象
                MediaTracker mediatracker = new MediaTracker(this);// 创建媒体跟踪对象
                URL imgUrl = WaterWaveActionFrame.class
                        .getResource("/img/image.jpg");// 获取图片资源的路径
                image = Toolkit.getDefaultToolkit().getImage(imgUrl);// 获取图像资源
                mediatracker.addImage(image, 0);// 添加图片
                try {
                    mediatracker.waitForAll();// 加载图片
                    imageLoaded = !mediatracker.isErrorAny();// 是否有错误发生
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) {// 加载图片失败
                    graphics.drawString("加载图片错误", 10, 40);// 输出错误信息
                    continue;
                }
                imageWidth = image.getWidth(this);// 得到图像宽度
                imageHeight = image.getHeight(this);// 得到图像高度
                createWave();// 调用方法,实现动画效果
                break;
            }
            try {
                while (true) {
                    repaint();// 重绘屏幕
                    currentIndex++;// 调整当前图像索引
                    if (currentIndex == 12) {// 如果当前图像索引为12
                        currentIndex = 0;// 设置当前图像索引为0
                    }
                    Thread.sleep(50);// 线程休眠
                }
            } catch (InterruptedException ex) {
            }
        }
        
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight);// 以图像高度创建Image实例
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics();// 得到Image对象的Graphics对象
                g.drawImage(image, 0, 0, this);// 绘制Image
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2));// 拷贝图像区域
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight);// 得到波浪效果的Image实例
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics();// 得到波浪效果的Graphics实例
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this);// 绘制图像
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// 调用方法
                    j++;
                }
                g.drawImage(image, 0, 0, this);// 绘制图像
            }
        }
        
        public void simulateWaves(Graphics g, int i) {// 波浪效果模拟
            double d = (6.0 * i) / 12;
            int j = (12 - i) * imageWidth;// 计算水平移动的距离
            int waveHeight = imageHeight / 16;// 用于计算水波的高度
            for (int m = 0; m < imageHeight; m++) {
                int k = (int) ((waveHeight * (m + 28) * Math.sin(waveHeight
                        * (imageHeight - m) / (m + 1) + d)) / imageHeight);// 用于控制要拷贝矩形区域的宽度
                if (m < -k)
                    g.copyArea(12 * imageWidth, m, imageWidth, 1, -j, 0);// 拷贝图像区域,形成波浪
                else
                    g.copyArea(12 * imageWidth, m + k, imageWidth, 1, -j, -k);// 拷贝图像区域,形成波浪
            }
        }
    }
}