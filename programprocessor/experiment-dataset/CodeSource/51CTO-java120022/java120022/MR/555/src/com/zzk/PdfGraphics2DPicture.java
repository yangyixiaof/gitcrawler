package com.zzk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGraphics2D;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGraphics2DPicture {
    
    public static void main(String[] args) {
        Document document = new Document(); // ���ĵ�
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\ʹ��PdfGraphics2D����ͼƬ.pdf"));// �����ĵ��������
            document.open();// ���ĵ�
            PdfContentByte cb = writer.getDirectContent();// ��ȡ�ĵ�����
            PdfGraphics2D g = (PdfGraphics2D) cb.createGraphics(700, 800);// ����PdfGraphics2D����
            BufferedImage image = ImageIO.read(new File("image/picture.jpg"));// ��ȡͼƬ
            g.drawImage(image, 50, 10, null);// ����ͼƬ
            g.dispose();// ����
            cb.stroke();// ȷ�ϻ��Ƶ�����
            document.close();// �ر��ĵ�
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
