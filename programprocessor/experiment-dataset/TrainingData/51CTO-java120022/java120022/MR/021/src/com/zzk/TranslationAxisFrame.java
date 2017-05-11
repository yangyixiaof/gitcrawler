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

public class TranslationAxisFrame extends JFrame {
    int flag = 0; // Ϊ0ʱ��ԭ�����ᣬΪ1ʱƽ��������
    TranslationAxisPanel axisPanel = new TranslationAxisPanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        TranslationAxisFrame frame = new TranslationAxisFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public TranslationAxisFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("ƽ��������"); // �������
        setBounds(100, 100, 338, 230); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(axisPanel); // ��������ʵ����ӵ�����������
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // ƽ����������
                axisPanel.repaint();  // ���µ�������paint()����
            }
        });
        btn_deasil.setText("ƽ��������");
        panel.add(btn_deasil);
        
        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // ��ԭ��������
                axisPanel.repaint();// ���µ�������paint()����
            }
        });
        btn_restore.setText("��ԭ������");
        panel.add(btn_restore);
    }
    
    class TranslationAxisPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D) g; // ���Graphics2D����
            Rectangle2D.Float rect = new Rectangle2D.Float(10, 10, 80, 50);// �������ζ���
            BasicStroke stroke = new BasicStroke(10); // ���������10�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            g2.clearRect(0, 0, 338, 230);  // ���ԭ������
            if (flag == 0) {
                g2.translate(0, 0);// ƽ��������
                g2.draw(rect);// ���ƾ���
            } else if (flag == 1) {
                g2.translate(120, 60);// ƽ��������
                g2.draw(rect);// ���ƾ���
            }
        }
    }
}
