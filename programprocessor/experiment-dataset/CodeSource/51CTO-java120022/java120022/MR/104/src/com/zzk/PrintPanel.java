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
 * 带背景的面板组件
 * 
 * @author 张振坤
 */
public class PrintPanel extends JPanel implements Printable {

	/**
	 * 背景图片
	 */
	private Image image;
	private PageFormat pageFormat;
	private int px;
	private int py;
	private int pwidth;
	private int pheight;

	/**
	 * 构造方法
	 */
	public PrintPanel() {
		super();
		setLayout(null);
		pageFormat = new PageFormat();// 创建页面格式对象
		pageFormat.setOrientation(PageFormat.LANDSCAPE);// 设置横向页面
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);
		try {
			print(g, pageFormat, 0);// 调用打印方法绘制面板界面
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int print(Graphics g1, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D g = (Graphics2D) g1;
		px = (int) pageFormat.getImageableX();// 获取可打印区域的x坐标
		py = (int) pageFormat.getImageableY();// 获取可打印区域的y坐标
		pwidth = (int) pageFormat.getImageableWidth();// 获取可打印的宽度
		pheight = (int) pageFormat.getImageableHeight();// 获取可打印的高度
		int pageWidth = (int) pageFormat.getWidth();// 获取打印页面宽度
		int pageHeight = (int) pageFormat.getHeight();// 获取打印页面高度
		Dimension preferredSize = new Dimension(pageWidth, pageHeight);
		setPreferredSize(preferredSize);// 设置面板大小
		getParent().doLayout();// 重写布局父容器
		g.setColor(Color.WHITE);// 设置前景色为白色
		g.fill3DRect(0, 0, pageWidth, pageHeight, true);// 绘制与打印页面相同大小的矩形
		if (pageIndex < 1) {// 如果当前打印页数小于1
			g.drawImage(image, px, py, pwidth, pheight, this);// 绘制打印内容
			return Printable.PAGE_EXISTS;// 返回可打印标识
		} else// 否则
			return Printable.NO_SUCH_PAGE;// 返回不支持打印标识
	}

	public void pageSet(PrinterJob job) {
		pageFormat = job.pageDialog(pageFormat);
		repaint();
	}
}
