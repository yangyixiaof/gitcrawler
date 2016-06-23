package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class SaveWatermarkPictureFrame extends JFrame {
    private boolean watermark = false;// 水印标记，为true时绘制水印
    private Image img = null; // 声明图像对象
    private DrawWatermarkPanel watermarkPanel = null; // 声明图像面板对象
    
    public static void main(String args[]) {
        SaveWatermarkPictureFrame frame = new SaveWatermarkPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveWatermarkPictureFrame() {
        super();
        setTitle("为图片添加水印并保存"); // 设置窗体标题
        URL imgUrl = SaveWatermarkPictureFrame.class
                .getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        watermarkPanel = new DrawWatermarkPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 420, 320); // 设置窗体大小和位置
        this.add(watermarkPanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                watermark = true;
                watermarkPanel.repaint();// 调用paint()方法绘制水印
            }
        });
        button.setText("添加水印");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!watermark) {
                    JOptionPane.showMessageDialog(null,"还没有为图片添加水印。");// 显示提示信息
                    return;
                }
                FileDialog dialog = new FileDialog(SaveWatermarkPictureFrame.this,"保存");// 创建对话框
                dialog.setMode(FileDialog.SAVE);// 设置对话框为保存对话框
                dialog.setVisible(true);// 显示保存对话框
                String path = dialog.getDirectory();// 获得文件的保存路径
                String fileName = dialog.getFile();// 获得保存的文件名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 文件扩展名,不含点
                String pathAndName = path + "\\" + fileName;// 文件的完整路径
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveWatermarkPictureFrame.this), img
                        .getHeight(SaveWatermarkPictureFrame.this),
                        BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// 获得绘图上下文对象
                g2.drawImage(img, 0, 0, img.getWidth(SaveWatermarkPictureFrame.this), 
                        img.getHeight(SaveWatermarkPictureFrame.this), null);// 在缓冲图像上绘制图像
                g2.rotate(Math.toRadians(-30));// 旋转绘图上下文对象
                Font font = new Font("楷体", Font.BOLD, 72);// 创建字体对象
                g2.setFont(font);// 指定字体
                g2.setColor(Color.RED);// 指定颜色
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 获得表示透明度的AlphaComposite对象
                g2.setComposite(alpha);// 指定AlphaComposite对象
                g2.drawString("编程词典", -30, 240);// 绘制文本,实现水印
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// 将缓冲图像保存到磁盘
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"保存失败\n"+e1.getMessage());// 显示提示信息
                }
            }
        });
        button_1.setText("保存图片");
        panel.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_2.setText("退    出");
        panel.add(button_2);
    }
    
    // 创建面板类
    class DrawWatermarkPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 绘制图像
            if (watermark) {
                g2.rotate(Math.toRadians(-30));// 旋转绘图上下文对象
                Font font = new Font("楷体", Font.BOLD, 72);// 创建字体对象
                g2.setFont(font);// 指定字体
                g2.setColor(Color.RED);// 指定颜色
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 获得表示透明度的AlphaComposite对象
                g2.setComposite(alpha);// 指定AlphaComposite对象
                g2.drawString("编程词典", -30, 240);// 绘制文本,实现水印
            }
        }
    }
}
