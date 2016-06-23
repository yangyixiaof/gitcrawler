package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ClockwiseTextApplet extends JApplet {
    private JTextField textField;
    ClockwiseTextPanel clockwiseTextPanel = new ClockwiseTextPanel(); // ����������ʵ��
    public void init() {
        setLayout(new BorderLayout());
        add(clockwiseTextPanel); // ��������ʵ����ӵ�����������
        textField = new JTextField();
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent arg0) {
                String text = textField.getText();// ��ȡ�ı����ַ���
                clockwiseTextPanel.setText(text);// Ϊ����е�text������ֵ
            }
        });
        getContentPane().add(textField, BorderLayout.SOUTH);
    }
    class ClockwiseTextPanel extends JPanel { // �����ڲ������
        private String text;
        public ClockwiseTextPanel() {
            setOpaque(false);// �������Ϊ͸��
            setLayout(null);// ����Ϊ���Բ���
        }
        public String getText() {
            return text; // ��ó�Ա������ֵ
        }
        public void setText(String text) {
            this.text = text;// Ϊ��Ա������ֵ
            repaint();// ����paint()����
        }
        public void paint(Graphics g) {// ��дpaint()����
            Graphics2D g2 = (Graphics2D) g;// ���Graphics2D��ʵ��
            int width = getWidth();// ������Ŀ��
            int height = getHeight();// ������ĸ߶�
            if (text != null) {
                char[] array = text.toCharArray();// ���ı�ת��Ϊ�ַ�����
                int len = array.length * 5;// ����Բ�İ뾶��ͬʱ���Ե������ֵľ���
                Font font = new Font("����", Font.BOLD, 22);// ��������
                g2.setFont(font);// ��������
                double angle = 0;// �����ʼ�Ƕ�
                for (int i = 0; i < array.length; i++) {// �����ַ����е��ַ�
                    if (i == 0) {
                        g2.setColor(Color.BLUE);// ��һ���ַ�����ɫ
                    } else {
                        g2.setColor(Color.BLACK);// �����ַ��ú�ɫ
                    }
                    int x = (int) (len * Math.sin(Math.toRadians(angle + 270)));// ����ÿ�����ֵĺ�����λ��
                    int y = (int) (len * Math.cos(Math.toRadians(angle + 270)));// ����ÿ�����ֵ�������λ��
                    g2.drawString(array[i] + "", width / 2 + x, height / 2 - y);// �����ַ�
                    angle = angle + 360d / array.length;// �ı�Ƕ�
                }
            }
        }
    }
}
