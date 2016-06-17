package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
    
    /**
     * Launch the application
     * 
     * @param args
     */
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
    
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private Image src;
    private boolean isPreview = false; // 是否可以打印
    
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
        setTitle("倒序打印");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 750));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 打印预览
                if (imgFile != null) {
                    isPreview = true; // 表示可以打印
                    PrinterJob job = PrinterJob.getPrinterJob();// 获得打印对象
                    pf = job.pageDialog(pf);// 显示修改PageFormat实例的对话框
                    canvas.repaint();// 调用paint()方法
                } else {
                    JOptionPane.showMessageDialog(null, "请先选择要打印的图片！");
                }
            }
        });
        previewButton.setText("打印预览");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // 获取PrinterJob对象的实例
                if (!job.printDialog())// 打开打印对话框
                    return;// 单击打印对话框的取消按钮或关闭打印对话框结束程序的执行
                // 设置打印内容
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // 设置可以打印
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // 绘制打印内容
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("打印图形");
                try {
                    job.print();// 调用print()方法，实现打印
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("开始打印");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("请选择要打印的图片：");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // 选择文件按钮
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
        panel_1.add(selectButton);
        
        //
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
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虚线模式
                g2.drawRect(x, y, width, height);// 绘制虚线内框
                MainFrame.this.print(g2, pf, 0);// 调用print()方法
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 绘制倒序的打印内容
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
            double imgWidthS = imgWidth;// 存储图像的宽度
            double imgHeightS = imgHeight;// 存储图像的高度
            int mw = (int) pf.getWidth();// 获得打印页的宽度
            int mh = (int) pf.getHeight();// 获得打印页的高度
            if (imgWidth > mw) { // 如果宽大于可打印区域
                imgWidth = mw - x;// 设置新宽度值
            }
            if (imgHeight > mh) { // 如果高大于可打印区域
                imgHeight = mh - y;// 设置新的高度值
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, x,
                    (int) imgHeightS, (int) imgWidthS, y, this);// 绘制倒序图像
        }
        isPreview = false; // 设置不可以打印
    }
}
