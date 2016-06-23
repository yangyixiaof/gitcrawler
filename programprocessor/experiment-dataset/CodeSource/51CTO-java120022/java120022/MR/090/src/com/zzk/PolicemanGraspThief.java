package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PolicemanGraspThief extends JFrame {
    private JLabel label;
    private JButton button;
    final JLabel lb_thief = new JLabel(); // 显示小偷的标签
    final JLabel lb_policeman = new JLabel(); // 显示警察的标签
    // 创建线程对象
    private Thread thread = new Thread(new GraspThiefThread());
    private boolean stop = false; // 为true时，显示提示文本为“打中了”的标签，为false时不显示
    private final JLabel lb_tip = new JLabel("打中了");
    
    public PolicemanGraspThief() {
        super();
        setTitle("警察抓小偷");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 808, 584);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setFocusable(false);
        backgroundPanel.setImage(SwingResourceManager.getImage(
                PolicemanGraspThief.class, "/image/background.png"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        lb_thief.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/小偷.png"));
        lb_thief.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                stop = true; // stop为true时，显示提示文本为“打中了”的标签
                JOptionPane.showMessageDialog(null, "打中了..."); // 显示消息框
                lb_tip.setVisible(false);
            }
        });
        lb_thief.setBounds(350, 150, 50, 50);
        backgroundPanel.add(lb_thief);
        
        lb_tip.setBounds(350, 210, 66, 50);
        lb_tip.setForeground(new Color(0, 0, 255));
        lb_tip.setFont(new Font("", Font.BOLD, 16));
        backgroundPanel.add(lb_tip);
        
        lb_policeman.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/警察.png"));
        lb_policeman.setBounds(0, 131, 95, 88);
        backgroundPanel.add(lb_policeman);
        lb_tip.setVisible(false);
        backgroundPanel.add(getButton());
        backgroundPanel.add(getLabel());
        thread.start();
        
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.setIcon(SwingResourceManager.getIcon(
                    PolicemanGraspThief.class, "/icon/zailai.png"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent arg0) {
                    if (thread == null) { // 线程对象为空
                        thread = new Thread(new GraspThiefThread()); // 创建线程对象
                    }
                    if (!thread.isAlive()) { // 不是活动线程
                        stop = false; // stop为false时法显示提示文本为“打中了”的标签
                        lb_tip.setVisible(false); // 隐藏提示文本为“打中了”的标签
                        thread.start(); // 重新启动线程对象
                    }
                }
            });
            button.setBounds(616, 485, 149, 47);
        }
        return button;
    }
    
    /**
     * @author 张振坤
     *         使小偷运动的线程
     */
    private class GraspThiefThread implements Runnable {
        boolean flag = false; // 标识小偷向左运动还是向右运动的变量
        int x = 400; // 小偷标签左侧边界的横坐标
        
        public void run() {
            while (true) {
                if (stop) { // stop为true时，显示提示文本为“打中了”标签
                    int x = lb_thief.getX(); // 获得小偷标签的横坐标
                    int y = lb_thief.getY(); // 获得小偷标签的纵坐标
                    lb_tip.setBounds(x, y + 60, 50, 50); // 设置提示文本为“打中了”标签的显示位置和大小
                    lb_tip.setVisible(true); // 显示提示文本为“打中了”的标签
                    thread = null; // 释放线程资源
                    break; // 退出循环，结束线程的执行
                }
                if (flag == false) { // flag为false向右运动
                    x += 20; // x的值增加表示向右运动
                    if (x == 640) { // 当小偷标签左侧边界的横坐标是640时
                        flag = true; // 将flag赋值为true
                    }
                } else { // flag为true向左运动
                    x -= 20; // x的值减少表示向左运动
                    if (x == 100) { // 当小偷标签左侧边界的横坐标是100时
                        flag = false; // 将flag赋值为false
                    }
                }
                // 生成100-200之间的随机整数，用于设置小偷标签上边界的纵坐标
                int y = (int) (Math.random() * 100) + 100;
                lb_thief.setLocation(x, y); // 设置小偷标签的显示位置
                try {
                    Thread.sleep(200); // 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PolicemanGraspThief frame = new PolicemanGraspThief();
        frame.setVisible(true);
    }
    
    /**
     * @return
     */
    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setForeground(new Color(255, 0, 0));
            label.setFont(new Font("", Font.BOLD, 26));
            label.setText("注意：请使用鼠标当枪，单击小偷。");
            label.setBounds(40, 428, 468, 80);
        }
        return label;
    }
    
}
