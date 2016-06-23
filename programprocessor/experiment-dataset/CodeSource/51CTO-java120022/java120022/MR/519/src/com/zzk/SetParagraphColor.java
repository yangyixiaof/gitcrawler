package com.zzk;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SetParagraphColor {
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java���ο��ֲ�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Paragraph paragraph = new Paragraph(new Paragraph("JFrame Class Member List",FontFactory.getFont(FontFactory.HELVETICA, 30, BaseColor.BLUE )));// �������䶨�����岢�������
			paragraph.setFirstLineIndent(100); // ���ö�����������
			document.add(paragraph);// ���ĵ���Ӷ���
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
