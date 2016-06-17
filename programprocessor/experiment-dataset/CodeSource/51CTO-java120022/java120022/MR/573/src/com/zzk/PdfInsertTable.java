package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfInsertTable {
    public static void main(String[] args) {
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(
                    "c:\\PdfǶ�ױ��.pdf"));
            document.open();// ���ĵ�
            PdfPTable table = new PdfPTable(4);// ����������
            PdfPTable table1 = new PdfPTable(2);// ����������
            table1.addCell("1.1");// ��ӵ�Ԫ������
            table1.addCell("1.2");// ��ӵ�Ԫ������
            PdfPTable table2 = new PdfPTable(1);// ����������
            table2.addCell("2.1");// ��ӵ�Ԫ������
            table2.addCell("2.2");// ��ӵ�Ԫ������
            table.addCell("table1");// ��ӵ�Ԫ������
            table.addCell("table2");// ��ӵ�Ԫ������
            table.addCell("text");// ��ӵ�Ԫ������
            table.addCell("text");// ��ӵ�Ԫ������
            table.addCell(table1);// ���Ƕ�׵ı��
            table.addCell(table2);// ���Ƕ�׵ı��
            table.addCell("cell");// ��ӵ�Ԫ������
            table.addCell("cell");// ��ӵ�Ԫ������
            document.add(table);// �������ӵ��ĵ���
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
}
