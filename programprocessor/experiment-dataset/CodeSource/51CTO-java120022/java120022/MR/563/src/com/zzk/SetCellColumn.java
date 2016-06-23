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

public class SetCellColumn {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���õ�Ԫ����ռ������.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 10, Font.NORMAL, Color.red);// ʵ��������
            Table table = new Table(5);// ������
            table.addCell("1,1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("2,1");
            Cell cell = new Cell(new Paragraph("��ռ��2��", FontChinese));// ����һ�����Ԫ
            cell.setColspan(2);// ���ñ���п��(�ϲ�������Ԫ��)
            table.addCell(cell); // ����Ԫ���뵽���
            table.addCell("2,1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("2,2");
            table.addCell("3,2");
            table.addCell("1,3");
            table.addCell("2,3");
            table.addCell("3,3");
            Cell cell2 = new Cell(new Paragraph("��ռ��4�� ", FontChinese));// ����һ�����Ԫ
            cell2.setColspan(4);// ���ñ���п��(�ϲ�4����Ԫ��)
            table.addCell(cell2); // ����Ԫ���뵽���
            table.addCell("3,1"); // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("2,1");
            table.addCell("2,2");
            table.addCell("3,2");
            table.addCell("1,3");
            table.addCell("3,3");
            table.addCell("1,3");
            Cell cell3 = new Cell(new Paragraph("��ռ��3�� ", FontChinese));// ����һ�����Ԫ
            cell3.setColspan(3);// ���ñ���п��(�ϲ�3����Ԫ��)
            table.addCell(cell3); // ����Ԫ���뵽���
            table.addCell("2,3");
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
