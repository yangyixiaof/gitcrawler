package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class OnlyShowOnePage {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ֻ��ʾһ��ҳ��.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);// �����Ķ���ֻ��ʾһ��ҳ��
            document.open();// ���ĵ�
            document.add(new Paragraph("PageLayoutSinglePage 1"));// ���ĵ����������
            document.newPage();// �����ҳ
            document.add(new Paragraph("PageLayoutSinglePage 2"));// ���ĵ����������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
    }
}
