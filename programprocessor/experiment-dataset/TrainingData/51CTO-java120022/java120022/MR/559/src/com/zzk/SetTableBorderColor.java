package com.zzk;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class SetTableBorderColor {
    
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���ñ��ı߿���ɫ.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// ʵ��������
            Table table = new Table(3);// ������
            document.add(new Paragraph("                  Ĭ�ϱ߿���ɫ�ı��",
                    fontChinese));// ���ĵ��������
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("Cell1.1");
            table.addCell("Cell1.2");
            table.addCell("Cell1.3");
            table.addCell("Cell2.1");
            table.addCell("Cell2.2");
            table.addCell("Cell2.3");
            Table table1 = new Table(3);// ������
            table1.setBorderColor(Color.BLUE);// ���ñ��߿���ɫ
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table1.addCell("Cell1.1");
            table1.addCell("Cell1.2");
            table1.addCell("Cell1.3");
            table1.addCell("Cell2.1");
            table1.addCell("Cell2.2");
            table1.addCell("Cell2.3");
            document.add(table);// �������ӵ��ĵ�
            document.add(new Paragraph("                  ���ñ߿���ɫ��ı��",
                    fontChinese));// ���ĵ��������
            document.add(table1);// �������ӵ��ĵ�
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