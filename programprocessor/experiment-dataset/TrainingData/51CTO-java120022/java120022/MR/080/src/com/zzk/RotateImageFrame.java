package com.zzk;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图片资源
        rotatePanel = new RotatePanel(); // 创建旋转图像的面板对象
        this.setBounds(150, 120, 380, 277);// 设置窗体大小和位置
        add(rotatePanel);// 在窗体上放置图像面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片旋转动画"); // 设置窗体标题
        Thread th = new Thread(rotatePanel);// 创建线程对象
        th.start();// 启动线程
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel implements Runnable {
        int angle = 0;// 初始旋转角度
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            g2.translate(190, 120);// 平移坐标轴
            g2.clearRect(-190, -120, getWidth(), getHeight());// 清除画布上的内容
            g2.rotate(Math.toRadians(angle)); // 旋转画布
            g2.drawImage(img, -95, -80, 190, 160, this);// 绘制指定大小的图片
        }
        public void run() {
            while (true) {
                angle = (angle + 10) % 360;// 计算旋转角度
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
