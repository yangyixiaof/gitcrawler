package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FullScreenFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5506015730370161973L;
    private JPanel pdfPanel;
    private JFrame pdfPage = null;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	// right 1
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
        	// right 1
            e.printStackTrace();
        }
        // right 1
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FullScreenFrame frame = new FullScreenFrame();
                    // right 1
                    frame.setVisible(true);
                } catch (Exception e) {
                	// right 1
                    e.printStackTrace();
                }
            }
        });
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
    	// right 1
        JFileChooser fileChooser = new JFileChooser();
        // right 4
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF文件", "pdf"));
        // right 4
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // right 4
        fileChooser.setMultiSelectionEnabled(false);
        // right 2
        int result = fileChooser.showOpenDialog(this);
        // right 3
        if (result == JFileChooser.APPROVE_OPTION) {
        	// right 3
            File selectFile = fileChooser.getSelectedFile();
            System.out.println(selectFile);
        }
    }
    
    protected void do_fullscreenButton_actionPerformed(ActionEvent arg0) {
    	// right 1
        if (pdfPage == null) {
        	// right 3
            JOptionPane.showMessageDialog(this, "请选择PDF文件", null, JOptionPane.WARNING_MESSAGE);
         // right 1
            return;
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // right 1
        Dimension dimension = toolkit.getScreenSize(); // 获得用户的显示器大小
        System.out.println(dimension);
        if (pdfPanel != null) {
        	// right 1
            pdfPanel.removeAll();
        }
        pdfPanel.add(new JLabel(new ImageIcon("pic"))); // 显示生成的图片
    }
}
