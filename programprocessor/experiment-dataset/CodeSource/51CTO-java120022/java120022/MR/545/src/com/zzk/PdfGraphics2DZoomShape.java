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

public class PdfGraphics2DZoomShape {
    
    public static void main(String[] args) throws MalformedURLException {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��PdfGraphics2D���Ż��Ƶ�ͼ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(700, 800);// ����Graphics������
            Rectangle2D rect = new Rectangle2D.Double(50, 30, 120, 150);// ����ԭ���ζ���
            g.setColor(Color.BLUE);// ������ɫ
            g.fill(rect);// ���������ɫ��ͼ��
            rect = new Rectangle2D.Double(150, 30, 120, 150);// ������ԭ���δ�С��ͬ�ľ��ζ���
            g.scale(1.4, 1.2f);// ���ž��ζ���
            g.setColor(Color.PINK);// ������ɫ
            g.fill(rect);// ���������ɫ��ͼ��
            g.dispose();// ����
            cb.stroke();// ȷ�ϻ�������
            document.close();// �ر��ĵ�
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
