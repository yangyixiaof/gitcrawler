package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private BackgroundPanel backgroundPanel = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame thisClass = new MainFrame();
                thisClass.setVisible(true);
            }
        });
    }
    
    public MainFrame() {
        super();
        setTitle("ѩ��Ʈ�䶯��");
        setSize(628, 441);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = new ImageIcon(getClass().getResource("/image/cursor.png")).getImage();// ����ͼ�����
        Cursor cursor = getToolkit().createCustomCursor(image, new Point(),"ħ��");// ������������
        setCursor(cursor);// ָ�������
        setResizable(false);// ������ı䴰���С
        backgroundPanel = new BackgroundPanel();// �����������
        // Ϊ�������ָ��ͼ��
        backgroundPanel.setImage(new ImageIcon(getClass().getResource("/image/bg.jpg")).getImage());
        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {// ����ƶ��¼�
                SnowFlakeLabel snow = new SnowFlakeLabel();// ����ѩ��Ʈ���ǩ
                Point point = e.getPoint();// ������λ��
                snow.setLocation(point);// ָ��ѩ���ڱ�������ϵ�λ��
                backgroundPanel.add(snow);// ��ѩ����ӵ����������
            }
        });
        getContentPane().setLayout(new BorderLayout());// ָ�������������Ϊ�߽粼��
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);// �ڴ��������������ӱ������
    }
}