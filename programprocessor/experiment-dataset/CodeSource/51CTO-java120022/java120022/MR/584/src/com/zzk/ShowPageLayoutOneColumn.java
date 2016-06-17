package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ShowPageLayoutOneColumn {
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\������ʾ.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.PageLayoutOneColumn); // �����Ķ���������ʾ
            document.open();// ���ĵ�
            document.add(new Paragraph("ShowOneColumn Page 1."));// ���ĵ����������
            document.newPage();// ������ҳ
            document.add(new Paragraph("ShowOneColumn Page 2."));// ���ĵ����������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
    }
}
