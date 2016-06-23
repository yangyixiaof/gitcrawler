package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SetTextWarp {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\�������ֻ���.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            Image image = Image.getInstance("image/picture.jpg");// ����ͼ�����
            image.scalePercent(33);// ����ԭͼ��ı���
            image.setAlignment(Image.TEXTWRAP);// ��ͼƬ����Ϊ���ֻ���
            document.add(image);// ���ĵ����ͼƬ
            StringBuffer sb = new StringBuffer();// �����ַ�������
            for (int i = 1; i <= 200; i++) {
                sb.append(i + " ");// ���ַ����������������
            }
            Paragraph p = new Paragraph(sb.toString());// �����������
            document.add(p);// ��������ӵ��ĵ���
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
