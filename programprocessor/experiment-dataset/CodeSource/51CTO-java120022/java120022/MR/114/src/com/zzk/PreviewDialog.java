package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JDialog;

public class PreviewDialog extends JDialog implements Printable {

	private static PrintCodingFrame frame;
	private CodePanel codePanel;

	/**
	 * Create the panel
	 */
	private PreviewDialog() {
		super();
	}

	public PreviewDialog(PrintCodingFrame frame) {
		super(frame);
		this.frame = frame;
		codePanel = frame.getCodePanel();// ��ȡ���������
	}
	public PreviewDialog(PrintCodingFrame frame,boolean modal) {
		super(frame,modal);
		setTitle("��ӡԤ��");
		this.frame = frame;
		codePanel = frame.getCodePanel();// ��ȡ���������
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pf, int pageIndex)
			throws PrinterException {// ʵ��Printable�ӿڵķ���
		if(pageIndex>0)// ֻ��ӡ1ҳ
			return Printable.NO_SUCH_PAGE;
		Graphics2D g = (Graphics2D) graphics;
		int px = (int) pf.getImageableX();// ��ȡ�ɴ�ӡ����
		int py = (int) pf.getImageableY();
		int pw = (int) pf.getImageableWidth();
		int ph = (int) pf.getImageableHeight();
		int width = (int) pf.getWidth();// ��ȡҳ���С
		int height = (int) pf.getHeight();
		setSize(new Dimension(width, height));// ���������ҳ���С��ͬ

		g.setColor(Color.WHITE);
		g.fill3DRect(0, 0, width, height, true);// ʹ�ð�ɫ����ֽ�Ž���
		g.translate(px, py);
		g.setClip(0, 0, pw, ph);
		codePanel.print(g);// �������������
		return Printable.PAGE_EXISTS;
	}

	@Override
	public void paint(Graphics g) {// ���»��ƽ���ķ���
		try {
			print(g,codePanel.getFormat(),0);// ����print()����
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	public static Printable getPage(){
		return new PreviewDialog(PrintCodingFrame.frame);
	}
}
