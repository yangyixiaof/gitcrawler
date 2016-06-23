package com.zzk;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PigWalkMazeFrame extends JFrame implements KeyListener, Runnable {
    Rectangle rect1, rect2, rect3, rect4;
    int gobuttonX = 0, gobuttonY = 0;
    final JButton goButton = new JButton();
    URL url = getClass().getResource("/images/pig.png");
    ImageIcon imageIcon = new ImageIcon(url);
    final JLabel label = new JLabel();
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PigWalkMazeFrame frame = new PigWalkMazeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public PigWalkMazeFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                goButton.requestFocus(); // 使小猪获取焦点
            }
        });
        getContentPane().setLayout(null);
        setBounds(100, 100, 488, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("小猪走迷宫");
        BakcgroundPanel panel = new BakcgroundPanel();
        rect1 = new Rectangle(0, 55, 190, 40);
        rect2 = new Rectangle(190, 40, 40, 240);
        rect3 = new Rectangle(190, 180, 230, 40);
        rect4 = new Rectangle(300, 180, 40, 140);
        setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0, 0, 482, 341);
        getContentPane().add(panel);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                buttonAction(e);
            }
        });
        URL url = getClass().getResource("/images/button.png");
        ImageIcon imageIcons = new ImageIcon(url);
        button.setIcon(imageIcons);
        button.setBounds(27, 100, 106, 28);
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight());
        panel.add(goButton);
        panel.add(button);
        gobuttonX = goButton.getBounds().x; // 定义小猪按钮的横坐标
        gobuttonY = goButton.getBounds().y; // 定义小猪按钮的纵坐标
        goButton.addKeyListener(this);
        goButton.setIcon(imageIcon);
        goButton.setContentAreaFilled(false); // 取消填充区域
        goButton.setBorder(null); // 取消边框
        url = getClass().getResource("/images/exit.png");
        imageIcons = new ImageIcon(url);
        label.setIcon(imageIcons);
        label.setBounds(300, 315, imageIcons.getIconWidth(), imageIcons
                .getIconHeight());
        panel.add(label);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if ((gobuttonY == 286)) { // 如果小猪的纵坐标等于286
            Thread thread = new Thread(this);
            thread.start(); // 启动线程
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { // 如果用户按下了向上键
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20); // 创建Rectangle对象
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) { // 判断小猪是否走出了迷宫
                gobuttonY = gobuttonY - 2; // 设置变量坐标
                goButton.setLocation(gobuttonX, gobuttonY); // 设置按钮坐标
            } else { // 如果小猪走出了迷宫
                JOptionPane.showMessageDialog(this, "撞墙了吧！重新开始吧！", "撞墙啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 判断用户是否按向下键
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonY = gobuttonY + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞墙了吧！重新开始吧！", "撞墙啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 如果用户按向左键
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX - 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞墙了吧！重新开始吧！", "撞墙啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 如果用户按向右键
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞墙了吧！重新开始吧！", "撞墙啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    
    public void buttonAction(ActionEvent e) {
        goButton.setIcon(imageIcon); // 重新设置按钮的显示图片
        goButton.addKeyListener(this); // 为按钮添加键盘事件
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight()); // 设置小猪位置
        gobuttonX = goButton.getBounds().x; // 获得小猪当前位置的X坐标
        gobuttonY = goButton.getBounds().y;// 获得小猪当前位置的Y坐标
        goButton.requestFocus(); // 设置按钮获取焦点
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void run() {
        URL out = getClass().getResource("/images/pigOut.png"); // 获取图片URL
        ImageIcon imageout = new ImageIcon(out); // 创建图片对象
        goButton.setIcon(imageout); // 设置小猪按钮显示图片
        goButton.setBounds(gobuttonX,
                gobuttonY - imageout.getIconHeight() + 50, imageout
                        .getIconWidth(), imageout.getIconHeight()); // 重新设置按钮位置
        goButton.removeKeyListener(this); // 按钮移除键盘事件
    }
    
}
