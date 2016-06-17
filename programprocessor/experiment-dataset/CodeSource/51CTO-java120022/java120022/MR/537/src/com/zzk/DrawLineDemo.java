package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class DrawLineDemo {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\����ֱ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ����
            cb.moveTo(50, 780); // �����������
            cb.lineTo(260, 780); // �����յ�����
            cb.stroke(); // ȷ��ֱ�ߵĻ���
            cb.moveTo(50, 750);
            cb.lineTo(260, 750);
            cb.stroke();
            cb.moveTo(50, 720);
            cb.lineTo(260, 720);
            cb.stroke();
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
