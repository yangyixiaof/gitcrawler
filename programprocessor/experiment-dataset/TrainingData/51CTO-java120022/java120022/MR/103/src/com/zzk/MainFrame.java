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
 * @author ������
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    private boolean previewFlag = false; // �Ƿ��Ѿ�����Ԥ��
    
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
        setTitle("��ӡ����");
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
                MainFrame.this.print(g, pf, 0);// ����print()����
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
        ResultSet rs = QueryResultSet.gainRecord();// ��ò�ѯ�����
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// ����Ϊ��ɫ
            g2.setFont(new Font("�����п�",Font.BOLD,60));// ��������
            g2.drawString("Ա��������Ϣ����", x + 40, y + 70);// ���Ʊ������
            g2.setColor(Color.BLACK);// ����Ϊ��ɫ
            g2.setFont(new Font("����",Font.PLAIN,12));// ��������
            g2.setStroke(new BasicStroke(1f));// ָ��ʵ��ģʽ
            try {
                y = y + 80;// ������ӡλ�õ�yֵ
                int y1 = y;// ����������ӡλ�õ�yֵ
                while (rs.next()) {
                    y = y + 30;// ������ӡλ�õ�yֵ
                    g2.drawLine(x + 20, y - 20, x + w - 20, y - 20);// ����ˮƽֱ��
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// ���Ʊ�������
                    g2.drawString(rs.getString(2), x + 60, y);// ���Ʊ�������
                    g2.drawString(rs.getString(3), x + 120, y);// ���Ʊ�������
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 170, y);// ���Ʊ�������
                    g2.drawString(rs.getString(5), x + 220, y);// ���Ʊ�������
                    g2.drawString(rs.getString(6), x + 420, y);// ���Ʊ�������
                    g2.drawString(rs.getString(7), x + 480, y);// ���Ʊ�������
                }
                g2.drawLine(x + 20, y1 + 10, x + 20, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 110, y1 + 10, x + 110, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 160, y1 + 10, x + 160, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 210, y1 + 10, x + 210, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 410, y1 + 10, x + 410, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 470, y1 + 10, x + 470, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + w - 20, y1 + 10, x + w - 20, y + 10);// ���ƴ�ֱֱ��
                g2.drawLine(x + 20, y + 30 - 20, x + w - 20, y + 30 - 20);// ����ˮƽֱ��
                rs.close();// �رս����
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // ���ò����Դ�ӡ
        previewFlag = true;
    }
}
