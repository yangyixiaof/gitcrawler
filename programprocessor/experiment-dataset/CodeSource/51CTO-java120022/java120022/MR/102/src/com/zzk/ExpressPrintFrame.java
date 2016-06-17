package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.zzk.panel.BackgroundPanel;

@SuppressWarnings("serial")
public class ExpressPrintFrame extends JFrame {
    private URL url = null;// 声明图片的URL
    private Image image = null;// 声明图像对象
    private BackgroundPanel backPanel = null;// 声明自定义背景面板对象
    private Robot robot = null; // 声明Robot对象
    private BufferedImage buffImage = null; // 声明缓冲图像对象
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExpressPrintFrame frame = new ExpressPrintFrame();
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
    public ExpressPrintFrame() {
        super();
        setTitle("打印快递单");
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        url = ExpressPrintFrame.class.getResource("/image/express.jpg"); // 获得图片的URL
        image = new ImageIcon(url).getImage(); // 创建图像对象
        backPanel = new BackgroundPanel(image);
        backPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {// 单击鼠标右键
                    int x = e.getX();// 获得鼠标位置的X坐标
                    int y = e.getY();// 获得鼠标位置的Y坐标
                    TargetTextField tf = new TargetTextField();// 创建自定义文本框的实例
                    tf.addMouseListener(tf);// 添加鼠标监听器
                    tf.addMouseMotionListener(tf);// 添加鼠标监听器
                    tf.addActionListener(tf);// 添加动作监听器
                    tf.setBounds(x, y, 147, 22);// 指定文本框的位置和大小
                    backPanel.add(tf);// 添加到背景面板上
                    tf.requestFocus();// 使文本框获得焦点
                }
            }
        });
        backPanel.setLayout(null);
        getContentPane().add(backPanel);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
                    if (!job.printDialog()) // 打开打印对话框
                        return;// 单击打印对话框的取消按钮或关闭打印对话框结束程序的执行
                    job.setPrintable(new Printable() {
                        // 实现print()方法，绘制打印内容
                        public int print(Graphics graphics,
                                PageFormat pf, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // 获得图形上下文对象
                            int x = (int)(ExpressPrintFrame.this.getBounds().getX())+8;// 背景面板在屏幕上的X坐标
                            int y = (int)(ExpressPrintFrame.this.getBounds().getY())+30;// 背景面板在屏幕上的Y坐标
                            int w = (int)backPanel.getBounds().getWidth();// 背景面板的宽度
                            int h = (int)backPanel.getBounds().getHeight();// 背景面板的高度
                            Rectangle rect = new Rectangle(x, y, w, h);// 创建Rectangle对象
                            buffImage = robot.createScreenCapture(rect);// 获得缓冲图像对象
                            int imgWidth = buffImage.getWidth();// 图像的宽度
                            int imgHeight = buffImage.getHeight();// 图像的高度
                            float wh = imgWidth / imgHeight;// 图像宽高比
                            int printX = (int) pf.getImageableX();// 获得可打印区域的x坐标
                            int printY = (int) pf.getImageableY();// 获得可打印区域的y坐标
                            int width = (int) pf.getImageableWidth();// 获得可打印区域的宽度
                            int height = (int) pf.getImageableHeight();// 获得可打印区域的高度
                            if (imgWidth > width) { // 如果宽大于可打印区域
                                imgWidth = width;
                                imgHeight = (int)(imgHeight * wh);
                            }
                            if (imgHeight > height) { // 如果高大于可打印区域
                                imgHeight = height;
                                imgWidth = (int)(imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth, imgHeight, ExpressPrintFrame.this);// 将缓冲图像绘制到打印页
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("打印快递单"); // 设置打印任务的名称
                    job.print(); // 调用print()方法开始打印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打印快递单");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
}
