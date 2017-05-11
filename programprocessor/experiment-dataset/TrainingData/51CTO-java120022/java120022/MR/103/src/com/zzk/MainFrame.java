package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * @author 张振坤
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // 是否可以打印
    private boolean previewFlag = false; // 是否已经进行预览
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
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
    public MainFrame() {
        super();
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
        setTitle("打印报表");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(830, 704));
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
                if (!previewFlag){
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
                MainFrame.this.print(g, pf, 0);// 调用print()方法
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
        ResultSet rs = QueryResultSet.gainRecord();// 获得查询结果集
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// 设置为蓝色
            g2.setFont(new Font("华文行楷",Font.BOLD,60));// 设置字体
            g2.drawString("员工基本信息报表", x + 40, y + 70);// 绘制报表标题
            g2.setColor(Color.BLACK);// 设置为黑色
            g2.setFont(new Font("宋体",Font.PLAIN,12));// 设置字体
            g2.setStroke(new BasicStroke(1f));// 指定实线模式
            try {
                y = y + 80;// 调整打印位置的y值
                int y1 = y;// 保存调整后打印位置的y值
                while (rs.next()) {
                    y = y + 30;// 调整打印位置的y值
                    g2.drawLine(x + 20, y - 20, x + w - 20, y - 20);// 绘制水平直线
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// 绘制报表内容
                    g2.drawString(rs.getString(2), x + 60, y);// 绘制报表内容
                    g2.drawString(rs.getString(3), x + 120, y);// 绘制报表内容
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 170, y);// 绘制报表内容
                    g2.drawString(rs.getString(5), x + 220, y);// 绘制报表内容
                    g2.drawString(rs.getString(6), x + 420, y);// 绘制报表内容
                    g2.drawString(rs.getString(7), x + 480, y);// 绘制报表内容
                }
                g2.drawLine(x + 20, y1 + 10, x + 20, y + 10);// 绘制垂直直线
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// 绘制垂直直线
                g2.drawLine(x + 110, y1 + 10, x + 110, y + 10);// 绘制垂直直线
                g2.drawLine(x + 160, y1 + 10, x + 160, y + 10);// 绘制垂直直线
                g2.drawLine(x + 210, y1 + 10, x + 210, y + 10);// 绘制垂直直线
                g2.drawLine(x + 410, y1 + 10, x + 410, y + 10);// 绘制垂直直线
                g2.drawLine(x + 470, y1 + 10, x + 470, y + 10);// 绘制垂直直线
                g2.drawLine(x + w - 20, y1 + 10, x + w - 20, y + 10);// 绘制垂直直线
                g2.drawLine(x + 20, y + 30 - 20, x + w - 20, y + 30 - 20);// 绘制水平直线
                rs.close();// 关闭结果集
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // 设置不可以打印
        previewFlag = true;
    }
}
