package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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
    private JTextField filePath;
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private BufferedImage src;
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    
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
        setTitle("��ӡԤ��");
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
                    canvas.repaint();// ����paint()����
                } else {
                    JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡԤ����ͼƬ��");
                }
            }
        });
        previewButton.setText("��ӡԤ��");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        
        printButton.setText("��    ��");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel_1.setLayout(null);
        panel_1.setPreferredSize(new Dimension(0, 70));
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("��ѡ��Ҫ��ӡԤ����ͼƬ��");
        label.setBounds(30, 25, 156, 18);
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(500, 24));
        filePath.setBounds(195, 20, 466, 24);
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
        selectButton.setBounds(676, 20, 86, 28);
        panel_1.add(selectButton);
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
                MainFrame.this.print(g2, pf, 0);// ����print()����
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
            int mw = (int) pf.getWidth() - x * 2;// ֽ�ſ�ȼ�ȥ���ұ�Ե
            int mh = (int) pf.getHeight() - y * 2;// ֽ�Ÿ߶ȼ�ȥ���±�Ե
            if (imgWidth > mw) { // ���ͼ��Ŀ�ȴ��ڿɴ�ӡ����
                imgWidth = mw;
            }
            if (imgHeight > mh) { // ���ͼ��ĸ߶ȴ��ڿɴ�ӡ����
                imgHeight = mh;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ��������ͼ��
        }
        isPreview = false; // ���ò����Դ�ӡ
    }
}
