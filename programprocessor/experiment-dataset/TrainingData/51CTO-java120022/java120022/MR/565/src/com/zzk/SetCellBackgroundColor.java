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

public class SetCellBackgroundColor {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���õ�Ԫ��ı���ɫ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 12, Font.NORMAL);// ʵ��������
            document
                    .add(new Paragraph("                 Ϊ��Ԫ�������ɫ", FontChinese));
            Table table = new Table(5);// ������
            table.addCell("1.1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("2.1");
            table.addCell("3.1");
            table.addCell("2.1");
            table.addCell("2.2");
            Cell cell = new Cell();// ������Ԫ��
            cell.setBackgroundColor(Color.yellow);// Ϊ��Ԫ����䱳��ɫ
            table.addCell(cell); // ����Ԫ�����뵽���
            table.addCell("1.3");
            table.addCell("2.3");
            Cell cell2 = new Cell();// ������Ԫ��
            cell2.setBackgroundColor(Color.red);// Ϊ��Ԫ����䱳��ɫ
            table.addCell(cell2);
            table.addCell("3.0");
            table.addCell("3.1");
            table.addCell("2.1");
            Cell cell3 = new Cell();// ������Ԫ��
            cell3.setBackgroundColor(Color.green);// Ϊ��Ԫ����䱳��ɫ
            table.addCell(cell3);
            table.addCell("3.2");
            table.addCell("1.3");
            table.addCell("2.3");
            Cell cell4 = new Cell();// ������Ԫ��
            cell4.setBackgroundColor(Color.red);// Ϊ��Ԫ����䱳��ɫ
            table.addCell(cell4); // ����Ԫ�����뵽���
            table.addCell("2.1");
            table.addCell("2.2");
            Cell cell5 = new Cell();// ������Ԫ��
            cell5.setBackgroundColor(Color.blue);// Ϊ��Ԫ����䱳��ɫ
            table.addCell(cell5); // ����Ԫ�����뵽���
            table.addCell("1.3");
            table.addCell("2.3");
            table.addCell("3.3");
            table.addCell("3.0");
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
