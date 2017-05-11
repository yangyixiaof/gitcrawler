package com.zzk;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class SetTableHeader {
    
    public static void main(String[] args) {
        Cell cell0 = null;// ���嵥Ԫ��
        Cell cell1 = null;
        Cell cell2 = null;
        Cell cell3 = null;
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���ñ��ı�ͷ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 12, Font.NORMAL);// ʵ��������
            Table table = new Table(4);// ������
            cell0 = new Cell(new Paragraph("���", FontChinese));// ������Ԫ��
            cell0.setHorizontalAlignment(Element.ALIGN_CENTER);// ���ú�����ж���
            cell0.setVerticalAlignment(Element.ALIGN_CENTER);// ���ô�ֱ���ж���
            cell0.setBackgroundColor(Color.GRAY);// ���ñ�����ɫ
            cell0.setHeader(true);// ����Ԫ������Ϊ��ͷ
            cell1 = new Cell(new Paragraph("����", FontChinese)); // ������Ԫ��
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);// ���ú�����ж���
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);// ���ô�ֱ���ж���
            cell1.setBackgroundColor(Color.GRAY);// ���ñ�����ɫ
            cell1.setHeader(true);// ����Ԫ������Ϊ��ͷ
            cell2 = new Cell(new Paragraph("����", FontChinese)); // ������Ԫ��
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);// ���ú�����ж���
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);// ���ô�ֱ���ж���
            cell2.setBackgroundColor(Color.GRAY);// ���ñ�����ɫ
            cell2.setHeader(true);// ����Ԫ������Ϊ��ͷ
            cell3 = new Cell(new Paragraph("�绰", FontChinese)); // ������Ԫ��
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);// ���ú�����ж���
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);// ���ô�ֱ���ж���
            cell3.setBackgroundColor(Color.GRAY);// ���ñ�����ɫ
            cell3.setHeader(true);// ����Ԫ������Ϊ��ͷ
            // ������ӵ�Ԫ��
            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.setPadding(4);// ���������뵥Ԫ����
            for (int i = 1; i <= 3; i++) {// ����ĵ�Ԫ���������
                table.addCell(new Paragraph("95**0" + i));
                table.addCell(new Paragraph("��*��", FontChinese));
                table.addCell(new Paragraph("30"));
                table.addCell(new Paragraph("0431-2222****"));
            }
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
