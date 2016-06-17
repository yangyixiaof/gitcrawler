package com.zzk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class UnitePDFDocument {
    
    public static void main(String[] args) {
        String[] subFiles = { "c:\\ԭ�ĵ�-1.pdf", "c:\\ԭ�ĵ�-2.pdf", "c:\\ԭ�ĵ�-3.pdf" }; // ���ϲ���PDF�ĵ�
        String newFile = "C:\\�ϲ����.pdf";// �ϲ�������ĵ�
        Document document = new Document();// �����ı��ĵ�
        try {
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newFile));// ����copy��������ĵ��������
            document.open();// ���ĵ�
            for (int i = 0; i < subFiles.length; i++) {// ��ѭ�� ��ȡ���ϲ��ļ�����
                PdfReader reader = new PdfReader(subFiles[i]);// ��ȡ���ϲ��ļ�����
                int totalPages = reader.getNumberOfPages();// ���ÿ�����ĵ�����ҳ��
                for (int p = 1; p <= totalPages; p++) {// �������ĵ���ÿһҳ
                    copy.addPage(copy.getImportedPage(reader, p));// �����ĵ���ÿһҳ����ӵ����ĵ���
                }
            }
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
