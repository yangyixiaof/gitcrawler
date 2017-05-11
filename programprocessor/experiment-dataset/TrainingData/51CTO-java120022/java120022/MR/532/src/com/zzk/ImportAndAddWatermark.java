package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class ImportAndAddWatermark {
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\ˮӡ.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			document.add(new Paragraph("No. One.")); // ���ĵ����������
			document.add(new Paragraph("No. Two.")); // ���ĵ����������
			document.add(new Paragraph("No. Three.")); // ���ĵ����������
			document.add(new Paragraph("No. Four.")); // ���ĵ����������
			document.add(new Paragraph("No. Five.")); // ���ĵ����������
			document.close();// �ر��ĵ�����
			PdfReader reader = new PdfReader("c:\\ˮӡ.pdf");// ������ˮӡ.pdf����PdfReader����
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("c:\\���벢���ˮӡ.pdf"));// ����PdfStamper����
			Image img = Image.getInstance("image/watermark.jpg");// д������
			img.setAbsolutePosition(30, 385);// ��λͼƬ����
			PdfContentByte under = stamp.getUnderContent(1);// ��õ�һҳ������
			under.addImage(img);// ���ͼƬ,���ˮӡ����
			stamp.close();// PdfStamper���󣬽��ӡ�ˮӡ.pdf���ж�ȡ���ĵ����ˮӡ��д�롰���벢���ˮӡ.pdf��
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
