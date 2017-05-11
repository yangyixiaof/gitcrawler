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
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class ScreenZoomInFrame extends JFrame {
    private PartZoomInPanel partZoomInPanel = null; // ����ͼ��������
    private int pressPanelX = 0, pressPanelY = 0;// ��갴�µ��X��Y����
    private int pressX = 0, pressY = 0;// ��갴�µ�����Ļ�ϵ�X��Y����
    private int releaseX = 0, releaseY = 0;// ����ͷŵ�����Ļ�ϵ�X��Y����
    private Robot robot = null; // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    private BufferedImage zoomBuffImage = null; // ��������ͼ�����
    private boolean flag = false; // ������Ǳ�����Ϊtrueʱ��ʾѡ������ľ��Σ�������ʾ
    private boolean mouseFlag = false;// ���зŴ�ͼ��ı�Ǳ�����Ϊtrueʱ���зŴ󣬷��򲻷Ŵ�
    private Rectangle rect = null;
    private Dimension dim = null;
    
    public static void main(String args[]) {
        ScreenZoomInFrame frame;
        try {
            frame = new ScreenZoomInFrame("��Ļ�Ŵ�");
            frame.setVisible(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }
    
    public ScreenZoomInFrame(String title) throws AWTException {
        super(title);
        getContentPane().addMouseListener(new MouseAdapter() {
        	public void mouseClicked(final MouseEvent e) {
        		if (e.getButton()==MouseEvent.BUTTON3) {
        			System.exit(0);// ��������Ҽ��˳�
        		}
        	}
        });
        setAlwaysOnTop(true);
        partZoomInPanel = new PartZoomInPanel(); // ����ͼ��������
        Toolkit toolkit = getToolkit();
        dim = toolkit.getScreenSize();
        setBounds(0, 0, dim.width, dim.height - 30); // ���ô����С��λ��
        setUndecorated(true);
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        AWTUtilities.setWindowOpacity(this, 0.01f);
        robot = new Robot();// ����Robot����
        rect = new Rectangle(0, 0, dim.width, dim.height);// ����Rectangle����
        buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(final WindowEvent e) {
            }
            
            public void windowLostFocus(final WindowEvent e) {
                flag = false;// Ϊ��Ǳ�����ֵΪtrue
                mouseFlag = false;// ���������Ϊfalse
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 0.01f);
                buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 1f);
                robot.mouseMove(200, 730);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        });
        //
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // ���������¼�
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 0.01f);
                buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 1f);
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
                if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                    Rectangle rect = new Rectangle(pressX, pressY, releaseX
                            - pressX, releaseY - pressY);// ����Rectangle����
                    zoomBuffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                }
                flag = false;// Ϊ��Ǳ�����ֵΪfalse
                mouseFlag = true;// ���Ϊtrue�����зŴ�
                partZoomInPanel.repaint();// ����paint()������ʵ�ַŴ�
            }
            
            public void mouseClicked(final MouseEvent e) { // ���������¼�
                if (e.getButton() == MouseEvent.BUTTON3) {// ��������Ҽ�
                    System.exit(0);// �˳�ϵͳ
                }
                mouseFlag = false;// ���������Ϊfalse�����Ŵ�ͼ��
                partZoomInPanel.repaint();// ����PartZoomInPanel����paint()����
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
    
    class PartZoomInPanel extends JPanel {// ���������
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(buffImage, 0, 0, this);// ����ͼ��
            g2.setColor(Color.BLACK);
            if (flag) {
                float[] arr = { 5.0f }; // ��������ģʽ������
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // ���������1��ƽͷ���߱ʻ�����
                g2.setStroke(stroke);// ���ñʻ�����
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// ���ƾ���ѡ��
            }
            
            if (mouseFlag) {// ����Ϊ��
                int zoomX = pressPanelX - (releaseX - pressX) / 4;// �Ŵ����ݻ��Ƶ��x����
                int zoomY = pressPanelY - (releaseY - pressY) / 4;// �Ŵ����ݻ��Ƶ��y����
                if (zoomX <= 0) {
                    zoomX = 0;// ����ֵС�ڵ���0��������ֵΪ0
                }
                if (zoomY <= 0) {
                    zoomY = 0;// ����ֵС�ڵ���0��������ֵΪ0
                }
                g2.drawImage(zoomBuffImage, zoomX, zoomY,
                        (int) ((releaseX - pressX) * 1.5f),
                        (int) ((releaseY - pressY) * 1.5f), this);// ���ƷŴ�������
            }
        }
    }
    
}
