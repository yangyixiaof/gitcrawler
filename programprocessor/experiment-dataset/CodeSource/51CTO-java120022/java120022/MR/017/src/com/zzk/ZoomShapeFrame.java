package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ZoomShapeFrame extends JFrame {
    int flag = 0; // Ϊ0ʱ��ʾԭ��С��Ϊ1ʱ�Ŵ�ͼ�Σ�Ϊ2ʱ��Сͼ��
    ZoomShapePanel zoomPanel = new ZoomShapePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        ZoomShapeFrame frame = new ZoomShapeFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public ZoomShapeFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("����ͼ��"); // �������
        setBounds(100, 100, 338, 220); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(zoomPanel); // ��������ʵ����ӵ�����������
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_zoomin = new JButton();
        btn_zoomin.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // �Ŵ�ͼ�α��
                zoomPanel.repaint();  // ���µ�������paint()����
            }
        });
        btn_zoomin.setText("��    ��");
        panel.add(btn_zoomin);
        
        final JButton btn_zoomout = new JButton();
        btn_zoomout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // ��Сͼ�α��
                zoomPanel.repaint();// ���µ�������paint()����
            }
        });
        btn_zoomout.setText("��    С");
        panel.add(btn_zoomout);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // ��ԭͼ��
                zoomPanel.repaint();// ���µ�������paint()����
            }
        });
        btn_restore.setText("��    ԭ");
        panel.add(btn_restore);
    }
    
    class ZoomShapePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D) g; // ���Graphics2D����
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// �������ζ���
            BasicStroke stroke = new BasicStroke(10); // ���������10�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            g2.clearRect(0, 0, 338, 220);  // ���ԭ������
            if (flag == 0) {
                g2.draw(rect);// ����ԭ����
            } else if (flag == 1) {
                g2.scale(1.3, 1.3);// �Ŵ�1.3��
                g2.draw(rect);// ���ƾ���
            } else if (flag == 2) {
                g2.scale(0.5, 0.5);// ��С0.5��
                g2.draw(rect);// ���ƾ���
            }
        }
    }
}
