package com.zzk;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AbsoluteOrientationTable {
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\���Զ�λ���.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            float[] columnSize = { 21F, 21F, 21F };// �����п�
            PdfPTable table = null;
            PdfPCell cell = null;
            table = new PdfPTable(columnSize);// �����±��
            table.getDefaultCell().setBorder(1);// ���ñ��߿���
            table.setHorizontalAlignment(Element.ALIGN_CENTER);// ���þ��ж���
            table.setTotalWidth(500); // �����ܿ��500
            table.setLockedWidth(true); // ����
            cell = new PdfPCell(new Phrase("Add table"));// ���嵥Ԫ��
            cell.setColspan(3);// ���õ�Ԫ����3
            table.addCell(cell);// ������ӵ�Ԫ��
            table.addCell(new PdfPCell(new Phrase("Add 001")));// �����������
            table.addCell(new PdfPCell(new Phrase("Add 002")));// �����������
            table.addCell(new PdfPCell(new Phrase("Add 003")));// �����������
            document.add(table);// ���ĵ���ӱ��
            table = new PdfPTable(columnSize);
            // �����±��
            table.getDefaultCell().setBorder(1);// ���ñ��߿���
            table.setHorizontalAlignment(Element.ALIGN_CENTER);// ���þ��ж���
            table.setTotalWidth(500);// �����ܿ��500
            table.setLockedWidth(true);// ����
            cell = new PdfPCell(new Phrase("Table writeSelectedRows"));// ���嵥Ԫ��
            cell.setColspan(columnSize.length);// ���õ�Ԫ����3
            table.addCell(cell); // ������ӵ�Ԫ��
            table.addCell(new PdfPCell(new Phrase("Add 004")));// �����������
            table.addCell(new PdfPCell(new Phrase("Add 005")));// �����������
            table.addCell(new PdfPCell(new Phrase("Add 006")));// �����������
            table.writeSelectedRows(0, 2, 50, 750, writer.getDirectContent());// ��ָ��λ����ӱ������
            document.close();// �ر��ĵ�
        } catch (DocumentException de) {
        } catch (IOException ioe) {
            
        }
        
    }
}
