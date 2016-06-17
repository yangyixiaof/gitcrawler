package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeDetectImageFrame extends JFrame {
    private BufferedImage image;// 声明缓冲图像对象
    private EdgeDetectImagePanel edgeDetectImagePanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        EdgeDetectImageFrame frame = new EdgeDetectImageFrame();
        frame.setVisible(true);
    }
    
    public EdgeDetectImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片照亮边缘特效"); // 设置窗体标题
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        edgeDetectImagePanel = new EdgeDetectImagePanel(); // 创建图像面板对象
        this.add(edgeDetectImagePanel); // 在窗体上添加图像面板对象
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,4.0f,-1.0f,0.0f,-1.0f,0.0f};// 声明表示像素分量的数组
                convolve(elements);// 调用方法实现图片照亮边缘功能
            }
        });
        button.setText("照亮边缘");
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
        Kernel kernel = new Kernel(3, 3, elements);             // 创建 Kernel对象
        ConvolveOp op = new ConvolveOp(kernel);         // 创建ConvolveOp对象
        if (image == null) {
            return;
        }
        image = op.filter(image, null);                     // 过滤缓冲图像对象
        repaint();                                  // 调用paint()方法
    }

    
    // 创建面板类
    class EdgeDetectImagePanel extends JPanel {
        public EdgeDetectImagePanel(){
            Image img = null;// 声明创建图像对象
            try {
                img = ImageIO.read(new File("src/img/image.jpg"));  // 创建图像对象
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
            image.getGraphics().drawImage(img, 0, 0,  null);// 在缓冲图像对象上绘制图像
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// 绘制缓冲图像对象
            }
        }
    }
}
