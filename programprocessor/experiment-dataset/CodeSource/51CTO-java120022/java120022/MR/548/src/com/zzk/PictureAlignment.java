package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class PictureAlignment {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\����ͼƬ���뷽ʽ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            Image image = Image.getInstance("image/picture.jpg");// ����ͼ�����
            image.setAlignment(Image.LEFT);// ����ͼƬ����
            image.scalePercent(25);// ����ԭͼ��ı���
            document.add(image);// ���ĵ����ͼƬ
            image = Image.getInstance("image/picture.jpg");// ����ͼ�����
            image.setAlignment(Image.MIDDLE);// ����ͼƬ����
            image.scalePercent(30);// ����ԭͼ��ı���
            document.add(image);// ���ĵ����ͼƬ
            image = Image.getInstance("image/picture.jpg");// ����ͼ�����
            image.setAlignment(Image.RIGHT);// ����ͼƬ����
            image.scalePercent(20);// ����ԭͼ��ı���
            document.add(image);// ���ĵ����ͼƬ
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
