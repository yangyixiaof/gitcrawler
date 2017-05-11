package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TranslucenceImageFrame extends JFrame {
    private Image img = null; // 声明图像对象
    private TranslucenceImagePanel translucencePanel = null; // 声明图像面板对象
    private AlphaComposite alpha = AlphaComposite.SrcOver.derive(1.0f);// 创建表示不透明的AlphaComposite对象
    public static void main(String args[]) {
        TranslucenceImageFrame frame = new TranslucenceImageFrame();
        frame.setVisible(true);
    }
    
    public TranslucenceImageFrame() {
        super();
        URL imgUrl = TranslucenceImageFrame.class.getResource("/img/imag.jpg");// 获取图片资源的路径
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
        translucencePanel = new TranslucenceImagePanel(); // 创建图像面板对象
        this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
        this.add(translucencePanel); // 在窗体上添加图像面板对象
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
        this.setTitle("图片半透明特效"); // 设置窗体标题

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(0.5f);// 获得表示半透明的AlphaComposite对象
                translucencePanel.repaint();// 调用paint()方法
            }
        });
        button.setText("半透明");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(1.0f);// 获得表示不透明的AlphaComposite对象
                translucencePanel.repaint();// 调用paint()方法
            }
        });
        button_1.setText("不透明");
        panel.add(button_1);
    }
    
    // 创建面板类
    class TranslucenceImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
            g2.clearRect(0, 0,  getWidth(), getHeight());// 清除绘图上下文的内容
            g2.setComposite(alpha);// 指定AlphaComposite对象
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// 绘制图像
        }
        
    }
}
