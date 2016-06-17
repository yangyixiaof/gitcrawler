package com.zzk;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class TextFileToPdf {

	public static void main(String[] args) {
		// ���ı��ļ�oldTextFile.txtת��ΪPDF�ļ�newPdfFile.pdf
		txtFileToPdfFile("textFile\\oldTextFile.txt", "C:\\newPdfFile.pdf");
	}

	/**
	 * ���ı��ļ�ת��ΪPDF�ļ��ķ���
	 * @param txtFile ԭ�ı��ļ���·��
	 * @param pdfFile ����pdf�ļ���·��
	 */
	private static void txtFileToPdfFile(String txtFile, String pdfFile) {
		Document doc = new Document();// �����ĵ�����
		try {
			FileReader fileRead = new FileReader(txtFile);// �����ַ�������
			BufferedReader read = new BufferedReader(fileRead);// �����ַ�����������
			PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));// �����ĵ������������
			doc.open();// ���ĵ�
			BaseFont Chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
			Font fontChinese = new Font(Chinese, 18, Font.BOLDITALIC,
					BaseColor.BLUE);// ʵ���������ࡢ���������С����ɫ
			String line = null;// �洢���ı��ļ��ж�ȡ������
			while ((line = read.readLine()) != null) {// ��ȡһ����Ϣ
				doc.add(new Paragraph(line, fontChinese));// ����ȡ����Ϣ��ӵ��ĵ���
			}
			doc.close();// �ر��ĵ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
