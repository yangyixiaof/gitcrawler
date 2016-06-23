package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CenterWindow {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\����Ļ������ʾ�ĵ�����.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.CenterWindow);// �����Ķ�������Ļ������ʾ
            document.open();// ���ĵ�
            document.add(new Paragraph("ShowCenterWindow"));// ���ĵ����������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
    }
}
