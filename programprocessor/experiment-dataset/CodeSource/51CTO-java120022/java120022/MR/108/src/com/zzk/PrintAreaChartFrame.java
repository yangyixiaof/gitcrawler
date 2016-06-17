package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PrintAreaChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
    ChartPanel chartPanel = null;
    private Robot robot = null; // 声明Robot对象
    private BufferedImage buffImage = null; // 声明缓冲图像对象
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintAreaChartFrame frame = new PrintAreaChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintAreaChartFrame() {
        super();
        setTitle("打印区域图表");
        setBounds(0, 0, 820, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        XYDataset xydataset = createDataset();              // 创建数据集对象
        JFreeChart chart = createChart(xydataset);          // 创建JFreeChart对象
        
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
                            int x = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getX()) + 8;// 背景面板在屏幕上的X坐标
                            int y = (int) (PrintAreaChartFrame.this.getBounds()
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
                                    imgHeight, PrintAreaChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("打印区域图表"); // 设置打印任务的名称
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
    }
    
    
    private XYDataset createDataset() {
        long value = 0;
        Day day = new Day(1, 1, 2009);// 获得2009年1月1日的Day对象
        long seed = System.currentTimeMillis();// 获得当前时间的毫秒数
        Random ran = new Random(seed);// 创建随机数种子为seed的Random对象
        TimeSeries soft = new TimeSeries("明日科技图书");// 创建时间序列
        for (int i = 0; i < 365; i++) {                 //添加一年365天的数据
            value += ran.nextInt() / 10000;// 随机获得数据
            soft.add(day, value);// 添加数据
            day = (Day) day.next();// 获得下一个日期的Day对象
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(soft);// 创建数据集对象
        return dataset;
    }

    @SuppressWarnings("deprecation")
    private JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 24));//设置标题字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD, 14));//设置图例的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.BOLD, 18));//设置轴向的字体
        ChartFactory.setChartTheme(standardChartTheme);//设置主题样式
        JFreeChart jfreechart = ChartFactory.createXYAreaChart(
                "明日科技图书，年度用户满意度统计", // 图表标题
                "年统计月份", // X轴标题
                "用户满意度", // Y轴标题
                xydataset, // 制图的数据集
                PlotOrientation.VERTICAL, // 定义区域图的方向为纵向
                false,      // 是否显示图例标识
                true,       // 是否显示提示信息
                false);     // 是否支持超链接
        jfreechart.setBackgroundPaint(Color.PINK); // 设置背景
        XYPlot xyplot = (XYPlot) jfreechart.getPlot(); //获得XYPlot对象
        xyplot.setDomainGridlinePaint(Color.GREEN);// 设置图表网格线的颜色
        xyplot.setDomainGridlineStroke(new BasicStroke(1f));// 设置网格线的粗细
        XYPlot plot = jfreechart.getXYPlot();// 获取图表的绘制属性
        // 创建指定样式的日期格式对象
        DateFormat format = new SimpleDateFormat("MM月份");
        DateAxis domainAxis = new DateAxis("2009年统计月份           ");// 创建时间轴对象
        DateTickUnit dtu = new DateTickUnit(DateTickUnit.DAY, 29, format);
        domainAxis.setTickUnit(dtu);       // 设置横轴上的时间刻度的显示格式
        domainAxis.setLowerMargin(0.0); // 设置图表空白
        domainAxis.setUpperMargin(0.0); // 设置图表空白
        domainAxis.setTickLabelFont(new Font("黑体", Font.BOLD, 14)); // 设置轴标记字体
        domainAxis.setLabelFont(new Font("宋体", Font.ITALIC, 20));   // 设置横轴字体
        plot.setDomainAxis(domainAxis); // 为绘图属性添加横轴对象
        plot.setForegroundAlpha(0.5f);// 设置前景透明度为50%
        return jfreechart;
    }
    
    /////
}
