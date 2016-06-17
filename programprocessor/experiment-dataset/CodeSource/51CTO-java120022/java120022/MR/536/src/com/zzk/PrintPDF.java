package com.zzk;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

public class PrintPDF {
    
    public static void main(String args[]) throws IOException,
            IllegalArgumentException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();// ������ӡ��ҵ
        PDDocument document = PDDocument.load("�������.pdf");// ��ȡ����ӡ���ĵ�
        Printable printable = new PDPageable(document);// ����Printable����
        job.setPrintable(printable);// ���ô�ӡ����
        job.print();// ��ӡ
        
    }
}
