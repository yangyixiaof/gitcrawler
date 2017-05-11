package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicFlexImageFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private DynamicFlexPanel imagePanel = null; // 声明图像面板对象
    private Thread thread = null;// 声明线程对象
    
    public static void main(String args[]) {
        DynamicFlexImageFrame frame = new DynamicFlexImageFrame();
        frame.setVisible(true);
    }
    
    public DynamicFlexImageFrame() {
        super();
        URL imgUrl = DynamicFlexImageFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        imagePanel = new DynamicFlexPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 340, 200); // 设置窗体大小和位置
        this.add(imagePanel); // 在窗体上添加图像面板对象
        thread = new Thread(imagePanel);// 创建线程对象
        thread.start();// 启动线程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片动态拉伸"); // 设置窗体标题
    }
    
    // 创建面板类
    class DynamicFlexPanel extends JPanel implements Runnable {
        private boolean flag = true;// 标记变量
        int width = 0;// 调整图像宽度的变量
        int height = 0;// 调整图像高度的变量
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除绘图上下文的内容
            g.drawImage(img, 0, 0, width, height, this); // 绘制指定大小的图片
        }
        public void run() {
            while (true) {
                if (flag) {
                    width+=2;// 调整宽度值
                    height++;// 调整高度值
                    if (width >= getWidth() || height >= getHeight()) {
                        flag = false;// 达到图像的宽度或高度时，改变标记变量的值
                    }
                } else {
                    width -= 2;// 调整宽度值
                    height--;// 调整高度值
                    if (width <= 0 || height <= 0) {
                        flag = true;// 图像的宽度或高度小于等于0时，改变标记变量的值
                    }
                }
                repaint();// 调用paint()方法
                try {
                    Thread.sleep(20);// 线程休眠20毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
