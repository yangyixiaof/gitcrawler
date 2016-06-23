package com.zzk;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyboardMovePictureFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private ImageIcon icon = null;// 声明图像图标
    final JLabel lb_move = new JLabel();// 通过键盘控制的标签
    
    public static void main(String args[]) {
        KeyboardMovePictureFrame frame = new KeyboardMovePictureFrame();
        frame.setVisible(true);// 显示窗体
        frame.getContentPane().requestFocus();// 使窗体的内容面板获得焦点
    }
    
    public KeyboardMovePictureFrame() {
        super();
        getContentPane().addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                int x = lb_move.getLocation().x;// 获得移动标签的x坐标
                int y = lb_move.getLocation().y;// 获得移动标签的y坐标
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    lb_move.setLocation(x - 10, y);// 向左移动，x坐标减小
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    lb_move.setLocation(x, y - 10);// 向上移动，y坐标减小
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    lb_move.setLocation(x + 10, y);// 向右移动，x坐标增加
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lb_move.setLocation(x, y + 10);// 向下移动，y坐标增加
                }
            }
        });
        setTitle("通过键盘移动图片");
        getContentPane().setLayout(null);
        setBounds(100, 100, 364, 239);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = KeyboardMovePictureFrame.class
                .getResource("/image/coney.png");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        icon = new ImageIcon(img);// 创建图像图标
        lb_move.setIcon(icon);// 指定标签显示的图标
        lb_move.setBounds(35, 30, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // 在图片上单击鼠标右键退出系统
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
    }
}
