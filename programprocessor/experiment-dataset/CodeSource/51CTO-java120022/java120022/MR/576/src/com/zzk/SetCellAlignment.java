package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetCellAlignment {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���õ�Ԫ��Ķ��뷽ʽ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfPTable table = new PdfPTable(2);// ������
            Paragraph p = new Paragraph("I think Bale will win");// ������������
            table.addCell("alignment left");// ��Ԫ���������
            PdfPCell cell = new PdfPCell(p);// ���嵥Ԫ��
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);// ���õ�Ԫ��ˮƽ�������
            table.addCell(cell);
            table.addCell("alignment right");// ��Ԫ���������
            PdfPCell cell1 = new PdfPCell(p);// ���嵥Ԫ��
            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);// ���õ�Ԫ��ˮƽ���Ҷ���
            table.addCell(cell1);
            table.addCell("alignment justified");// ��Ԫ���������
            PdfPCell cell2 = new PdfPCell(p);// ���嵥Ԫ��
            cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);// ���õ�Ԫ��Ϊ����Ķ��뷽ʽ
            table.addCell(cell2);
            table.addCell("alignment center");// ��Ԫ���������
            PdfPCell cell3 = new PdfPCell(p);// ���嵥Ԫ��
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);// ���õ�Ԫ��ˮƽ���м����
            table.addCell(cell3);
            document.add(table);// �������ӵ��ĵ�
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
