package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class ImportAlreadyDocument {

	public static void main(String[] args) {
		try {
			PdfReader reader = new PdfReader("c:\\newPdfFile.pdf");// ���������ĵ���PdfReader����
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					"c:\\���������ĵ�.pdf"));// ���������ĵ��������
			stamp.close();// �ر�PdfStamper��������ĵ����빦��
			JOptionPane.showMessageDialog(null, "����ɹ�...\n��������ĵ��ĵ��롣");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
