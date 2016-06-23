package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PrintShapeFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintShapeFrame frame = new PrintShapeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintShapeFrame() {
        super();
        setTitle("打印图形");
        getContentPane().setLayout(null);
        setBounds(100, 100, 281, 179);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    if (!job.printDialog()) // 打开打印对话框
                        return;// 单击打印对话框的取消按钮或关闭打印对话框结束程序的执行
                    job.setPrintable(new Printable() {
                        // 实现print()方法，绘制打印内容
                        public int print(Graphics graphics,
                                PageFormat pageFormat, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D)graphics; // 获得Graphics2D对象
                            BasicStroke stroke = new BasicStroke(3); // 创建宽度是3的笔画对象
                            g2.setStroke(stroke);// 设置笔画对象
                            Color color = new Color(0,162,232);// 创建颜色对象
                            g2.setColor(color);// 设置颜色
                            g2.drawOval(30, 40, 60, 60);     // 绘制第一个圆
                            color = new Color(233,123,16);   // 创建新的颜色对象
                            g2.setColor(color);// 设置颜色
                            g2.drawOval(60, 70, 60, 60);     // 绘制第二个圆
                            color = new Color(28,20,100);// 创建新的颜色对象
                            g2.setColor(color);// 设置颜色
                            g2.drawOval(92, 40, 60, 60);     // 绘制第三个圆
                            color = new Color(0,255,0);// 创建新的颜色对象
                            g2.setColor(color);// 设置颜色
                            g2.drawOval(122, 70, 60, 60);    // 绘制第四个圆
                            color = new Color(255,0,0);// 创建新的颜色对象
                            g2.setColor(color);// 设置颜色
                            g2.drawOval(154, 40, 60, 60);    // 绘制第五个圆
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("打印五环图形"); // 设置打印任务的名称
                    job.print(); // 调用print()方法开始打印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打印图形");
        button.setBounds(28, 56, 86, 28);
        getContentPane().add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        button_1.setBounds(151, 56, 86, 28);
        getContentPane().add(button_1);
        
        //
    }
    
}
