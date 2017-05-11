package com.zzk;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class InsertTable {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\Ƕ�ױ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 10, Font.BOLDITALIC,
                    Color.BLUE);// ʵ��������
            Font font = new Font(Chinese, 10, Font.NORMAL);
            Table table1 = new Table(3);// �������
            Cell cell = new Cell(new Paragraph("Ƕ��ı�һ", FontChinese));// ������Ԫ��
            cell.setColspan(3);// �����п��
            // ��Ԫ�����뵽��������Զ�����
            table1.addCell(cell);
            table1.addCell(new Paragraph("��һ 0.0", FontChinese));
            table1.addCell(new Paragraph("��һ 0.1", FontChinese));
            table1.addCell(new Paragraph("��һ 0.2", FontChinese));
            table1.addCell(new Paragraph("��һ 1.0", FontChinese));
            table1.addCell(new Paragraph("��һ 1.1", FontChinese));
            table1.addCell(new Paragraph("��һ 1.2", FontChinese));
            Table table2 = new Table(2);// �������
            // ��Ԫ�����뵽��������Զ�����
            table2.addCell(new Paragraph("��� 0.0", FontChinese));
            table2.addCell(new Paragraph("���0.1", FontChinese));
            table2.addCell(new Paragraph("��� 1.0", FontChinese));
            table2.addCell(new Paragraph("��� 1.1", FontChinese));
            Cell tableCell = new Cell(new Paragraph("ʹ��CellǶ��ı��", FontChinese));// ����һ����Ԫ��
            tableCell.add(table2);// �������ӵ���Ԫ��
            Table table3 = new Table(5, 5);// ����5��5�е�ԭ��
            table3.insertTable(table1); // ����һ�����Ƕ�뵽ԭ���е�һ��
            // ��Ԫ�����뵽��������Զ�����
            table3.addCell(new Paragraph("ԭ��1.1", font));
            table3.addCell(new Paragraph("ԭ��1.2", font));
            table3.addCell(new Paragraph("ԭ��1.3", font));
            table3.setPadding(5);// �������ֵΪ5
            table3.addCell(tableCell);// ��ӵ�Ԫ��ʵ�ֵڶ�������Ƕ��
            document.add(table3);// ���ĵ������ԭ��
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
