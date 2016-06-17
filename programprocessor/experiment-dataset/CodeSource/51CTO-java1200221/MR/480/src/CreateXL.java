import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CreateXL {
    /** Excel �ļ�Ҫ��ŵ�λ�ã��ٶ���C�̸�Ŀ¼�� */
    String outputFile = "c:/temp.xls";
    static HSSFSheet sheet = null;
    HSSFWorkbook excelbook;
    
    // ��������Excel��񷽷�
    
public void createExcel() {
    try {
        sheet = excelbook.createSheet("���ʱ�"); // ������0��λ�ô����У���˵��У�
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell monadism = row.createCell((short) 0); // ������0��λ�ô�����Ԫ�����϶ˣ�
        monadism.setCellType(HSSFCell.CELL_TYPE_STRING); // ���嵥Ԫ��Ϊ�ַ�������
        monadism.setCellValue("����");// �ڵ�Ԫ��������һЩ����
        row.createCell((short) 1).setCellValue("�Ա�"); // �ڵ�һ�еڶ����������
        row.createCell((short) 2).setCellValue("����");
        row.createCell((short) 3).setCellValue("����");
        row.createCell((short) 4).setCellValue("ְλ");
        row.createCell((short) 5).setCellValue("������Ϣ");
        FileOutputStream out = new FileOutputStream(outputFile); // �½�����ļ���
        excelbook.write(out);// ����Ӧ��Excel����������
        out.flush();
        out.close(); // �����������ر��ļ�
        System.out.println("�ļ������ɹ�������");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    // ��Ա������������ݷ���

public void insertvalue(String name, String sex, String age, String dept,
        String job, String laborage) {
    try {
        excelbook = new HSSFWorkbook(new FileInputStream(outputFile));  //����Excel�����
        HSSFSheet sheet = excelbook.getSheet("���ʱ�");        //��ȡָ���Ĺ�����
        int count = sheet.getPhysicalNumberOfRows();        //��ȡ���������ܵ�����
        HSSFRow row = sheet.createRow((short) count);       //�½�һ��
        row.createCell((short) 0).setCellValue(name);        // ������0��λ�ô�����Ԫ�����϶ˣ�
        row.createCell((short) 1).setCellValue(sex);
        row.createCell((short) 2).setCellValue(age);
        row.createCell((short) 3).setCellValue(dept);
        row.createCell((short) 4).setCellValue(job);
        row.createCell((short) 5).setCellValue(laborage);        
        FileOutputStream out;// �½�����ļ���        
        out = new FileOutputStream(outputFile);
        excelbook.write(out);// ����Ӧ��Excel����������
        out.flush();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
