package com.zzk; 

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AddTitle {
		
	
	public static void main(String[] args){
		Document document=new Document();//�����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\��ӱ���.pdf"));// �����ĵ������������
			document.addTitle("Java��̴ʵ�");// ���ĵ�����ӱ���
			document.open();// ���ĵ�
			document.add(new Paragraph("Add Title"));// ���ĵ����������
			
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}      
	}
}
