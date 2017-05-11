package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import javax.swing.UIManager;
import java.awt.Font;

public class HandleFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7985884042293882592L;
    private JPanel contentPane;
    private PDFPage pdfPage;
    private JPanel pdfPanel;
    private Point pressedPoint;
    private boolean isDragged;
    private PagePanel pagePanel;
    
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
                    HandleFrame frame = new HandleFrame();
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
    public HandleFrame() {
        setTitle("\u5E94\u7528PDF Renderer\u7EC4\u4EF6\u5B9E\u73B0\u6293\u624B\u529F\u80FD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.SOUTH);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6");
        chooseButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(chooseButton);
        
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
            pagePanel = new PagePanel();
            pagePanel.addMouseListener(new MouseAction());
            pagePanel.addMouseMotionListener(new MouseMotionAction());
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
        return pdfFile.getPage(2);
    }
    
    private class MouseAction extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent arg0) {
            pressedPoint = new Point(arg0.getX(), arg0.getY());
            isDragged = true;
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        public void mouseReleased(MouseEvent arg0) {
            isDragged = false;
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    private class MouseMotionAction extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent arg0) {
            if (isDragged) {// ����û�ִ����ק��������ı�PDF�ĵ�����ʾλ��
                // pdfPanel��getLocation()�����ɻ��PDF�ļ�����ʾλ��
                int x = pdfPanel.getLocation().x + arg0.getX() - pressedPoint.x;
                // pressedPoint���û�������������
                int y = pdfPanel.getLocation().y + arg0.getY() - pressedPoint.y;
                Point draggedPoint = new Point(x, y);
                pdfPanel.setLocation(draggedPoint);// ����PDF�ĵ�����ʾλ��
            }
        }
    }
    
}
