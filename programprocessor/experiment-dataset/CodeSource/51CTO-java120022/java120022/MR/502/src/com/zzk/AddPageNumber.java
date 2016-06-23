package com.zzk;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.exceptions.IllegalPdfSyntaxException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class AddPageNumber extends PdfPageEventHelper {

	public PdfTemplate pdfTemplate;// ����ģ�����
	public BaseFont baseFont;// ���������������

	public static void main(String[] args) {
		Document document = new Document(PageSize.A4);// ����A4ֽ�Ŵ�С��PDF�ĵ�����
		try {
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("C:\\���ҳ��.pdf"));// �����ĵ������������ 
			writer.setPageEvent(new AddPageNumber());// ���ҳ���¼�������
			document.open();// ���ĵ�
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
			Paragraph pargraph = new Paragraph("���ҳ�����ҳ����", new Font(bf, 15,
					Font.BOLD, BaseColor.BLUE));// �����������ָ������
			document.add(pargraph);// ���ĵ�����Ӷ���
			document.newPage();// ������ҳ
			document.add(pargraph);// ��Ӷ���
			document.newPage();// ������ҳ
			document.add(pargraph);// ��Ӷ���
			document.close();// �ر��ĵ�
		} catch (IllegalPdfSyntaxException de) {
			de.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onOpenDocument(PdfWriter writer, Document document) {// ���ĵ�ʱִ�и÷���
			pdfTemplate = writer.getDirectContent().createTemplate(180, 180);// ����ģ�����
			try {
				baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
						BaseFont.NOT_EMBEDDED);// ������������
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void onEndPage(PdfWriter writer, Document document) {// ����ҳʱִ�и÷���
		PdfContentByte cb = writer.getDirectContent();// �������ݶ���
		cb.saveState();// ����״̬
		cb.beginText();// �ı���ʼ���
		cb.setFontAndSize(baseFont, 12);// ����������ֺ�
		cb.setTextMatrix(260, 800);// �����ı���ʾλ��
		String page = "��" + writer.getPageNumber() + "ҳ/��";// �����ʾҳ����Ϣ�ı���
		cb.showText(page);// ��ʾ�ı�
		cb.endText();// �ı��������
		cb.addTemplate(pdfTemplate, 305, 800);// ���ģ�����
		cb.stroke();// ȷ�ϲ��������
		cb.restoreState();// �ָ����
		cb.closePath();// �ر�����ͨ��
	}

	public void onCloseDocument(PdfWriter writer, Document document) {// �ر��ĵ�ʱִ�и÷���
		pdfTemplate.beginText();// ģ���ı���ʼ���
		pdfTemplate.setFontAndSize(baseFont, 12);// ����ģ��������ֺ�
		int totalPages = writer.getPageNumber() - 1;// �����ҳ��
		pdfTemplate.showText(totalPages + "ҳ");// ��ʾ��ҳ����Ϣ
		pdfTemplate.endText();// ģ���ı��������
		pdfTemplate.closePath();// �ر�ģ��ͨ��
	}
}
