package com.zzk;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgLabel extends JLabel {
    public ImgLabel() {
        super();
    }
    @Override
    public void paint(Graphics g) {
        try {
            int width = this.getWidth(); // 获取图片宽度
            int height = this.getHeight(); // 获取图片高度
            ImageIcon icon = (ImageIcon) getIcon(); // 获取ImageIcon对象
            if (icon != null) {// 图片不为空
                g.drawImage(icon.getImage(), 0, 0, width, height, this); // 绘制图片
            }
        } catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }
    }
}
