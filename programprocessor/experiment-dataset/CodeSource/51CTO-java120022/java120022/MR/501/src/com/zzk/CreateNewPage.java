package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateNewPage {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\������ҳ.pdf"));// �����ĵ��������
            document.open();// ���ĵ�
            document.add(new Paragraph("Old Page"));// Ϊ��һҳ�������
            document.newPage();// �����µ�ҳ
            document.add(new Paragraph("New Page"));// Ϊ��ҳ�������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
