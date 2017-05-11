package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFlashFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private TextFlashPanel textFlashPanel = null; // 声明图像面板对象
    public static void main(String args[]) {
        TextFlashFrame frame = new TextFlashFrame();
        frame.setVisible(true);
    }
    
    public TextFlashFrame() {
        super();
        URL imgUrl = TextFlashFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        textFlashPanel = new TextFlashPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 310, 230); // 设置窗体大小和位置
        this.add(textFlashPanel); // 在窗体上添加图像面板对象
        Thread thread = new Thread(textFlashPanel);// 创建线程对象
        thread.start();// 启动线程对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("文字闪现特效"); // 设置窗体标题
    }
    
    // 创建面板类
    class TextFlashPanel extends JPanel implements Runnable {
        boolean flag = true;// 标记变量
        String value = "";// 存放绘制内容的变量
        public void paint(Graphics g) {
            g.clearRect(0, 0, 310, 230);// 清除绘图上下文的内容
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// 绘制图像
            Font font = new Font("华文楷体", Font.BOLD, 42);// 创建字体对象
            g.setFont(font);// 指定字体
            g.setColor(Color.RED);// 指定颜色
            g.drawString(value, 10, 110);// 绘制文本
            
        }
        public void run() {
            try {
                while (true) { // 读取内容
                    Thread.sleep(150); // 当前线程休眠1秒
                    if (flag) {// flag为true
                        flag = false;   // 赋值为false
                        value="明日编程词典";// 为value赋值
                    } else {
                        flag = true;// 赋值为true
                        value="";// 赋值为空字符串
                    }
                    repaint();// 调用paint()方法
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
