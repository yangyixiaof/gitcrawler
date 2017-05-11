import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;

public class CreateExcelUseformula {
    
    /**
     * @param args
     */
    
public static void main(String[] args) {
    try {
        /** Excel �ļ�Ҫ��ŵ�λ�ã��ٶ���C�̸�Ŀ¼�� */
        String outputFile = "c://temps.xls";
        // �����µ�Excel ������
        HSSFWorkbook excelbook = new HSSFWorkbook();
        // ��Ҫ�½�һ��Ϊ"���ʱ�"�Ĺ����������Ϊ��
        HSSFSheet sheet = excelbook.createSheet("���ʱ�");
        // ������0��λ�ô����У���˵��У�
        HSSFRow row = sheet.createRow((short) 0);
        // ������0��λ�ô�����Ԫ�����϶ˣ�
        HSSFCell monadism = row.createCell(0);
        // ���嵥Ԫ��Ϊ�ַ�������
        monadism.setCellType(HSSFCell.CELL_TYPE_STRING);
        // �ڵ�Ԫ��������һЩ����
        monadism.setCellValue("����");
        // �ڵ�һ�еڶ����������
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("����");
        row.createCell(2).setCellValue("����");
        row.createCell(3).setCellValue("��Ǯ");
        for (int i = 1; i <= 5; i++) { // ͨ��forѭ���������
            HSSFRow row2 = sheet.createRow(i); // �ڹ������д���һ��
            row2.createCell(0).setCellValue("ƻ��"); // �ڹ����������½�һ��
            row2.createCell(1).setCellValue(i); // ���õ�Ԫ��ֵ
            row2.createCell(2).setCellValue(1.2);
            row2.createCell(3).setCellFormula(
                    "B" + (i + 1) + "*C" + (i + 1) + ""); // Ϊ��Ԫ����ӹ�ʽ
        }
        // �½�����ļ���
        FileOutputStream out = new FileOutputStream(outputFile);
        // ����Ӧ��Excel����������
        excelbook.write(out);
        out.flush();
        // �����������ر��ļ�
        out.close();
        System.out.println("�ļ������ɹ�������");
    } catch (Exception e) {
        e.printStackTrace();
    }    
}
    
}
