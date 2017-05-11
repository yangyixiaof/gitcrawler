package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * ��������������
 * 
 * @author ������
 */
public class PrintPanel extends JPanel implements Printable {
    
    /**
     * ����ͼƬ
     */
    private PageFormat pageFormat;// ��ӡ��ʽ����
    private JTextArea textArea;// �ı������
    private int px;// �ɴ�ӡ�����ˮƽ����
    private int py;// �ɴ�ӡ����Ĵ�ֱ����
    private int pwidth;// �ɴ�ӡ����Ŀ��
    private int pheight;// �ɴ�ӡ����ĸ߶�
    private boolean reverse = false;// �Ƿ�ת
    
    /**
     * ���췽��
     */
    public PrintPanel() {
        super();
        setLayout(null);
        pageFormat = new PageFormat();// ����ҳ���ʽ����
        pageFormat.setOrientation(PageFormat.LANDSCAPE);// ���ú���ҳ��
        textArea = new JTextArea();// �����ı������
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBounds(200, 5, 100, 54);
        textArea.setLineWrap(true);// �Զ�����
        textArea.setOpaque(false);
        // ���ó�ʶ�ı�
        textArea.setText("������������Ҫ��ӡ���ı�����������Ч����ť���Կ�Ч������ӡ��ť���Ե�ǰЧ����ӡ��");
        add(textArea);
        setVisible(true);// ��ʾ����
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// ����ԭ���������
        try {
            print(g, pageFormat, 0);// ���ô�ӡ��������������
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int print(Graphics g1, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        Graphics2D g = (Graphics2D) g1;
        px = (int) pageFormat.getImageableX();// ��ȡ�ɴ�ӡ�����x����
        py = (int) pageFormat.getImageableY();// ��ȡ�ɴ�ӡ�����y����
        pwidth = (int) pageFormat.getImageableWidth();// ��ȡ�ɴ�ӡ�Ŀ��
        pheight = (int) pageFormat.getImageableHeight();// ��ȡ�ɴ�ӡ�ĸ߶�
        textArea.setBounds(px, py, pwidth, pheight);// �����ı��������С
        int pageWidth = (int) pageFormat.getWidth();// ��ȡ��ӡҳ����
        int pageHeight = (int) pageFormat.getHeight();// ��ȡ��ӡҳ��߶�
        Dimension preferredSize = new Dimension(pageWidth, pageHeight);
        setPreferredSize(preferredSize);// ��������С
        getParent().doLayout();// ��д���ָ�����
        g.setColor(Color.WHITE);// ����ǰ��ɫΪ��ɫ
        g.fill3DRect(0, 0, pageWidth, pageHeight, true);// �������ӡҳ����ͬ��С�ľ���
        if (pageIndex < 1 && textArea != null && reverse) {// �����ǰ��ӡҳ��С��1���ҿ�������Ч��
            BufferedImage image = new BufferedImage(pwidth - px, pheight - py,
                    BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            Graphics2D graphics = image.createGraphics();// ��ȡͼƬ����Ļ�ͼ������
            graphics.setColor(Color.WHITE);// ����ǰ��ɫΪ��ɫ
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());// ʹ�ð�ɫ������
            graphics.setColor(Color.BLACK);// ����ǰ��ɫΪ��ɫ
            Font font = textArea.getFont();// ��ȡ�ı���������������
            graphics.setFont(font);// ������������ø�ͼƬ�Ļ�ͼ������
            textArea.print(graphics);// ���ı��������Ƶ�����ͼ�������
            image.flush();// ˢ��ͼƬ��ͼ������
            g.drawImage(image, px, py, pwidth, pheight, image.getWidth(), 0, 0,
                    image.getHeight(), this);// ������ƴ�ӡ���ݣ�ʵ�־���Ч��
            return Printable.PAGE_EXISTS;// ���ؿɴ�ӡ��ʶ
        } else {// ����
            return Printable.NO_SUCH_PAGE;// ���ز�֧�ִ�ӡ��ʶ
        }
    }
    
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
        if (reverse) {
            textArea.setVisible(false);
            textArea.setEditable(false);
        } else {
            textArea.setVisible(true);
            textArea.setEditable(true);
        }
    }
    
    public void pageSet(PrinterJob job) {
        pageFormat = job.pageDialog(pageFormat);// ��ҳ�����öԻ���
        repaint();// ���»��ƽ���
    }
    
    public boolean isReverse() {
        return reverse;
    }
}
