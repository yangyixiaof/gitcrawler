package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class CreateAddParagraph {
	public static void main(String[] args){
		Document document = new Document();	//�����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java��̴ʵ�.pdf"));	//�����ĵ������������
			document.open();				//���ĵ�
			Paragraph P1 = new Paragraph("Java programming dictionary");	
			//�������䲢�������
			document.add(P1);		//���ĵ���Ӷ���
			Paragraph P2 = new Paragraph("The richest resource for learning");
			document.add(P2);		//���ĵ���Ӷ���		
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (DocumentException e) {
				e.printStackTrace();
		}
		finally{
			document.close();		//�ر��ĵ�	
		}
	}
}
