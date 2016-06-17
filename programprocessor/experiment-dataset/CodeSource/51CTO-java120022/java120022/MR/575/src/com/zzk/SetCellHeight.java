package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetCellHeight {
    
    public static void main(String[] args) {
        
        try {
            Font font = FontFactory.getFont("COURIER", 10, Font.BOLD);// ����һ������
            Font xfont = FontFactory.getFont("HELVETICA", 10, Font.BOLD);// ����һ������
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���õ�Ԫ��ĸ߶�.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfPTable table = new PdfPTable(2);// ������
            table.getDefaultCell().setBackgroundColor(BaseColor.ORANGE);
            table.addCell(new Paragraph("default height", xfont));// ������ӵ�Ԫ��
            PdfPCell cell = new PdfPCell(new Paragraph("AAA", font));// ����һ�����Ԫ
            table.addCell(cell);// ������ӵ�Ԫ��
            table.addCell(new Paragraph("set height", xfont));
            PdfPCell cell2 = new PdfPCell(new Paragraph("ABC", font));// ���嵥Ԫ��
            cell2.setFixedHeight(60);// ���õ�Ԫ��߶�Ϊ60
            table.addCell(cell2);// ����Ԫ���뵽���
            table.addCell(new Paragraph("minimum height", xfont));
            PdfPCell cell3 = new PdfPCell(new Paragraph(
                    "A cat may look at a king.", font));// ���嵥Ԫ��
            cell3.setMinimumHeight(40);// ���õ�Ԫ��߶�Ϊ40
            table.addCell(cell3);// ����Ԫ���뵽���
            document.add(table);// �������ӵ��ĵ�
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
