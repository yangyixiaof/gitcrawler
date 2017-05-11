package com.zzk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGraphics2DText {
    
    public static void main(String[] args) throws MalformedURLException {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��PdfGraphics2D�����ı�.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(700, 800);// ���PdfGraphics2D����
            g.drawString("draw text. ", 54, 10);// �����ı�
            g.drawString("second row text. ", 54, 30);// �����ı�
            g.drawString("third row text. ", 54, 50);// �����ı�
            g.dispose();// ����
            cb.stroke();// ȷ�ϻ��Ƶ�����
            document.close();// �ر��ĵ�
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
