package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();// �����������
        AWTUtilities.setWindowOpacity(frame, 0.8f); // ���ô���80%͸��
        frame.setVisible(true);// ��ʾ����
    }
    
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private Image src;
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        getContentPane().setBackground(new Color(70, 130, 180));
        setTitle("͸���Ĵ�ӡԤ���Ի���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 750));
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
                if (imgFile != null) {
                    isPreview = true; // ��ʾ���Դ�ӡ
                    PrinterJob job = PrinterJob.getPrinterJob();
                    pf = job.pageDialog(pf);
                    canvas.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡ��ͼƬ��");
                }
            }
        });
        previewButton.setText("��ӡԤ��");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // ��ȡPrinterJob�����ʵ��
                if (!job.printDialog())
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
                job.setJobName("��ӡͼ��");
                try {
                    job.print();
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("��ʼ��ӡ");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("��ѡ��Ҫ��ӡ��ͼƬ��");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // ѡ���ļ���ť
        final JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");
                fileChooser.setFileFilter(filter);
                int i = fileChooser.showOpenDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ�е�ͼƬ����
                    filePath.setText(imgFile.getAbsolutePath()); // ��ʾͼƬ·��
                }
            }
        });
        selectButton.setText("ѡ���ļ�");
        panel_1.add(selectButton);
        // setGlassPane(getContentPane());
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
        return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
    }
    
    // ����
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);
                int x = (int) (pf.getImageableX() - 1);
                int y = (int) (pf.getImageableY() - 1);
                int width = (int) (pf.getImageableWidth() + 1);
                int height = (int) (pf.getImageableHeight() + 1);
                int mw = (int) pf.getWidth();
                int mh = (int) pf.getHeight();
                g2.setColor(new Color(255, 253, 234)); // ����ǰ��ɫ
                g2.fillRect(1, 1, mw - 1, mh - 1);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));
                g2.setColor(Color.BLACK); // ����ǰ��ɫ
                g2.drawRect(x, y, width, height);
                g2.setColor(Color.WHITE); // ����ǰ��ɫ
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);
                MainFrame.this.print(g, pf, 0);
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
        int x = (int) pageFormat.getImageableX(); // ��ȡ�ɴ�ӡ���������Xλ��
        int y = (int) pageFormat.getImageableY(); // ��ȡ�ɴ�ӡ���������Yλ��
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// ����Image����
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            double imgWidth = src.getWidth(this);
            double imgHeight = src.getHeight(this);
            double percent = imgWidth / imgHeight; // �������
            int mw = (int) pf.getWidth() - x * 2;
            int mh = (int) pf.getHeight() - y * 2;
            if (imgWidth > mw) { // �������ڿɴ�ӡ����
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // ����ߴ��ڿɴ�ӡ����
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ��������ͼ��
        }
        isPreview = false; // ���ò����Դ�ӡ
    }
}
