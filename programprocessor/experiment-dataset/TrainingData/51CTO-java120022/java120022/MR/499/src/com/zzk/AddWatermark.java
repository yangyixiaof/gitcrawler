package com.zzk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class AddWatermark {
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"c:\\tempWatermark.pdf"));// �����ĵ���������ʱ�ļ��������
			document.open();// ���ĵ�
			document.add(new Paragraph(" ")); // ���ĵ����������
			document.close();// �ر��ĵ�����
			PdfReader reader = new PdfReader("c:\\tempWatermark.pdf");// ������tempWatermark.pdf����PdfReader����
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					"c:\\���ˮӡ.pdf"));// ����PdfStamper����
			Image img = Image.getInstance("image/watermark.jpg");// ����ͼ�����
			img.setAbsolutePosition(50, 385);// ��λͼƬ����
			PdfContentByte under = stamp.getUnderContent(1);// ��õ�һҳ������
			under.addImage(img);// ���ͼƬ,���ˮӡ����
			BaseFont chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// ��������
			under.beginText();// ����ı���ʼ
			under.setFontAndSize(chinese, 42);// ����������ֺ�
			under.setTextMatrix(70, 550);// ����������ݵ���ʾλ��
			under.showText("��������ӵ�ˮӡͼƬ.");// �������
			under.endText();// ����ı�����
			stamp.close();// PdfStamper���󣬽��ӡ�tempWatermark.pdf���ж�ȡ���ĵ����ˮӡ��д�롰���ˮӡ.pdf��
			File file = new File("c:\\tempWatermark.pdf");// ������ʱ�ļ���File����
			file.delete();// ɾ����ʱ�ļ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
