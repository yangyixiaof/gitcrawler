package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class SetSubSupScrip {
	public static void main(String[] args) {
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"C:\\�����ϱ���±�.pdf"));// �����ĵ������������
			document.open();
			BaseFont Chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
			Font fontChinese = new Font(Chinese, 20, Font.NORMAL);// ʵ���������������������С����
			document.add(new Paragraph("������ʹ���ϱ��Ч����", fontChinese));// ��Ӷ���
			Chunk chunk = new Chunk("X");// ������
			document.add(chunk);// ���ĵ��������
			chunk = new Chunk("2");// ������
			chunk.setTextRise(4.0f);// �������ı�
			document.add(chunk);// ��ӿ�
			chunk = new Chunk("+Y");// ������
			document.add(chunk);// ��ӿ�
			chunk = new Chunk("2");// ������
			chunk.setTextRise(4.0f);// �������ı�
			document.add(chunk);// ��ӿ�
			document.add(new Paragraph("������ʹ���±��Ч����", fontChinese));// ��Ӷ���
			chunk = new Chunk("M");// ������
			document.add(chunk);// ��ӿ�
			chunk = new Chunk("2");// ������
			chunk.setTextRise(-3.0f);// ���Ϳ��ı�
			document.add(chunk);// ��ӿ�
			chunk = new Chunk("+N");// ������
			document.add(chunk);// ��ӿ�
			chunk = new Chunk("2");// ������
			chunk.setTextRise(-3.0f);// ���Ϳ��ı�
			document.add(chunk);// ��ӿ�
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
