package com.zzk;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class ImportAddNewPageContent {
	public static void main(String[] args) {
		try {
			PdfReader reader = new PdfReader("c:\\������һ��PDF�ĵ�.pdf");// �����ĵ�
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					"c:\\���벢�����ҳ������.pdf"));// �����ĵ��������
			BaseFont Chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
			PdfContentByte cb = stamp.getOverContent(1);// ��ȡ��һҳ����
			cb.beginText();// д����
			cb.setFontAndSize(Chinese, 25);// ������������
			cb.setTextMatrix(15, 15);// ���þ���(����)
			cb.showText("��һҳ");// ������ʾ�ı�
			cb.showTextAligned(Element.ALIGN_CENTER, "���������ݡ�", 180, 760, 0);// �����ı����룬���ݣ�λ�ú���ת�Ƕ�
			cb.endText();// ���ݽ���
			stamp.insertPage(2, PageSize.A4);// ������ҳ
			cb = stamp.getOverContent(2);// ��ȡ��2ҳ����
			cb.beginText();// д����
			cb.setFontAndSize(Chinese, 20);// ������������
			cb.showTextAligned(Element.ALIGN_LEFT, "��������ҳ����ӵ����ݡ�", 100, 600, 0);// �����ı����룬���ݣ�λ�ú���ת�Ƕ�
			cb.endText();// ���ݽ���
			stamp.close();// �ر�
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
