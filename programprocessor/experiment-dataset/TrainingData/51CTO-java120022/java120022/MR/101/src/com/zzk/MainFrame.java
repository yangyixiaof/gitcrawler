package com.zzk;

import java.awt.AlphaComposite;
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
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    
    private JTextField watermarkText;
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
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
    
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private BufferedImage src;
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    private String watermarkWord = "���տƼ�"; // ˮӡ����
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// �����С�ı�ʱִ��
                // ��ӡԤ��
                if (imgFile != null) {
                    isPreview = true; // ��ʾ���Դ�ӡ
                    canvas.repaint();// ����paint()����
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("�Զ�Ϊ��ӡ�������ˮӡ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 770));
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
                    watermarkWord = watermarkText.getText(); // ��ȡ�����ˮӡ����
                    PrinterJob job = PrinterJob.getPrinterJob();// ��ô�ӡ����
                    pf = job.pageDialog(pf);// ��ʾ�޸�PageFormatʵ���ĶԻ���
                    canvas.repaint();// ����paint()����
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
                if (!job.printDialog())// �򿪴�ӡ�Ի���
                    return;
                // ���ô�ӡ����
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // ���ÿ��Դ�ӡ
                        if (pageIndex < 1) {
                            watermarkWord = watermarkText.getText(); // ��ȡ�������ӡ����
                            printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("��ӡͼ��");
                try {
                    job.print();// ����print()������ʵ�ִ�ӡ
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("��ʼ��ӡ");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel_1.setLayout(null);
        panel_1.setPreferredSize(new Dimension(0, 70));
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("��ѡ��Ҫ��ӡ��ͼƬ��");
        label.setBounds(34, 15, 130, 18);
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(500, 24));
        filePath.setBounds(165, 10, 500, 24);
        panel_1.add(filePath);
        
        final JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// ����������
                fileChooser.setFileFilter(filter);// ���ù�����
                int i = fileChooser.showOpenDialog(getContentPane());// ��ʾ�򿪶Ի���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ�е�ͼƬ����
                    filePath.setText(imgFile.getAbsolutePath()); // ��ʾͼƬ·��
                }
            }
        });
        selectButton.setText("ѡ���ļ�");
        selectButton.setBounds(680, 10, 86, 28);
        panel_1.add(selectButton);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("������ˮӡ���֣�");
        label_1.setBounds(60, 40, 104, 18);
        panel_1.add(label_1);
        
        watermarkText = new JTextField();
        watermarkText.setPreferredSize(new Dimension(500, 24));
        watermarkText.setBounds(165, 40, 500, 24);
        panel_1.add(watermarkText);
        // ѡ���ļ���ť
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        // TODO Auto-generated method stub
        
        printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
        
        return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
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
                g2.setColor(new Color(255, 253, 234)); // ����ǰ��ɫ
                g2.fillRect(1, 1, mw - 1, mh - 1);// �����������
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// ָ������ģʽ
                g2.setColor(Color.BLACK); // ����ǰ��ɫ
                g2.drawRect(x, y, width, height);// ���������ڿ�
                g2.setColor(Color.WHITE); // ����ǰ��ɫ
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
        int x = (int) pageFormat.getImageableX(); // ��ȡ�ɴ�ӡ���������Xλ��
        int y = (int) pageFormat.getImageableY(); // ��ȡ�ɴ�ӡ���������Yλ��
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// ����BufferedImage����
            } catch (IOException e) {
                e.printStackTrace();
            }
            double imgWidth = src.getWidth(this);// ���ͼ��Ŀ��
            double imgHeight = src.getHeight(this);// ���ͼ��ĸ߶�
            int wordSize = (int) imgWidth / 10;
            int mw = (int) pf.getWidth() - x * 2;
            int mh = (int) pf.getHeight() - y * 2;
            if (imgWidth > mw) { // �������ڿɴ�ӡ����
                imgWidth = mw;
            }
            if (imgHeight > mh) { // ����ߴ��ڿɴ�ӡ����
                imgHeight = mh;
            }
            /*********************** ���ˮӡ���� ****************************/
            Graphics2D g = src.createGraphics(); // ��ȡͼƬ��ͼ������
            Font font = new Font("����", Font.BOLD, wordSize); // �����������
            g.setFont(font); // ���û�ͼ����
            g.setPaint(Color.RED); // ���û�ͼ��ɫ
            // ��ȡ����ռ�õ���������
            Rectangle2D rec = font.getStringBounds(watermarkWord, g
                    .getFontRenderContext());
            double pw = rec.getWidth(); // ��ȡˮӡ����ռ�õ����ؿ��
            double ph = rec.getHeight(); // ��ȡˮӡ����ռ�õ����ظ߶�
            g.rotate(Math.toRadians(30), wordSize + pw / 2, wordSize + ph / 2); // ת���Ƕ�
            g.setComposite(AlphaComposite.SrcOver.derive(0.4f));// ����ˮӡ͸���ϳɹ���
            g.drawString(watermarkWord, wordSize * 2, wordSize * 2 + (int) ph); // ��������ˮӡ
            /***************************************************************/
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ����ͼ��
        }
        isPreview = false; // ���ò����Դ�ӡ
    }
}
