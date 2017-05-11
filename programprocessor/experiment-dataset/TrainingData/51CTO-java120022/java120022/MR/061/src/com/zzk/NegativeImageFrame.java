package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NegativeImageFrame extends JFrame {
    private BufferedImage image;// 声明缓冲图像对象
    private NegativeImagePanel negativeImagePanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        NegativeImageFrame frame = new NegativeImageFrame();
        frame.setVisible(true);
    }
    
    public NegativeImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片反向特效"); // 设置窗体标题
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativeImagePanel = new NegativeImagePanel(); // 创建图像面板对象
        this.add(negativeImagePanel); // 在窗体上添加图像面板对象
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256*1];// 创建表示颜色反向的分量数组
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// 为数组赋值
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// 创建查找表对象
                LookupOp op = new LookupOp(table,null);// 创建实现从源到目标查找操作的LookupOp对象
                image = op.filter(image, null);// 调用LookupOp对象的filter()方法，实现图像反向功能
                repaint();  // 调用paint()方法
            }
        });
        button.setText("反    向");
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
    
    
    // 创建面板类
    class NegativeImagePanel extends JPanel {
        public NegativeImagePanel(){
            Image img = null;// 声明创建图像对象
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // 创建图像对象
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
