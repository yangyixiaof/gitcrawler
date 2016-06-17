package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class NonFullScreenPageModeUseOutlines {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\ȫ��ģʽ����ʾ���.pdf"));// �����ĵ������������
            writer.setViewerPreferences(PdfWriter.PageModeFullScreen);// ����Ϊȫ��ģʽ
            writer
                    .setViewerPreferences(PdfWriter.NonFullScreenPageModeUseOutlines);// �����Ķ�����ȫ��ģʽ����ʾ���
            document.open();// ���ĵ�
            BaseFont Chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������   
            Font fontChinese1 = new Font(Chinese, 18, Font.BOLDITALIC,BaseColor.RED);// ʵ���������ࡢ���������С����ɫ
            Font fontChinese2 = new Font(Chinese, 15, Font.BOLDITALIC,BaseColor.BLUE);// ʵ���������ࡢ���������С����ɫ
            Paragraph paragraph = new Paragraph("�½�",fontChinese1);// �����������
            Chapter chapter = new Chapter(paragraph,1);// �����½ڶ���
            paragraph = new Paragraph("С��һ",fontChinese2);// �����������
            chapter.addSection(paragraph);// ���С��
            paragraph = new Paragraph("С�ڶ�",fontChinese2);// �����������
            chapter.addSection(paragraph);// ���С��
            document.add(chapter);// ���ĵ�����½�
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
