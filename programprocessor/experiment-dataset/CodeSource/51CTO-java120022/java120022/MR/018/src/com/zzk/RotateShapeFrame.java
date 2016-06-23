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

public class RotateShapeFrame extends JFrame {
    int flag = 0; // Ϊ0ʱ��ʾԭͼ�Σ�Ϊ1ʱ˳ʱ����ת��Ϊ2ʱ��ʱ����ת
    double rotateValue = 0.0;  // ֵ���˳ʱ����ת��ֵ��С��ʱ����ת
    RotateShapePanel rotateShapePanel = new RotateShapePanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        RotateShapeFrame frame = new RotateShapeFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public RotateShapeFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("��תͼ��"); // �������
        setBounds(100, 100, 338, 220); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(rotateShapePanel); // ��������ʵ����ӵ�����������
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // ˳ʱ����
                rotateValue += 0.1;   // ˳ʱ����תֵ
                rotateShapePanel.repaint();  // ���µ�������paint()����
            }
        });
        btn_deasil.setText("˳ʱ��");
        panel.add(btn_deasil);
        
        final JButton btn_widdershins = new JButton();
        btn_widdershins.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // ��ʱ����
                rotateValue -= 0.1; // ��ʱ����תֵ
                rotateShapePanel.repaint();// ���µ�������paint()����
            }
        });
        btn_widdershins.setText("��ʱ��");
        panel.add(btn_widdershins);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // ��ԭͼ��
                rotateValue = 0;
                rotateShapePanel.repaint();// ���µ�������paint()����
            }
        });
        btn_restore.setText("��    ԭ");
        panel.add(btn_restore);
    }
    
    class RotateShapePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D) g; // ���Graphics2D����
            Rectangle2D.Float rect = new Rectangle2D.Float(40, 40, 80, 50);// �������ζ���
            BasicStroke stroke = new BasicStroke(10); // ���������10�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            g2.clearRect(0, 0, 338, 220);  // ���ԭ������
            if (flag == 0) {
                g2.draw(rect);// ����ԭ����
            } else if (flag == 1) {
                g2.rotate(rotateValue);// ˳ʱ����ת
                g2.draw(rect);// ���ƾ���
            } else if (flag == 2) {
                g2.rotate(rotateValue);// ��ʱ����ת
                g2.draw(rect);// ���ƾ���
            }
        }
    }
}
