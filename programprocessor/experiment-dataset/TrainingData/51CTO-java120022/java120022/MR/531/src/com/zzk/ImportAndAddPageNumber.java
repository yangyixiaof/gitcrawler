package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class ImportAndAddPageNumber {
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream("c:\\ҳ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            document.add(new Paragraph("No. 1 page")); // ���ĵ����������
            document.newPage();
            document.add(new Paragraph("No. 2 page")); // ���ĵ����������
            document.close();// �ر��ĵ�����
            PdfReader reader = new PdfReader("c:\\ҳ��.pdf");// ������ҳ��.pdf����PdfReader����
            int totalPages = reader.getNumberOfPages();
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
                    "c:\\���벢���ҳ��.pdf"));// ����PdfStamper����
            BaseFont chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// ��������
            PdfContentByte under = null;
            for (int i = 1; i <= totalPages; i++) {
                under = stamp.getUnderContent(i);// ���ÿһҳ������
                under.beginText();// ����ı���ʼ
                under.setFontAndSize(chinese, 18);// ����������ֺ�
                under.setTextMatrix(280, 15);// ����ҳ�����ʾλ��
                under.showText("��" + i + "ҳ");// ���ҳ��
                under.endText();// ����ı�����
            }
            stamp.close();// PdfStamper���󣬽��ӡ�ҳ��.pdf���ж�ȡ���ĵ����ҳ���д�롰���ҳ��.pdf��
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
