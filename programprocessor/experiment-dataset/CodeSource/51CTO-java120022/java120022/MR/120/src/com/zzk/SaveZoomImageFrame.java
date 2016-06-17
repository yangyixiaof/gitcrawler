package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
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
public class SaveZoomImageFrame extends JFrame {
    private JButton button_3;
    private JButton button_2;
    private JButton button_1;
    private JButton button;
    private JPanel panel;
    private Image img = null; // 声明图像对象
    private ZoomImagePanel imagePanel = null; // 声明图像面板对象
    private int imgWidth, imgHeight;// 用于存储图片的宽度和高度
    private int newW, newH;// 用于存储图片缩放后的宽度和高度
    private float value = 50.0f;// 控制图像大小的变量
    
    public static void main(String args[]) {
        SaveZoomImageFrame frame = new SaveZoomImageFrame();
        frame.setVisible(true);
    }
    
    public SaveZoomImageFrame() {
        super();
        this.setTitle("缩放图片并保存"); // 设置窗体标题
        URL imgUrl = SaveZoomImageFrame.class.getResource("/img/image.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        imagePanel = new ZoomImagePanel(); // 创建图像面板对象
        this.setBounds(200, 160, 355, 253); // 设置窗体大小和位置
        this.add(imagePanel); // 在窗体中部位置添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        getContentPane().add(getPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * @return
     */
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getButton());
            panel.add(getButton_1());
            panel.add(getButton_2());
            panel.add(getButton_3());
        }
        return panel;
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value += 5;// 调整value的值，用于放大图片
                    if (value >= 200.0f) {// 如果value的值大于等于200
                        value = 200.0f;// 使value的值等于200
                    }
                    imagePanel.repaint();// 重新调用面板类的paint()方法
                }
            });
            button.setText("放  大");
        }
        return button;
    }
    
    /**
     * @return
     */
    protected JButton getButton_1() {
        if (button_1 == null) {
            button_1 = new JButton();
            button_1.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value -= 5;// 调整value的值，用于缩小图片
                    if (value <= 0.0f) {// 如果value的值小于等于0
                        value = 0.0f;// 使value的值等于0
                    }
                    imagePanel.repaint();// 重新调用面板类的paint()方法
                }
            });
            button_1.setText("缩    小");
        }
        return button_1;
    }
    
    /**
     * @return
     */
    protected JButton getButton_2() {
        if (button_2 == null) {
            button_2 = new JButton();
            button_2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (newW <= 0 || newH <= 0) {
                        JOptionPane.showMessageDialog(null, "图像的宽度和高度必须大于0");// 显示提示信息
                        return;
                    }
                    FileDialog dialog = new FileDialog(SaveZoomImageFrame.this,
                            "保存");// 创建对话框
                    dialog.setMode(FileDialog.SAVE);// 设置对话框为保存对话框
                    dialog.setVisible(true);// 显示保存对话框
                    String path = dialog.getDirectory();// 获得文件的保存路径
                    String fileName = dialog.getFile();// 获得保存的文件名
                    if (path == null || fileName == null) {
                        return;
                    }
                    String fileExtName = fileName.substring(fileName
                            .indexOf(".") + 1);// 文件扩展名,不含点
                    String pathAndName = path + "\\" + fileName;// 文件的完整路径
                    BufferedImage bufferImage = new BufferedImage(newW, newH,
                            BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
                    Graphics g = bufferImage.getGraphics();// 获得绘图上下文对象
                    g.drawImage(img, 0, 0, newW, newH, null);// 在缓冲图像上绘制图像
                    try {
                        ImageIO.write(bufferImage, fileExtName, new File(
                                pathAndName));// 将缓冲图像保存到磁盘
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "保存失败\n"
                                + e1.getMessage());// 显示提示信息
                    }
                }
            });
            button_2.setText("保    存");
        }
        return button_2;
    }
    
    /**
     * @return
     */
    protected JButton getButton_3() {
        if (button_3 == null) {
            button_3 = new JButton();
            button_3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                }
            });
            button_3.setText("退    出");
        }
        return button_3;
    }
    
    // 创建面板类
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清除绘图上下文的内容
            imgWidth = img.getWidth(this); // 获取图片宽度
            imgHeight = img.getHeight(this); // 获取图片高度
            newW = (int) (imgWidth * value / 100);// 计算图片缩放后的宽度
            newH = (int) (imgHeight * value / 100);// 计算图片缩放后的高度
            g.drawImage(img, 0, 0, newW, newH, this);// 绘制指定大小的图片
        }
    }
    
}
