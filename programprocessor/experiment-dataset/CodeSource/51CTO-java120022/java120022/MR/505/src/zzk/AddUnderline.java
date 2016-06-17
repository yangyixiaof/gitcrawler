package zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AddUnderline {
	
	
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\���տƼ����.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			document.add(new Paragraph("Generalize"));// ���ĵ����������
			document.add(new Paragraph("Welcome to GuangZhou��", FontFactory.getFont(FontFactory.HELVETICA,15,Font.UNDERLINE)));// ���ĵ����������
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}

