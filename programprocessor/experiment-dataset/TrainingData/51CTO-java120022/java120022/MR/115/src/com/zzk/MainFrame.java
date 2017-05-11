package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.swtdesigner.SwingResourceManager;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    private String border = ""; // 边框类型
    private PageFormat pf; // 描述页面大小和方向的对象
    private PreviewCanvas canvas; // 打印预览画布
    private File imgFile = null; // 相片文件
    private Image src;// 相片图片
    private boolean isPreview = false; // 是否可以打印
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        setTitle("相册特效打印程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 780));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(0, 80));
        panel_1.setLayout(null);
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setBounds(50, 15, 130, 18);
        label.setText("请选择要打印的相片：");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setBounds(186, 12, 502, 24);
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // 选择文件按钮
        final JButton selectButton = new JButton();
        selectButton.setBounds(694, 10, 86, 28);
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
        
        final ImgLabel imgLabel1 = new ImgLabel();
        imgLabel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠标左键
                    border = "border1"; // 设置边框类型
                    if (imgFile != null) {
                        isPreview = true; // 表示可以打印
                        canvas.repaint(); // 重绘画布
                    } else {
                        JOptionPane.showMessageDialog(null, "请先选择要打印的图片！");
                    }
                }
            }
        });
        imgLabel1.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border1.png"));
        imgLabel1.setBounds(186, 42, 86, 35);
        panel_1.add(imgLabel1);
        
        final ImgLabel imgLabel2 = new ImgLabel();
        imgLabel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠标左键
                    border = "border2"; // 设置边框类型
                    if (imgFile != null) {
                        isPreview = true; // 表示可以打印
                        canvas.repaint(); // 重绘画布
                    } else {
                        JOptionPane.showMessageDialog(null, "请先选择要打印的图片！");
                    }
                }
            }
        });
        imgLabel2.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border2.png"));
        imgLabel2.setBounds(289, 42, 86, 35);
        panel_1.add(imgLabel2);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("选择边框观看预览效果：");
        label_1.setBounds(37, 50, 143, 18);
        panel_1.add(label_1);
        
        final JButton pageSetButton = new JButton();
        pageSetButton.setBounds(602, 43, 86, 28);
        panel_1.add(pageSetButton);
        pageSetButton.addActionListener(new ActionListener() {
            //
            /*
             * “页面设置”按钮的单击事件
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // 获取PrinterJob类的实例
                pf = job.pageDialog(pf); // 打开页面设置对话框
                isPreview = true; // 表示可以打印
                canvas.repaint(); // 重绘画面
            }
        });
        pageSetButton.setText("页面设置");
        
        final JButton printButton = new JButton();
        printButton.setBounds(694, 43, 86, 28);
        panel_1.add(printButton);
        printButton.addActionListener(new ActionListener() {
            /*
             * “开始打印”按钮的单击事件
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
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
                            return Printable.PAGE_EXISTS; // 返回PAGE_EXISTS
                        } else {
                            return Printable.NO_SUCH_PAGE; // 返回NO_SUCH_PAGE
                        }
                    }
                    
                });
                job.setJobName("打印相片"); // 设置打印文档的名称
                try {
                    job.print(); // 开始打印
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("开始打印");
        
        final ImgLabel imgLabel3 = new ImgLabel();
        imgLabel3.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠标左键
                    border = "border3"; // 设置边框类型
                    if (imgFile != null) {
                        isPreview = true; // 表示可以打印
                        canvas.repaint(); // 重绘画布
                    } else {
                        JOptionPane.showMessageDialog(null, "请先选择要打印的图片！");
                    }
                }
            }
        });
        imgLabel3.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border3.png"));
        imgLabel3.setBounds(393, 42, 86, 35);
        panel_1.add(imgLabel3);
        
        final ImgLabel imgLabel4 = new ImgLabel();
        imgLabel4.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠标左键
                    border = "border4"; // 设置边框类型
                    if (imgFile != null) {
                        isPreview = true; // 表示可以打印
                        canvas.repaint(); // 重绘画布
                    } else {
                        JOptionPane.showMessageDialog(null, "请先选择要打印的图片！");
                    }
                }
            }
        });
        imgLabel4.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border4.PNG"));
        imgLabel4.setText("New ImgLabel");
        imgLabel4.setBounds(497, 42, 86, 35);
        panel_1.add(imgLabel4);
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        // TODO Auto-generated method stub
        printPicture(graphics, pageFormat, pageIndex); // 绘制打印内容
        return Printable.PAGE_EXISTS; // 返回PAGE_EXISTS
    }
    
    // 画布
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g; // 获取Graphics2D对象
                g2.translate(10, 10); // 将原点坐标平移10个象素
                int x = (int) (pf.getImageableX() - 1); // 获取可打印区域起始点的x轴坐标
                int y = (int) (pf.getImageableY() - 1); // 获取可打印区域起始点的y轴坐标
                int width = (int) (pf.getImageableWidth() + 1); // 获取可打印区域结束点的x轴坐标
                int height = (int) (pf.getImageableHeight() + 1); // 获取可打印区域结束点的y轴坐标
                int mw = (int) pf.getWidth(); // 获取页面宽度
                int mh = (int) pf.getHeight();// 获取页面高度
                g2.drawRect(0, 0, mw, mh);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f)); // 设置线条的样式
                g2.drawRect(x, y, width, height); // 绘制矩形边框
                g2.setColor(Color.WHITE); // 设置前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1); // 绘制白色背景的矩形
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
            double imgWidth = src.getWidth(this); // 获取图片的宽
            double imgHeight = src.getHeight(this); // 获取图片的高
            double percent = imgWidth / imgHeight; // 长宽比例
            int mw = (int) pf.getWidth() - x * 2; // 计算可打印区域的宽
            int mh = (int) pf.getHeight() - y * 2; // 计算可打印区域的高
            if (imgWidth > mw) { // 如果宽大于可打印区域
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // 如果高大于可打印区域
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // 绘制正常图像，如图片、照片等
            /********************* 绘制边框 *************************/
            if (!border.equals("")) {
                ImageIcon icon = SwingResourceManager.getIcon(MainFrame.class,
                        "/com/zzk/" + border + ".png"); // 获取ImageIcon对象
                Image borderImg = icon.getImage(); // 获取用于绘制边框的Image对象
                g2.drawImage(borderImg, x, y, (int) imgWidth, (int) imgHeight,
                        this); // 绘制边框
            }
            /*****************************************************/
        }
        isPreview = false; // 设置不可以打印
    }
}
