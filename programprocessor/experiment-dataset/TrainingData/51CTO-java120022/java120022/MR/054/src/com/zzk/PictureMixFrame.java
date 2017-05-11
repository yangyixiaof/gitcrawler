package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class PictureMixFrame extends JFrame {
    private Image img1 = null; // 声明图像对象
    private Image img2 = null; // 声明图像对象
    private JSlider slider = null;
    private PictureMixPanel pictureMixPanel = null; // 声明图像面板对象
    public static void main(String args[]) {
        PictureMixFrame frame = new PictureMixFrame();
        frame.setVisible(true);
    }
    
    public PictureMixFrame() {
        super();
        URL imgUrl = PictureMixFrame.class.getResource("/img/img.jpg");// 获取图片资源的路径
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        imgUrl = PictureMixFrame.class.getResource("/img/imag.jpg");// 获取图片资源的路径
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        pictureMixPanel = new PictureMixPanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(pictureMixPanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片溶合特效"); // 设置窗体标题

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                pictureMixPanel.repaint();// 重新调用面板类的paint()方法
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);
    }
    
    // 创建面板类
    class PictureMixPanel extends JPanel {
        boolean flag = true;// 定义标记变量，用于控制x的值
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);// 获得表示透明度的AlphaComposite对象
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            g2.drawImage(img1, 0, 0,  getWidth(), getHeight(), this);// 绘制图像
            float value = slider.getValue();// 滑块组件的取值
            float alphaValue = value / 100;// 计算透明度的值
            alpha = AlphaComposite.SrcOver.derive(alphaValue);// 获得表示透明度的AlphaComposite对象
            g2.setComposite(alpha);// 指定AlphaComposite对象
            g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);// 绘制调整透明度后的图片
        }
        
    }
}
