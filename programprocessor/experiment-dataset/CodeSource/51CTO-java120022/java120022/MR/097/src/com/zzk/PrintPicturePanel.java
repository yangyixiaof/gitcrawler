package com.zzk;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class PrintPicturePanel extends JPanel implements Printable {
    private JButton printButton;
    private JButton previewButton;
    private JPanel controlPanel;
    private File imgFile = null;
    private BufferedImage src;
    private PrinterJob job;
    private PageFormat pf;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("��ӡͼƬ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 280);
        PrintPicturePanel panel = new PrintPicturePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    
    public PrintPicturePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(getControlPanel(), BorderLayout.SOUTH);
        pf = new PageFormat();// ����PageFormat����
        pf.setOrientation(PageFormat.LANDSCAPE);// ���ô�ӡ����
        job = PrinterJob.getPrinterJob();// ��ô�ӡ����
    }
    
    private void drawPage(Graphics2D g2) {
        int imgW = 0;
        int imgH = 0;
        if (src != null) {
            imgW = src.getWidth();
            imgH = src.getHeight();
            if (imgW > getWidth()) {
                imgW = getWidth();
            }
            if (imgH > getHeight()) {
                imgH = getHeight();
            }
        }
        g2.drawImage(src, 0, 0, imgW, imgH, this);// ����ͼ��
    }
    
    protected JButton getPreviewButton() {
        if (previewButton == null) {
            previewButton = new JButton();
            previewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                    FileFilter filter = new FileNameExtensionFilter(
                            "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// ����������
                    fileChooser.setFileFilter(filter);// ���ù�����
                    int i = fileChooser.showOpenDialog(null);// ��ʾ�򿪶Ի���
                    if (i == JFileChooser.APPROVE_OPTION) {
                        imgFile = fileChooser.getSelectedFile(); // ��ȡѡ��ͼƬ��File����
                    }
                    if (imgFile != null) {
                        try {
                            src = ImageIO.read(imgFile);// ����BufferedImage����
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    PrintPicturePanel.this.repaint();// ����paintComponent()����
                }
            });
            previewButton.setText("ѡ��ͼƬ");
        }
        return previewButton;
    }
    
    protected JButton getPrintButton() {
        if (printButton == null) {
            printButton = new JButton();
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        job.setPrintable(PrintPicturePanel.this);// Ϊ��ӡ����ָ��Printable����
                        job.setJobName("��ӡͼƬ");// ���ô�ӡ���������
                        job.print();// ִ�д�ӡ
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            printButton.setText("��ӡ");
        }
        return printButton;
    }
    
    protected JPanel getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new JPanel();
            controlPanel.setBorder(new LineBorder(Color.BLUE, 1, false));
            final FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(20);
            controlPanel.setLayout(flowLayout);
            controlPanel.add(getPreviewButton());
            controlPanel.add(getPrintButton());
        }
        return controlPanel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0)
            return Printable.NO_SUCH_PAGE;// û�д�ӡҳ
        int x = (int) pageFormat.getImageableX();//��ÿɴ�ӡ�����x����
        int y = (int) pageFormat.getImageableY();//��ÿɴ�ӡ�����y����
        Graphics2D g2 = (Graphics2D) graphics;
        int ableW = (int) pageFormat.getImageableWidth();//��ÿɴ�ӡ����Ŀ��
        int ableH = (int) pageFormat.getImageableHeight();//��ÿɴ�ӡ����ĸ߶�
        int imgW = 0;// �����ӡͼƬ�Ŀ��
        int imgH = 0;// �����ӡͼƬ�ĸ߶�
        if (src != null) {
            imgW = src.getWidth();// ���ͼƬ�Ŀ��
            imgH = src.getHeight();// ���ͼƬ�ĸ߶�
            if (imgW > ableW) {// ͼƬ��ȴ��ڴ�ӡ����Ŀ��
                imgW = ableW;// ͼƬ���Ϊ��ӡ����Ŀ��
            }
            if (imgH > ableH) {// ͼƬ�߶ȴ��ڴ�ӡ����ĸ߶�
                imgH = ableH;// ͼƬ�߶�Ϊ��ӡ����ĸ߶�
            }
        }
        g2.drawImage(src, x, y, imgW, imgH, this);// ���ƴ�ӡ����
        return Printable.PAGE_EXISTS;// ���ش��ڴ�ӡ���ݵ���Ϣ
    }
}
