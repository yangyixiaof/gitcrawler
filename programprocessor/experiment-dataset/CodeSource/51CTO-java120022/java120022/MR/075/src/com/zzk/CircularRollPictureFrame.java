package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularRollPictureFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CircularRollPictureFrame frame = new CircularRollPictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CircularRollPictureFrame() {
        super();
        setTitle("循环滚动图片");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircularRollPicturePanel panel = new CircularRollPicturePanel();// 创建面板对象
        getContentPane().add(panel);// 将面板添加到窗体容器
        Thread thread = new Thread(panel);// 创建线程对象
        thread.start();// 启动线程对象
    }
    
    /**
     * @author 张振坤
     * 创建实现Runnable接口的内部面板类
     */
    private class CircularRollPicturePanel extends JPanel implements Runnable {
        int x = 0;// 定义图片移动位置的x坐标
        int y = 30;// 定义图片移动位置的y坐标
        URL imgUrl = CircularRollPictureFrame.class.getResource("/image/picture.png");// 获取图片资源的路径
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像对象
        public void paint(Graphics g){
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的内容
            g.drawImage(img, x, y, this);// 在面板的指定位置绘制图像
        }
        public void run() {
            boolean flag = true;// 声明标记变量
            while (true){
                if (flag) {// 标记变量为true
                    x = x + 10;// 图片x坐标值加10
                    if (x >= getWidth() - img.getWidth(this)) {// 图片的x坐标值大于等于面板与图片宽度的差
                        x = getWidth() - img.getWidth(this); // 图片的x坐标值等于面板与图片宽度的差
                        flag = false;// 设置标记变量为false
                    }
                }else {// 标记变量为false
                    x = x - 10;// 图片x坐标值减10
                    if ( x <= 0 ) {// 图片的x坐标值小于等于0
                        x = 0;// 图片的x坐标值等于0
                        flag = true;// 设置标记变量为true
                    }
                }
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
