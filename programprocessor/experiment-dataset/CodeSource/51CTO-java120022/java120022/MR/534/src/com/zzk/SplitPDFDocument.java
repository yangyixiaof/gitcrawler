package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class SplitPDFDocument {
	public static void main(String[] args) {
		String filePathFile = "c:\\ԭ�ĵ�.pdf ";// ��Ҫ��ֵ�ԭ�ĵ�
		PdfReader reader = null;// ����PdfReader����
		try {
			reader = new PdfReader(filePathFile);// ����PdfReader����
		} catch (IOException e) {
			e.printStackTrace();
		}
		int pageN = reader.getNumberOfPages();// ��ȡ�ļ��ڵ�ҳ��
		for (int i = 0; i < pageN; i++) {// ѭ��������ҳ
			Document document = new Document(reader
					.getPageSizeWithRotation(i + 1));// �����ĵ� ͬʱ���ǰ��ѭ����ҳ
			PdfCopy copy = null;
			try {
				int len = filePathFile.length();// ����ļ�����·���ĳ���
				String noExt = filePathFile.substring(0, len - 5);// ȥ���ļ���չ�����·��
				String fileName = noExt + "-" + (i + 1) + ".pdf";// ��ֺ����ɵ��ļ�����
				copy = new PdfCopy(document, new FileOutputStream(fileName));// ���������������ĵ������������
				document.open();// ���ĵ�
				copy.addPage(copy.getImportedPage(reader, i + 1));// ���ݻ�õ�ҳ�������ĵ�
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
}
