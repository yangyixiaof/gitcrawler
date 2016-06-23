package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ReportToPdfFrame extends JFrame {
    
    private JTable table;
    private String[] title = { "编号", "姓名", "性别", "年龄", "地址", "邮编", "电话" };
    
    /**
     * @return JTable表格的标题向量
     */
    public Vector getTitleVector() {
        Vector titleVec = new Vector();
        for (int i = 0; i < title.length; i++) {
            titleVec.add(title[i]);
        }
        return titleVec;
    }
    
    /**
     * @return JTable表格的内容向量
     */
    public Vector getDataVectors() {
        ResultSet rs = QueryResultSet.gainRecord();
        Vector dataVector = new Vector();
        try {
            while (rs.next()) {
                Vector rowVector = new Vector();
                for (int i = 0; i < title.length; i++) {
                    if (i == 0 || i == 3) {
                        rowVector.add(rs.getInt(i + 1));
                    } else {
                        rowVector.add(rs.getString(i + 1));
                    }
                }
                dataVector.add(rowVector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataVector;
    }
    
    public void toPdf() {
        try {
            Document document = new Document(PageSize.A4, 71.4f, 71.4f, 71.4f, 71.4f);// 定义文档以A4纸张的格式打印
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\report.pdf"));// 设置输出的位置及文件名
            document.open();// 打开文档
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 定义基本字体
            Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 定义加粗字体，用于设置表格标题的字体
            Font contentFont = new Font(bfChinese, 12);// 定义普通字体
            PdfPCell cell = null;// 定义PDF单元格
            PdfPTable table = new PdfPTable(new float[] { 40.9f, 40.9f, 40.9f,
                    40.9f, 79.1f, 40.9f, 79.1f });// 建立一个7列的pdf表格
            table.setTotalWidth(460);// 设置表格的宽度为460
            table.setLockedWidth(true);// 锁定表格的宽度
            for (int i = 0; i < title.length; i++) {
                cell = new PdfPCell(new Paragraph(title[i], titleFont));// 创建单元格对象
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 单元格内容水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 单元格内容垂直居中
                cell.setFixedHeight(20.0f);// 设置单元格的高度
                table.addCell(cell);// 为表格添加单元格
            }
            
            ResultSet rs = QueryResultSet.gainRecord();// 获得数据库表的结果集对象
            while (rs.next()) {// 遍历结果集
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// 定义存放单元格内容的变量
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// 从记录集获得单元格内容
                    } else {
                        cellContent = rs.getString(i + 1);// 从记录集获得单元格内容
                    }
                    cell = new PdfPCell(new Paragraph(cellContent, contentFont));// 创建单元格对象
                    if (i == 6) {
                        cell.setBorderWidthRight(0.01f);// 单元格右边框的宽度
                    } else {
                        cell.setBorderWidthRight(0f);// 单元格右边框的宽度
                    }
                    cell.setBorderWidthTop(0f);// 单元格上边框的宽度
                    cell.setBorderWidthBottom(0.01f);// 单元格下边框的宽度
                    cell.setFixedHeight(20.0f);// 单元格的高度
                    table.addCell(cell);// 为表格添加单元格
                }
            }
            titleFont = new Font(bfChinese, 36, Font.BOLD);// 定义加粗字体，用于设置标题的字体
            Paragraph pdfTitle = new Paragraph("员工基本信息报表\n\n", titleFont);// 创建用于指定标题的Paragraph对象
            pdfTitle.setAlignment(Element.ALIGN_CENTER);// 居中Paragraph对象
            document.add(pdfTitle);// 为PDF文档添加标题
            document.add(table);// 为PDF文档添加表格
            document.close();// 关闭文档
            JOptionPane.showMessageDialog(null, "已经成功地将报表\n导出到“C:/report.pdf”中。");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToPdfFrame frame = new ReportToPdfFrame();
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
    public ReportToPdfFrame() {
        super();
        setTitle("导出报表到PDF文档");
        setBounds(100, 100, 383, 264);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                toPdf();
            }
        });
        button.setText("导出为PDF");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退出系统");
        panel.add(button_1);
        //
    }
}
