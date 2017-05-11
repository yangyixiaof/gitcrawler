package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private Image fadeImage = null;// 声明闪现的图像对象
    private TextFadePanel textFadePanel = null; // 声明图像面板对象
    private URL imgUrl = null;// 声明URL对象
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        imgUrl = PictureFadeFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像对象
        imgUrl = PictureFadeFrame.class.getResource("/img/fade.jpg");// 获取图片资源的路径
        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像对象
        textFadePanel = new TextFadePanel(); // 创建图像面板对象
        this.setBounds(200, 160, 310, 230); // 设置窗体大小和位置
        this.add(textFadePanel); // 在窗体上添加图像面板对象
        Thread thread = new Thread(textFadePanel);// 创建线程对象
        thread.start();// 启动线程对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片闪现动画"); // 设置窗体标题
    }
    
    // 创建面板类
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// 标记变量
        String value = "";// 存放绘制内容的变量
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(),getHeight());// 清除绘图上下文的内容
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// 绘制图像
            g.drawImage(fadeImage,10,10,getWidth()-20,getHeight()-20,this);// 绘制闪现的图像对象
        }
        public void run() {
            try {
                while (true) { // 读取内容
                    Thread.sleep(200); // 当前线程休眠200毫秒
                    if (flag) {// flag为true
                        flag = false;   // 赋值为false
                        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像对象
                    } else {
                        flag = true;// 赋值为true
                        fadeImage = Toolkit.getDefaultToolkit().getImage(""); // 获取图像对象
                    }
                    repaint();// 调用paint()方法
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
