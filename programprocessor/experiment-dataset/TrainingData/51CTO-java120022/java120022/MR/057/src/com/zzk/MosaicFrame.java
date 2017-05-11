package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MosaicFrame extends JFrame {
    private BufferedImage image;// 声明缓冲图像对象
    private Image img = null; // 声明图像对象
    private MosaicPanel mosaicPanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
    
    public MosaicFrame() {
        super();
        URL imgUrl = MosaicFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        mosaicPanel = new MosaicPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(mosaicPanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片马赛克特效"); // 设置窗体标题

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int x = 104;// 矩形绘制点的x坐标
                int y = 60; // 矩形绘制点的y坐标
                Rectangle2D.Float rect = null;
                image = new BufferedImage(getWidth() + 10, getHeight(),
                        BufferedImage.TYPE_INT_ARGB);// 创建缓冲图像对象
                Graphics2D gs2d = (Graphics2D) image.getGraphics();// 获得缓冲图像对象的Graphics2D对象
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.90f);// 获得表示透明度的AlphaComposite对象
                gs2d.setComposite(alpha);// 设置透明度
                GradientPaint paint = null;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint = new GradientPaint(x, y, Color.white, x + 10,
                                y + 10, Color.gray,true);// 创建循环渐变的GraphientPaint对象
                        gs2d.setPaint(paint);// 设置渐变
                        rect = new Rectangle2D.Float(x, y, 20, 20);// 创建矩形对象
                        gs2d.fill(rect);// 在缓冲图像对象上绘制矩形
                        y = y + 20;// 计算下一个矩形的y坐标
                    }
                    y = 60;// 还原y坐标
                    x = x + 20;// 计算x坐标
                }
                
                for (int i = 0; i < 3; i++) {// 该for循环，实现3次模糊
                    float[] elements = new float[9];// 定义表示像素分量的数组
                    for (int j = 0; j < 9; j++) {
                        elements[j] = 0.11f;// 为数组赋值
                    }
                    convolve(elements);// 调用方法，实现模糊功能
                }
                mosaicPanel.repaint();// 调用paint()方法
            }
        });
        button.setText("添加马赛克");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// 创建 Kernel对象
        ConvolveOp op = new ConvolveOp(kernel);// 创建ConvolveOp对象
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // 过滤缓冲图像对象
    }
    
    // 创建面板类
    class MosaicPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, getWidth(),getHeight(), this);// 绘制图像对象
            g2.drawImage(image, 0, 0, this);// 绘制缓冲图像对象
        }
    }
}
