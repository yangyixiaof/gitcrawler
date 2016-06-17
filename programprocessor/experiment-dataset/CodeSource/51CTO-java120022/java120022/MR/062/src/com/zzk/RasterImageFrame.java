package com.zzk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RasterImageFrame extends JFrame {
    private static final double XMIN = -2;// 声明在x方向的最小值
    private static final double XMAX = 2;// 声明在x方向的最大值
    private static final double YMIN = -2;// 声明在y方向的最小值
    private static final double YMAX = 2;// 声明在y方向的最大值
    private static final int MAX_ITERATIONS = 16;// 声明最大迭代次数
    
    public RasterImageFrame() {
        setTitle("光栅图像");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage image = makeRasterImage(300, 240);
        add(new JLabel(new ImageIcon(image)));
    }
    
    public static void main(String[] args) {
        JFrame frame = new RasterImageFrame();
        frame.setVisible(true);
    }
    
    private BufferedImage makeRasterImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);// 创建缓冲图像对象
        WritableRaster raster = image.getRaster();// 获得提供像素写入功能的WritableRaster对象
        ColorModel model = image.getColorModel();// 获得缓冲图像的颜色模型
        Color fractalColor = Color.RED;// 定义表示红色的颜色对象
        int argb = fractalColor.getRGB();// 获得表示颜色的RGB值
        Object colorData = model.getDataElements(argb, null);// 返回ColorModel中指定像素的数组表示形式
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // 计算点（i,j）是否导致与点(a,b)上的像素收敛
                double a = XMIN + i * (XMAX - XMIN) / width;
                double b = YMIN + j * (YMAX - YMIN) / height;
                if (!isOrNotConvergence(a, b)) {// 如果点（i,j）导致与点(a,b)上的像素收敛，escapesToInfinity()方法返回false，则进行光栅绘制
                    raster.setDataElements(i, j, colorData);// 为类型TransferType基本数组中的单个像素设置数据
                }
            }
        }
        return image;// 返回绘制有光栅图像的缓冲图像对象
    }
    
    private boolean isOrNotConvergence(double a, double b) {// 判断数字序列上的点(a,b)是收敛的，还是发散的
        double x = 0.0D;// 如果x大于2，数字序列就是发散的
        double y = 0.0D;// 如果y大于2，数字序列也是发散的
        int iterations = 0;// 循环变量
        while (x <= 2 && y <= 2 && iterations < MAX_ITERATIONS) {
            double xNew = x * x - y * y + a;// 计算每个点的x值
            double yNew = 2 * x * y + b;// 计算每个点的y值
            x = xNew;// 赋值给变量x，用于判断是收敛还是发散
            y = yNew;// 赋值给变量y，用于判断是收敛还是发散
            iterations++; // 调整迭代器变量的值
        }
        return x > 2 || y > 2;// 返回false表示收敛则进行绘制，为true表示发散则透明
    }
}