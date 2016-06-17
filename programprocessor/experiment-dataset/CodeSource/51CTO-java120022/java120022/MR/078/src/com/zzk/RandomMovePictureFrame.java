package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RandomMovePictureFrame extends JFrame {
    private final int winWIDTH = 450;
    private final int winHEIGHT = 300;
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RandomMovePictureFrame frame = new RandomMovePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public RandomMovePictureFrame() {
        super();
        setTitle("随机移动的图片");
        setBounds(100, 100, winWIDTH, winHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RandomMovePicturePanel panel = new RandomMovePicturePanel();// 创建面板对象
        getContentPane().add(panel);// 将面板添加到窗体容器
        Thread thread = new Thread(panel);// 创建线程对象
        thread.start();// 启动线程对象
    }
    
    /**
     * @author zzk
     * 创建实现Runnable接口的内部面板类
     */
    private class RandomMovePicturePanel extends JPanel implements Runnable {
        Random random = new Random();// 创建Random对象
        int x = 0;// 定义图片移动位置的x坐标
        int y = 0;// 定义图片移动位置的y坐标
        URL imgUrl = RandomMovePictureFrame.class
                .getResource("/image/picture.png");// 获取图片资源的路径
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);// 获取图像对象
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的内容
            g.drawImage(img, x, y, this);// 在面板的指定位置绘制图像
        }
        public void run() {
            while (true) {
                x = random.nextInt(winWIDTH - 110);// 随机获得图片移动位置的x坐标
                y = random.nextInt(winHEIGHT - 140);// 随机获得图片移动位置的y坐标
                try {
                    Thread.sleep(200);// 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// 调用paint()方法
            }
        }
    }
}
