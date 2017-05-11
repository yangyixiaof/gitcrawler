package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author ������
 */
@SuppressWarnings("serial")
public class PrintShapeFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintShapeFrame frame = new PrintShapeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintShapeFrame() {
        super();
        setTitle("��ӡͼ��");
        getContentPane().setLayout(null);
        setBounds(100, 100, 281, 179);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    if (!job.printDialog()) // �򿪴�ӡ�Ի���
                        return;// ������ӡ�Ի����ȡ����ť��رմ�ӡ�Ի�����������ִ��
                    job.setPrintable(new Printable() {
                        // ʵ��print()���������ƴ�ӡ����
                        public int print(Graphics graphics,
                                PageFormat pageFormat, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D)graphics; // ���Graphics2D����
                            BasicStroke stroke = new BasicStroke(3); // ���������3�ıʻ�����
                            g2.setStroke(stroke);// ���ñʻ�����
                            Color color = new Color(0,162,232);// ������ɫ����
                            g2.setColor(color);// ������ɫ
                            g2.drawOval(30, 40, 60, 60);     // ���Ƶ�һ��Բ
                            color = new Color(233,123,16);   // �����µ���ɫ����
                            g2.setColor(color);// ������ɫ
                            g2.drawOval(60, 70, 60, 60);     // ���Ƶڶ���Բ
                            color = new Color(28,20,100);// �����µ���ɫ����
                            g2.setColor(color);// ������ɫ
                            g2.drawOval(92, 40, 60, 60);     // ���Ƶ�����Բ
                            color = new Color(0,255,0);// �����µ���ɫ����
                            g2.setColor(color);// ������ɫ
                            g2.drawOval(122, 70, 60, 60);    // ���Ƶ��ĸ�Բ
                            color = new Color(255,0,0);// �����µ���ɫ����
                            g2.setColor(color);// ������ɫ
                            g2.drawOval(154, 40, 60, 60);    // ���Ƶ����Բ
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("��ӡ�廷ͼ��"); // ���ô�ӡ���������
                    job.print(); // ����print()������ʼ��ӡ
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��ӡͼ��");
        button.setBounds(28, 56, 86, 28);
        getContentPane().add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        button_1.setBounds(151, 56, 86, 28);
        getContentPane().add(button_1);
        
        //
    }
    
}
