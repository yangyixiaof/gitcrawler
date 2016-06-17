package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PictureMatchingFrame extends JFrame implements MouseListener,
        MouseMotionListener {
    private JLabel img[] = new JLabel[3];// ��ʾͼ��ı�ǩ
    private JLabel targets[] = new JLabel[3];// ����������ʾ���ֵı�ǩ
    private Point pressPoint; // ��갴��ʱ����ʼ����
    
    public static void main(String args[]) {
        PictureMatchingFrame frame = new PictureMatchingFrame(); // �����������
        frame.setVisible(true); // ���ô���Ϊ����״̬
    }
    
    public PictureMatchingFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 100, 364, 312);
        setTitle("ͼƬ�����Ϸ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        setGlassPane(imagePanel);
        getGlassPane().setVisible(true);
        ImageIcon icon[] = new ImageIcon[3];
        icon[0] = new ImageIcon(getClass().getResource("screen.png"));
        icon[1] = new ImageIcon(getClass().getResource("clothing.png"));
        icon[2] = new ImageIcon(getClass().getResource("bike.png"));
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        for (int i = 0; i < 3; i++) {
            img[i] = new JLabel(icon[i]); // ����ͼ���ǩ
            img[i].setSize(50, 50); // ���ñ�ǩ��С
            img[i].setBorder(new LineBorder(Color.GRAY)); // �������Ա߿�
            int x = (int) (Math.random() * (getWidth() - 50)); // �������X����
            int y = (int) (Math.random() * (getHeight() - 150));// �������Y����
            img[i].setLocation(x, y); // �����������
            img[i].addMouseListener(this); // Ϊÿ��ͼ���ǩ�������¼�������
            img[i].addMouseMotionListener(this);
            imagePanel.add(img[i]); // ���ͼ���ǩ��ͼ�����
            targets[i] = new JLabel(); // ����ƥ��λ�ñ�ǩ
            targets[i].setOpaque(true); // ʹ��ǩ��͸���������ñ���ɫ
            targets[i].setBackground(Color.ORANGE); // ���ñ�ǩ����ɫ
            targets[i].setHorizontalTextPosition(SwingConstants.CENTER); // �����ı���ͼ��ˮƽ����
            targets[i].setVerticalTextPosition(SwingConstants.BOTTOM); // �����ı���ʾ��ͼ���·�
            targets[i].setPreferredSize(new Dimension(80, 80)); // ���ñ�ǩ���ȴ�С
            targets[i].setHorizontalAlignment(SwingConstants.CENTER); // ���־��ж���
            bottomPanel.add(targets[i]); // ��ӱ�ǩ���ײ����
        }
        targets[0].setText("��ʾ��"); // ����ƥ��λ�õ��ı�
        targets[1].setText("�·�");
        targets[2].setText("���г�");
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint(); // �����Ϸ�ͼƬ��ǩʱ����ʼ����
    }
    
    public void mouseReleased(MouseEvent e) {
        if (checkPosition()) { // ��������ȷ
            getGlassPane().setVisible(false);
            for (int i = 0; i < 3; i++) { // ��������ƥ��λ�õı�ǩ
                targets[i].setText("ƥ��ɹ�"); // ������ȷ��ʾ
                targets[i].setIcon(img[i].getIcon()); // ����ƥ���ͼ��
            }
        }
    }
    
    /**
     * ����϶��ؼ�ʱ���¼�������
     */
    public void mouseDragged(MouseEvent e) {
        JLabel source = (JLabel) e.getSource(); // ��ȡ�¼�Դ�ؼ�
        Point imgPoint = source.getLocation(); // ��ȡ�ؼ�����
        Point point = e.getPoint(); // ��ȡ�������
        source.setLocation(imgPoint.x + point.x - pressPoint.x, imgPoint.y
                + point.y - pressPoint.y); // ���ÿؼ�������
    }
    
    private boolean checkPosition() {// �������Ƿ���ȷ
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            Point location = img[i].getLocationOnScreen(); // ��ȡÿ��ͼ���ǩ��λ��
            Point seat = targets[i].getLocationOnScreen(); // ��ȡÿ����Ӧλ�õ�����
            targets[i].setBackground(Color.GREEN); // ����ƥ������ɫ
            // �����Դ���
            if (location.x < seat.x || location.y < seat.y
                    || location.x > seat.x + 80 || location.y > seat.y + 80) {
                targets[i].setBackground(Color.ORANGE); // �ظ���Ӧλ�õ���ɫ
                result = false; // �����Ϊfalse
            }
        }
        return result; // ���ؼ����
    }
}
