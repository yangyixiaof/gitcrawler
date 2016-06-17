package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PrintReverseTextFrame extends JFrame {
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
                    PrintReverseTextFrame frame = new PrintReverseTextFrame();
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
    public PrintReverseTextFrame() {
        super();
        setTitle("镜面效果文本打印");
        job = PrinterJob.getPrinterJob();
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(scrollPane);
        
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);
        panel_1.setLayout(flowLayout);
        scrollPane.setViewportView(panel_1);
        
        final PrintPanel printPanel = new PrintPanel();
        printPanel.setLayout(null);
        panel_1.add(printPanel);
        printPanel.setBorder(new LineBorder(Color.ORANGE, 2, false));
        printPanel.setName("printPanel");
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JToggleButton reverseBtn = new JToggleButton();
        reverseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean reverse = reverseBtn.isSelected();
                printPanel.setReverse(reverse);// 重新绘制打印面板
            }
        });
        reverseBtn.setText("镜面效果/还原");
        panel.add(reverseBtn);
        
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
