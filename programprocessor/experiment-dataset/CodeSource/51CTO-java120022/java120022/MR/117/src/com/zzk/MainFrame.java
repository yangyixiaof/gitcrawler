package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();// 创建窗体对象
        AWTUtilities.setWindowOpacity(frame, 0.8f); // 设置窗体80%透明
        frame.setVisible(true);// 显示窗体
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
        getContentPane().setBackground(new Color(70, 130, 180));
        setTitle("透明的打印预览对话框");
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
        panel.setOpaque(false);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 打印预览
                if (imgFile != null) {
                    isPreview = true; // 表示可以打印
                    PrinterJob job = PrinterJob.getPrinterJob();
                    pf = job.pageDialog(pf);
                    canvas.repaint();
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
                if (!job.printDialog())
                    return;
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
                    job.print();
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("开始打印");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
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
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "图像文件（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");
                fileChooser.setFileFilter(filter);
                int i = fileChooser.showOpenDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 获取选中的图片对象
                    filePath.setText(imgFile.getAbsolutePath()); // 显示图片路径
                }
            }
        });
        selectButton.setText("选择文件");
        panel_1.add(selectButton);
        // setGlassPane(getContentPane());
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
                g2.translate(10, 10);
                int x = (int) (pf.getImageableX() - 1);
                int y = (int) (pf.getImageableY() - 1);
                int width = (int) (pf.getImageableWidth() + 1);
                int height = (int) (pf.getImageableHeight() + 1);
                int mw = (int) pf.getWidth();
                int mh = (int) pf.getHeight();
                g2.setColor(new Color(255, 253, 234)); // 设置前景色
                g2.fillRect(1, 1, mw - 1, mh - 1);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));
                g2.setColor(Color.BLACK); // 设置前景色
                g2.drawRect(x, y, width, height);
                g2.setColor(Color.WHITE); // 设置前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);
                MainFrame.this.print(g, pf, 0);
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
                src = ImageIO.read(imgFile);// 构造Image对象
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            double imgWidth = src.getWidth(this);
            double imgHeight = src.getHeight(this);
            double percent = imgWidth / imgHeight; // 长宽比例
            int mw = (int) pf.getWidth() - x * 2;
            int mh = (int) pf.getHeight() - y * 2;
            if (imgWidth > mw) { // 如果宽大于可打印区域
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // 如果高大于可打印区域
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // 绘制正常图像
        }
        isPreview = false; // 设置不可以打印
    }
}
