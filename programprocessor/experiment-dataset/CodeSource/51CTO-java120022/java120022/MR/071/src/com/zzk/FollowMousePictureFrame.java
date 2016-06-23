package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FollowMousePictureFrame extends JFrame {
    private Image img = null;
    private ImageIcon icon = null;
    final JLabel lb_move = new JLabel();
    final JLabel lb_tip = new JLabel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FollowMousePictureFrame frame = new FollowMousePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FollowMousePictureFrame() {
        super();
        setTitle("随鼠标移动的图片");
        getContentPane().setLayout(null);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = FollowMousePictureFrame.class
                .getResource("/image/coney.png");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        icon = new ImageIcon(img);// 创建图像图标
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                int x = e.getX(); // 获得鼠标在窗体容器中的横坐标值
                int y = e.getY(); // 获得鼠标在窗体容器中的纵坐标值
                int w = lb_move.getWidth(); // 获得随鼠标移动的图形所在标签的宽度
                int h = lb_move.getHeight(); // 获得随鼠标移动的图形所在标签的高度
                int x1 = x - w / 2; // 计算出随鼠标移动的图形所在标签的横坐标值
                int y1 = y - h / 2; // 计算出随鼠标移动的图形所在标签的纵坐标值
                lb_move.setLocation(x1, y1); // 设置随鼠标移动的图形所在标签的显示位置
                int x2 = x - 52; // 计算显示文字的标签的横坐标值
                int y2 = y1 + 120; // 计算显示文字的标签的纵坐标值
                lb_tip.setLocation(x2, y2); // 设置显示文字的标签的显示位置
            }
        });
        lb_move.setIcon(icon);
        lb_move.setBounds(191, 61, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // 在图片上单击鼠标右键退出系统
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
        lb_tip.setText("我就跟着你。。。");
        lb_tip.setBounds(180, 191, 104, 18);
        getContentPane().add(lb_tip);
        Thread thread = new Thread(new FlareThread()); // 创建线程对象
        thread.start(); // 启动线程对象
    }
    
    /**
     * @author Administrator
     *         动态改变文字颜色的线程
     */
    class FlareThread implements Runnable {
        public void run() {
            while (true) {
                int red = (int) (Math.random() * 256); // 随机生成RGB颜色中的R，即红色
                int green = (int) (Math.random() * 256); // 随机生成RGB颜色中的G，即绿色
                int blue = (int) (Math.random() * 256); // 随机生成RGB颜色中的B，即蓝色
                lb_tip.setForeground(new Color(red, green, blue)); // 设置标签上文字的前景色
                try {
                    Thread.sleep(500); // 线程休眠500毫秒
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
