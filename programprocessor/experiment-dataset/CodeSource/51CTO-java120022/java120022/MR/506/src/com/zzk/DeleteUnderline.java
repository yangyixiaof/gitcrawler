package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class DeleteUnderline {
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\��˾���.pdf"));//�����ĵ������������
			document.open();// ���ĵ�
			document.add(new Paragraph("The company was builded in 1998!", FontFactory.getFont(FontFactory.COURIER, 15, Font.NORMAL | Font.STRIKETHRU)));	//���ĵ����������
			document.add(new Paragraph("The company was builded in 1999!"));	//���ĵ����������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
