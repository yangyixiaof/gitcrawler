package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PrintDesktop extends JFrame {
    private PrinterJob job;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintDesktop frame = new PrintDesktop();
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
    public PrintDesktop() {
        super();
        setTitle("��ӡ����ͼƬ");
        job = PrinterJob.getPrinterJob();
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(scrollPane);
        
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        flowLayout.setHgap(30);
        panel_1.setLayout(flowLayout);
        scrollPane.setViewportView(panel_1);
        
        final PrintPanel printPanel = new PrintPanel();
        printPanel.setBorder(new LineBorder(Color.ORANGE, 2, false));
        printPanel.setName("printPanel");
        panel_1.add(printPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton getDesktopBtn = new JButton();
        getDesktopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    Robot robot = new Robot();// ����Robot���ʵ������
                    Dimension size = getToolkit().getScreenSize();// ��ȡ��Ļ��С
                    Rectangle screenRect = new Rectangle(0, 0, size.width,
                            size.height);// ������Ļ��С�ľ��ζ���
                    setVisible(false);// ���ش���
                    Thread.sleep(1000);// ����1����
                    BufferedImage image = robot.createScreenCapture(screenRect);// ץ����Ļͼ��
                    setVisible(true);// ��ʾ����
                    printPanel.setImage(image);// Ϊ��ӡ�������ͼ��
                    printPanel.repaint();// ���»��ƴ�ӡ���
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getDesktopBtn.setText("��ȡ����");
        panel.add(getDesktopBtn);
        
        final JButton pageSetBtn = new JButton();
        pageSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (job != null) {// �����ӡ����ΪNULL
                    printPanel.pageSet(job);// ���ô�ӡ����ҳ�����÷���
                }
            }
        });
        pageSetBtn.setText("ҳ������");
        panel.add(pageSetBtn);
        
        final JButton printBtn = new JButton();
        printBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean print = job.printDialog();// ��ʾ��ӡ�Ի���
                if (print) {// ����û�ȷ�ϴ�ӡ
                    try {
                        job.setJobName("��ӡ����ͼƬ");// ���ô�ӡ��������
                        job.setPrintable(printPanel);// ���ô�ӡҳ��Ϊ��ӡ���
                        job.print();// ��ʼ��ӡ����
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        printBtn.setText("��ӡ");
        panel.add(printBtn);
        //
    }
    
}
