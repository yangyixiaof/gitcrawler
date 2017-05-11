package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CaptureCarbFrame extends JFrame implements Runnable {
    private JLabel[] carb; // 存放显示螃蟹的标签数组
    private ImageIcon imgCarb; // 螃蟹图片对象
    private MouseCrab mouseCrab;// 标签的事件监听器
    
    /**
     * 显示螃蟹的标签控件的鼠标事件监听器
     * @author 张振坤
     */
    private final class MouseCrab implements MouseListener {
        private final Cursor cursor1;// 鼠标图标1
        private final Cursor cursor2;// 鼠标图标2
        
        /**
         * 构造方法
         * 
         * @param cursor1
         * @param cursor2
         */
        private MouseCrab(Cursor cursor1, Cursor cursor2) {
            this.cursor1 = cursor1;
            this.cursor2 = cursor2;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(cursor1);// 鼠标按键释放时设置光标为cursor1
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            setCursor(cursor2);// 鼠标按键按下时设置光标为cursor2
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(cursor1);// 鼠标离开控件区域时设置光标为cursor1
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
    
    /**
     * 鼠标单击事件监听器，用于改变鼠标的图标。
     * 
     * @author 张振坤
     */
    private final class Catcher extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // 获取事件源，即螃蟹标签
            if (source instanceof JLabel) { // 如果事件源是标签组件
                JLabel carb = (JLabel) source; // 强制转换为JLabel标签
                if (carb.getIcon() != null)
                    carb.setIcon(imgCarb2); // 为该标签添加螃蟹图片
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // 获取事件源，即螃蟹标签
            if (source instanceof JLabel) { // 如果事件源是标签组件
                JLabel carb = (JLabel) source; // 强制转换为JLabel标签
                carb.setIcon(null);// 清除标签中的螃蟹图片
            }
        }
    }
    
    private ImageIcon imgCarb2;
    
    /**
     * 程序入口方法
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 创建程序窗体
                    CaptureCarbFrame frame = new CaptureCarbFrame();
                    frame.setVisible(true);// 显示窗体
                    new Thread(frame).start();// 创建线程并启动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * 构造方法
     */
    public CaptureCarbFrame() {
        super();
        // 创建第一个鼠标图标
        ImageIcon icon = new ImageIcon(getClass().getResource("hand.jpg"));
        // 创建第二个鼠标图标
        ImageIcon icon2 = new ImageIcon(getClass().getResource("hand2.jpg"));
        // 获取每个图标的图片
        Image image = icon.getImage();
        Image image2 = icon2.getImage();
        // 使用图片创建2个鼠标光标对象
        final Cursor cursor1 = getToolkit().createCustomCursor(image,
                new Point(0, 0), "hand1");
        final Cursor cursor2 = getToolkit().createCustomCursor(image2,
                new Point(0, 0), "hand2");
        // 初始化显示螃蟹标签组件的事件监听器
        mouseCrab = new MouseCrab(cursor1, cursor2);
        setResizable(false); // 禁止调整窗体大小
        getContentPane().setLayout(null); // 窗体不使用布局管理器
        setTitle("海滩捉螃蟹"); // 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化背景图片对象
        ImageIcon img = new ImageIcon(getClass().getResource("background.jpg"));
        // 初始化螃蟹图片对象
        imgCarb = new ImageIcon(getClass().getResource("crab.png"));
        imgCarb2 = new ImageIcon(getClass().getResource("crab2.png"));
        carb = new JLabel[6]; // 创建显示螃蟹的标签数组
        Catcher catcher = new Catcher();// 标签的鼠标单击事件监听器
        for (int i = 0; i < 6; i++) { // 遍历数组
            carb[i] = new JLabel(); // 初始化每一个数组元素
            // 设置标签与螃蟹图片相同大小
            carb[i].setSize(imgCarb.getIconWidth(), imgCarb.getIconHeight());
            // 为标签添加事件监听器
            carb[i].addMouseListener(catcher);
            carb[i].addMouseListener(mouseCrab);
            getContentPane().add(carb[i]); // 添加显示螃蟹的标签到窗体
        }
        
        carb[0].setLocation(253, 315); // 设置每个标签的位置
        carb[1].setLocation(333, 265);
        carb[2].setLocation(388, 311);
        carb[3].setLocation(362, 379);
        carb[4].setLocation(189, 368);
        carb[5].setLocation(240, 428);
        
        final JLabel backLabel = new JLabel(); // 创建显示背景的标签
        // 设置标签与背景图片相同大小
        backLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        // 设置窗体近似背景图片大小
        setBounds(100, 100, img.getIconWidth(), img.getIconHeight() + 30);
        backLabel.setIcon(img); // 添加背景到标签
        getContentPane().add(backLabel); // 添加背景标签到窗体
        setCursor(cursor1);// 设置默认使用第一个鼠标光标
        addMouseListener(mouseCrab);// 为面板添加鼠标事件监听器
    }
    
    /**
     * 线程的核心方法
     */
    public void run() {
        while (true) { // 使用无限循环
            try {
                Thread.sleep(1000); // 使线程休眠1秒
                int index = (int) (Math.random() * 6);// 生成随机的螃蟹索引
                if (carb[index].getIcon() == null) {// 如果螃蟹标签没有设置图片
                    carb[index].setIcon(imgCarb);// 为该标签添加螃蟹图片
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
