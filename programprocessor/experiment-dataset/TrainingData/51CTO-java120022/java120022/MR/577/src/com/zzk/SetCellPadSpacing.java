package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetCellPadSpacing {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���õ�Ԫ��������м��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            PdfPTable table = new PdfPTable(2);// ������
            table.addCell("no Padding");// ��Ԫ���������
            table.addCell("MingRiCompany MingRi MingRiCompany");// ��Ԫ�����ǰ����
            table.addCell("Set Padding");// ��Ԫ���������
            table.getDefaultCell().setPadding(24);// ��Ԫ���������24
            table.addCell("MingRi MingRiCompany");// ��Ԫ����������
            document.add(table);// �������ӵ��ĵ�
            PdfPTable table1 = new PdfPTable(2);// ������
            table1.addCell("no Leading");// ��Ԫ���������
            table1
                    .addCell("MingRi MingRi MingRiCompanyMingRiCompany MingRiCompany");// ����м��ǰ����
            table1.getDefaultCell().setLeading(12, 1);// ����м��
            table1.addCell("Set Leading");// ��Ԫ���������
            table1
                    .addCell("MingRi MingRi MingRiCompanyMingRiCompany MingRiCompany");// ����м�������
            document.add(table1);// �������ӵ��ĵ�
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
