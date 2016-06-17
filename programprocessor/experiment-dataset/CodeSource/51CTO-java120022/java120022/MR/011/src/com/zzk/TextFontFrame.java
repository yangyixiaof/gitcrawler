package com.zzk;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class TextFontFrame extends JFrame {
    ChangeTextFontPanel changeTextFontPanel = new ChangeTextFontPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        TextFontFrame frame = new TextFontFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    public TextFontFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("�����ı�������"); // �������
        setBounds(100, 100, 333, 199); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(changeTextFontPanel); // ��������ʵ����ӵ�����������
    }
    class ChangeTextFontPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) { // ��дpaint()����
            String value = "���ձ�̴ʵ�����";
            int x = 40; // �ı�λ�õĺ�����
            int y = 50; // �ı�λ�õ�������
            Font font = new Font("�����п�", Font.BOLD + Font.ITALIC, 26); // �����������
            g.setFont(font); // ��������
            g.drawString(value, x, y); // �����ı�
            value = "http://community.mrbccd.com";
            x = 10; // �ı�λ�õĺ�����
            y = 100; // �ı�λ�õ�������
            font = new Font("����", Font.BOLD, 20); // �����������
            g.setFont(font); // ��������
            g.drawString(value, x, y); // �����ı�
        }
    }
}
