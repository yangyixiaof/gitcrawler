package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PrintDesktop extends JFrame {
    private PrinterJob job;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintDesktop frame = new PrintDesktop();
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
    public PrintDesktop() {
        super();
        setTitle("打印桌面图片");
        job = PrinterJob.getPrinterJob();
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(scrollPane);
        
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        flowLayout.setHgap(30);
        panel_1.setLayout(flowLayout);
        scrollPane.setViewportView(panel_1);
        
        final PrintPanel printPanel = new PrintPanel();
        printPanel.setBorder(new LineBorder(Color.ORANGE, 2, false));
        printPanel.setName("printPanel");
        panel_1.add(printPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton getDesktopBtn = new JButton();
        getDesktopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    Robot robot = new Robot();// 创建Robot类的实例对象
                    Dimension size = getToolkit().getScreenSize();// 获取屏幕大小
                    Rectangle screenRect = new Rectangle(0, 0, size.width,
                            size.height);// 创建屏幕大小的矩形对象
                    setVisible(false);// 隐藏窗体
                    Thread.sleep(1000);// 休眠1秒钟
                    BufferedImage image = robot.createScreenCapture(screenRect);// 抓起屏幕图像
                    setVisible(true);// 显示窗体
                    printPanel.setImage(image);// 为打印面板设置图像
                    printPanel.repaint();// 重新绘制打印面板
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getDesktopBtn.setText("获取桌面");
        panel.add(getDesktopBtn);
        
        final JButton pageSetBtn = new JButton();
        pageSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (job != null) {// 如果打印对象不为NULL
                    printPanel.pageSet(job);// 调用打印面板的页面设置方法
                }
            }
        });
        pageSetBtn.setText("页面设置");
        panel.add(pageSetBtn);
        
        final JButton printBtn = new JButton();
        printBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean print = job.printDialog();// 显示打印对话框
                if (print) {// 如果用户确认打印
                    try {
                        job.setJobName("打印桌面图片");// 设置打印任务名称
                        job.setPrintable(printPanel);// 设置打印页面为打印面板
                        job.print();// 开始打印任务
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        printBtn.setText("打印");
        panel.add(printBtn);
        //
    }
    
}
