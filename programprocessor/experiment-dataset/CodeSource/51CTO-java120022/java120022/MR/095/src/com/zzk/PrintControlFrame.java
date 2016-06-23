package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
public class PrintControlFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintControlFrame frame = new PrintControlFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintControlFrame() {
        super();
        setTitle("ʵ�ִ�ӡ");
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
                                return Printable.NO_SUCH_PAGE;// ���ظ�ֵ��ʾû�д�ӡҳ
                            Graphics2D g2 = (Graphics2D) graphics; // ���ͼ�������Ķ���
                            g2.setColor(Color.BLUE); // ���õ�ǰ��ͼ��ɫ
                            Font font = new Font("����", Font.BOLD, 24);
                            g2.setFont(font); // ��������
                            g2.drawString("��ҹ˼", 80, 40); // �����ı�
                            font = new Font("����", Font.BOLD | Font.ITALIC, 18);
                            g2.setFont(font); // ��������
                            g2.drawString("���", 130, 70); // �����ı�
                            font = new Font("����", Font.PLAIN, 14);
                            g2.setFont(font); // ��������
                            g2.drawString("��ǰ���¹⣬", 40, 100); // �����ı�
                            g2.drawString("���ǵ���˪��", 120, 100); // �����ı�
                            g2.drawString("��ͷ�����£�", 40, 120); // �����ı�
                            g2.drawString("��ͷ˼���硣", 120, 120); // �����ı�
                            return Printable.PAGE_EXISTS;// ���ظ�ֵ��ʾ���ڴ�ӡҳ
                        }
                    });
                    job.setJobName("��ӡ��ʫ����ҹ˼��"); // ���ô�ӡ���������
                    job.print(); // ����print()������ʼ��ӡ
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��    ӡ");
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
