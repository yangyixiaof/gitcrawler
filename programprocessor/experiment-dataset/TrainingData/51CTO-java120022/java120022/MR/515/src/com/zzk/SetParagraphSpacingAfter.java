package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SetParagraphSpacingAfter {
	public static void main(String[] args){
		Document document = new Document();		//�����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java.pdf"));// �����ĵ������������
			document.open();					//���ĵ�
			Paragraph paragraph1 = new Paragraph("Java resource");	//���������������
			paragraph1.setSpacingBefore(10); 	//���ö����ϱ߾�
			paragraph1.setSpacingAfter(30); 	//���ö����±߾�
			document.add(paragraph1);			//���ĵ���Ӷ���
			Paragraph paragraph2 = new Paragraph("Java classes introduce");			//���������������
			paragraph2.setSpacingAfter(30); 	//���ö����±߾�
			document.add(paragraph2);			//���ĵ���Ӷ���
			document.close();					//�ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
