package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFadeFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private TextFadePanel textFadePanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        TextFadeFrame frame = new TextFadeFrame();
        frame.setVisible(true);
    }
    
    public TextFadeFrame() {
        super();
        URL imgUrl = TextFadeFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        textFadePanel = new TextFadePanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(textFadePanel); // 在窗体上添加图像面板对象
        Thread thread = new Thread(textFadePanel);// 创建线程对象
        thread.start();// 启动线程对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("文字淡入淡出特效"); // 设置窗体标题
    }
    
    // 创建面板类
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// 定义标记变量，用于控制x的值
        float x = 0.0f;// 定义表示透明度的变量x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// 获得表示透明度的AlphaComposite对象
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// 绘制图像
            Font font = new Font("华文楷体", Font.BOLD, 60);// 创建字体对象
            g2.setFont(font);// 指定字体
            g2.setColor(Color.RED);// 指定颜色
            g2.setComposite(alpha);// 指定AlphaComposite对象
            g2.drawString("编程词典", 30, 120);// 绘制文本
        }
        ////// 注意浮点数相减，计算结果不精确，所以加上if (x <= 0.0f) { x = 0.0f;
        public void run() {
            while (true) {
                if (flag) {        // flag为true时
                    x-=0.1f;       // x进行减0.1计算
                    if (x <= 0.0f) {// x小于等于0.0f时
                        x = 0.0f;   // x等于0.0f
                        flag = false;// 为flag赋值为false
                    }
                } else {// flag为false时
                    x+=0.1f;// x进行加0.1计算
                    if (x >= 1.0f) {// x大于等于1.0f时
                        x = 1.0f;// x等于1.0f
                        flag = true;// 为flag赋值为true
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// 重新获得表示透明度的AlphaComposite对象
                repaint();// 调用paint()方法
                try {
                    Thread.sleep(150);// 休眠150毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
