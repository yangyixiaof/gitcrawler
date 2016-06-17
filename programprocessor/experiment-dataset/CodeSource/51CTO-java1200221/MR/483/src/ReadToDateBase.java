import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
public class ReadToDateBase {
    static JDBCUtil util = new JDBCUtil();
    public static void main(String[] args) {
String fileToBeRead = "c:\\temp.xls";
try {           
    HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead)); // ������Excel�������ļ�������          
    HSSFSheet sheet = workbook.getSheet("Ա����");  // �����Թ���������á�
    int rows = sheet.getPhysicalNumberOfRows();         //��ȡ��������         
    for (int r = 1; r < rows; r++) {                //ѭ������������
        String value ="";                           //���屣���ȡ���ݵ�String����
        HSSFRow row = sheet.getRow(r);              //��ȡ��Ԫ����ָ�����ж���  
        if (row != null) {
           int  cells = row.getPhysicalNumberOfCells(); //��ȡ��Ԫ����ָ���ж���
            for (short c = 1; c < cells; c++) {      //ѭ��������Ԫ���е���                  
                HSSFCell cell = row.getCell((short) c); //��ȡָ����Ԫ���е���                      
                if (cell != null) {
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  //�жϵ�Ԫ���ֵ�Ƿ�Ϊ�ַ�������                                
                        value += cell.getStringCellValue()+",";
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  //�жϵ�Ԫ���ֵ�Ƿ�Ϊ��������                                
                        value += cell.getNumericCellValue()+",";
                    } else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){    //�жϵ�Ԫ���ֵ�Ƿ�Ϊ��������                      
                        value += cell.getStringCellValue()+",";
                    }
                }                       
            }                  
        }
        String [] str = value.split(",");           //���ַ������зָ�
        util.insertEmp(str);                    //���������ݿ�������ݷ���
    }   
} catch (Exception e) {
    e.printStackTrace();            
}
    }
}
