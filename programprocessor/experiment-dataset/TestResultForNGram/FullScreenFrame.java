package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.pdfview.FullScreenWindow;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import javax.swing.UIManager;
import java.awt.Font;

public class FullScreenFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5506015730370161973L;
    private JPanel contentPane;
    private JPanel pdfPanel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	// right 1
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
        	// right 2
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FullScreenFrame frame = new FullScreenFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                	// right 2
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
    	// right 2
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF�ļ�", "pdf"));
        // right 2
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // right 1
        fileChooser.setMultiSelectionEnabled(false);
        // right 1
        int result = fileChooser.showOpenDialog(this);
        // right 1
        if (result == JFileChooser.APPROVE_OPTION) {
        	// right 1
            File selectFile = fileChooser.getSelectedFile();
            System.out.println(selectFile);
        }
    }
    
    protected void do_fullscreenButton_actionPerformed(ActionEvent arg0) {
    	// right 1
        if (pdfPage == null) {
        	// right 3
            JOptionPane.showMessageDialog(this, "��ѡ��PDF�ļ�", null, JOptionPane.WARNING_MESSAGE);
            // right 1
            return;
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        System.out.println(dimension);
        if (pdfPanel != null) {
            pdfPanel.removeAll();
        }
        pdfPanel.add(new JLabel(new ImageIcon("pic")));
    }
}
