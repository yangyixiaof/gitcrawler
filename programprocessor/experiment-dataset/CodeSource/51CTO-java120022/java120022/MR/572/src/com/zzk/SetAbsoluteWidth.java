package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetAbsoluteWidth {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���þ��Կ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 10, Font.NORMAL);// ʵ��������
            PdfPTable table1 = new PdfPTable(3);// ������
            PdfPCell cell1 = new PdfPCell(new Paragraph("new table colspan 3"));// ����һ�����Ԫ
            cell1.setColspan(3);// ���ñ����
            table1.addCell(cell1);// ����Ԫ���뵽���
            table1.addCell("1.1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table1.addCell("2.1");
            table1.addCell("3.1");
            table1.addCell("1.2");
            table1.addCell("2.2");
            table1.addCell("3.2");
            table1.addCell("1.3");
            table1.addCell("2.3");
            table1.addCell("3.3");
            PdfPTable table2 = new PdfPTable(3);// ������
            PdfPCell cell2 = new PdfPCell(new Paragraph("new table colspan 3"));// ����һ�����Ԫ
            cell2.setColspan(3);// ���ñ����
            table2.addCell(cell2);// ����Ԫ���뵽���
            table2.addCell("1.1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table2.addCell("2.1");
            table2.addCell("3.1");
            table2.addCell("1.2");
            table2.addCell("2.2");
            table2.addCell("3.2");
            table2.addCell("1.3");
            table2.addCell("2.3");
            table2.addCell("3.3");
            table2.setTotalWidth(200);// ���ñ����Ϊ200
            table2.setLockedWidth(true);// �������
            document.add(new Paragraph("                  Ĭ�ϵı��\n\n", FontChinese));
            document.add(table1);// �������ӵ��ĵ�
            document.add(new Paragraph("                  ���ñ����200",
                    FontChinese));
            document.add(table2);// �������ӵ��ĵ�
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
