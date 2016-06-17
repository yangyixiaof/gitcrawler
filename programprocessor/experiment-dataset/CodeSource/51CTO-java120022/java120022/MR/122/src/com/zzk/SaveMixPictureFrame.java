package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FileDialog;
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
public class SaveMixPictureFrame extends JFrame {
    private Image img1 = null; // 声明图像对象
    private Image img2 = null; // 声明图像对象
    private boolean mixFlag = false;// 用于决定是否溶合图片，为true时溶合图片
    private boolean firstOrSecondFlag = false;// 为false时显示第一张图片，为true时显示第二张图片
    private MixPicturePanel mixPicturePanel = null; // 声明图像面板对象
    private int panelWidth = 0;// 图像面板的宽度
    private int panelHeight = 0;// 图像面板的高度
    public static void main(String args[]) {
        SaveMixPictureFrame frame = new SaveMixPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveMixPictureFrame() {
        super();
        URL imgUrl = SaveMixPictureFrame.class.getResource("/img/img.jpg");// 获取图片资源的路径
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        imgUrl = SaveMixPictureFrame.class.getResource("/img/imag.jpg");// 获取图片资源的路径
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        mixPicturePanel = new MixPicturePanel(); // 创建图像面板对象
        this.setBounds(200, 160, 476, 336); // 设置窗体大小和位置
        this.add(mixPicturePanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("溶合两张图片并保存"); // 设置窗体标题
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = false;// 标记不绘制第二张图片
                mixFlag = false;// 标记没溶合图片
                mixPicturePanel.repaint();// 调用paint()方法绘制第一张图片
            }
        });
        button_2.setText("第一张");
        panel.add(button_2);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// 标记绘制第二张图片
                mixFlag = false;// 标记没溶合图片
                mixPicturePanel.repaint();// 调用paint()方法绘制第一张图片
            }
        });
        button_3.setText("第二张");
        panel.add(button_3);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// 标记绘制第二张图片
                mixFlag = true;// 标记溶合图片
                mixPicturePanel.repaint();// 调用paint()方法绘制第一张图片
            }
        });
        button.setText("溶合图片");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!mixFlag) {
                    JOptionPane.showMessageDialog(null,"还没有溶合图片。");// 显示提示信息
                    return;
                }
                FileDialog dialog = new FileDialog(SaveMixPictureFrame.this,"保存");// 创建对话框
                dialog.setMode(FileDialog.SAVE);// 设置对话框为保存对话框
                dialog.setVisible(true);// 显示保存对话框
                String path = dialog.getDirectory();// 获得文件的保存路径
                String fileName = dialog.getFile();// 获得保存的文件名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 文件扩展名,不含点
                String pathAndName = path + "\\" + fileName;// 文件的完整路径
                BufferedImage bufferImage = new BufferedImage(panelWidth, panelHeight,
                        BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// 获得绘图上下文对象
                g2.drawImage(img1, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// 绘制图像
                    AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 获得表示透明度的AlphaComposite对象
                    g2.setComposite(alpha);// 指定AlphaComposite对象
                    g2.drawImage(img2, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// 绘制调整透明度后的图片
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
        
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_4.setText("退    出");
        panel.add(button_4);
        
    }
    
    // 创建面板类
    class MixPicturePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            panelWidth = getWidth();// 获得图像面板的宽度
            panelHeight = getHeight();// 获得图像面板的高度
            g2.drawImage(img1, 0, 0, panelWidth, panelHeight, this);// 绘制图像
            if (mixFlag) {// 标记溶合图片
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 获得表示透明度的AlphaComposite对象
                g2.setComposite(alpha);// 指定AlphaComposite对象
            }
            if (firstOrSecondFlag) {// 标记绘制第二张图片
                g2.drawImage(img2, 0, 0, panelWidth, panelHeight, this);// 绘制调整透明度后的图片
            }
        }
    }
}
