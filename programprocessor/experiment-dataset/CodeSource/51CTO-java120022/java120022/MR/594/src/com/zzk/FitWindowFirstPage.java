package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class FitWindowFirstPage {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ�ĵ������ʺ���ʾ��һҳ.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.FitWindow);// �����Ķ����ʺ���ʾ��һҳ
            document.open();// ���ĵ�
            for (int i = 1; i <= 100; i++) {
                document.add(new Paragraph("Fit show Page 1. row " + i));// ���ĵ�����Ӷ�������
            }
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
