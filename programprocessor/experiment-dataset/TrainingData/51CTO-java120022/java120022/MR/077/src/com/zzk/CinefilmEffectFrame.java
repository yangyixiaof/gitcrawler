package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class CinefilmEffectFrame extends JFrame {
    Thread thread = new Thread(new CinefileThread());
    private final JLabel ggLabel = new JLabel();
    private final JLabel label_1 = new JLabel(); // 显示图片的第1个标签
    private final JLabel label_2 = new JLabel(); // 显示图片的第2个标签
    private final JLabel label_3 = new JLabel(); // 显示图片的第3个标签
    private final JLabel label_4 = new JLabel(); // 显示图片的第4个标签
    private final JLabel label_5 = new JLabel(); // 显示图片的第5个标签
    private final JLabel ffLabel = new JLabel();
    int x1 = 0; // 第1个标签显示位置的变量
    int x2 = 98; // 第2个标签显示位置的变量
    int x3 = 196; // 第3个标签显示位置的变量
    int x4 = 294; // 第4个标签显示位置的变量
    int x5 = 392; // 第5个标签显示位置的变量
    boolean indexFlag = false; // 标识标签是否换图的变量
    
    public CinefilmEffectFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // 启动线程对象
            }
        });
        setTitle("电影胶片特效"); // 设置窗体的标题
        setBounds(260, 240, 400, 280); // 图片的宽度和高度
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置窗体的关闭方式
        
        ggLabel.setOpaque(true);
        ggLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/胶片.JPG"));
        ggLabel.setText("  ");
        getContentPane().add(ggLabel, BorderLayout.NORTH);
        
        ffLabel.setText("  ");
        ffLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/胶片.JPG"));
        ffLabel.setOpaque(true);
        getContentPane().add(ffLabel, BorderLayout.SOUTH);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        label_1.setBounds(0, 0, 98, 210);
        label_1.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/1.jpg"));
        label_1.setText("New JLabelfdbb");
        panel.add(label_1);
        label_2.setBounds(98, 0, 98, 210);
        label_2.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/2.jpg"));
        label_2.setText("22222222222222");
        panel.add(label_2);
        label_3.setBounds(196, 0, 98, 210);
        label_3.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/3.jpg"));
        label_3.setText("11111111111111");
        panel.add(label_3);
        label_4.setBounds(294, 0, 98, 210);
        label_4.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/4.jpg"));
        label_4.setText("New JLabelfdww");
        panel.add(label_4);
        label_5.setBounds(392, 0, 98, 210);
        label_5.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/5.jpg"));
        label_5.setText("33333333333333");
        panel.add(label_5);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        CinefilmEffectFrame frame = new CinefilmEffectFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author 张振坤
     *         用于实现动画的线程
     */
    private class CinefileThread implements Runnable {
        public void run() {
            while (true) {
                x1 = x1 - 7; // 第1个标签的左边界减7，使其左移
                x2 = x2 - 7; // 第2个标签的左边界减7，使其左移
                x3 = x3 - 7; // 第3个标签的左边界减7，使其左移
                x4 = x4 - 7; // 第4个标签的左边界减7，使其左移
                x5 = x5 - 7; // 第5个标签的左边界减7，使其左移
                label_1.setBounds(x1, 0, 98, 210); // 设置第1个标签的显示位置
                label_2.setBounds(x2, 0, 98, 210); // 设置第1个标签的显示位置
                label_3.setBounds(x3, 0, 98, 210); // 设置第1个标签的显示位置
                label_4.setBounds(x4, 0, 98, 210); // 设置第1个标签的显示位置
                label_5.setBounds(x5, 0, 98, 210); // 设置第1个标签的显示位置
                
                if (x1 == -98) { // 当第1个标签的显示位置是-98时执行
                    indexFlag = !indexFlag; // 改变indexFlag的值
                    x1 = 392; // 设置第1个标签的显示位置
                    if (indexFlag) {
                        // indexFlag为true时改变的图片
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/6.jpg"));
                    } else {
                        // indexFlag为false时改变的图片
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/1.jpg"));
                    }
                }
                if (x2 == -98) { // 当第2个标签的显示位置是-98时执行
                    indexFlag = !indexFlag; // 改变indexFlag的值
                    x2 = 392; // 设置第2个标签的显示位置
                    if (indexFlag) {
                        // indexFlag为true时改变的图片
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/7.jpg"));
                    } else {
                        // indexFlag为false时改变的图片
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/2.jpg"));
                    }
                }
                if (x3 == -98) { // 当第3个标签的显示位置是-98时执行
                    indexFlag = !indexFlag; // 改变indexFlag的值
                    x3 = 392; // 设置第3个标签的显示位置
                    if (indexFlag) {
                        // indexFlag为true时改变的图片
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/8.jpg"));
                    } else {
                        // indexFlag为false时改变的图片
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/3.jpg"));
                    }
                }
                if (x4 == -98) { // 当第4个标签的显示位置是-98时执行
                    indexFlag = !indexFlag; // 改变indexFlag的值
                    x4 = 392; // 设置第4个标签的显示位置
                    if (indexFlag) {
                        // indexFlag为true时改变的图片
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/9.jpg"));
                    } else {
                        // indexFlag为false时改变的图片
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/4.jpg"));
                    }
                }
                if (x5 == -98) { // 当第5个标签的显示位置是-98时执行
                    indexFlag = !indexFlag; // 改变indexFlag的值
                    x5 = 392; // 设置第5个标签的显示位置
                    if (indexFlag) {
                        // indexFlag为true时改变的图片
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/10.jpg"));
                    } else {
                        // indexFlag为false时改变的图片
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/5.jpg"));
                    }
                }
                try {
                    Thread.sleep(150); // 线程睡眠150毫秒
                } catch (Exception ex) {
                    
                }
            }
        }
    }
}
