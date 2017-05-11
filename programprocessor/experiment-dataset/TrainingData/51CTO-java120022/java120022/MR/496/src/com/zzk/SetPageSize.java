package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;


public class SetPageSize {
	public static void main(String[] args) {	
		Document document = new Document();// �����ĵ����������ĵ���С
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java���Ͽ�.pdf"));// �����ĵ������������
			Rectangle pageSize= new Rectangle(300, 100);// ����ҳ���С
			document.setPageSize(pageSize); // ����ҳ���С
			document.open();// ���ĵ�
			document.add(new Paragraph("Page Size: 300*100"));// ���ĵ����������   
	        document.close();// �ر��ĵ�  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}			          
	}
}


