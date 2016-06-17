package com.zzk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class EditPdfDocumentDemo {
	public static void main(String[] args) {
		createOldFile();// ����ԭ�ļ�
		editOldFile();// �༭ԭ�ļ�
	}

	public static void createOldFile() {// ����ԭ�ļ��ķ���
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter
					.getInstance(document, new FileOutputStream("c:\\ԭ�ĵ�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			document.add(new Paragraph("First")); // ���ĵ����������
			document.newPage();
			document.add(new Paragraph("Second")); // ���ĵ����������
			document.newPage();
			document.add(new Paragraph("Third")); // ���ĵ����������
			document.close();// �ر��ĵ�����
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static void editOldFile() {// �༭ԭ�ļ��ķ���
		try {
			PdfReader reader = new PdfReader("c:\\ԭ�ĵ�.pdf");// ������ԭ�ĵ�.pdf����PdfReader����
			int totalPages = reader.getNumberOfPages();// �����ҳ��
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					"c:\\�༭���ĵ�����ʱ�ļ�.pdf"));// ����PdfStamper����
			BaseFont chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// ��������
			PdfContentByte under = null;
			for (int i = 1; i <= totalPages; i++) {
				under = stamp.getUnderContent(i);// ���ÿһҳ������
				under.beginText();// ����ı���ʼ
				under.setFontAndSize(chinese, 18);// ����������ֺ�
				under.setTextMatrix(200, 810);// ����ҳ�����ʾλ��
				under.showText("��" + i + "ҳ");// ���ҳ��
				under.endText();// ����ı�����
				under.beginText();// ����ı���ʼ
				under.setFontAndSize(chinese, 32);// ����������ֺ�
				under.setTextMatrix(100, 750);// �����ı�����ʾλ��
				under.showText("����ӵ�����" + i);// ������ı�
				under.endText();// ����ı�����
			}
			stamp.close();// PdfStamper���󣬽��ӡ�ԭ�ĵ�.pdf���ж�ȡ���ĵ����ҳ���д�롰�༭���ĵ�����ʱ�ļ�.pdf��
			File oldFile = new File("c:\\ԭ�ĵ�.pdf");// ����ԭ�ļ���File����
			oldFile.delete();// ɾ��ԭ�ļ�
			File tempFile = new File("c:\\�༭���ĵ�����ʱ�ļ�.pdf");// ������ʱ�ļ���File����
			tempFile.renameTo(oldFile);// ��������ʱ�ļ�Ϊԭ�ļ���
			tempFile.delete();// ɾ����ʱ�ļ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
