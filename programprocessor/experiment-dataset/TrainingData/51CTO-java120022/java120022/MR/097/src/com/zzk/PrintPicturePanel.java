package com.zzk;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author 张振坤
 *
 */
@SuppressWarnings("serial")
public class PrintPicturePanel extends JPanel implements Printable {
    private JButton printButton;
    private JButton previewButton;
    private JPanel controlPanel;
    private File imgFile = null;
    private BufferedImage src;
    private PrinterJob job;
    private PageFormat pf;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("打印图片");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 280);
        PrintPicturePanel panel = new PrintPicturePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    
    public PrintPicturePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(getControlPanel(), BorderLayout.SOUTH);
        pf = new PageFormat();// 创建PageFormat对象
        pf.setOrientation(PageFormat.LANDSCAPE);// 设置打印方向
        job = PrinterJob.getPrinterJob();// 获得打印对象
    }
    
    private void drawPage(Graphics2D g2) {
        int imgW = 0;
        int imgH = 0;
        if (src != null) {
            imgW = src.getWidth();
            imgH = src.getHeight();
            if (imgW > getWidth()) {
                imgW = getWidth();
            }
            if (imgH > getHeight()) {
                imgH = getHeight();
            }
        }
        g2.drawImage(src, 0, 0, imgW, imgH, this);// 绘制图像
    }
    
    protected JButton getPreviewButton() {
        if (previewButton == null) {
            previewButton = new JButton();
            previewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();// 创建文件选择器
                    FileFilter filter = new FileNameExtensionFilter(
                            "图像文件（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 创建过滤器
                    fileChooser.setFileFilter(filter);// 设置过滤器
                    int i = fileChooser.showOpenDialog(null);// 显示打开对话框
                    if (i == JFileChooser.APPROVE_OPTION) {
                        imgFile = fileChooser.getSelectedFile(); // 获取选中图片的File对象
                    }
                    if (imgFile != null) {
                        try {
                            src = ImageIO.read(imgFile);// 构造BufferedImage对象
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    PrintPicturePanel.this.repaint();// 调用paintComponent()方法
                }
            });
            previewButton.setText("选择图片");
        }
        return previewButton;
    }
    
    protected JButton getPrintButton() {
        if (printButton == null) {
            printButton = new JButton();
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        job.setPrintable(PrintPicturePanel.this);// 为打印对象指定Printable对象
                        job.setJobName("打印图片");// 设置打印任务的名称
                        job.print();// 执行打印
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            printButton.setText("打印");
        }
        return printButton;
    }
    
    protected JPanel getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new JPanel();
            controlPanel.setBorder(new LineBorder(Color.BLUE, 1, false));
            final FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(20);
            controlPanel.setLayout(flowLayout);
            controlPanel.add(getPreviewButton());
            controlPanel.add(getPrintButton());
        }
        return controlPanel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0)
            return Printable.NO_SUCH_PAGE;// 没有打印页
        int x = (int) pageFormat.getImageableX();//获得可打印区域的x坐标
        int y = (int) pageFormat.getImageableY();//获得可打印区域的y坐标
        Graphics2D g2 = (Graphics2D) graphics;
        int ableW = (int) pageFormat.getImageableWidth();//获得可打印区域的宽度
        int ableH = (int) pageFormat.getImageableHeight();//获得可打印区域的高度
        int imgW = 0;// 定义打印图片的宽度
        int imgH = 0;// 定义打印图片的高度
        if (src != null) {
            imgW = src.getWidth();// 获得图片的宽度
            imgH = src.getHeight();// 获得图片的高度
            if (imgW > ableW) {// 图片宽度大于打印区域的宽度
                imgW = ableW;// 图片宽度为打印区域的宽度
            }
            if (imgH > ableH) {// 图片高度大于打印区域的高度
                imgH = ableH;// 图片高度为打印区域的高度
            }
        }
        g2.drawImage(src, x, y, imgW, imgH, this);// 绘制打印内容
        return Printable.PAGE_EXISTS;// 返回存在打印内容的信息
    }
}
