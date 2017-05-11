package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveTextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null; // 声明面板对象
    private int newW, newH;// 用于存储图片缩放后的宽度和高度
    
    public static void main(String args[]) {
        SaveTextureFillFrame frame = new SaveTextureFillFrame();
        frame.setVisible(true);
    }
    
    public SaveTextureFillFrame() {
        super();
        setTitle("填充纹理并保存为图片"); // 设置窗体标题
        textureFillPanel = new TextureFillPanel(); // 创建图像面板对象
        setBounds(200, 160, 346, 285); // 设置窗体大小和位置
        add(textureFillPanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FileDialog dialog = new FileDialog(SaveTextureFillFrame.this,
                        "保存");// 创建对话框
                dialog.setMode(FileDialog.SAVE);// 设置对话框为保存对话框
                dialog.setVisible(true);// 显示保存对话框
                String path = dialog.getDirectory();// 获得文件的保存路径
                String fileName = dialog.getFile();// 获得保存的文件名
                if (path == null || fileName == null) {
                    JOptionPane.showMessageDialog(null, "请指定文件的保存路径和文件名。");// 显示提示信息
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// 文件扩展名,不含点
                String pathAndName = path + "\\" + fileName;// 文件的完整路径
                BufferedImage image = new BufferedImage(200, 200,
                        BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                Graphics2D g2 = image.createGraphics();// 获得缓冲图像对象的绘图上下文对象
                g2.setColor(Color.BLUE);// 设置颜色
                g2.fillRect(0, 0, 90, 90);// 绘制填充矩形
                g2.setColor(Color.RED);// 设置颜色
                g2.fillOval(95, 0, 90, 90);// 绘制带填充色的圆形
                g2.setColor(Color.GREEN);// 设置颜色
                g2.fillRect(95, 95, 90, 90);// 绘制填充矩形
                g2.setColor(Color.ORANGE);// 设置颜色
                g2.fillOval(0, 95, 90, 90);// 绘制带填充色的圆形
                Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// 创建Rectangle2D对象
                TexturePaint textPaint = new TexturePaint(image, rect);// 创建纹理填充对象
                BufferedImage bufferImage = new BufferedImage(newW, newH,
                        BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                Graphics g = bufferImage.getGraphics();// 获得缓冲图像的绘图上下文对象
                Graphics2D graphics2 = (Graphics2D) g;// 转换绘图上下文对象为Graphics2D类型
                graphics2.setPaint(textPaint);// 为绘图上下文对象指定纹理填充对象
                Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW,
                        newH);// 创建矩形对象
                graphics2.fill(ellipse2);// 绘制填充纹理的矩形
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// 将缓冲图像保存到磁盘
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "保存失败\n"
                            + e1.getMessage());// 显示提示信息
                }
            }
        });
        button.setText("保存为图片");
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
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
            Graphics2D g2 = image.createGraphics();// 获得缓冲图像对象的绘图上下文对象
            g2.setColor(Color.BLUE);// 设置颜色
            g2.fillRect(0, 0, 90, 90);// 绘制填充矩形
            g2.setColor(Color.RED);// 设置颜色
            g2.fillOval(95, 0, 90, 90);// 绘制带填充色的圆形
            g2.setColor(Color.GREEN);// 设置颜色
            g2.fillRect(95, 95, 90, 90);// 绘制填充矩形
            g2.setColor(Color.ORANGE);// 设置颜色
            g2.fillOval(0, 95, 90, 90);// 绘制带填充色的圆形
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// 创建Rectangle2D对象
            TexturePaint textPaint = new TexturePaint(image, rect);// 创建纹理填充对象
            Graphics2D graphics2 = (Graphics2D) g;// 转换paint()方法的绘图上下文对象
            graphics2.setPaint(textPaint);// 为绘图上下文对象设置纹理填充对象
            newW = getWidth();
            newH = getHeight();
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW, newH);// 创建矩形对象
            graphics2.fill(ellipse2);// 绘制填充纹理的矩形
        }
    }
}
