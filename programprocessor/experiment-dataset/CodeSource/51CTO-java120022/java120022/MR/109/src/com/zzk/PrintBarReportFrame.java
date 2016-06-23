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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class PrintBarReportFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    private boolean previewFlag = false; // �Ƿ��Ѿ�����Ԥ��
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintBarReportFrame frame = new PrintBarReportFrame();
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
    public PrintBarReportFrame() {
        super();
        
        CategoryDataset dataset = createDataset();// �������ݼ�����
        JFreeChart chart = createChart(dataset);// ����ͼ�����
        String path = System.getProperty("user.dir")+"/src/chartImg/chart.jpg";// ͼ��Ĵ洢λ��
        try {
            ChartUtilities.writeChartAsJPEG(new FileOutputStream(path), chart, 450, 360);// ��ͼ��洢ΪͼƬ
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(final WindowEvent e) {
                if (previewFlag) {
                    isPreview = true;// ����Ϊ���Դ�ӡ
                    canvas.repaint();// ����paint()����
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// �����С�ı�ʱִ��
                if (previewFlag) {
                    isPreview = true;// ����Ϊ���Դ�ӡ
                    canvas.repaint();// ����paint()����
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("��ӡ������ͼ��ı���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        //pf.setOrientation(PageFormat.LANDSCAPE);// ��ӡ��ʼ��,Ҳ���Ǵ�ӡ����
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
                // ��ӡԤ��
                if (QueryResultSet.gainRecord() != null) {
                    isPreview = true;// ��ʾ���Դ�ӡ
                    previewFlag = true;// ����Ϊ�Ѿ���ӡԤ��
                    PrinterJob job = PrinterJob.getPrinterJob();// ��ô�ӡ����
                    pf = job.pageDialog(pf);// ��ʾ�޸�PageFormatʵ���ĶԻ���
                    canvas.repaint();// ����paint()����
                } 
            }
        });
        previewButton.setText("Ԥ������");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!previewFlag){
                    return;
                }
                PrinterJob job = PrinterJob.getPrinterJob(); // ��ȡPrinterJob�����ʵ��
                if (!job.printDialog())// �򿪴�ӡ�Ի���
                    return;
                // ���ô�ӡ����
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // ���ÿ��Դ�ӡ
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("��ӡ����");
                try {
                    job.print();// ����print()������ʵ�ִ�ӡ
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        printButton.setText("��ӡ����");
        panel.add(printButton);
    }
    
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();// �������ݼ�����
        ResultSet rs = QueryResultSet.gainRecord();// ��ò�ѯ�����
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// �������
                String sex = rs.getString(2);// ����Ա�
                String address = rs.getString(3);// ��õ�ַ
                dataset.addValue(value, sex, address);// �����ݼ������������Ϣ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;// �������ݼ�����
    }
    
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("\n\nͳ�����Ը�������ŮԱ����ƽ������", // ͼ��ı����ı�
                "���ڳ���       ", // x���ϵı�ǩ����
                "ƽ������", // y���ϵı�ǩ����
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ��ֱ����
                true, // ��ʾͼ��
                true, // ��ʾ˵������
                false // ����������
                );
        chart.setBackgroundPaint(Color.white);// ���ñ�����ɫ
        CategoryPlot plot = (CategoryPlot) chart.getPlot();// ���ͼ���CategoryPlot����
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();// ���y���ʵ��
        rangeAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// ����y����ʾֵ������
        rangeAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// ����y���ϱ�ǩ���ֵ�����
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// ����y����ʾ��׼��������Ԫ
        BarRenderer renderer = (BarRenderer) plot.getRenderer();// ������ε���������
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
                0.0f, new Color(0, 0, 64));// ��������ɫ����
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
                0.0f, new Color(0, 64, 0));// ��������ɫ����
        renderer.setSeriesPaint(0, gp0);// ָ��������ɫ
        renderer.setSeriesPaint(1, gp1);// ָ��������ɫ
        CategoryAxis domainAxis = plot.getDomainAxis();// ���x���ʵ��
        domainAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// ����x����ʾ��Ϣ������
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// ����x���ϱ�ǩ���ֵ�����
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 6.0));// ָ��x������ʾ��Ϣ��λ��
        TextTitle textTitle = chart.getTitle();// ��ñ������
        textTitle.setFont(new Font("����", Font.PLAIN, 20));// ���ñ��������
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));// ����ͼ���ı�������
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);// ����ͼ���Ķ��뷽ʽ
        return chart;
    }
    
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex);// ���ƴ�ӡ����
        return Printable.PAGE_EXISTS;// ����PAGE_EXISTS
    }
    
    // ����
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// ƽ�ƻ�ͼ������
                int x = (int) (pf.getImageableX() - 1);// ��ÿɴ�ӡ�����x����ƫ�����ڻ�������
                int y = (int) (pf.getImageableY() - 1);// ��ÿɴ�ӡ�����y����ƫ�ϣ����ڻ�������
                int width = (int) (pf.getImageableWidth() + 1);// ��ÿɴ�ӡ����Ŀ��ƫ�ң����ڻ�������
                int height = (int) (pf.getImageableHeight() + 1);// ��ÿɴ�ӡ����ĸ߶�ƫ�£����ڻ�������
                int mw = (int) pf.getWidth();// ��ô�ӡҳ�Ŀ��
                int mh = (int) pf.getHeight();// ��ô�ӡҳ�ĸ߶�
                g2.drawRect(0, 0, mw, mh);// ����ʵ�����
                g2.setColor(new Color(255, 253, 234));// ����ǰ��ɫ
                g2.fillRect(1, 1, mw - 1, mh - 1);// �����������
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// ָ������ģʽ
                g2.setColor(Color.BLACK);// ����ǰ��ɫ
                g2.drawRect(x, y, width, height);// ���������ڿ�
                g2.setColor(Color.WHITE);// ����ǰ��ɫ
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// �����������
                PrintBarReportFrame.this.print(g, pf, 0);// ����print()����
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ���ƴ�ӡ����
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX();// ��ÿɴ�ӡ���������Xλ��
        int y = (int) pageFormat.getImageableY();// ��ÿɴ�ӡ���������Yλ��
        int w = (int) pageFormat.getImageableWidth();// ��ÿɴ�ӡ����Ŀ��
        Graphics2D g2 = (Graphics2D) graphics;// ת��ΪGraphics2D����
        ResultSet rs = QueryResultSet.gainReport();// ��ò�ѯ�����
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// ����Ϊ��ɫ
            g2.setFont(new Font("�����п�",Font.BOLD,40));// ��������
            g2.drawString("Ա��������Ϣ����", x + 60, y + 50);// ���Ʊ������
            g2.setColor(Color.BLACK);// ����Ϊ��ɫ
            g2.setFont(new Font("����",Font.PLAIN,12));// ��������
            g2.setStroke(new BasicStroke(1f));// ָ��ʵ��ģʽ
            try {
                y = y + 80;// ������ӡλ�õ�yֵ
                int y1 = y;// ����������ӡλ�õ�yֵ
                /**************���Ʊ���ı���*******************/
                g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// ����ˮƽֱ��
                g2.drawString("���", x + 20, y);// ���Ʊ�������
                g2.drawString("����", x + 60, y);// ���Ʊ�������
                g2.drawString("�Ա�", x + 110, y);// ���Ʊ�������
                g2.drawString("����", x + 150, y);// ���Ʊ�������
                g2.drawString("��ϵ��ַ", x + 190, y);// ���Ʊ�������
                g2.drawString("��������", x + 290, y);// ���Ʊ�������
                g2.drawString("�绰����", x + 360, y);// ���Ʊ�������
                g2.drawLine(x + 10, y1 - 20, x + 10, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 50, y1 - 20, x + 50, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 100, y1 - 20, x + 100, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 140, y1 - 20, x + 140, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 180, y1 - 20, x + 180, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 280, y1 - 20, x + 280, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 350, y1 - 20, x + 350, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + w - 10, y1 - 20, x + w - 10, y + 10);// ���ƴ�ֱֱ��
                /************************************************/
                while (rs.next()) {
                    y = y + 30;// ������ӡλ�õ�yֵ
                    g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// ����ˮƽֱ��
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// ���Ʊ�������
                    g2.drawString(rs.getString(2), x + 60, y);// ���Ʊ�������
                    g2.drawString(rs.getString(3), x + 110, y);// ���Ʊ�������
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 150, y);// ���Ʊ�������
                    g2.drawString(rs.getString(5), x + 190, y);// ���Ʊ�������
                    g2.drawString(rs.getString(6), x + 290, y);// ���Ʊ�������
                    g2.drawString(rs.getString(7), x + 360, y);// ���Ʊ�������
                }
                g2.drawLine(x + 10, y1 + 10, x + 10, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 100, y1 + 10, x + 100, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 140, y1 + 10, x + 140, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 180, y1 + 10, x + 180, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 280, y1 + 10, x + 280, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 350, y1 + 10, x + 350, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + w - 10, y1 + 10, x + w - 10, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 10, y + 30 - 20, x + w - 10, y + 30 - 20);// ����ˮƽֱ��
                /*********************����ͼ��ͼƬ*****************************/
                URL imgUrl = PrintBarReportFrame.class.getResource("/chartImg/chart.jpg");// ��ȡͼƬ��Դ��·��
                Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ��ͼƬ��ͼ�����
                g2.drawImage(img, x+10, y+20,420,300, this);// �ڴ�ӡҳ����ͼ��ͼƬ
                
                rs.close();// �رս����
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // ���ò����Դ�ӡ
        previewFlag = true;
    }
}
