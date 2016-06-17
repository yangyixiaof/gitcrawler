package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class AddPageFoot {
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\ҳüҳ��.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			document.add(new Paragraph("Add Page Top And Foot.")); // ���ĵ����������
			document.close();// �ر��ĵ�����
			PdfReader reader = new PdfReader("c:\\ҳüҳ��.pdf");// ������ҳüҳ��.pdf����PdfReader����
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("c:\\���ҳüҳ��.pdf"));// ����PdfStamper����
			PdfContentByte over = stamp.getOverContent(1);// ��õ�һҳ������
			over.setTextRise(810);// �ı����Ƶ�810��λ��
			over.beginText();// ����ı���ʼ
			BaseFont chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// ��������
			over.setFontAndSize(chinese, 18);// ����������ֺ�
			over.showText("                                           ҳü������");// ���ҳü
			over.endText();// ����ı�����
			stamp.insertPage(2, PageSize.A4);// �����µ�һҳ��Ϊ��ҳ���ҳ��
			PdfContentByte under = stamp.getUnderContent(2);// ��õڶ�ҳ������
			under.setTextRise(15);// �ı����Ƶ�15��λ��
			under.beginText();// ����ı���ʼ
			under.setFontAndSize(chinese, 18);// ����������ֺ�
			under.showText("                                          ҳ�ŵ�����");// ���ҳ��
			under.endText();// ����ı�����
			stamp.close();// PdfStamper���󣬽��ӡ�ҳüҳ��.pdf���ж�ȡ���ĵ����ҳüҳ�ź�д�롰���ҳüҳ��.pdf��
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
