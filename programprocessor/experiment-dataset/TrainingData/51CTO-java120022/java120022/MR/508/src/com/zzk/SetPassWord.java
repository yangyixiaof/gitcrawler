package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SetPassWord {
	
	
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\��������.pdf"));// �����ĵ������������
			writer.setEncryption("zzk".getBytes(), "123".getBytes(),
					PdfWriter.ALLOW_COPY, PdfWriter.STANDARD_ENCRYPTION_128);// ������������ͳ���
			document.open();// ���ĵ�
			document.add(new Paragraph("Set Encryption"));// ���ĵ��������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
