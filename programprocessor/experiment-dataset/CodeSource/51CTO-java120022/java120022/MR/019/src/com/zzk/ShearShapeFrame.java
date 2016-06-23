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

public class ShearShapeFrame extends JFrame {
    int flag = 0; // Ϊ0ʱ��ʾԭͼ�Σ�Ϊ1ʱ����б�У�Ϊ2ʱ����б��
    ShearShapePanel shearShapePanel = new ShearShapePanel(); // ����������ʵ��
    public static void main(String args[]) { // ������
        ShearShapeFrame frame = new ShearShapeFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public ShearShapeFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("б��ͼ��"); // �������
        setBounds(100, 100, 338, 230); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(shearShapePanel); // ��������ʵ����ӵ�����������
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // ��б�б��
                shearShapePanel.repaint();  // ���µ�������paint()����
            }
        });
        btn_deasil.setText("��б��");
        panel.add(btn_deasil);
        
        final JButton btn_widdershins = new JButton();
        btn_widdershins.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // ��б�б��
                shearShapePanel.repaint();// ���µ�������paint()����
            }
        });
        btn_widdershins.setText("��б��");
        panel.add(btn_widdershins);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // ��ԭͼ��
                shearShapePanel.repaint();// ���µ�������paint()����
            }
        });
        btn_restore.setText("��    ԭ");
        panel.add(btn_restore);
    }
    
    class ShearShapePanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            Graphics2D g2 = (Graphics2D) g; // ���Graphics2D����
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// �������ζ���
            BasicStroke stroke = new BasicStroke(10); // ���������10�ıʻ�����
            g2.setStroke(stroke);// ���ñʻ�����
            g2.clearRect(0, 0, 338, 230);  // ���ԭ������
            if (flag == 0) {
                g2.draw(rect);// ����ԭ����
            } else if (flag == 1) {
                g2.shear(0.2,0.2);// ����б��
                g2.draw(rect);// ���ƾ���
            } else if (flag == 2) {
                g2.shear(-0.2,-0.2);// ����б��
                g2.draw(rect);// ���ƾ���
            }
        }
    }
}
