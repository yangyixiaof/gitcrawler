package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
public class PrintControlFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintControlFrame frame = new PrintControlFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintControlFrame() {
        super();
        setTitle("实现打印");
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
                                return Printable.NO_SUCH_PAGE;// 返回该值表示没有打印页
                            Graphics2D g2 = (Graphics2D) graphics; // 获得图形上下文对象
                            g2.setColor(Color.BLUE); // 设置当前绘图颜色
                            Font font = new Font("宋体", Font.BOLD, 24);
                            g2.setFont(font); // 设置字体
                            g2.drawString("静夜思", 80, 40); // 绘制文本
                            font = new Font("宋体", Font.BOLD | Font.ITALIC, 18);
                            g2.setFont(font); // 设置字体
                            g2.drawString("李白", 130, 70); // 绘制文本
                            font = new Font("宋体", Font.PLAIN, 14);
                            g2.setFont(font); // 设置字体
                            g2.drawString("床前明月光，", 40, 100); // 绘制文本
                            g2.drawString("疑是地上霜。", 120, 100); // 绘制文本
                            g2.drawString("举头望明月，", 40, 120); // 绘制文本
                            g2.drawString("低头思故乡。", 120, 120); // 绘制文本
                            return Printable.PAGE_EXISTS;// 返回该值表示存在打印页
                        }
                    });
                    job.setJobName("打印唐诗《静夜思》"); // 设置打印任务的名称
                    job.print(); // 调用print()方法开始打印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打    印");
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
