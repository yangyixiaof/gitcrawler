package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class SetDocumentFontDemo {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream("c://Java���.pdf"));	//�����ĵ������������
            document.open();							//���ĵ�
            BaseFont bfChinese = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);//�����������
            Font contentFont = new Font(bfChinese, 20);	//������ͨ����ʹ�С
            document.add(new Paragraph("Java Function Classes", contentFont));	//���ĵ���������ݲ�ָ����ͨ����
            document.close();							//�ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
}
