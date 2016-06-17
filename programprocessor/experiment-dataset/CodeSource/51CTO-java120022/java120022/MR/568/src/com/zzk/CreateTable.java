package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateTable {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\�������.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 12, Font.NORMAL);// ʵ��������
            PdfPTable table = new PdfPTable(3);// �������
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("1.1");
            table.addCell("1.2");
            table.addCell("1.3");
            table.addCell("2.1");
            table.addCell("2.2");
            table.addCell("2.3");
            table.addCell("3.1");
            table.addCell("3.2");
            table.addCell("3.3");
            document.add(new Paragraph("                    ����һ��3��3�еı��\n.",
                    FontChinese));// ���ĵ��������
            document.add(table);// �������ӵ��ĵ�
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
