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
 * @author ������
 */
@SuppressWarnings("serial")
public class PrintAreaChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
    ChartPanel chartPanel = null;
    private Robot robot = null; // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    
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
        setTitle("��ӡ����ͼ��");
        setBounds(0, 0, 820, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        XYDataset xydataset = createDataset();              // �������ݼ�����
        JFreeChart chart = createChart(xydataset);          // ����JFreeChart����
        
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
                    final PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
                    if (!job.printDialog()) // �򿪴�ӡ�Ի���
                        return;// ������ӡ�Ի����ȡ����ť��رմ�ӡ�Ի�����������ִ��
                    job.setPrintable(new Printable() {
                        // ʵ��print()���������ƴ�ӡ����
                        public int print(Graphics graphics, PageFormat pf,
                                int pageIndex) throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // ���ͼ�������Ķ���
                            int x = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getX()) + 8;// �����������Ļ�ϵ�X����
                            int y = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getY()) + 30;// �����������Ļ�ϵ�Y����
                            int w = (int) chartPanel.getBounds().getWidth();// ͼ��Ŀ��
                            int h = (int) chartPanel.getBounds().getHeight();// ͼ��ĸ߶�
                            Rectangle rect = new Rectangle(x, y, w, h);// ����Rectangle����
                            buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                            int imgWidth = buffImage.getWidth();// ͼ��Ŀ��
                            int imgHeight = buffImage.getHeight();// ͼ��ĸ߶�
                            float wh = imgWidth / imgHeight;// ͼ���߱�
                            int printX = (int) pf.getImageableX();// ��ÿɴ�ӡ�����x����
                            int printY = (int) pf.getImageableY();// ��ÿɴ�ӡ�����y����
                            int width = (int) pf.getImageableWidth();// ��ÿɴ�ӡ����Ŀ��
                            int height = (int) pf.getImageableHeight();// ��ÿɴ�ӡ����ĸ߶�
                            if (imgWidth > width) { // �������ڿɴ�ӡ����
                                imgWidth = width;
                                imgHeight = (int) (imgHeight * wh);
                            }
                            if (imgHeight > height) { // ����ߴ��ڿɴ�ӡ����
                                imgHeight = height;
                                imgWidth = (int) (imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth,
                                    imgHeight, PrintAreaChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("��ӡ����ͼ��"); // ���ô�ӡ���������
                    job.print(); // ����print()������ʼ��ӡ
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��    ӡ");
        button.setBounds(28, 56, 86, 28);
        final JButton button_1 = new JButton();
        panel.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        button_1.setBounds(151, 56, 86, 28);
    }
    
    
    private XYDataset createDataset() {
        long value = 0;
        Day day = new Day(1, 1, 2009);// ���2009��1��1�յ�Day����
        long seed = System.currentTimeMillis();// ��õ�ǰʱ��ĺ�����
        Random ran = new Random(seed);// �������������Ϊseed��Random����
        TimeSeries soft = new TimeSeries("���տƼ�ͼ��");// ����ʱ������
        for (int i = 0; i < 365; i++) {                 //���һ��365�������
            value += ran.nextInt() / 10000;// ����������
            soft.add(day, value);// �������
            day = (Day) day.next();// �����һ�����ڵ�Day����
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(soft);// �������ݼ�����
        return dataset;
    }

    @SuppressWarnings("deprecation")
    private JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 24));//���ñ�������
        standardChartTheme.setRegularFont(new Font("����", Font.BOLD, 14));//����ͼ��������
        standardChartTheme.setLargeFont(new Font("����", Font.BOLD, 18));//�������������
        ChartFactory.setChartTheme(standardChartTheme);//����������ʽ
        JFreeChart jfreechart = ChartFactory.createXYAreaChart(
                "���տƼ�ͼ�飬����û������ͳ��", // ͼ�����
                "��ͳ���·�", // X�����
                "�û������", // Y�����
                xydataset, // ��ͼ�����ݼ�
                PlotOrientation.VERTICAL, // ��������ͼ�ķ���Ϊ����
                false,      // �Ƿ���ʾͼ����ʶ
                true,       // �Ƿ���ʾ��ʾ��Ϣ
                false);     // �Ƿ�֧�ֳ�����
        jfreechart.setBackgroundPaint(Color.PINK); // ���ñ���
        XYPlot xyplot = (XYPlot) jfreechart.getPlot(); //���XYPlot����
        xyplot.setDomainGridlinePaint(Color.GREEN);// ����ͼ�������ߵ���ɫ
        xyplot.setDomainGridlineStroke(new BasicStroke(1f));// ���������ߵĴ�ϸ
        XYPlot plot = jfreechart.getXYPlot();// ��ȡͼ��Ļ�������
        // ����ָ����ʽ�����ڸ�ʽ����
        DateFormat format = new SimpleDateFormat("MM�·�");
        DateAxis domainAxis = new DateAxis("2009��ͳ���·�           ");// ����ʱ�������
        DateTickUnit dtu = new DateTickUnit(DateTickUnit.DAY, 29, format);
        domainAxis.setTickUnit(dtu);       // ���ú����ϵ�ʱ��̶ȵ���ʾ��ʽ
        domainAxis.setLowerMargin(0.0); // ����ͼ��հ�
        domainAxis.setUpperMargin(0.0); // ����ͼ��հ�
        domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 14)); // ������������
        domainAxis.setLabelFont(new Font("����", Font.ITALIC, 20));   // ���ú�������
        plot.setDomainAxis(domainAxis); // Ϊ��ͼ������Ӻ������
        plot.setForegroundAlpha(0.5f);// ����ǰ��͸����Ϊ50%
        return jfreechart;
    }
    
    /////
}
