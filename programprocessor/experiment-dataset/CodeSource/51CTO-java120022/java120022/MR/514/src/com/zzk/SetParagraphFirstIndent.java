package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class SetParagraphFirstIndent {
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\��˾���.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Paragraph P1 = new Paragraph("MR company was builded in 1999!");// �������䲢�������
			P1.setFirstLineIndent(20); 		//���ö�����������
			document.add(new Paragraph(P1));//���ĵ���Ӷ���
			Paragraph P2 = new Paragraph("Company own about for fifty employees.");//�������䲢�������
			document.add(P2);		//���ĵ���Ӷ���
			document.close();		//�ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
