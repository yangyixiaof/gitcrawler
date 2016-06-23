package com.util;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import javax.swing.filechooser.FileFilter;
import com.sun.pdfview.FullScreenWindow;
import com.sun.pdfview.OutlineNode;

import com.sun.pdfview.PDFFile;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class MainFrame extends JFrame 
         {
    
    /**
     * @param args
     */
    // ���������ݵ����
    JPanel jpmain = new JPanel();
    // ���ö�ȡPDF�ĵ����ݵ����
    PagePanel jp;
    // ȫ����ʾ���
    FullScreenWindow fullScreen;
    
    JScrollPane documentScroller = new JScrollPane();
    // PDFRender���л�ȡPDF�ĵ���PDFFile��
    PDFFile pdffile;
    // �û���дҳ����ı���
    JTextField pageField;
    
    int curpage = -1;
    // ���ڻ�ȡ��ٵ�OutlineNode����
    OutlineNode outline = null;
    JButton smallButton;
    JButton fullScreenButton;
    PageFormat pformat = PrinterJob.getPrinterJob().defaultPage();
    String docName;
    
    private Timer activityMonitor;
    private SimulateActivity activity;
    PagePanel fspp;

    
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    
    public MainFrame() {
        setTitle("��ʾPDF�ļ�");
        setSize(new Dimension(721, 666));
        getContentPane().setLayout(null);
        getContentPane().add(CreateMenuBar());
        /*
         * �����м���������
         */
        jp = new PagePanel();

        jp.addMouseMotionListener(new JPMouseMotionAction());
        contentPanel = new JScrollPane();
        contentPanel.setBounds(0, 26, 705, 607);
        getContentPane().add(contentPanel);
        contentPanel.setColumnHeaderView(jpmain);
        jpmain.setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - getWidth()) / 2;
        int y = (screen.height - getHeight()) / 2;
        setLocation(x, y);
    }
    
    public static void main(String[] args) {
        // TODO �Զ����ɷ������
        new MainFrame().setVisible(true);
    }
    //����˵�����
    public JMenuBar CreateMenuBar() {
        
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 705, 25);
        final JMenu newItemMenuItem1 = new JMenu();
        newItemMenuItem1.setText("�ļ�(F)");
        
        menuBar.add(newItemMenuItem1);
        final JMenuItem openfile = new JMenuItem();
        openfile.setText("��");
        newItemMenuItem1.add(openfile);
        openfile.setToolTipText("Open PDF file");
        openfile
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // TODO �Զ����ɷ������
                        doOpen();   //�򿪲˵���ť����doOpen()����
                    }
                });
        return menuBar;
    }

    
    
    //Ϊ�ļ�ѡ������ӹ�����
    private File prevDirChoice;    
    FileFilter pdfFilter = new FileFilter() {
        
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith(".pdf");
        }
        
        public String getDescription() {
            return "Choose a PDF file";
        }
    };    
    private JScrollPane contentPanel;       
    public void doOpen() {
        try {
            if (jp != null) {
                doClose();
            }
            JFileChooser fc = new JFileChooser();   //����JFileChooserʵ��
            fc.setCurrentDirectory(prevDirChoice);  //���õ�ǰ·��
            fc.setFileFilter(pdfFilter);            //����ļ�������
            fc.setMultiSelectionEnabled(false);    
            int returnVal = fc.showOpenDialog(this);    //���ļ�ѡ����
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    prevDirChoice = fc.getSelectedFile();   //��ȡ�û�ѡ���·��
                    RandomAccessFile raf = new RandomAccessFile(prevDirChoice
                            .getAbsolutePath(), "r");   //ʵ������������ʵ��ļ�����
                    FileChannel channel = raf.getChannel(); //ʵ����FileChannel����
                    ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
                            0, channel.size()); 
                    try {
                        pdffile = new PDFFile(buf);     //ʵ����һ��PDFFileʵ��
                    } catch (IOException ioe) {
                        return;
                    }
                    docName = prevDirChoice.getName();
                    setTitle(docName);
                    activityMonitor = new Timer(500, new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            int current = activity.getCurrent();
                            contentPanel.getVerticalScrollBar().setValue(
                                    current);
                            if (current == activity.getTarget()) {
                                activityMonitor.stop();
                                
                            }
                        }
                    });
                    
//���ڽ�PDF�ĵ���ʾ������ķ��ڴ棬���Ա�ʵ����ǰ10ҳ�ĵ���ʾ���������ϣ������forѭ������ֵֹ�޸�Ϊpdffile.getNumPages()�����ʾ��ʾ����PDF�ĵ��е�����ҳ��
                    for (int i = 1; i < 10; i++) {
                        contentPanel.setViewportView(jpmain); // �����������ڹ��������
                        activity = new SimulateActivity(contentPanel
                                .getVerticalScrollBar().getMaximum());
                        new Thread(activity).start();
                        activityMonitor.start();
                        PDFPage page = pdffile.getPage(i); // ��ȡÿҳPDF�ĵ�
                        PagePanel jp2 = new PagePanel(); // ʵ����PagePanel����
                        jpmain.add(jp2); // ����������ӵ��������
                        validate(); // ˢ�´���
                        jp2.showPage(page); // ��ʾ��ҳPDF�ĵ�
                    }

                    try {
                        outline = pdffile.getOutline();
                    } catch (IOException ioe) {
                    }
                    if (outline != null) {
                        if (outline.getChildCount() > 0) {
                            JTree jt = new JTree(outline);
                            jt.setRootVisible(false);                          
                            JScrollPane jsp = new JScrollPane(jt);                            
                        }                        
                    }
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }
                validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gotoPage(int pagenum) {
        if (pagenum < 0) {
            pagenum = 0;
        } else if (pagenum >= pdffile.getNumPages()) {
            pagenum = pdffile.getNumPages() - 1;
        }
        
    }
    

    
    public void doClose() {
        if (jp != null) {
            jpmain.removeAll();
        }
        pdffile = null;
    }
    
    public void doPageSetup() {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        pformat = pjob.pageDialog(pformat);
    }

  
    private final class JPMouseMotionAction extends
            java.awt.event.MouseMotionAdapter {
        public void mouseDragged(java.awt.event.MouseEvent e) {
            if (isDragged) {// ���Ϸ��¼��в��ϼ��º͸ı�λ��
                loc = new Point(jp.getLocation().x + e.getX() - tmp.x, jp
                        .getLocation().y
                        + e.getY() - tmp.y);
                jp.setLocation(loc);
            }
        }
    }  
    
    class SimulateActivity implements Runnable {
        private volatile int current;        
        private int target;        
        public SimulateActivity(int t) {
            // TODO �Զ����ɹ��캯�����
            current = 0;
            target = t;
        }        
        public int getTarget() {
            return target;
        }        
        public int getCurrent() {
            return current;
        }        
        public void run() {
            try {
                while (current < target) {
                    Thread.sleep(100);
                    current++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }  
 
}
