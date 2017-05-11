package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    private JTextField filePath;
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private BufferedImage src;
    private boolean isPreview = false; // 是否可以打印
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// 窗体大小改变时执行
                // 打印预览
                if (imgFile != null) {
                    isPreview = true; // 表示可以打印
                    canvas.repaint();// 调用paint()方法
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("打印预览");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 770));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 打印预览
                if (imgFile != null) {
                    isPreview = true; // 表示可以打印
                    canvas.repaint();// 调用paint()方法
                } else {
                    JOptionPane.showMessageDialog(null, "请先选择要打印预览的图片！");
                }
            }
        });
        previewButton.setText("打印预览");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        
        printButton.setText("退    出");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel_1.setLayout(null);
        panel_1.setPreferredSize(new Dimension(0, 70));
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("请选择要打印预览的图片：");
        label.setBounds(30, 25, 156, 18);
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(500, 24));
        filePath.setBounds(195, 20, 466, 24);
        panel_1.add(filePath);
        
        final JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// 创建文件选择器
                FileFilter filter = new FileNameExtensionFilter(
                        "图像文件（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 创建过滤器
                fileChooser.setFileFilter(filter);// 设置过滤器
                int i = fileChooser.showOpenDialog(getContentPane());// 显示打开对话框
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 获取选中的图片对象
                    filePath.setText(imgFile.getAbsolutePath()); // 显示图片路径
                }
            }
        });
        selectButton.setText("选择文件");
        selectButton.setBounds(676, 20, 86, 28);
        panel_1.add(selectButton);
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex); // 绘制打印内容
        return Printable.PAGE_EXISTS; // 返回PAGE_EXISTS
    }
    
    // 画布
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// 平移绘图上下文
                int x = (int) (pf.getImageableX() - 1);// 获得可打印区域的x坐标偏左，用于绘制虚线
                int y = (int) (pf.getImageableY() - 1);// 获得可打印区域的y坐标偏上，用于绘制虚线
                int width = (int) (pf.getImageableWidth() + 1);// 获得可打印区域的宽度偏右，用于绘制虚线
                int height = (int) (pf.getImageableHeight() + 1);// 获得可打印区域的高度偏下，用于绘制虚线
                int mw = (int) pf.getWidth();// 获得打印页的宽度
                int mh = (int) pf.getHeight();// 获得打印页的高度
                g2.drawRect(0, 0, mw, mh);// 绘制实线外框
                g2.setColor(new Color(255, 253, 234)); // 设置前景色
                g2.fillRect(1, 1, mw - 1, mh - 1);// 绘制填充区域
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虚线模式
                g2.setColor(Color.BLACK); // 设置前景色
                g2.drawRect(x, y, width, height);// 绘制虚线内框
                g2.setColor(Color.WHITE); // 设置前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// 绘制填充区域
                MainFrame.this.print(g2, pf, 0);// 调用print()方法
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 绘制打印内容
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX(); // 获取可打印区域坐标的X位置
        int y = (int) pageFormat.getImageableY(); // 获取可打印区域坐标的Y位置
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// 构造BufferedImage对象
            } catch (IOException e) {
                e.printStackTrace();
            }
            double imgWidth = src.getWidth(this);// 获得图像的宽度
            double imgHeight = src.getHeight(this);// 获得图像的高度
            int mw = (int) pf.getWidth() - x * 2;// 纸张宽度减去左右边缘
            int mh = (int) pf.getHeight() - y * 2;// 纸张高度减去上下边缘
            if (imgWidth > mw) { // 如果图像的宽度大于可打印区域
                imgWidth = mw;
            }
            if (imgHeight > mh) { // 如果图像的高度大于可打印区域
                imgHeight = mh;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // 绘制正常图像
        }
        isPreview = false; // 设置不可以打印
    }
}
