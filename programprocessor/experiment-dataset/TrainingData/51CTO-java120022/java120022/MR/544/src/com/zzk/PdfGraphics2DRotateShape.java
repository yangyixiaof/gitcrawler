package com.zzk;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGraphics2DRotateShape {
    
    public static void main(String[] args) throws MalformedURLException {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��PdfGraphics2D��ת���Ƶ�ͼ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(700, 800);// ����PdfGraphics2D����
            Rectangle2D rect = new Rectangle2D.Double(200, 200, 150, 200);// �������ζ���
            g.setColor(Color.BLUE);// ����ͼ����ɫ
            g.rotate(20, 380, 150);// ��תͼ��
            g.draw(rect);// ���ƾ��ζ���
            g.dispose();// ����
            document.close();// �ر��ĵ�
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
