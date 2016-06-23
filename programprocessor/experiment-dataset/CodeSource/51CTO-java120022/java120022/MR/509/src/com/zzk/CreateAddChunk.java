package com.zzk;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateAddChunk {
	
	
	public static void main(String args[]){
		Document document = new Document();	//�����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\Java.pdf"));// �����ĵ������������
			document.open();				//���ĵ�
			Chunk chunk1 = new Chunk("Text chunk1",FontFactory.getFont(FontFactory.COURIER_BOLD,15,Font.ITALIC));// �����鶨�����������Ժ��������
			document.add(chunk1);
			Chunk chunk2 = new Chunk("Text chunk2",FontFactory.getFont(FontFactory.COURIER_BOLD,30,Font.BOLD));// �����鶨�����������Ժ��������
			document.add(chunk2);
			document.close();				//�ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
