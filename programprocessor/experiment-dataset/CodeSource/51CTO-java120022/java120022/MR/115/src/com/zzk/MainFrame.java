package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.swtdesigner.SwingResourceManager;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    private String border = ""; // �߿�����
    private PageFormat pf; // ����ҳ���С�ͷ���Ķ���
    private PreviewCanvas canvas; // ��ӡԤ������
    private File imgFile = null; // ��Ƭ�ļ�
    private Image src;// ��ƬͼƬ
    private boolean isPreview = false; // �Ƿ���Դ�ӡ
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        setTitle("�����Ч��ӡ����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 780));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(0, 80));
        panel_1.setLayout(null);
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setBounds(50, 15, 130, 18);
        label.setText("��ѡ��Ҫ��ӡ����Ƭ��");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setBounds(186, 12, 502, 24);
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // ѡ���ļ���ť
        final JButton selectButton = new JButton();
        selectButton.setBounds(694, 10, 86, 28);
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ���JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");
                fileChooser.setFileFilter(filter);
                int i = fileChooser.showOpenDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��ȡѡ�е�ͼƬ����
                    filePath.setText(imgFile.getAbsolutePath()); // ��ʾͼƬ·��
                }
            }
        });
        selectButton.setText("ѡ���ļ�");
        panel_1.add(selectButton);
        
        final ImgLabel imgLabel1 = new ImgLabel();
        imgLabel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ����������
                    border = "border1"; // ���ñ߿�����
                    if (imgFile != null) {
                        isPreview = true; // ��ʾ���Դ�ӡ
                        canvas.repaint(); // �ػ滭��
                    } else {
                        JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡ��ͼƬ��");
                    }
                }
            }
        });
        imgLabel1.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border1.png"));
        imgLabel1.setBounds(186, 42, 86, 35);
        panel_1.add(imgLabel1);
        
        final ImgLabel imgLabel2 = new ImgLabel();
        imgLabel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ����������
                    border = "border2"; // ���ñ߿�����
                    if (imgFile != null) {
                        isPreview = true; // ��ʾ���Դ�ӡ
                        canvas.repaint(); // �ػ滭��
                    } else {
                        JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡ��ͼƬ��");
                    }
                }
            }
        });
        imgLabel2.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border2.png"));
        imgLabel2.setBounds(289, 42, 86, 35);
        panel_1.add(imgLabel2);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("ѡ��߿�ۿ�Ԥ��Ч����");
        label_1.setBounds(37, 50, 143, 18);
        panel_1.add(label_1);
        
        final JButton pageSetButton = new JButton();
        pageSetButton.setBounds(602, 43, 86, 28);
        panel_1.add(pageSetButton);
        pageSetButton.addActionListener(new ActionListener() {
            //
            /*
             * ��ҳ�����á���ť�ĵ����¼�
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // ��ȡPrinterJob���ʵ��
                pf = job.pageDialog(pf); // ��ҳ�����öԻ���
                isPreview = true; // ��ʾ���Դ�ӡ
                canvas.repaint(); // �ػ滭��
            }
        });
        pageSetButton.setText("ҳ������");
        
        final JButton printButton = new JButton();
        printButton.setBounds(694, 43, 86, 28);
        panel_1.add(printButton);
        printButton.addActionListener(new ActionListener() {
            /*
             * ����ʼ��ӡ����ť�ĵ����¼�
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // ��ȡPrinterJob�����ʵ��
                if (!job.printDialog())
                    return;
                // ���ô�ӡ����
                job.setPrintable(new Printable() {
                    
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // ���ÿ��Դ�ӡ
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
                            return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
                        } else {
                            return Printable.NO_SUCH_PAGE; // ����NO_SUCH_PAGE
                        }
                    }
                    
                });
                job.setJobName("��ӡ��Ƭ"); // ���ô�ӡ�ĵ�������
                try {
                    job.print(); // ��ʼ��ӡ
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("��ʼ��ӡ");
        
        final ImgLabel imgLabel3 = new ImgLabel();
        imgLabel3.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ����������
                    border = "border3"; // ���ñ߿�����
                    if (imgFile != null) {
                        isPreview = true; // ��ʾ���Դ�ӡ
                        canvas.repaint(); // �ػ滭��
                    } else {
                        JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡ��ͼƬ��");
                    }
                }
            }
        });
        imgLabel3.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border3.png"));
        imgLabel3.setBounds(393, 42, 86, 35);
        panel_1.add(imgLabel3);
        
        final ImgLabel imgLabel4 = new ImgLabel();
        imgLabel4.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ����������
                    border = "border4"; // ���ñ߿�����
                    if (imgFile != null) {
                        isPreview = true; // ��ʾ���Դ�ӡ
                        canvas.repaint(); // �ػ滭��
                    } else {
                        JOptionPane.showMessageDialog(null, "����ѡ��Ҫ��ӡ��ͼƬ��");
                    }
                }
            }
        });
        imgLabel4.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border4.PNG"));
        imgLabel4.setText("New ImgLabel");
        imgLabel4.setBounds(497, 42, 86, 35);
        panel_1.add(imgLabel4);
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        // TODO Auto-generated method stub
        printPicture(graphics, pageFormat, pageIndex); // ���ƴ�ӡ����
        return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
    }
    
    // ����
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g; // ��ȡGraphics2D����
                g2.translate(10, 10); // ��ԭ������ƽ��10������
                int x = (int) (pf.getImageableX() - 1); // ��ȡ�ɴ�ӡ������ʼ���x������
                int y = (int) (pf.getImageableY() - 1); // ��ȡ�ɴ�ӡ������ʼ���y������
                int width = (int) (pf.getImageableWidth() + 1); // ��ȡ�ɴ�ӡ����������x������
                int height = (int) (pf.getImageableHeight() + 1); // ��ȡ�ɴ�ӡ����������y������
                int mw = (int) pf.getWidth(); // ��ȡҳ����
                int mh = (int) pf.getHeight();// ��ȡҳ��߶�
                g2.drawRect(0, 0, mw, mh);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f)); // ������������ʽ
                g2.drawRect(x, y, width, height); // ���ƾ��α߿�
                g2.setColor(Color.WHITE); // ����ǰ��ɫ
                g2.fillRect(x + 1, y + 1, width - 1, height - 1); // ���ư�ɫ�����ľ���
                MainFrame.this.print(g, pf, 0);
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ���ƴ�ӡ����
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX(); // ��ȡ�ɴ�ӡ���������Xλ��
        int y = (int) pageFormat.getImageableY(); // ��ȡ�ɴ�ӡ���������Yλ��
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// ����Image����
            } catch (IOException e) {
                e.printStackTrace();
            } 
            double imgWidth = src.getWidth(this); // ��ȡͼƬ�Ŀ�
            double imgHeight = src.getHeight(this); // ��ȡͼƬ�ĸ�
            double percent = imgWidth / imgHeight; // �������
            int mw = (int) pf.getWidth() - x * 2; // ����ɴ�ӡ����Ŀ�
            int mh = (int) pf.getHeight() - y * 2; // ����ɴ�ӡ����ĸ�
            if (imgWidth > mw) { // �������ڿɴ�ӡ����
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // ����ߴ��ڿɴ�ӡ����
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ��������ͼ����ͼƬ����Ƭ��
            /********************* ���Ʊ߿� *************************/
            if (!border.equals("")) {
                ImageIcon icon = SwingResourceManager.getIcon(MainFrame.class,
                        "/com/zzk/" + border + ".png"); // ��ȡImageIcon����
                Image borderImg = icon.getImage(); // ��ȡ���ڻ��Ʊ߿��Image����
                g2.drawImage(borderImg, x, y, (int) imgWidth, (int) imgHeight,
                        this); // ���Ʊ߿�
            }
            /*****************************************************/
        }
        isPreview = false; // ���ò����Դ�ӡ
    }
}
