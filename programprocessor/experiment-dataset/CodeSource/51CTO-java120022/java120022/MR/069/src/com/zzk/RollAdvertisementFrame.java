package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RollAdvertisementFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private RollAdvertisementPanel rollAdvertisementPanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        RollAdvertisementFrame frame = new RollAdvertisementFrame();
        frame.setVisible(true);
    }
    
    public RollAdvertisementFrame() {
        super();
        URL imgUrl = RollAdvertisementFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        rollAdvertisementPanel = new RollAdvertisementPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(rollAdvertisementPanel); // 在窗体上添加图像面板对象
        Thread thread = new Thread(rollAdvertisementPanel);// 创建线程对象
        thread.start();// 启动线程对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("滚动广告字幕"); // 设置窗体标题
    }
    
    // 创建面板类
    class RollAdvertisementPanel extends JPanel implements Runnable {
        int x = 316;// 存储绘制点的x坐标
        int y = 190;// 存储绘制点的y坐标
        String value = "明日编程词典网址：http://www.mrbccd.com";// 存储绘制的内容
        public void paint(Graphics g) {
            g.clearRect(0, 0, 316, 237);// 清除绘图上下文的内容
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 绘制图像
            Font font = new Font("华文楷体", Font.BOLD, 20);// 创建字体对象
            g.setFont(font);// 指定字体
            g.setColor(Color.RED);// 指定颜色
            g.drawString(value, x, y);// 绘制文本
        }
        public void run() {
            try {
                while (true) { // 读取内容
                    Thread.sleep(50); // 当前线程休眠1秒
                    if (x <= -400) {// 该条件可以根据需要自行调整
                        x = 316;// x坐标定位到最右侧
                    } else {
                        x -= 2;// x坐标左移
                    }
                    repaint();// 调用paint()方法
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
