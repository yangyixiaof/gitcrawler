package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AddCreator {

	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\Java��̴ʵ�.pdf"));// �����ĵ������������
			document.addCreator("���տƼ�"); // ��Ӵ�����
			document.open();// ���ĵ�
			document.add(new Paragraph("Creator"));// ���ĵ����������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
