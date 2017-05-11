package com.zzk;
import java.awt.*;
import javax.swing.*;
/**
 * @author 张振坤
 */
public class SnowFlakeLabel extends JLabel implements Runnable {
    private final static ImageIcon snow = new ImageIcon(SnowFlakeLabel.class
            .getResource("/image/snowflake.png"));
    private int width = snow.getIconWidth();// 宽度
    private int height = snow.getIconHeight();// 高度
    /**
     * 构造方法
     */
    public SnowFlakeLabel() {
        setSize(new Dimension(width, height));// 初始化大小
        setIcon(snow);// 指定图标
        new Thread(this).start();// 创建并启动线程
    }
    public void run() {
        Container parent = getParent();// 获取父容器对象
        Point myPoint = getLocation();// 获取初始位置
        while (true) {// 循环读取父容器对象
            if (parent == null) {
                try {
                    Thread.sleep(50);// 线程休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// 获取初始位置
                parent = getParent();// 获取父容器对象
            } else {// 如果已经获取到父容器
                break;// 跳出循环
            }
        }
        int sx = myPoint.x;// X坐标
        int sy = myPoint.y;// Y坐标
        int stime = (int) (Math.random() * 30 + 10);// 随机移动速度
        int parentHeight = parent.getHeight();// 容器高度
        while (parent.isVisible() && sy < parentHeight - height) {
            setLocation(sx, sy);// 指定位置
            try {
                Thread.sleep(stime);// 线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sy += 2;// 垂直偏移2个像素
        }
    }
}
