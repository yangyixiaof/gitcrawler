package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ReportToPdfFrame extends JFrame {
    
    private JTable table;
    private String[] title = { "���", "����", "�Ա�", "����", "��ַ", "�ʱ�", "�绰" };
    
    /**
     * @return JTable���ı�������
     */
    public Vector getTitleVector() {
        Vector titleVec = new Vector();
        for (int i = 0; i < title.length; i++) {
            titleVec.add(title[i]);
        }
        return titleVec;
    }
    
    /**
     * @return JTable������������
     */
    public Vector getDataVectors() {
        ResultSet rs = QueryResultSet.gainRecord();
        Vector dataVector = new Vector();
        try {
            while (rs.next()) {
                Vector rowVector = new Vector();
                for (int i = 0; i < title.length; i++) {
                    if (i == 0 || i == 3) {
                        rowVector.add(rs.getInt(i + 1));
                    } else {
                        rowVector.add(rs.getString(i + 1));
                    }
                }
                dataVector.add(rowVector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataVector;
    }
    
    public void toPdf() {
        try {
            Document document = new Document(PageSize.A4, 71.4f, 71.4f, 71.4f, 71.4f);// �����ĵ���A4ֽ�ŵĸ�ʽ��ӡ
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\report.pdf"));// ���������λ�ü��ļ���
            document.open();// ���ĵ�
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �����������
            Font titleFont = new Font(bfChinese, 12, Font.BOLD);// ����Ӵ����壬�������ñ����������
            Font contentFont = new Font(bfChinese, 12);// ������ͨ����
            PdfPCell cell = null;// ����PDF��Ԫ��
            PdfPTable table = new PdfPTable(new float[] { 40.9f, 40.9f, 40.9f,
                    40.9f, 79.1f, 40.9f, 79.1f });// ����һ��7�е�pdf���
            table.setTotalWidth(460);// ���ñ��Ŀ��Ϊ460
            table.setLockedWidth(true);// �������Ŀ��
            for (int i = 0; i < title.length; i++) {
                cell = new PdfPCell(new Paragraph(title[i], titleFont));// ������Ԫ�����
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// ��Ԫ������ˮƽ����
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// ��Ԫ�����ݴ�ֱ����
                cell.setFixedHeight(20.0f);// ���õ�Ԫ��ĸ߶�
                table.addCell(cell);// Ϊ�����ӵ�Ԫ��
            }
            
            ResultSet rs = QueryResultSet.gainRecord();// ������ݿ��Ľ��������
            while (rs.next()) {// ���������
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// �����ŵ�Ԫ�����ݵı���
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// �Ӽ�¼����õ�Ԫ������
                    } else {
                        cellContent = rs.getString(i + 1);// �Ӽ�¼����õ�Ԫ������
                    }
                    cell = new PdfPCell(new Paragraph(cellContent, contentFont));// ������Ԫ�����
                    if (i == 6) {
                        cell.setBorderWidthRight(0.01f);// ��Ԫ���ұ߿�Ŀ��
                    } else {
                        cell.setBorderWidthRight(0f);// ��Ԫ���ұ߿�Ŀ��
                    }
                    cell.setBorderWidthTop(0f);// ��Ԫ���ϱ߿�Ŀ��
                    cell.setBorderWidthBottom(0.01f);// ��Ԫ���±߿�Ŀ��
                    cell.setFixedHeight(20.0f);// ��Ԫ��ĸ߶�
                    table.addCell(cell);// Ϊ�����ӵ�Ԫ��
                }
            }
            titleFont = new Font(bfChinese, 36, Font.BOLD);// ����Ӵ����壬�������ñ��������
            Paragraph pdfTitle = new Paragraph("Ա��������Ϣ����\n\n", titleFont);// ��������ָ�������Paragraph����
            pdfTitle.setAlignment(Element.ALIGN_CENTER);// ����Paragraph����
            document.add(pdfTitle);// ΪPDF�ĵ���ӱ���
            document.add(table);// ΪPDF�ĵ���ӱ��
            document.close();// �ر��ĵ�
            JOptionPane.showMessageDialog(null, "�Ѿ��ɹ��ؽ�����\n��������C:/report.pdf���С�");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToPdfFrame frame = new ReportToPdfFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public ReportToPdfFrame() {
        super();
        setTitle("��������PDF�ĵ�");
        setBounds(100, 100, 383, 264);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                toPdf();
            }
        });
        button.setText("����ΪPDF");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�˳�ϵͳ");
        panel.add(button_1);
        //
    }
}
