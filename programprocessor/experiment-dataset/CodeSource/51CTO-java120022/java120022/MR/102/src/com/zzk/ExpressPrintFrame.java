package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.zzk.panel.BackgroundPanel;

@SuppressWarnings("serial")
public class ExpressPrintFrame extends JFrame {
    private URL url = null;// ����ͼƬ��URL
    private Image image = null;// ����ͼ�����
    private BackgroundPanel backPanel = null;// �����Զ��屳��������
    private Robot robot = null; // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExpressPrintFrame frame = new ExpressPrintFrame();
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
    public ExpressPrintFrame() {
        super();
        setTitle("��ӡ��ݵ�");
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        url = ExpressPrintFrame.class.getResource("/image/express.jpg"); // ���ͼƬ��URL
        image = new ImageIcon(url).getImage(); // ����ͼ�����
        backPanel = new BackgroundPanel(image);
        backPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {// ��������Ҽ�
                    int x = e.getX();// ������λ�õ�X����
                    int y = e.getY();// ������λ�õ�Y����
                    TargetTextField tf = new TargetTextField();// �����Զ����ı����ʵ��
                    tf.addMouseListener(tf);// �����������
                    tf.addMouseMotionListener(tf);// �����������
                    tf.addActionListener(tf);// ��Ӷ���������
                    tf.setBounds(x, y, 147, 22);// ָ���ı����λ�úʹ�С
                    backPanel.add(tf);// ��ӵ����������
                    tf.requestFocus();// ʹ�ı����ý���
                }
            }
        });
        backPanel.setLayout(null);
        getContentPane().add(backPanel);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
                    if (!job.printDialog()) // �򿪴�ӡ�Ի���
                        return;// ������ӡ�Ի����ȡ����ť��رմ�ӡ�Ի�����������ִ��
                    job.setPrintable(new Printable() {
                        // ʵ��print()���������ƴ�ӡ����
                        public int print(Graphics graphics,
                                PageFormat pf, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // ���ͼ�������Ķ���
                            int x = (int)(ExpressPrintFrame.this.getBounds().getX())+8;// �����������Ļ�ϵ�X����
                            int y = (int)(ExpressPrintFrame.this.getBounds().getY())+30;// �����������Ļ�ϵ�Y����
                            int w = (int)backPanel.getBounds().getWidth();// �������Ŀ��
                            int h = (int)backPanel.getBounds().getHeight();// �������ĸ߶�
                            Rectangle rect = new Rectangle(x, y, w, h);// ����Rectangle����
                            buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                            int imgWidth = buffImage.getWidth();// ͼ��Ŀ��
                            int imgHeight = buffImage.getHeight();// ͼ��ĸ߶�
                            float wh = imgWidth / imgHeight;// ͼ���߱�
                            int printX = (int) pf.getImageableX();// ��ÿɴ�ӡ�����x����
                            int printY = (int) pf.getImageableY();// ��ÿɴ�ӡ�����y����
                            int width = (int) pf.getImageableWidth();// ��ÿɴ�ӡ����Ŀ��
                            int height = (int) pf.getImageableHeight();// ��ÿɴ�ӡ����ĸ߶�
                            if (imgWidth > width) { // �������ڿɴ�ӡ����
                                imgWidth = width;
                                imgHeight = (int)(imgHeight * wh);
                            }
                            if (imgHeight > height) { // ����ߴ��ڿɴ�ӡ����
                                imgHeight = height;
                                imgWidth = (int)(imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth, imgHeight, ExpressPrintFrame.this);// ������ͼ����Ƶ���ӡҳ
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("��ӡ��ݵ�"); // ���ô�ӡ���������
                    job.print(); // ����print()������ʼ��ӡ
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��ӡ��ݵ�");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
}
