package com.zzk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class ReadPwdPdfFile {

	public static void main(String[] args) throws MalformedURLException {
		try {
			PdfReader reader = new PdfReader("c:\\��������.pdf", "123".getBytes());// ������ˮӡ.pdf����PdfReader����
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					"c:\\TempFile.pdf"));// ����PdfStamper����
			stamp.close();// �ر�PdfStamper���󣬲����ӡ���������.pdf���ж�ȡ������д�롰TempFile.pdf��
			Document document = new Document();// �����ĵ�����
			File file = new File("c:\\TempFile.pdf");// ����File����
			try {
				FileInputStream in = new FileInputStream(file);// ��������������
				PDFParser parser = new PDFParser(in);// ����PDF
				parser.parse();// ����PDF�ĵ�
				PDDocument pdfdocument = parser.getPDDocument();// ��ý������PDF�ĵ�
				PDFTextStripper stripper = new PDFTextStripper();// ����PDF�ı�������
				String msg = stripper.getText(pdfdocument);// ʹ�ð�������PDF�ĵ��а����ı���Ϣ
				System.out.println("��ȡ�����ܵ�PDF�ı���Ϣ����:\n" + msg);// �����Ϣ
				in.close();// �ر�����������
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();// �ر��ĵ�
			file.delete();// ɾ����TempFile.pdf��
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
