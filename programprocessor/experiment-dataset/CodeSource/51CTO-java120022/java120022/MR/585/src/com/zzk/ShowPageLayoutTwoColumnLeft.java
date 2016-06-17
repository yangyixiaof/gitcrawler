package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ShowPageLayoutTwoColumnLeft {
    
    public static void main(String args[]) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\˫����ʾ��ҳ����.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);// �����Ķ���˫����ʾ��ҳ����
            document.open();// ���ĵ�
            document.add(new Paragraph("This is Odd Page 1"));// ���ĵ����������
            document.newPage();// �����ڶ�ҳ
            document.add(new Paragraph("this is Even Page 2"));// ���ĵ����������
            document.newPage();// ��������ҳ
            document.add(new Paragraph("This is Odd Page 3"));// ���ĵ����������
            document.newPage();// ��������ҳ
            document.add(new Paragraph("This is Even Page 4"));// ���ĵ����������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
}
