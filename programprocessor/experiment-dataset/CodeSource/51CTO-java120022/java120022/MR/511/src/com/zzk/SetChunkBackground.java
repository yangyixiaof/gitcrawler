package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;


public class SetChunkBackground {
	
	
	public static void main(String[] args){
		Document document = new Document();// �����ĵ�����
		try {
			PdfWriter.getInstance(document, new FileOutputStream("c:\\Java���ȫ�ܴʵ�.pdf"));// �����ĵ������������
			document.open();// ���ĵ�
			Chunk chunk = new Chunk("Compile once, Run all!");// ����鲢�������
			chunk.setBackground(BaseColor.LIGHT_GRAY); // ���ñ�����ɫ
			document.add(chunk);// ��ӱ�����ɫ
			document.close();// �ر��ĵ�
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
