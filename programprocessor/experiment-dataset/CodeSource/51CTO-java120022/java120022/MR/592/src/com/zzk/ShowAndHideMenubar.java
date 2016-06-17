package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ShowAndHideMenubar {
    
    public static void main(String args[]) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\��ʾ�����ز˵�.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.HideMenubar);// �����Ķ������ز˵�
            document.open();// ���ĵ�
            document.add(new Paragraph("HideMenubar."));// ���ĵ����������
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
