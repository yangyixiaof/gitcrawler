package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.*;


public class HandleChineseErrorCode {
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\�����ٵ�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			BaseFont Chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������   
			Font FontChinese = new Font(Chinese, 20, Font.NORMAL);// ʵ���������������������С����           
			document.add(new Paragraph("�й���һ��ʵ���˽������ͽ�������һ��Ŀ��", FontChinese));// ���ĵ���������ݲ�ָ������
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