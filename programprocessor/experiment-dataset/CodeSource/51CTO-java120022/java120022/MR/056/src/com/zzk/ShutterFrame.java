package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShutterFrame extends JFrame {
    private BufferedImage image;// 声明缓冲图像对象
    private Image img = null; // 声明图像对象
    private ShutterPanel shutterPanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        ShutterFrame frame = new ShutterFrame();
        frame.setVisible(true);
    }
    
    public ShutterFrame() {
        super();
        URL imgUrl = ShutterFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        shutterPanel = new ShutterPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(shutterPanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片百叶窗特效"); // 设置窗体标题
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// 创建 Kernel对象
        ConvolveOp op = new ConvolveOp(kernel);// 创建ConvolveOp对象
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // 过滤缓冲图像对象
        repaint();// 调用paint()方法
    }
    
    // 创建面板类
    class ShutterPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this);// 绘制图像对象
            int y = 5;  // 直线绘制点的y坐标
            int space = 10;// 下一条直线的偏移量
            Line2D.Float line = null;
            image = new BufferedImage(getWidth() + 10, getHeight(),
                    BufferedImage.TYPE_INT_ARGB);// 创建缓冲图像对象
            Graphics2D gs2d = (Graphics2D) image.getGraphics();// 获得缓冲图像对象的Graphics2D对象
            BasicStroke stroke = new BasicStroke(7); // 创建宽度是7的笔画对象
            gs2d.setStroke(stroke);// 设置笔画对象
            gs2d.setColor(Color.WHITE);// 指定颜色
            while (y <= getHeight()) {
                line = new Line2D.Float(0, y, getWidth(), y);// 创建直线对象
                gs2d.draw(line);// 在缓冲图像对象上绘制直线
                y = y + space;// 计算下一条直线的y坐标
            }
            for (int i = 0; i < 3; i++) {// 该for循环，实现3次模糊
                float[] elements = new float[9];// 定义表示像素分量的数组
                for (int j = 0; j < 9; j++) {
                    elements[j] = 0.11f;// 为数组赋值
                }
                convolve(elements);// 调用方法，实现模糊功能
            }
            g2.drawImage(image, 0, 0, this);// 绘制缓冲图像对象
        }
    }
}
