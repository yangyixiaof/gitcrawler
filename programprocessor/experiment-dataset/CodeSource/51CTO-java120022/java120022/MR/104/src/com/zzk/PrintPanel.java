package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 * ��������������
 * 
 * @author ������
 */
public class PrintPanel extends JPanel implements Printable {

	/**
	 * ����ͼƬ
	 */
	private Image image;
	private PageFormat pageFormat;
	private int px;
	private int py;
	private int pwidth;
	private int pheight;

	/**
	 * ���췽��
	 */
	public PrintPanel() {
		super();
		setLayout(null);
		pageFormat = new PageFormat();// ����ҳ���ʽ����
		pageFormat.setOrientation(PageFormat.LANDSCAPE);// ���ú���ҳ��
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);
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
		int pageWidth = (int) pageFormat.getWidth();// ��ȡ��ӡҳ����
		int pageHeight = (int) pageFormat.getHeight();// ��ȡ��ӡҳ��߶�
		Dimension preferredSize = new Dimension(pageWidth, pageHeight);
		setPreferredSize(preferredSize);// ��������С
		getParent().doLayout();// ��д���ָ�����
		g.setColor(Color.WHITE);// ����ǰ��ɫΪ��ɫ
		g.fill3DRect(0, 0, pageWidth, pageHeight, true);// �������ӡҳ����ͬ��С�ľ���
		if (pageIndex < 1) {// �����ǰ��ӡҳ��С��1
			g.drawImage(image, px, py, pwidth, pheight, this);// ���ƴ�ӡ����
			return Printable.PAGE_EXISTS;// ���ؿɴ�ӡ��ʶ
		} else// ����
			return Printable.NO_SUCH_PAGE;// ���ز�֧�ִ�ӡ��ʶ
	}

	public void pageSet(PrinterJob job) {
		pageFormat = job.pageDialog(pageFormat);
		repaint();
	}
}
