package com.zzk;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class UseGraphics2D {
    
    public static void main(String[] args) throws MalformedURLException {
        Document document = new Document(); // �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ʹ��Graphics2D����ͼ��.pdf"));// �����ĵ������������
            document.open(); // ���ĵ�
            PdfContentByte cb = writer.getDirectContent(); // ��ȡ�ĵ�����
            Graphics2D g = cb.createGraphics(850, 850); // ����Graphics������
            Rectangle2D rect1 = new Rectangle2D.Double(50, 50, 200, 150); // �������ζ���
            g.draw(rect1); // ���ƾ���
            Rectangle2D rect2 = new Rectangle2D.Double(70, 70, 160, 110); // �������ζ���
            g.fill(rect2); // ����������
            g.dispose(); // ����
            cb.stroke(); // ȷ�ϻ��Ƶ�ͼ��
            document.close(); // �ر��ĵ�
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
