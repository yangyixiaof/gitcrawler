package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReportToExcelFrame extends JFrame {
    
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
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToExcelFrame frame = new ReportToExcelFrame();
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
    public ReportToExcelFrame() {
        super();
        setTitle("导出报表到Excel表格");
        setBounds(100, 100, 420, 281);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    writeExcel("c:/report.xls");// 调用方法，导出报表到Excel
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("导出到Excel");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退出系统");
        panel.add(button_1);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        // 
    }
    
    @SuppressWarnings("deprecation")
    public void writeExcel(String filename) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建工作簿对象
        HSSFSheet sheet = workbook.createSheet("ReportToExcel");// 创建名称为“ReportToExcel”的工作表
        HSSFRow row = sheet.createRow(0);// 在工作表中创建行，其行索引为0
        HSSFCell cell = null;// 声明单元格
        // 导出的报表在Excel中的标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell((short) i);// 创建单元格
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置单元格类型
            cell.setCellValue(title[i]);// 设置单元格的内容
        }
        // 导出的报表在Excel中的内容
        ResultSet rs = QueryResultSet.gainRecord();// 获得数据库表的结果集对象
        int rowIndex = 1;// 单元格内容的行索引值
        try {
            while (rs.next()) {// 遍历结果集
                row = sheet.createRow(rowIndex);
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// 定义存放单元格内容的变量
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// 从记录集获得单元格内容
                    } else {
                        cellContent = rs.getString(i + 1);// 从记录集获得单元格内容
                    }
                    cell = row.createCell((short) i);// 创建单元格对象
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置单元格类型
                    cell.setCellValue(cellContent);// 指定单元格的值
                }
                rowIndex++;// 调整单元格内容的行索引值
            }
            FileOutputStream fout = new FileOutputStream(filename);// 创建导出Excel的输出流
            workbook.write(fout);// 将工作簿写入输出流
            fout.flush();// 刷新输出流并强制写出所有缓冲的输出字节
            fout.close();// 关闭输出流对象
            JOptionPane.showMessageDialog(null, "已经成功地将报表\n导出到“C:/report.xls”中。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
