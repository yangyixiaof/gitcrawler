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
		codePanel = frame.getCodePanel();// 获取条形码面板
	}
	public PreviewDialog(PrintCodingFrame frame,boolean modal) {
		super(frame,modal);
		setTitle("打印预览");
		this.frame = frame;
		codePanel = frame.getCodePanel();// 获取条形码面板
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pf, int pageIndex)
			throws PrinterException {// 实现Printable接口的方法
		if(pageIndex>0)// 只打印1页
			return Printable.NO_SUCH_PAGE;
		Graphics2D g = (Graphics2D) graphics;
		int px = (int) pf.getImageableX();// 获取可打印区域
		int py = (int) pf.getImageableY();
		int pw = (int) pf.getImageableWidth();
		int ph = (int) pf.getImageableHeight();
		int width = (int) pf.getWidth();// 获取页面大小
		int height = (int) pf.getHeight();
		setSize(new Dimension(width, height));// 设置组件与页面大小相同

		g.setColor(Color.WHITE);
		g.fill3DRect(0, 0, width, height, true);// 使用白色绘制纸张界面
		g.translate(px, py);
		g.setClip(0, 0, pw, ph);
		codePanel.print(g);// 绘制条形码界面
		return Printable.PAGE_EXISTS;
	}

	@Override
	public void paint(Graphics g) {// 重新绘制界面的方法
		try {
			print(g,codePanel.getFormat(),0);// 调用print()方法
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	public static Printable getPage(){
		return new PreviewDialog(PrintCodingFrame.frame);
	}
}
