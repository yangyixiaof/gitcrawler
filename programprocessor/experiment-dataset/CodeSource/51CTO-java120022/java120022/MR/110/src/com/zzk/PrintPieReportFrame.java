package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class PrintPieReportFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // 是否可以打印
    private boolean previewFlag = false; // 是否已经进行预览
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintPieReportFrame frame = new PrintPieReportFrame();
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
    public PrintPieReportFrame() {
        super();
        PieDataset dataset = createDataset();// 创建数据集对象
        JFreeChart chart = createChart(dataset);// 创建图表对象
        String path = System.getProperty("user.dir")
                + "/src/chartImg/chart.jpg";// 图表的存储位置
        try {
            ChartUtilities.writeChartAsJPEG(new FileOutputStream(path), chart,
                    450, 360);// 将图表存储为图片
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(final WindowEvent e) {
                if (previewFlag) {
                    isPreview = true;// 设置为可以打印
                    canvas.repaint();// 调用paint()方法
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// 窗体大小改变时执行
                if (previewFlag) {
                    isPreview = true;// 设置为可以打印
                    canvas.repaint();// 调用paint()方法
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("打印带饼形图表的报表");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        // pf.setOrientation(PageFormat.LANDSCAPE);// 打印起始点,也就是打印方向
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(633, 931));
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
                if (QueryResultSet.gainRecord() != null) {
                    isPreview = true;// 表示可以打印
                    previewFlag = true;// 设置为已经打印预览
                    PrinterJob job = PrinterJob.getPrinterJob();// 获得打印对象
                    pf = job.pageDialog(pf);// 显示修改PageFormat实例的对话框
                    canvas.repaint();// 调用paint()方法
                }
            }
        });
        previewButton.setText("预览报表");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!previewFlag) {
                    return;
                }
                PrinterJob job = PrinterJob.getPrinterJob(); // 获取PrinterJob对象的实例
                if (!job.printDialog())// 打开打印对话框
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
                job.setJobName("打印报表");
                try {
                    job.print();// 调用print()方法，实现打印
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        printButton.setText("打印报表");
        panel.add(printButton);
    }
    
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();// 创建数据集对象
        ResultSet rs = QueryResultSet.gainRecord();// 获得查询结果集
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// 获得平均年龄
                String sex = rs.getString(2);// 获得性别
                String address = rs.getString(3);// 获得地址
                dataset.setValue(address + "," + sex, value);// 设置数据集的值
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("\n\n统计来自各城市男女员工的平均年龄", // 图表的标题文本
                dataset, // 数据集
                true, // 显示图例
                true, // 显示说明文字
                false // 不生成链接
                );
        chart.setBackgroundPaint(Color.white);// 设置背景颜色
        chart.getTitle().setFont(new Font("黑体", Font.PLAIN, 20));// 设置标题字体
        chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 12));// 设置图例文本的字体
        PiePlot plot = (PiePlot) chart.getPlot();// 获得图表的PiePlot对象
        plot.setLabelFont(new Font("黑体", Font.PLAIN, 12));// 设置饼状图标签的字体
        return chart;
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex);// 绘制打印内容
        return Printable.PAGE_EXISTS;// 返回PAGE_EXISTS
    }
    
    // 画布
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// 平移绘图上下文
                int x = (int) (pf.getImageableX() - 1);// 获得可打印区域的x坐标偏左，用于绘制虚线
                int y = (int) (pf.getImageableY() - 1);// 获得可打印区域的y坐标偏上，用于绘制虚线
                int width = (int) (pf.getImageableWidth() + 1);// 获得可打印区域的宽度偏右，用于绘制虚线
                int height = (int) (pf.getImageableHeight() + 1);// 获得可打印区域的高度偏下，用于绘制虚线
                int mw = (int) pf.getWidth();// 获得打印页的宽度
                int mh = (int) pf.getHeight();// 获得打印页的高度
                g2.drawRect(0, 0, mw, mh);// 绘制实线外框
                g2.setColor(new Color(255, 253, 234));// 设置前景色
                g2.fillRect(1, 1, mw - 1, mh - 1);// 绘制填充区域
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虚线模式
                g2.setColor(Color.BLACK);// 设置前景色
                g2.drawRect(x, y, width, height);// 绘制虚线内框
                g2.setColor(Color.WHITE);// 设置前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// 绘制填充区域
                PrintPieReportFrame.this.print(g, pf, 0);// 调用print()方法
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
        int x = (int) pageFormat.getImageableX();// 获得可打印区域坐标的X位置
        int y = (int) pageFormat.getImageableY();// 获得可打印区域坐标的Y位置
        int w = (int) pageFormat.getImageableWidth();// 获得可打印区域的宽度
        Graphics2D g2 = (Graphics2D) graphics;// 转换为Graphics2D对象
        ResultSet rs = QueryResultSet.gainReport();// 获得查询结果集
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// 设置为蓝色
            g2.setFont(new Font("华文行楷", Font.BOLD, 40));// 设置字体
            g2.drawString("员工基本信息报表", x + 60, y + 50);// 绘制报表标题
            g2.setColor(Color.BLACK);// 设置为黑色
            g2.setFont(new Font("宋体", Font.PLAIN, 12));// 设置字体
            g2.setStroke(new BasicStroke(1f));// 指定实线模式
            try {
                y = y + 80;// 调整打印位置的y值
                int y1 = y;// 保存调整后打印位置的y值
                /************** 绘制报表的标题 *******************/
                g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// 绘制水平直线
                g2.drawString("编号", x + 20, y);// 绘制报表内容
                g2.drawString("姓名", x + 60, y);// 绘制报表内容
                g2.drawString("性别", x + 110, y);// 绘制报表内容
                g2.drawString("年龄", x + 150, y);// 绘制报表内容
                g2.drawString("联系地址", x + 190, y);// 绘制报表内容
                g2.drawString("邮政编码", x + 290, y);// 绘制报表内容
                g2.drawString("电话号码", x + 360, y);// 绘制报表内容
                g2.drawLine(x + 10, y1 - 20, x + 10, y + 10);// 绘制垂直直线
                g2.drawLine(x + 50, y1 - 20, x + 50, y + 10);// 绘制垂直直线
                g2.drawLine(x + 100, y1 - 20, x + 100, y + 10);// 绘制垂直直线
                g2.drawLine(x + 140, y1 - 20, x + 140, y + 10);// 绘制垂直直线
                g2.drawLine(x + 180, y1 - 20, x + 180, y + 10);// 绘制垂直直线
                g2.drawLine(x + 280, y1 - 20, x + 280, y + 10);// 绘制垂直直线
                g2.drawLine(x + 350, y1 - 20, x + 350, y + 10);// 绘制垂直直线
                g2.drawLine(x + w - 10, y1 - 20, x + w - 10, y + 10);// 绘制垂直直线
                /************************************************/
                while (rs.next()) {
                    y = y + 30;// 调整打印位置的y值
                    g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// 绘制水平直线
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// 绘制报表内容
                    g2.drawString(rs.getString(2), x + 60, y);// 绘制报表内容
                    g2.drawString(rs.getString(3), x + 110, y);// 绘制报表内容
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 150, y);// 绘制报表内容
                    g2.drawString(rs.getString(5), x + 190, y);// 绘制报表内容
                    g2.drawString(rs.getString(6), x + 290, y);// 绘制报表内容
                    g2.drawString(rs.getString(7), x + 360, y);// 绘制报表内容
                }
                g2.drawLine(x + 10, y1 + 10, x + 10, y + 10);// 绘制垂直直线
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// 绘制垂直直线
                g2.drawLine(x + 100, y1 + 10, x + 100, y + 10);// 绘制垂直直线
                g2.drawLine(x + 140, y1 + 10, x + 140, y + 10);// 绘制垂直直线
                g2.drawLine(x + 180, y1 + 10, x + 180, y + 10);// 绘制垂直直线
                g2.drawLine(x + 280, y1 + 10, x + 280, y + 10);// 绘制垂直直线
                g2.drawLine(x + 350, y1 + 10, x + 350, y + 10);// 绘制垂直直线
                g2.drawLine(x + w - 10, y1 + 10, x + w - 10, y + 10);// 绘制垂直直线
                g2.drawLine(x + 10, y + 30 - 20, x + w - 10, y + 30 - 20);// 绘制水平直线
                /********************* 绘制图表图片 *****************************/
                URL imgUrl = PrintPieReportFrame.class.getResource("/chartImg/chart.jpg");// 获取图片资源的路径
                Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图表图片的图像对象
                g2.drawImage(img, x + 10, y + 20, 420, 300, this);// 在打印页绘制图表图片
                
                rs.close();// 关闭结果集
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // 设置不可以打印
        previewFlag = true;
    }
}
