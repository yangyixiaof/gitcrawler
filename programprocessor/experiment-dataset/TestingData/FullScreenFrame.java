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
    private JPanel content_pane;
    private JPanel pdf_panel;
    private PDFPage pdf_page;
    
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
                    FullScreenFrame f = new FullScreenFrame();
                    f.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public FullScreenFrame() {
        setTitle("\u5168\u5C4F\u663E\u793APDF\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        content_pane = new JPanel();
        content_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pane);
        content_pane.setLayout(new BorderLayout(0, 0));
        
        JPanel button_panel = new JPanel();
        content_pane.add(button_panel, BorderLayout.SOUTH);
        
        JButton choose_button = new JButton("\u9009\u62E9\u6587\u4EF6");
        choose_button.setFont(new Font("unseen", Font.PLAIN, 16));
        choose_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        button_panel.add(choose_button);
        
        JButton fullscreen_button = new JButton("\u5168\u5C4F\u663E\u793A");
        fullscreen_button.setFont(new Font("unseen", Font.PLAIN, 16));
        fullscreen_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_fullscreenButton_actionPerformed(arg0);
            }
        });
        button_panel.add(fullscreen_button);
        
        pdf_panel = new JPanel();
        content_pane.add(pdf_panel, BorderLayout.CENTER);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file_chooser.setMultiSelectionEnabled(false);
        int result = file_chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File select_file = file_chooser.getSelectedFile();
            try {
                pdf_page = getPDFPage(select_file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PagePanel page_panel = new PagePanel();
            pdf_panel.add(page_panel);
            validate();
            page_panel.showPage(pdf_page);
        }
    }
    
    private static PDFPage getPDFPage(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdf_file = new PDFFile(buffer);
        return pdf_file.getPage(2);
    }
    
    protected void do_fullscreenButton_actionPerformed(ActionEvent arg0) {
        if (pdf_page == null) {
            JOptionPane.showMessageDialog(this, "please choose PDF file", null, JOptionPane.WARNING_MESSAGE);
            return;
        }
        Rectangle rect = new Rectangle(0, 0, (int) pdf_page.getBBox().getWidth(), (int) pdf_page.getBBox().getHeight());// 获得用户选中的PDF页面的边框
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        double times = dimension.getHeight() / rect.height;
        Image image = pdf_page.getImage((int) (rect.width * times), dimension.height, rect, null, true, true);// 设置图片的大小
        if (pdf_panel != null) {
            pdf_panel.removeAll();
        }
        pdf_panel.add(new JLabel(new ImageIcon(image)));
        new FullScreenWindow(pdf_panel);
    }
}
