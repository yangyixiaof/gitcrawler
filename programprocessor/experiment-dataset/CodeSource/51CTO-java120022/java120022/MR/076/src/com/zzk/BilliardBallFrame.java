package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class BilliardBallFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BilliardBallFrame frame = new BilliardBallFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public BilliardBallFrame() {
        super();
        setTitle("撞球动画");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BilliardBallPanel panel = new BilliardBallPanel();// 创建面板对象
        getContentPane().add(panel);// 将面板添加到窗体容器
        Thread thread = new Thread(panel);// 创建线程对象
        thread.start();// 启动线程对象
    }
    
    /**
     * 创建实现Runnable接口的内部面板类
     */
    private class BilliardBallPanel extends JPanel implements Runnable {
        int x1 = 0;// 定义第一个球移动位置的x坐标
        int y1 = 60;// 定义第一个球移动位置的y坐标
        int x2 = 326 - 30;// 定义第二个球移动位置的初始x坐标为窗体宽度减球的宽度
        int y2 = 60;// 定义第二个球移动位置的y坐标
        int width = 30;// 定义球的宽度
        int height = 30;// 定义球的高度
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的内容
            g.setColor(Color.BLUE);// 设置颜色
            g.fillOval(x1, y1, width, height);// 绘制第一个球
            g.setColor(Color.RED);// 设置颜色
            g.fillOval(x2, y2, width, height);// 绘制第二个球
        }
        public void run() {
            boolean flag1 = true;// 声明第一个球的标记变量
            boolean flag2 = true;// 声明第二个球的标记变量
            while (true) {
                // 第一个球的x坐标值加上球的宽度大于等于第二个球的x坐标值加1，表示两球相遇
                if (x1 + width >= x2 + 1) {// 两球相撞
                    x1 = x1 + 5;// 计算第一个球的x坐标
                    width = width - 10;// 球的宽度减10
                    x2 = x1 + width;// 计算第二个球的x坐标
                    flag1 = false;// 设置第一个球的标记变量为false
                    flag2 = false;// 设置第二个球的标记变量为false
                    repaint();// 调用paint()方法
                } else {// 两球没相撞
                    if (flag1) {// 标记变量为true，第一个球右移
                        x1 = x1 + 10;// 图片x坐标值加10，第一个球右移
                        width = 30;// 球的宽度
                    } else {// 标记变量为false，第一个球左移
                        x1 = x1 - 10;// 图片x坐标值减10，第一个球左移
                        width = 30;// 球的宽度
                        if (x1 <= 0) {// 图片的x坐标值小于等于0
                            x1 = 0;// 图片的x坐标值等于0
                            flag1 = true;// 设置标记变量为true
                        }
                    }
                    if (flag2) {// 标记变量为true，第二个球左移
                        x2 = x2 - 10;// 图片x坐标值减10，即第二个球左移
                        width = 30;
                    } else {// 标记变量为false，第二个球右移
                        x2 = x2 + 10;// 图片x坐标值加10，即第二个球右移
                        width = 30;// 球的宽度
                        if (x2 >= getWidth() - width) {// 图片的x坐标值大于等于面板的宽度与球的宽度之差
                            x2 = getWidth() - width ;// 图片的x坐标值等于面板的宽度与球的宽度之差
                            flag2 = true;// 设置标记变量为true
                        }
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
