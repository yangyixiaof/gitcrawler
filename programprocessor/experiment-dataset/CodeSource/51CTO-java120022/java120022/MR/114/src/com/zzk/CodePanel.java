package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import jbarcodebean.JBarcodeBean;

public class CodePanel extends JPanel {
    private PageFormat format;// ����ҳ���ʽ
    private PrinterJob job = PrinterJob.getPrinterJob();// ��ô�ӡ����
    
    /**
     * Create the panel
     */
    public CodePanel() {
        super();
        setBackground(Color.WHITE);
        final GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setHgap(-2);
        setLayout(gridLayout);
        format = new PageFormat();
        format.setOrientation(PageFormat.LANDSCAPE);
        
        final JBarcodeBean barcodeBean_9 = new JBarcodeBean();// �������������
        barcodeBean_9.setBorder(new TitledBorder(null, "Code 11 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));// ����������ı߿�
        barcodeBean_9.setShowText(true);// ������ʾ�������ϵı���
        barcodeBean_9.setCodeType(new jbarcodebean.Code11());// ���������������
        add(barcodeBean_9);
        
        final JBarcodeBean barcodeBean = new JBarcodeBean();
        barcodeBean.setBorder(new TitledBorder(null, "Code 128 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean.setAngleDegrees(0);
        barcodeBean.setCodeType(new jbarcodebean.Code128());
        barcodeBean.setShowText(true);
        add(barcodeBean);
        
        final JBarcodeBean barcodeBean_1 = new JBarcodeBean();
        barcodeBean_1.setBorder(new TitledBorder(null, "Code 39 3:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_1.setShowText(true);
        add(barcodeBean_1);
        
        final JBarcodeBean barcodeBean_2 = new JBarcodeBean();
        barcodeBean_2.setBorder(new TitledBorder(null, "Code 39 2:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_2.setShowText(true);
        barcodeBean_2.setCodeType(new jbarcodebean.Code39_2to1());
        add(barcodeBean_2);
        
        final JBarcodeBean barcodeBean_3 = new JBarcodeBean();
        barcodeBean_3.setShowText(true);
        barcodeBean_3.setBorder(new TitledBorder(null, "Ext Code 39 3:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_3.setCodeType(new jbarcodebean.ExtendedCode39());
        add(barcodeBean_3);
        barcodeBean_3.setPreferredSize(new Dimension(barcodeBean_3.getWidth(),
                0));
        
        final JBarcodeBean barcodeBean_4 = new JBarcodeBean();
        barcodeBean_4.setBorder(new TitledBorder(null, "Ext Code 39 2:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_4.setShowText(true);
        barcodeBean_4.setCodeType(new jbarcodebean.ExtendedCode39_2to1());
        add(barcodeBean_4);
        
        final JBarcodeBean barcodeBean_5 = new JBarcodeBean();
        barcodeBean_5.setBorder(new TitledBorder(null, "Code 93 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_5.setShowText(true);
        barcodeBean_5.setCodeType(new jbarcodebean.Code93());
        add(barcodeBean_5);
        
        final JBarcodeBean barcodeBean_6 = new JBarcodeBean();
        barcodeBean_6.setBorder(new TitledBorder(null, "Code 93 Extended ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_6.setShowText(true);
        barcodeBean_6.setCodeType(new jbarcodebean.Code93Extended());
        add(barcodeBean_6);
        
        final JBarcodeBean barcodeBean_7 = new JBarcodeBean();
        barcodeBean_7.setBorder(new TitledBorder(null, "Interleaved 25 3:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_7.setShowText(true);
        barcodeBean_7.setCodeType(new jbarcodebean.Interleaved25());
        add(barcodeBean_7);
        
        final JBarcodeBean barcodeBean_8 = new JBarcodeBean();
        barcodeBean_8.setBorder(new TitledBorder(null, "Interleaved 25 2:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_8.setShowText(true);
        barcodeBean_8.setCodeType(new jbarcodebean.Interleaved25_2to1());
        add(barcodeBean_8);
        
        final JBarcodeBean barcodeBean_10 = new JBarcodeBean();
        barcodeBean_10.setBorder(new TitledBorder(null,
                "MSI (mod 10 check) ����", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        add(barcodeBean_10);
        barcodeBean_10.setShowText(true);
        barcodeBean_10.setCodeType(new jbarcodebean.MSI());
        
        final JBarcodeBean barcodeBean_11 = new JBarcodeBean();
        barcodeBean_11.setBorder(new TitledBorder(null, "Codabar 3:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_11.setShowText(true);
        barcodeBean_11.setCodeType(new jbarcodebean.Codabar());
        add(barcodeBean_11);
        
        final JBarcodeBean barcodeBean_12 = new JBarcodeBean();
        barcodeBean_12.setBorder(new TitledBorder(null, "Codabar 2:1 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        barcodeBean_12.setShowText(true);
        barcodeBean_12.setCodeType(new jbarcodebean.Codabar_2to1());
        add(barcodeBean_12);
        
        final JBarcodeBean barcodeBean_13 = new JBarcodeBean();
        barcodeBean_13.setBorder(new TitledBorder(null, "EAN-13 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        add(barcodeBean_13);
        barcodeBean_13.setShowText(true);
        barcodeBean_13.setCodeType(new jbarcodebean.Ean13());
        
        final JBarcodeBean barcodeBean_14 = new JBarcodeBean();
        barcodeBean_14.setBorder(new TitledBorder(null, "EAN-8 ����",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        add(barcodeBean_14);
        barcodeBean_14.setShowText(true);
        barcodeBean_14.setCodeType(new jbarcodebean.Ean8());
        //
    }
    
    /**
     * ҳ�����õķ���
     */
    public void pageSetup() {
        if (job != null) {
            format = job.pageDialog(format);// ��ҳ�����öԻ���
            repaint();// ���»��ƽ���
        }
    }
    
    /**
     * ִ�д�ӡ�����ķ���
     */
    public void doPrint() {
        if (job != null) {
            try {
                if (job.printDialog()) {// ����û�ȷ�ϴ�ӡ
                    job.setJobName("�����������ӡ");// ���ô�ӡ��������
                    Printable page = PreviewDialog.getPage();// ��ȡҳ�����
                    job.setPrintable(page);// ���ô�ӡҳ��
                    job.print();// ִ�д�ӡ
                }
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    public PageFormat getFormat() {
        return format;// ���ҳ���ʽ
    }
}
