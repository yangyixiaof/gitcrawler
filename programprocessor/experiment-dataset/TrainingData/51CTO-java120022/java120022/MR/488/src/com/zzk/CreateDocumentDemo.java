package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateDocumentDemo {
    
    /**
     * @param args
     */
	public static void main(String[] args) {
        try {
            Document document = new Document();				// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream("c:\\������һ��PDF�ĵ�.pdf"));// �����ĵ������������
            document.open();							// ���ĵ�
            document.add(new Paragraph("First Document."));		// ���ĵ����������
            document.add(new Paragraph("Success."));			// ���ĵ����������
            document.close();							// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
    }

}
