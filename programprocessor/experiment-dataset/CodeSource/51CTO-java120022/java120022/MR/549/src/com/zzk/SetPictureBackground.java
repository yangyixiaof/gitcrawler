package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class SetPictureBackground {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\��ͼƬ����Ϊ����.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(bfChinese, 50, Font.BOLD,
                    BaseColor.BLUE);// ʵ���������������������С����
            Paragraph p = new Paragraph("�����Ǳ���ͼƬ", FontChinese);// �����������
            p.setSpacingBefore(60); // ���ö����ϱ߾�
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            Image image = Image.getInstance("image/gb.jpg");// ����ͼƬ����
            image.setAlignment(Image.UNDERLYING);// ��ͼƬ����Ϊ����
            document.add(image);// ���ĵ������Ƭ
            document.add(p);// ��Ӷ���
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
