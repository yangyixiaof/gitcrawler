package com.zzk;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGraphics2DShape {
    
    public static void main(String[] args) throws MalformedURLException {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��PdfGraphics2D����ͼ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(700, 800);// ����Graphics������
            Rectangle2D rect = new Rectangle2D.Double(120, 100, 200, 100);// �������ζ���
            Ellipse2D circle = new Ellipse2D.Double();// ����Բ
            circle.setFrameFromCenter(220, 80, 370, 150);// ����Բ�ε����ĵ�����ͽǵ�����
            g.draw(rect);// ���ƾ��ζ���
            g.draw(circle);// ����Բ�ζ���
            g.dispose();// ����
            cb.stroke();// �ر�
            document.close();// �ر��ĵ�
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
