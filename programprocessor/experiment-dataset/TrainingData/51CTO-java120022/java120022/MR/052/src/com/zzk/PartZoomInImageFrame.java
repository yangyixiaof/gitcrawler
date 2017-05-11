package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PartZoomInImageFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private PartZoomInPanel partZoomInPanel = null; // ����ͼ��������
    private int pressPanelX = 0, pressPanelY = 0;// ��갴�µ��X��Y����
    private int pressX = 0, pressY = 0;// ��갴�µ�����Ļ�ϵ�X��Y����
    private int releaseX = 0, releaseY = 0;// ����ͷŵ�����Ļ�ϵ�X��Y����
    private Robot robot = null; // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    private boolean flag = false; // ������Ǳ�����Ϊtrueʱ��ʾѡ������ľ��Σ�������ʾ
    private boolean mouseFlag = false;// ���оֲ��Ŵ�ͼ��ı�Ǳ�����Ϊtrueʱ���оֲ��Ŵ�
    
    public static void main(String args[]) {
        PartZoomInImageFrame frame = new PartZoomInImageFrame();
        frame.setVisible(true);
    }
    
    public PartZoomInImageFrame() {
        super();
        URL imgUrl = PartZoomInImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        partZoomInPanel = new PartZoomInPanel(); // ����ͼ��������
        this.setBounds(200, 160, 355, 276); // ���ô����С��λ��
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // ���������¼�
                pressPanelX = e.getX(); // �����갴�µ��X����
                pressPanelY = e.getY();// �����갴�µ��Y����
                pressX = e.getXOnScreen() + 1;// ��갴�µ�����Ļ�ϵ�X�����1����ȥ��ѡ����
                pressY = e.getYOnScreen() + 1;// ��갴�µ�����Ļ�ϵ�Y�����1����ȥ��ѡ����
                flag = true;// Ϊ��Ǳ�����ֵΪtrue
                mouseFlag = false;// ���������Ϊfalse
            }
            
            public void mouseReleased(final MouseEvent e) { // �����ͷ��¼�
                releaseX = e.getXOnScreen() - 1;// ����ͷŵ�����Ļ�ϵ�X�����1����ȥ��ѡ����
                releaseY = e.getYOnScreen() - 1;// ����ͷŵ�����Ļ�ϵ�Y�����1����ȥ��ѡ����
                try {
                    robot = new Robot();// ����Robot����
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// ����Rectangle����
                        buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                flag = false;// Ϊ��Ǳ�����ֵΪfalse
                mouseFlag = true;// ���Ϊtrue�����оֲ��Ŵ�
                repaint();// ����paint()������ʵ�־ֲ��Ŵ�
            }
            
            public void mouseClicked(final MouseEvent e) { // ���������¼�
                mouseFlag = false;// ���������Ϊfalse�����Ŵ�ͼ��
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
        this.setTitle("�ֲ�ͼ��Ŵ�"); // ���ô������
    }
    
    class PartZoomInPanel extends JPanel {// ��������ԭͼ��������
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);// ����ͼ��
            g2.setColor(Color.WHITE);
            if (flag) {
                float[] arr = { 5.0f }; // ��������ģʽ������
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // ���������1��ƽͷ���߱ʻ�����
                g2.setStroke(stroke);// ���ñʻ�����
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// ���ƾ���ѡ��
            }
            
            if (mouseFlag) {// ����Ϊ��
                int zoomX = pressPanelX - (releaseX - pressX) / 4;// �Ŵ�ͼ����Ƶ��x����
                int zoomY = pressPanelY - (releaseY - pressY) / 4;// �Ŵ�ͼ����Ƶ��y����
                if (zoomX <= 0) {
                    zoomX = 0;// ����ֵС�ڵ���0��������ֵΪ0
                }
                if (zoomY <= 0) {
                    zoomY = 0;// ����ֵС�ڵ���0��������ֵΪ0
                }
                g.drawImage(buffImage, zoomX, zoomY,
                        (int) ((releaseX - pressX) * 1.5f),
                        (int) ((releaseY - pressY) * 1.5f), this);// ���ƷŴ��ľֲ�ͼ��
            }
        }
    }
    
}
