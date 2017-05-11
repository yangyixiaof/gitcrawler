package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class PortraitShowPage {
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ����������ĵ���С
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"c:\\������ʾҳ��.pdf"));// �����ĵ������������
			Rectangle pageSize = new Rectangle(220, 150);// ������ʾҳ���С�ľ��ζ���,�þ��ζ����Ǻ�����ʾ��
			pageSize = pageSize.rotate();// ת��Ϊ����
			document.setPageSize(pageSize); // ����ҳ���С
			document.open();// ���ĵ�
			document.add(new Paragraph("Page Size"));// ���ĵ����������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}
}
