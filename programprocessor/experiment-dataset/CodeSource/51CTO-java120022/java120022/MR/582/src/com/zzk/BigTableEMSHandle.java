package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BigTableEMSHandle {
    
    public static void main(String args[]) {
        int bigtablesize = 5;
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "c:\\������ڴ洦��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            
            BaseFont chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font fontChinese1 = new Font(chinese, 10, Font.NORMAL,
                    new BaseColor(90, 90, 90));// ʵ���������������������С����
            Font fontChinese2 = new Font(chinese, 15, Font.NORMAL,
                    BaseColor.BLUE);// ʵ���������������������С����
            document.add(new Paragraph("������ڴ����\n\n", fontChinese2));
            float[] hw = { 0.1f, 0.2f, 0.1f, 0.2f, 0.1f, 0.3f };// �����п�
            PdfPTable table = new PdfPTable(hw);// �������
            table.setHeaderRows(2);// ����ͷ��
            table.addCell("10%");// ����Ԫ������˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("20%");
            table.addCell("10%");
            table.addCell("20%");
            table.addCell("10%");
            table.addCell("30%");
            for (int i = 1; i <= 500; i++) {// ѭ�����������500����¼
                if (i % bigtablesize == 4) {// ����
                    document.add(table);// ���ĵ���ӱ��
                    table.deleteBodyRows();// ɾ��������
                    table.setSkipFirstHeader(true);// ʹ��ͷʼ�ձ���������
                }
                PdfPCell cell0 = new PdfPCell(new Paragraph(String.valueOf(i),
                        fontChinese1));// ��Ԫ���������
                table.addCell(cell0);// ������ӵ�Ԫ��
                PdfPCell cell1 = new PdfPCell(new Paragraph("���տƼ�",
                        fontChinese1));// ��Ԫ���������
                table.addCell(cell1);// ������ӵ�Ԫ��
                PdfPCell cell2 = new PdfPCell(new Paragraph(String.valueOf(i),
                        fontChinese1));// ��Ԫ���������
                table.addCell(cell2);// ������ӵ�Ԫ��
                PdfPCell cell3 = new PdfPCell(new Paragraph("���տƼ�",
                        fontChinese1));// ��Ԫ���������
                table.addCell(cell3);// ������ӵ�Ԫ��
            }
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
