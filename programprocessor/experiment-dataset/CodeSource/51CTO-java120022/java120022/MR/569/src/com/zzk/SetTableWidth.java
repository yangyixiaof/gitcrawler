package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SetTableWidth {
    
    public static void main(String[] args) {
        
        try {
            Document document = new Document();// �����ĵ�����
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\���ñ����.pdf"));// �����ĵ������������
            document.open();// ���ĵ�
            float[] widths = { 0.05f, 0.10f, 0.30f, 0.55f };// �����п���ر���Ϊ5%,10%,30%,55%
            PdfPTable table = new PdfPTable(widths);// �����������п�
            table.setWidthPercentage(60);// Ϊ������ðٷֱȿ��
            // ����Ԫ��˳�εļ��뵽��񣬵�һ�г���ʱ�Զ�����
            table.addCell("5%");
            table.addCell("10%");
            table.addCell("30%");
            table.addCell("55%");
            table.addCell("w");
            table.addCell("1");
            table.addCell("2");
            table.addCell("3");
            table.addCell("h");
            table.addCell("1");
            table.addCell("2");
            table.addCell("3");
            table.addCell("s");
            table.addCell("1");
            table.addCell("2");
            table.addCell("3");
            document.add(table);// �������ӵ��ĵ�
            document.close();// �ر��ĵ�
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
