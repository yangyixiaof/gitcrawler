import java.io.File;

import java.io.FileOutputStream;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

import java.sql.*;

/**
 * . * Microsoft Excel д������
 * . *
 * . * @author HeDYn
 * . * @version --
 * .
 */
public class ExcelWriter {
    
    private File xlsFile = null;
    private String dateFormat = null;
    
    private HSSFWorkbook workbook = null;
    private SimpleDateFormat dateFormatter = null;
    
    /**
     * . * ����һ��Excel�ļ�д�������������ݽ�д�뵽ָ����xls�ļ���
     * . *
     * . * @param xlsFile ָ��Ŀ���ļ�λ�ã�ԭλ���Ѵ���ͬ���ļ���������
     * .
     */
    public ExcelWriter(File xlsFile) {
        this(xlsFile, null);
        
    }
    
/**
 * . * ����һ��Excel�ļ�д�������������ݽ�д�뵽ָ����xls�ļ��У�
 * . * д������ʱ��ָ����ʽ��ʽд��
 * . *
 * . * @param xlsFile ָ��Ŀ���ļ�λ�ã�ԭλ���Ѵ���ͬ���ļ���������
 * . * @param dateFormat ���ڸ�ʽ�����Ϊnull�򰴱������ڸ�ʽ
 * .
 */
public ExcelWriter(File xlsFile, String dateFormat) {
    this.xlsFile = xlsFile;
    workbook = new HSSFWorkbook();
    try {
        FileOutputStream fileoUut = new FileOutputStream(xlsFile);
        workbook.write(fileoUut);
        fileoUut.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    /**
     * д�����ݿ����������������ݵ�ָ��������
     */

private void writeSheet(File file, ResultSet resultSet) throws SQLException {
    HSSFWorkbook book = new HSSFWorkbook(); // ���幤��������
    HSSFSheet sheet = book.createSheet("Ա����"); // ����������
    ResultSetMetaData metaData = resultSet.getMetaData(); // ��ȡ���� ResultSet�������е����ͺ�������Ϣ�Ķ���
    int rowNum = 0;
    HSSFRow header = sheet.createRow(rowNum); // д������
    int colCount = metaData.getColumnCount(); // ��ȡ���ݿ���й��м���
    for (int i = 0; i < colCount; i++) { // ѭ���������ݱ�����
        HSSFCell cell = header.createCell(i); // �������ݿ����ݴ�����Ԫ��
        writeCell(cell, metaData.getColumnLabel(i + 1)); // �����ݿ��е�����д�뵽��Ԫ����
    }
    while (resultSet.next()) { // ѭ��������ѯ�����
        rowNum++;
        HSSFRow row = sheet.createRow(rowNum); // ����һ��
        for (int i = 0; i < colCount; i++) {
            HSSFCell cell = row.createCell(i); // �½���Ԫ��
            writeCell(cell, resultSet.getObject(i + 1)); // �������������д�뵽��Ԫ����
        }
    }
    try {
        FileOutputStream fileO = new FileOutputStream(file); // ����FileOutputStream����
        book.write(fileO);
        fileO.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    /**
     * д�����ݵ�ָ����Ԫ��
     */
    
private void writeCell(HSSFCell hssFcell, Object object) {
    if (object instanceof Date) { // �ж�Ҫд�����ֵ�Ƿ�Ϊ��������
        Date d = (Date) object;
        hssFcell.setCellValue(new HSSFRichTextString(dateFormatter
                .format(d)));// �������ı���ʽд��
    } else if (object instanceof Boolean) { // �ж�Ҫд�����ֵ�Ƿ�Ϊ��������
        boolean b = (Boolean) object;
        hssFcell.setCellValue(b); // ����д������
    } else if (object instanceof Number) { // �ж�Ҫд��������Ƿ�Ϊ��ֵ����
        double d = ((Number) object).doubleValue();
        hssFcell.setCellValue(d);// ����д����
    } else {
        String s = (String) object;
        hssFcell.setCellValue(new HSSFRichTextString(s));
    }
}

public ResultSet getRest() {
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection conn = null;
    String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �����������ݿ��url
    String userName = "sa"; // �������ݿ���û���
    String passWord = ""; // �������ݿ������
    try {
        conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
    } catch (SQLException e) {
        e.printStackTrace();
    }
    ResultSet rest = null;
    // �����ѯ��SQL���
    String sql = "select * from tb_emp";
    Statement statement;
    try {
        statement = conn.createStatement(); // ����Statementʵ��
        rest = statement.executeQuery(sql); // ִ��SQL���
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rest;
}
    
    public static void main(String[] args) {
        File file = new File("c:\\temp.xls");
        ExcelWriter excelWriter = new ExcelWriter(file);
        ResultSet rest = excelWriter.getRest();
        try {
            excelWriter.writeSheet(file, rest);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
