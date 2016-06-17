package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PictureMatchingFrame extends JFrame implements MouseListener,
        MouseMotionListener {
    private JLabel img[] = new JLabel[3];// 显示图标的标签
    private JLabel targets[] = new JLabel[3];// 窗体下面显示文字的标签
    private Point pressPoint; // 鼠标按下时的起始坐标
    
    public static void main(String args[]) {
        PictureMatchingFrame frame = new PictureMatchingFrame(); // 创建本类对象
        frame.setVisible(true); // 设置窗体为可视状态
    }
    
    public PictureMatchingFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 100, 364, 312);
        setTitle("图片配对游戏");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        setGlassPane(imagePanel);
        getGlassPane().setVisible(true);
        ImageIcon icon[] = new ImageIcon[3];
        icon[0] = new ImageIcon(getClass().getResource("screen.png"));
        icon[1] = new ImageIcon(getClass().getResource("clothing.png"));
        icon[2] = new ImageIcon(getClass().getResource("bike.png"));
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        for (int i = 0; i < 3; i++) {
            img[i] = new JLabel(icon[i]); // 创建图像标签
            img[i].setSize(50, 50); // 设置标签大小
            img[i].setBorder(new LineBorder(Color.GRAY)); // 设置线性边框
            int x = (int) (Math.random() * (getWidth() - 50)); // 随机生成X坐标
            int y = (int) (Math.random() * (getHeight() - 150));// 随机生成Y坐标
            img[i].setLocation(x, y); // 设置随机坐标
            img[i].addMouseListener(this); // 为每个图像标签添加鼠标事件监听器
            img[i].addMouseMotionListener(this);
            imagePanel.add(img[i]); // 添加图像标签到图像面板
            targets[i] = new JLabel(); // 创建匹配位置标签
            targets[i].setOpaque(true); // 使标签不透明，以设置背景色
            targets[i].setBackground(Color.ORANGE); // 设置标签背景色
            targets[i].setHorizontalTextPosition(SwingConstants.CENTER); // 设置文本与图像水平居中
            targets[i].setVerticalTextPosition(SwingConstants.BOTTOM); // 设置文本显示在图像下方
            targets[i].setPreferredSize(new Dimension(80, 80)); // 设置标签首先大小
            targets[i].setHorizontalAlignment(SwingConstants.CENTER); // 文字居中对齐
            bottomPanel.add(targets[i]); // 添加标签到底部面板
        }
        targets[0].setText("显示器"); // 设置匹配位置的文本
        targets[1].setText("衣服");
        targets[2].setText("自行车");
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint(); // 保存拖放图片标签时的起始坐标
    }
    
    public void mouseReleased(MouseEvent e) {
        if (checkPosition()) { // 如果配对正确
            getGlassPane().setVisible(false);
            for (int i = 0; i < 3; i++) { // 遍历所有匹配位置的标签
                targets[i].setText("匹配成功"); // 设置正确提示
                targets[i].setIcon(img[i].getIcon()); // 设置匹配的图标
            }
        }
    }
    
    /**
     * 鼠标拖动控件时的事件处理方法
     */
    public void mouseDragged(MouseEvent e) {
        JLabel source = (JLabel) e.getSource(); // 获取事件源控件
        Point imgPoint = source.getLocation(); // 获取控件坐标
        Point point = e.getPoint(); // 获取鼠标坐标
        source.setLocation(imgPoint.x + point.x - pressPoint.x, imgPoint.y
                + point.y - pressPoint.y); // 设置控件新坐标
    }
    
    private boolean checkPosition() {// 检查配对是否正确
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            Point location = img[i].getLocationOnScreen(); // 获取每个图像标签的位置
            Point seat = targets[i].getLocationOnScreen(); // 获取每个对应位置的坐标
            targets[i].setBackground(Color.GREEN); // 设置匹配后的颜色
            // 如果配对错误
            if (location.x < seat.x || location.y < seat.y
                    || location.x > seat.x + 80 || location.y > seat.y + 80) {
                targets[i].setBackground(Color.ORANGE); // 回复对应位置的颜色
                result = false; // 检测结果为false
            }
        }
        return result; // 返回检测结果
    }
}
