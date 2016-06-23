package com.zzk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RasterImageFrame extends JFrame {
    private static final double XMIN = -2;// ������x�������Сֵ
    private static final double XMAX = 2;// ������x��������ֵ
    private static final double YMIN = -2;// ������y�������Сֵ
    private static final double YMAX = 2;// ������y��������ֵ
    private static final int MAX_ITERATIONS = 16;// ��������������
    
    public RasterImageFrame() {
        setTitle("��դͼ��");
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
                BufferedImage.TYPE_INT_ARGB);// ��������ͼ�����
        WritableRaster raster = image.getRaster();// ����ṩ����д�빦�ܵ�WritableRaster����
        ColorModel model = image.getColorModel();// ��û���ͼ�����ɫģ��
        Color fractalColor = Color.RED;// �����ʾ��ɫ����ɫ����
        int argb = fractalColor.getRGB();// ��ñ�ʾ��ɫ��RGBֵ
        Object colorData = model.getDataElements(argb, null);// ����ColorModel��ָ�����ص������ʾ��ʽ
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // ����㣨i,j���Ƿ������(a,b)�ϵ���������
                double a = XMIN + i * (XMAX - XMIN) / width;
                double b = YMIN + j * (YMAX - YMIN) / height;
                if (!isOrNotConvergence(a, b)) {// ����㣨i,j���������(a,b)�ϵ�����������escapesToInfinity()��������false������й�դ����
                    raster.setDataElements(i, j, colorData);// Ϊ����TransferType���������еĵ���������������
                }
            }
        }
        return image;// ���ػ����й�դͼ��Ļ���ͼ�����
    }
    
    private boolean isOrNotConvergence(double a, double b) {// �ж����������ϵĵ�(a,b)�������ģ����Ƿ�ɢ��
        double x = 0.0D;// ���x����2���������о��Ƿ�ɢ��
        double y = 0.0D;// ���y����2����������Ҳ�Ƿ�ɢ��
        int iterations = 0;// ѭ������
        while (x <= 2 && y <= 2 && iterations < MAX_ITERATIONS) {
            double xNew = x * x - y * y + a;// ����ÿ�����xֵ
            double yNew = 2 * x * y + b;// ����ÿ�����yֵ
            x = xNew;// ��ֵ������x�������ж����������Ƿ�ɢ
            y = yNew;// ��ֵ������y�������ж����������Ƿ�ɢ
            iterations++; // ����������������ֵ
        }
        return x > 2 || y > 2;// ����false��ʾ��������л��ƣ�Ϊtrue��ʾ��ɢ��͸��
    }
}