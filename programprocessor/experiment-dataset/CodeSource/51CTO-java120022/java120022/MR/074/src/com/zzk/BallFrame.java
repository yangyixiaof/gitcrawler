package com.zzk;

import javax.swing.*;

/**
 * @author 张振坤
 */
public class BallFrame extends JFrame {
    private JPanel panel = null;// 背景面板
    private BallPanel ballPanel = null;// 窗体提供一个小球
    
    public static void main(String[] args) {
        BallFrame thisClass = new BallFrame();
        thisClass.setVisible(true);
    }
    
    /**
     * 构造方法
     */
    public BallFrame() {
        super();
        setSize(320, 223);// 设置窗体大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// 禁止调整窗体大小
        setTitle("桌面弹球动画");// 设置窗体标题文本
        ballPanel = new BallPanel();// 创建小球
        ballPanel.setBounds(121, 67, 20, 20);// 设置小球位置与大小
        panel = (JPanel) getContentPane();// 获得窗体的内容面板
        panel.setLayout(null);// 窗体内容面板使用null布局
        panel.add(ballPanel, null);// 添加小球到窗体的内容面板
    }
}
