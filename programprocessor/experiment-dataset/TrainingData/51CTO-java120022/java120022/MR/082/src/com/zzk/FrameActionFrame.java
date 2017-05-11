package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class FrameActionFrame extends JFrame {
    Thread thread = new Thread(new ActionThread());
    boolean flag = true; // 用于标识动画播放、暂停和继续的成员变量
    boolean stop = false; // 用于标识动画播放和停止的成员变量
    final JLabel label = new JLabel(); // 显示图片的标签
    
    public FrameActionFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // 启动线程，在窗体打开时播放动画
            }
        });
        setTitle("帧动画效果");
        setBounds(260, 240, 324, 227); // 图片的宽度和高度392,208
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);// 标签内容水平居中
        label.setIcon(SwingResourceManager.getIcon(FrameActionFrame.class,
                "/image/1.gif"));
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (thread == null) {
                    thread = new Thread(new ActionThread()); // 如果线程为空，则创建线程对象
                }
                if (!thread.isAlive()) {
                    // 如果线程不是活动线程则执行下面的代码，启动线程的执行
                    stop = false;
                    flag = true;
                    thread.start();
                }
            }
        });
        button.setText("播  放");
        panel.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = false; // 暂停动画播放
            }
        });
        button_1.setText("暂  停");
        panel.add(button_1);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = true; // 继续播放动画
            }
        });
        button_3.setText("继  续");
        panel.add(button_3);
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                stop = true; // 停止播放动画
            }
        });
        button_2.setText("停  止");
        panel.add(button_2);
    }
    
    public static void main(String[] args) {
        FrameActionFrame frame = new FrameActionFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author 张振坤
     *         用于实现动画的线程
     */
    private class ActionThread implements Runnable {
        private int index = 1; // 用于控制图形文件的主文件名
        public void run() {
            while (true) {
                if (stop) {
                    thread = null; // 销毁线程
                    break; // 跳出循环，结束线程的执行
                }
                if (flag) {
                    String picture = "/image/"; // 创建图片存放位置和文件名的变量
                    index++;
                    if (index <= 8) {
                        picture = picture + index + ".jpg"; // 通过索引获得图片的位置和文件名
                    } else {
                        index = 1;
                        picture = picture + index + ".jpg"; // 通过索引获得图片的位置和文件名
                    }
                    // 改变标签上显示的图片实现动画效果
                    label.setIcon(SwingResourceManager.getIcon(
                            FrameActionFrame.class, picture));
                    try {
                        Thread.sleep(200); // 线程睡眠200毫秒
                    } catch (Exception ex) {
                        
                    }
                }
            }
        }
    }
}
