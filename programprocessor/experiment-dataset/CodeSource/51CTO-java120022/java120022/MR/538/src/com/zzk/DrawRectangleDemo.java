package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class DrawRectangleDemo {
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\���ƾ���.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ����
            cb.rectangle(50, 650, 200, 150); // ���ƾ���
            cb.stroke();// ȷ�ϻ��Ƶľ���
            cb.rectangle(70, 675, 160, 100); // ���ƾ���
            cb.stroke();// ȷ�ϻ��Ƶľ���
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
