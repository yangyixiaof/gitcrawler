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
 * 带背景的面板组件
 * 
 * @author 张振坤
 */
public class PrintPanel extends JPanel implements Printable {
    
    /**
     * 背景图片
     */
    private PageFormat pageFormat;// 打印格式对象
    private JTextArea textArea;// 文本域组件
    private int px;// 可打印区域的水平坐标
    private int py;// 可打印区域的垂直坐标
    private int pwidth;// 可打印区域的宽度
    private int pheight;// 可打印区域的高度
    private boolean reverse = false;// 是否反转
    
    /**
     * 构造方法
     */
    public PrintPanel() {
        super();
        setLayout(null);
        pageFormat = new PageFormat();// 创建页面格式对象
        pageFormat.setOrientation(PageFormat.LANDSCAPE);// 设置横向页面
        textArea = new JTextArea();// 创建文本域组件
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBounds(200, 5, 100, 54);
        textArea.setLineWrap(true);// 自动换行
        textArea.setOpaque(false);
        // 设置初识文本
        textArea.setText("请在这里输入要打印的文本，单击镜面效果按钮可以看效果，打印按钮将以当前效果打印。");
        add(textArea);
        setVisible(true);// 显示窗体
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// 绘制原有组件内容
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
        textArea.setBounds(px, py, pwidth, pheight);// 设置文本域组件大小
        int pageWidth = (int) pageFormat.getWidth();// 获取打印页面宽度
        int pageHeight = (int) pageFormat.getHeight();// 获取打印页面高度
        Dimension preferredSize = new Dimension(pageWidth, pageHeight);
        setPreferredSize(preferredSize);// 设置面板大小
        getParent().doLayout();// 重写布局父容器
        g.setColor(Color.WHITE);// 设置前景色为白色
        g.fill3DRect(0, 0, pageWidth, pageHeight, true);// 绘制与打印页面相同大小的矩形
        if (pageIndex < 1 && textArea != null && reverse) {// 如果当前打印页数小于1并且开启镜面效果
            BufferedImage image = new BufferedImage(pwidth - px, pheight - py,
                    BufferedImage.TYPE_INT_RGB);// 创建缓冲图像对象
            Graphics2D graphics = image.createGraphics();// 获取图片对象的绘图上下文
            graphics.setColor(Color.WHITE);// 设置前景色为白色
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());// 使用白色填充界面
            graphics.setColor(Color.BLACK);// 设置前景色为黑色
            Font font = textArea.getFont();// 获取文本域组件的字体对象
            graphics.setFont(font);// 把字体对象设置给图片的绘图上下文
            textArea.print(graphics);// 把文本域界面绘制到缓冲图像对象上
            image.flush();// 刷新图片绘图缓冲区
            g.drawImage(image, px, py, pwidth, pheight, image.getWidth(), 0, 0,
                    image.getHeight(), this);// 反向绘制打印内容，实现镜面效果
            return Printable.PAGE_EXISTS;// 返回可打印标识
        } else {// 否则
            return Printable.NO_SUCH_PAGE;// 返回不支持打印标识
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
        pageFormat = job.pageDialog(pageFormat);// 打开页面设置对话框
        repaint();// 重新绘制界面
    }
    
    public boolean isReverse() {
        return reverse;
    }
}
