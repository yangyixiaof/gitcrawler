package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class ZoomInFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8631064680832300933L;
    private JPanel contentPane;
    private PDFPage pdfPage;
    private JPanel pdfPanel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ZoomInFrame frame = new ZoomInFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ZoomInFrame() {
        setTitle("\u5E94\u7528PDF Renderer\u7EC4\u4EF6\u5B9E\u73B0\u653E\u5927PDF\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6");
        chooseButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(chooseButton);
        
        JButton zoominButton = new JButton("\u653E\u5927\u6587\u4EF6");
        zoominButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        zoominButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_zoominButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(zoominButton);
        
        pdfPanel = new JPanel();
        contentPane.add(pdfPanel, BorderLayout.CENTER);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF�ļ�", "pdf"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = fileChooser.getSelectedFile();
            try {
                pdfPage = getPDFPage(selectFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PagePanel pagePanel = new PagePanel();
            pdfPanel.add(pagePanel);
            validate();
            pagePanel.showPage(pdfPage);
        }
    }
    
    private static PDFPage getPDFPage(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdfFile = new PDFFile(buffer);
        return pdfFile.getPage(0);
    }
    
    protected void do_zoominButton_actionPerformed(ActionEvent arg0) {
        if (pdfPage == null) {
            JOptionPane.showMessageDialog(this, "��ѡ��PDF�ļ�", null, JOptionPane.WARNING_MESSAGE);
            return;
        }
        Rectangle rect = new Rectangle(0, 0, (int) pdfPage.getBBox().getWidth(), (int) pdfPage.getBBox().getHeight());// ����û�ѡ�е�PDFҳ��ı߿�
        // ���û�ѡ�е�PDFҳ��ת����ͼƬ������Ŵ�һ��
        Image image = pdfPage.getImage(rect.width * 2, rect.height * 2, rect, null, true, true);
        if (pdfPanel != null) {
            pdfPanel.removeAll();// ���JPanel�ؼ������Ŀؼ�
        }
        pdfPanel.add(new JLabel(new ImageIcon(image)));// ��JLabel�ķ�ʽ��ʾ�Ŵ��ͼƬ
        validate(); // ��֤�����������ӿؼ�
        repaint(); // ����JPanel
        
    }
}
