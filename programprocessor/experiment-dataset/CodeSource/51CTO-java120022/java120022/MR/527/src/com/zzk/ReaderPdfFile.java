package com.zzk;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import com.itextpdf.text.Document;

public class ReaderPdfFile {

	public static void main(String[] args) throws MalformedURLException {
		Document document = new Document();// �����ĵ�����
		File file = new File("c:\\������һ��PDF�ĵ�.pdf");// ����File����
		try {
			FileInputStream in = new FileInputStream(file);// ��������������
			PDFParser parser = new PDFParser(in);// ����PDF������
			parser.parse();// ����PDF�ĵ�
			PDDocument pdfdocument = parser.getPDDocument();// ��ý������PDF�ĵ�
			PDFTextStripper stripper = new PDFTextStripper();// ����PDF�ı�������
			String msg = stripper.getText(pdfdocument);// ʹ�ð�������PDF�ĵ��а����ı���Ϣ
			System.out.println("��ȡ����PDF�ı���Ϣ����:\n" + msg);// �����Ϣ
			in.close();// �ر�����������
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();// �ر��ĵ�
	}
}
