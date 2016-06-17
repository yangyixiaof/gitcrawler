package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveImageFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private DrawImagePanel imagePanel = null; // 声明图像面板对象
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaveImageFrame frame = new SaveImageFrame();
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
    public SaveImageFrame() {
        super();
        setTitle("保存图片文件"); // 设置窗体标题
        setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = SaveImageFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        imagePanel = new DrawImagePanel(); // 创建图像面板对象
        add(imagePanel); // 在窗体上添加图像面板对象
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveImageFrame.this), img
                        .getHeight(SaveImageFrame.this),
                        BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                Graphics g = bufferImage.getGraphics();// 获得绘图上下文对象
                g.drawImage(img, 0, 0, null);// 在缓冲图像上绘制图像
                try {
                    ImageIO.write(bufferImage, "jpg", new File("c:/image.jpg"));// 将缓冲图像保存到磁盘
                    JOptionPane.showMessageDialog(null,
                            "已将图片image.jpg\n成功地保存到C:盘");// 显示提示信息
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("保存图片");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
        //
    }
    
    // 创建面板类
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // 绘制指定的图片
        }
    }
}
