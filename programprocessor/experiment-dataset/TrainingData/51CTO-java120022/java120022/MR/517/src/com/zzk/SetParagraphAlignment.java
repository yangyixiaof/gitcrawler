package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class SetParagraphAlignment {
	
	
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Paragraph paragraph1 = new Paragraph("www.mingrosoft.com");// �������䲢�������
			paragraph1.setAlignment(Element.ALIGN_LEFT); // �����
			document.add(new Paragraph(paragraph1));     // ���ĵ���Ӷ���	
			Paragraph paragraph2 = new Paragraph("www.mingribook.com");// �������䲢�������
			paragraph2.setAlignment(Element.ALIGN_RIGHT);// �Ҷ���
			document.add(new Paragraph(paragraph2));	 // ���ĵ���Ӷ���
					
			Paragraph paragraph3 = new Paragraph("www.mingribccd.com"); // �������䲢�������
			paragraph3.setAlignment(Element.ALIGN_CENTER);// ���ж��� 
			document.add(new Paragraph(paragraph3));	  // ���ĵ���Ӷ���	
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	 }
}

