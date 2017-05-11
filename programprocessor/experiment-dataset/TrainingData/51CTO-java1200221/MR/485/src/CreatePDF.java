import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

public class CreatePDF {
    /**
     * @param args
     */

public void writePDF(String fileName) {
    File file = new File(fileName); // ���ݲ�������File����
    FileOutputStream out = null; // ����FileOutputStreamʵ��
    Document documentPDF = new Document(PageSize.A5, 50, 50, 50, 50); // ����PDF�ĵ�����
    try {
        out = new FileOutputStream(file); // ʵ����FileOutputStream����
        PdfWriter writer = PdfWriter.getInstance(documentPDF, out); // ΪDocument���󴴽�д����
        documentPDF.open(); // ����Ŀ���ļ�������
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 18.2f,
                Font.BOLDITALIC, new BaseColor(255, 0, 0));// ��������
        Paragraph chapter = new Paragraph(); // ����������
        Chapter chapter1 = new Chapter(chapter, 1); // �����½ڶ���
        chapter1.setNumberDepth(0); // ����ż�������Ϊ0����ʾ������ҳ������ʾ�½ڱ��
        font = FontFactory.getFont(FontFactory.HELVETICA, 16.0f, Font.BOLD,
                new BaseColor(255, 0, 0)); // ����������������ɫ
        Paragraph section1_title1 = new Paragraph("frist itxt PDF", font); // ���ĵ��в�������
        Section section1 = chapter1.addSection(section1_title1);
        Paragraph text = new Paragraph("this is frist text");
        section1.add(text); // ���ĵ�������½�
        documentPDF.add(chapter1);
        documentPDF.close(); // �ر��ĵ�
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        CreatePDF paf = new CreatePDF();
        paf.writePDF("C://temp.pdf");
        
    }
}
