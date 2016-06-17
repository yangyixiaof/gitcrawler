package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class AddChapter {
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("C:\\����½�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Chapter chapter = new Chapter("This is chapter 1", 1);// ���������½ڶ������������
			document.add(chapter);// ���ĵ�������½�
			chapter = new Chapter("This is chapter 2", 2);// ���������½ڶ������������
			document.add(chapter);// ���ĵ�������½�
			chapter = new Chapter("This is chapter 3", 3);// ���������½ڶ������������
			document.add(chapter);// ���ĵ�������½�
			chapter = new Chapter("This is chapter 4", 4);// ���������½ڶ������������
			document.add(chapter);// ���ĵ�������½�
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
}
