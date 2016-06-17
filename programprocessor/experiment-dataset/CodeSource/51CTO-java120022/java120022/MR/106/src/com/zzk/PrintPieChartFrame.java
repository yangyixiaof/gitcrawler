package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class PrintPieChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
    ChartPanel chartPanel = null;
    private Robot robot = null; // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintPieChartFrame frame = new PrintPieChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintPieChartFrame() {
        super();
        setTitle("��ӡ����ͼ��");
        setBounds(0, 0, 500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PieDataset dataset = createDataset();
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
                            int x = (int) (PrintPieChartFrame.this.getBounds()
                                    .getX()) + 8;// �����������Ļ�ϵ�X����
                            int y = (int) (PrintPieChartFrame.this.getBounds()
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
                                    imgHeight, PrintPieChartFrame.this);
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
        
        //
    }
    
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();// �������ݼ�����
        ResultSet rs = QueryResultSet.gainRecord();// ��ò�ѯ�����
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// ���ƽ������
                String sex = rs.getString(2);// ����Ա�
                String address = rs.getString(3);// ��õ�ַ
                dataset.setValue(address + "," + sex, value);// �������ݼ���ֵ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("\n\nͳ�����Ը�������ŮԱ����ƽ������", // ͼ��ı����ı�
                dataset, // ���ݼ�
                true, // ��ʾͼ��
                true, // ��ʾ˵������
                false // ����������
                );
        chart.setBackgroundPaint(Color.white);// ���ñ�����ɫ
        chart.getTitle().setFont(new Font("����", Font.PLAIN, 20));// ���ñ�������
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));// ����ͼ���ı�������
        PiePlot plot = (PiePlot) chart.getPlot();// ���ͼ���PiePlot����
        plot.setLabelFont(new Font("����", Font.PLAIN, 12));// ���ñ�״ͼ��ǩ������
        return chart;
    }
}
