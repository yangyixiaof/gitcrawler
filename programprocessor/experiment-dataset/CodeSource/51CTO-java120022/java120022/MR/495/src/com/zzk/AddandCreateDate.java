package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AddandCreateDate {
	public static void main(String args[]){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java��̴ʵ�.pdf"));// �����ĵ������������
			document.addAuthor("���տƼ�");	//�������
			document.addCreationDate();// ��������
			document.open();// ���ĵ�
			document.add(new Paragraph("CreateDate"));// ���ĵ����������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
