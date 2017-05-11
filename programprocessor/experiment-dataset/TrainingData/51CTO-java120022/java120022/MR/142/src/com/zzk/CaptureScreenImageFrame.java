package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class CaptureScreenImageFrame extends JFrame {
    private PartZoomInPanel partZoomInPanel = null;// ����ͼ��������
    private int pressPanelX = 0, pressPanelY = 0;// ��갴�µ��X��Y����
    private int pressX = 0, pressY = 0;// ��갴�µ�����Ļ�ϵ�X��Y����
    private int releaseX = 0, releaseY = 0;// ����ͷŵ�����Ļ�ϵ�X��Y����
    private Robot robot = null;// ����Robot����
    private BufferedImage buffImage = null;// ��������ͼ�����
    private boolean flag = false;// ������Ǳ�����Ϊtrueʱ��ʾѡ������ľ��Σ�������ʾ
    private Rectangle rect = null;
    
    public static void main(String args[]) {
        CaptureScreenImageFrame frame;
        try {
            frame = new CaptureScreenImageFrame("��Ļץͼ����");
            frame.setVisible(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public CaptureScreenImageFrame(String title) throws AWTException {
        super(title);
        setAlwaysOnTop(true);
        partZoomInPanel = new PartZoomInPanel(); // ����ͼ��������
        Toolkit toolkit = getToolkit();
        Dimension dim = toolkit.getScreenSize();
        setBounds(0, 0, dim.width, dim.height - 30); // ���ô����С��λ��
        setUndecorated(true);// ȡ����������
        AWTUtilities.setWindowOpacity(this, 0.01f);// ���ô���͸��
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        robot = new Robot();// ����Robot����
        rect = new Rectangle(0, 0, dim.width, dim.height);// ����Rectangle����
        buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // ���������¼�
                buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(CaptureScreenImageFrame.this, 1f);// ���ô���Ϊ��͸��
                pressPanelX = e.getX(); // �����갴�µ��X����
                pressPanelY = e.getY();// �����갴�µ��Y����
                pressX = e.getXOnScreen() + 1;// ��갴�µ�����Ļ�ϵ�X�����1����ȥ��ѡ����
                pressY = e.getYOnScreen() + 1;// ��갴�µ�����Ļ�ϵ�Y�����1����ȥ��ѡ����
                flag = true;// Ϊ��Ǳ�����ֵΪtrue
            }
            
            public void mouseReleased(final MouseEvent e) { // �����ͷ��¼�
                releaseX = e.getXOnScreen() - 1;// ����ͷŵ�����Ļ�ϵ�X�����1����ȥ��ѡ����
                releaseY = e.getYOnScreen() - 1;// ����ͷŵ�����Ļ�ϵ�Y�����1����ȥ��ѡ����
                try {
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// ����Rectangle����
                        buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                        FileOutputStream out = new FileOutputStream("c:/zzkkee.jpg");// ����λ�õ����������
                        ImageIO.write(buffImage, "jpg", out);// д�����
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                flag = false;   // Ϊ��Ǳ�����ֵΪfalse
                if (e.getButton() == MouseEvent.BUTTON1) {// ��������Ҽ�
                    CaptureScreenImageFrame.this.dispose();
                    CaptureScreenImageFrame frame;
                    try {
                        frame = new CaptureScreenImageFrame("��Ļץͼ����");
                        AWTUtilities.setWindowOpacity(frame, 0.01f);
                        frame.setVisible(true);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            
            public void mouseClicked(final MouseEvent e) { // ���������¼�
                if (e.getButton() == MouseEvent.BUTTON3) {// ��������Ҽ�
                    System.exit(0);// �˳�ϵͳ
                }
            }
        });
        partZoomInPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// ����϶��¼�
                if (flag) {
                    releaseX = e.getXOnScreen();// �������ͷŵ�����Ļ�ϵ�X����
                    releaseY = e.getYOnScreen();// �������ͷŵ�����Ļ�ϵ�Y����
                    partZoomInPanel.repaint();// ����PartZoomInPanel����paint()����
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
    }
    
    class PartZoomInPanel extends JPanel {// ��������ԭͼ��������
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(buffImage, 0, 0, PartZoomInPanel.this);
            g2.setColor(Color.BLACK);
            if (flag) {
                float[] arr = { 5.0f }; // ��������ģʽ������
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // ���������1��ƽͷ���߱ʻ�����
                g2.setStroke(stroke);// ���ñʻ�����
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// ���ƾ���ѡ��
            }
        }
    }
    
}
