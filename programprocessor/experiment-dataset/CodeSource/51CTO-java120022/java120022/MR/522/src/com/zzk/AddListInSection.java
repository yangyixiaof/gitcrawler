package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class AddListInSection {
	
	
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\��С��������б�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			BaseFont Chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������   
			Font fontChinese1 = new Font(Chinese, 18, Font.BOLDITALIC,BaseColor.RED);// ʵ���������ࡢ���������С����ɫ
			Font fontChinese2 = new Font(Chinese, 15, Font.BOLDITALIC,BaseColor.BLUE);// ʵ���������ࡢ���������С����ɫ
			Font fontChinese3 = new Font(Chinese, 12, Font.NORMAL,BaseColor.BLACK);// ʵ���������ࡢ���������С����ɫ
			Paragraph paragraph = new Paragraph("�½�",fontChinese1);// �����������
			Chapter chapter = new Chapter(paragraph,1);// �����½ڶ���
			paragraph = new Paragraph("С��",fontChinese2);// �����������
			Section section = chapter.addSection(paragraph);// ����������С�ڶ���
			paragraph = new Paragraph("\nС������ӵ��б����£�\n\n",fontChinese3);// �����������
			section.add(paragraph);// ��С����Ӷ���
			List list = new List(true, false, 10);// �����б�
			list.add(new ListItem("С���е��б�һ",fontChinese3));// ���б��������
			list.add(new ListItem("С���е��б��",fontChinese3));
			list.add(new ListItem("С���е��б���",fontChinese3));
			section.add(list);// ��С������б�
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
