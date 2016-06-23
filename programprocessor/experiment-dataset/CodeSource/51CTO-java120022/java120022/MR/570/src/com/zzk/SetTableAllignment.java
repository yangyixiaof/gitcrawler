package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetTableAllignment {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���ñ����뷽ʽ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 10, Font.BOLD);// ʵ��������
            PdfPTable table = new PdfPTable(3);// ������
            table.setTotalWidth(200);// ���ñ����Ϊ200
            table.setLockedWidth(true);
            PdfPCell cell = new PdfPCell(new Paragraph("new table colspan 3"));// ����һ�����Ԫ
            cell.setColspan(3);// ���ñ����
            table.addCell(cell);// ����Ԫ���뵽���
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("1.1");
            table.addCell("2.1");
            table.addCell("3.1");
            table.setHorizontalAlignment(Element.ALIGN_LEFT);// ����ˮƽ���뷽ʽ ����
            
            PdfPTable table1 = new PdfPTable(3);// ������
            table1.setTotalWidth(200);// ���ñ����Ϊ200
            table1.setLockedWidth(true);
            PdfPCell cell2 = new PdfPCell(new Paragraph("new table colspan 3"));// ����һ�����Ԫ
            cell2.setColspan(3);// ���ñ����
            table1.addCell(cell2);// ����Ԫ���뵽���
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table1.addCell("1.1");
            table1.addCell("2.1");
            table1.addCell("3.1");
            table1.setHorizontalAlignment(Element.ALIGN_CENTER);// ����ˮƽ���뷽ʽ ����
            PdfPTable table2 = new PdfPTable(3);// ������
            table2.setTotalWidth(200);// ���ñ����Ϊ200
            table2.setLockedWidth(true);
            PdfPCell cell3 = new PdfPCell(new Paragraph("new table colspan 3"));// ����һ�����Ԫ
            cell3.setColspan(3);// ���ñ����
            table2.addCell(cell2);// ����Ԫ���뵽���
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table2.addCell("1.1");
            table2.addCell("2.1");
            table2.addCell("3.1");
            table2.setHorizontalAlignment(Element.ALIGN_RIGHT);// ����ˮƽ���뷽ʽ ����
            Paragraph p = new Paragraph("���������\n\n", FontChinese);
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);// ���ĵ��������
            document.add(table);// �������ӵ��ĵ�
            Paragraph p2 = new Paragraph("�����ж���\n\n", FontChinese);
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);// ���ĵ��������
            document.add(table1);// �������ӵ��ĵ�
            Paragraph p3 = new Paragraph("�����Ҷ���\n\n", FontChinese);
            p3.setAlignment(Element.ALIGN_RIGHT);
            document.add(p3);// ���ĵ��������
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
