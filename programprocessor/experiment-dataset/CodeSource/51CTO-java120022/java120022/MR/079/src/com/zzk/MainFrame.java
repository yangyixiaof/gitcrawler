package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private BackgroundPanel backgroundPanel = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame thisClass = new MainFrame();
                thisClass.setVisible(true);
            }
        });
    }
    
    public MainFrame() {
        super();
        setTitle("雪花飘落动画");
        setSize(628, 441);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = new ImageIcon(getClass().getResource("/image/cursor.png")).getImage();// 创建图像对象
        Cursor cursor = getToolkit().createCustomCursor(image, new Point(),"魔棒");// 创建鼠标光标对象
        setCursor(cursor);// 指定鼠标光标
        setResizable(false);// 不允许改变窗体大小
        backgroundPanel = new BackgroundPanel();// 创建背景面板
        // 为背景面板指定图像
        backgroundPanel.setImage(new ImageIcon(getClass().getResource("/image/bg.jpg")).getImage());
        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {// 鼠标移动事件
                SnowFlakeLabel snow = new SnowFlakeLabel();// 创建雪花飘落标签
                Point point = e.getPoint();// 获得鼠标位置
                snow.setLocation(point);// 指定雪花在背景面板上的位置
                backgroundPanel.add(snow);// 将雪花添加到背景面板上
            }
        });
        getContentPane().setLayout(new BorderLayout());// 指定窗体内容面板为边界布局
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);// 在窗体内容面板上添加背景面板
    }
}