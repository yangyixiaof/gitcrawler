package com.zzk;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGraphics2DTransferShape {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��PdfGraphics2Dƽ�ƻ��Ƶ�ͼ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(1200, 800);// ����Graphics������
            Rectangle2D rect = new Rectangle2D.Double(50, 30, 120, 150);// ����ԭ���ζ���
            g.setColor(Color.BLUE);// ������ɫ
            g.fill(rect);// ���������ɫ��ͼ��
            g.translate(150.0f, 1.0f);// ƽ�ƾ��ζ���
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
