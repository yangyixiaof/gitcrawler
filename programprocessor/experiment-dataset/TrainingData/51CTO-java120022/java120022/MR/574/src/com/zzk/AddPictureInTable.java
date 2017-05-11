package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AddPictureInTable {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\�ڱ�������ͼƬ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            Image image = Image.getInstance("image/picture.jpg");// ����ͼ�����
            PdfPTable table = new PdfPTable(3);// ������
            table.addCell("Text");// ��ӵ�Ԫ������
            table.addCell("Picture");// ��ӵ�Ԫ������
            table.addCell("Text");// ��ӵ�Ԫ������
            table.addCell("This is a cell.");// ��ӵ�Ԫ������
            table.addCell(image);// ��Ԫ�������ͼ�����
            table.addCell("This is a cell.");// ��ӵ�Ԫ������
            document.add(table);// �������ӵ��ĵ�
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
