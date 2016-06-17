package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class BoldFontDocumentDemo {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream("c:\\Java.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);// �����������
            Font contentFont = new Font(bfChinese, 12, Font.BOLD);// ����Ӵ�����
            document.add(new Paragraph("www.mingribccd.com",contentFont));// ���ĵ���������ݲ�ָ���Ӵ�����
            document.add(new Paragraph("www.mingribook.com"));
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
