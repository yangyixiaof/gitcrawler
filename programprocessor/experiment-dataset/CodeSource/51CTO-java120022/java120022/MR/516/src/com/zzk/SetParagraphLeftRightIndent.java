package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class SetParagraphLeftRightIndent {
	
	
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\Java��̴ʵ�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Paragraph paragraph1 = new Paragraph("The Paragraph IndentLeft");// ʵ�������䲢�������
			paragraph1.setIndentationLeft(100);// ����������
			document.add(paragraph1);// ���ĵ�����Ӷ���
			Paragraph paragraph2 = new Paragraph("The Paragraph IndentRight");// ʵ�������䲢�������
			paragraph2.setIndentationRight(100);// ����������
			document.add(paragraph2);// ���ĵ�����Ӷ���
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}