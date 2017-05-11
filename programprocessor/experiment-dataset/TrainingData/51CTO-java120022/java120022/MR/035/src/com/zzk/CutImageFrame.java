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
import javax.swing.JSplitPane;
public class CutImageFrame extends JFrame {
    private Image img = null; // ����ͼ�����
    private OldImagePanel oldImagePanel = null; // ����ͼ��������
    private int pressPanelX = 0, pressPanelY = 0;// ��갴�µ��X��Y���� 
    private int pressX = 0, pressY = 0;// ��갴�µ�����Ļ�ϵ�X��Y����
    private int releaseX = 0, releaseY = 0;// ����ͷŵ�����Ļ�ϵ�X��Y����
    private Robot robot = null;  // ����Robot����
    private BufferedImage buffImage = null; // ��������ͼ�����
    private CutImagePanel cutImagePanel = new CutImagePanel(); // �������Ʋü���������
    private boolean flag = false;  // ������Ǳ�����Ϊtrueʱ��ʾѡ������ľ��Σ�������ʾ
    public static void main(String args[]) {
        CutImageFrame frame = new CutImageFrame();
        frame.setVisible(true);
    }
    public CutImageFrame() {
        super();
        URL imgUrl = CutImageFrame.class.getResource("/img/image.jpg");// ��ȡͼƬ��Դ��·��
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��ȡͼ����Դ
        oldImagePanel = new OldImagePanel(); // ����ͼ��������
        this.setBounds(200, 160, 355, 276); // ���ô����С��λ��
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation((this.getWidth() / 2) - 10);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setLeftComponent(oldImagePanel);
        splitPane.setRightComponent(cutImagePanel);
        oldImagePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {  // ���������¼�
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
                    robot = new Robot();// ����Robot����
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// ����Rectangle����
                        buffImage = robot.createScreenCapture(rect);// ��û���ͼ�����
                        cutImagePanel.repaint(); // ����CutImagePanel����paint()����
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                flag = false;// Ϊ��Ǳ�����ֵΪfalse
            }
        });
        oldImagePanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// ����϶��¼�
                if (flag) {
                    releaseX = e.getXOnScreen();// �������ͷŵ�����Ļ�ϵ�X����
                    releaseY = e.getYOnScreen();// �������ͷŵ�����Ļ�ϵ�Y����
                    oldImagePanel.repaint();// ����OldImagePanel����paint()����
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        this.setTitle("�ü�ͼƬ"); // ���ô������
    }
    
    class OldImagePanel extends JPanel {// ��������ԭͼ��������
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
        }
    }
    
    class CutImagePanel extends JPanel {// �������Ʋü�����������
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �����ͼ�����ĵ�����
            g.drawImage(buffImage, 0, 0, releaseX - pressX, releaseY - pressY,
                    this);// ����ͼ��
        }
    }
}
