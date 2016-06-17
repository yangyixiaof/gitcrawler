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
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AddTableInSection {

	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\��С������ӱ��.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			BaseFont Chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
			Font fontChinese1 = new Font(Chinese, 18, Font.BOLDITALIC,
					BaseColor.RED);// ʵ���������ࡢ���������С����ɫ
			Font fontChinese2 = new Font(Chinese, 15, Font.BOLDITALIC,
					BaseColor.BLUE);// ʵ���������ࡢ���������С����ɫ
			Font fontChinese3 = new Font(Chinese, 12, Font.NORMAL,
					BaseColor.BLACK);// ʵ���������ࡢ���������С����ɫ
			Paragraph paragraph = new Paragraph("�½�", fontChinese1);// �����������
			Chapter chapter = new Chapter(paragraph, 1);// �����½ڶ���
			paragraph = new Paragraph("С��", fontChinese2);// �����������
			Section section = chapter.addSection(paragraph);// ����������С�ڶ���
			PdfPTable table = new PdfPTable(3);// ����������
			table.addCell("1,1");// ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
			table.addCell("1,2");
			table.addCell("1,3");
			table.addCell("2,1");
			table.addCell("2,2");
			table.addCell("2,3");
			table.addCell("3,1");
			table.addCell("3,2");
			table.addCell("3,3");
			paragraph = new Paragraph("\n������С������ӵı��\n\n", fontChinese3);// ��������
			section.add(paragraph);// ��С����Ӷ�������
			section.add(table);// �������ӵ�С��
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
