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

public class ExcursionTable {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ƫ�Ʊ��.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font FontChinese = new Font(Chinese, 10, Font.NORMAL);// ʵ��������
            Table table = new Table(3);// �������
            table.setBorderWidth(1);// ���ñ��߿���
            table.setBorderColor(Color.blue);// ���ñ��߿���ɫ
            table.setSpacing(5);// ���ñ���뵥Ԫ��ļ��
            table.setPadding(5);// ���õ�Ԫ�������ݵļ��
            table.addCell("1.1");// ��ӵ�Ԫ��
            table.addCell("1.2");
            table.addCell("1.3");
            document
                    .add(new Paragraph("                     ԭ���", FontChinese));// ���ĵ��������
            document.add(table);// ���ĵ���ӱ��
            document.add(new Paragraph("                     Ĭ�ϵľ��롣",
                    FontChinese));
            document.add(table);
            document.add(new Paragraph("                     ���ñ��ƫ��ֵΪ0�ľ��롣",
                    FontChinese));
            table.setOffset(0);// ���ñ��ƫ����ֵ
            document.add(table);
            document.add(new Paragraph("                     ���ñ��ƫ��ֵΪ-15�ľ��롣",
                    FontChinese));
            table.setOffset(-15);// ���ñ��ƫ����ֵ
            document.add(table);
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
