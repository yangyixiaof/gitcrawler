package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PrintLineChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
    ChartPanel chartPanel = null;
    private Robot robot = null; // 声明Robot对象
    private BufferedImage buffImage = null; // 声明缓冲图像对象
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintLineChartFrame frame = new PrintLineChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintLineChartFrame() {
        super();
        setTitle("打印折线图表");
        setBounds(0, 0, 500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        getContentPane().add(chartPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JButton button = new JButton();
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
                    if (!job.printDialog()) // 打开打印对话框
                        return;// 单击打印对话框的取消按钮或关闭打印对话框结束程序的执行
                    job.setPrintable(new Printable() {
                        // 实现print()方法，绘制打印内容
                        public int print(Graphics graphics, PageFormat pf,
                                int pageIndex) throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // 获得图形上下文对象
                            int x = (int) (PrintLineChartFrame.this.getBounds()
                                    .getX()) + 8;// 背景面板在屏幕上的X坐标
                            int y = (int) (PrintLineChartFrame.this.getBounds()
                                    .getY()) + 30;// 背景面板在屏幕上的Y坐标
                            int w = (int) chartPanel.getBounds().getWidth();// 图表的宽度
                            int h = (int) chartPanel.getBounds().getHeight();// 图表的高度
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
                                imgHeight = (int) (imgHeight * wh);
                            }
                            if (imgHeight > height) { // 如果高大于可打印区域
                                imgHeight = height;
                                imgWidth = (int) (imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth,
                                    imgHeight, PrintLineChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("打印折线图表"); // 设置打印任务的名称
                    job.print(); // 调用print()方法开始打印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打    印");
        button.setBounds(28, 56, 86, 28);
        
        final JButton button_1 = new JButton();
        panel.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        button_1.setBounds(151, 56, 86, 28);
        
        //
    }
    
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();// 创建数据集对象
        ResultSet rs = QueryResultSet.gainRecord();// 获得查询结果集
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// 获得平均年龄
                String sex = rs.getString(2);// 获得性别
                String address = rs.getString(3);// 获得地址
                dataset.addValue(value, sex, address);// 向数据集添加数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart("\n\n统计来自各城市男女员工的平均年龄", // 图表的标题文本
                "所在城市       ", // x轴上的标签文字
                "平均年龄", // y轴上的标签文字
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 垂直方向
                true, // 显示图例
                true, // 显示说明文字
                false // 不生成链接
                );
        chart.setBackgroundPaint(Color.white);// 设置背景颜色
        CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图表的CategoryPlot对象
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();// 获得y轴的实例
        rangeAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// 设置y轴显示值的字体
        rangeAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));// 设置y轴上标签文字的字体
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// 设置y轴显示标准的整数单元
        CategoryAxis domainAxis = plot.getDomainAxis();// 获得x轴的实例
        domainAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// 设置x轴显示信息的字体
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置x轴上标签文字的字体
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 6.0));// 指定x轴上显示信息的位置
        TextTitle textTitle = chart.getTitle();// 获得标题对象
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));// 设置标题的字体
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));// 设置图例文本的字体
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);// 设置图例的对齐方式
        return chart;
    }
}
