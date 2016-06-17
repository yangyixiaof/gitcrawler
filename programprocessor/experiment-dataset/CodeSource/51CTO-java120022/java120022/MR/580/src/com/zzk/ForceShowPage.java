package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ForceShowPage {
	

	public static void main(String[] args) {
		
        Document document = new Document();// �����ĵ�����
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\ǿ����һҳ��ʾ�����ܻᶪʧ����).pdf"));// �����ĵ������������	
            document.open();// ���ĵ�
            String[] data = { "C033010", "MX", "980", "350", "800", "999",
                    "655", "800", "23", "860" };// ����������Ϣ
            PdfPTable table = new PdfPTable(10);// ������
            int columnwidths[] = { 8, 3, 11, 10, 8, 6, 8, 12, 3, 6 };// �����п�
            table.setWidths(columnwidths);// ��������п�
            table.setWidthPercentage(100);// ������Ӿ��Կ��
            table.getDefaultCell().setPadding(3);// ���õ�Ԫ�����Ϊ3
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);// ���õ�Ԫ����ж���
            table.addCell("Number");// ����Ԫ������˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("Name");
            table.addCell("aggression");
            table.addCell("defend");
            table.addCell("reaction");
            table.addCell("shoot");
            table.addCell("header");
            table.addCell("bodybalance");
            table.addCell("age");
            table.addCell("speed");
            table.setHeaderRows(1);// Ϊ���ÿһҳ���ñ�ͷ
            for (int i = 1; i < 100; i++) {// ѭ�����������100����¼
                if (i % 2 == 1) {
                    table.getDefaultCell().setBackgroundColor(
                            BaseColor.LIGHT_GRAY);// �����ɫ
                } else {
                    table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);// �����ɫ
                }
                for (int x = 0; x < 10; x++) {
                    String var = data[x];// ��������е�����
                    for (int y = 0; y < i; y++) {
                        var += "\n" + y;// �����ַ������ɵ�Ԫ������
                    }
                    table.addCell(var);// Ϊ��Ԫ���������
                }
            }
     		table.setSplitRows(false);// ʹ��ǿ����һҳ��ʾ�����ǿ��ܻᶪʧ����
     		document.add(table);// ���ĵ���ӱ��
     		document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
	}
}
