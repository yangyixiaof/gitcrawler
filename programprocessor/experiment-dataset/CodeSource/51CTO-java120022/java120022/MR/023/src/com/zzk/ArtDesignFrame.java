package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArtDesignFrame extends JFrame {
    ArtDesignPanel artDesignPanel = new ArtDesignPanel(); // ����������ʵ��
    
    public static void main(String args[]) { // ������
        ArtDesignFrame frame = new ArtDesignFrame(); // �����������ʵ��
        frame.setVisible(true); // ��ʾ����
    }
    
    public ArtDesignFrame() {
        super(); // ���ó���Ĺ��췽��
        setTitle("��������ͼ��"); // �������
        setBounds(100, 100, 338, 230); // �������ʾλ�úʹ�С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����رշ�ʽ
        add(artDesignPanel); // ��������ʵ����ӵ�����������
    }
    
    class ArtDesignPanel extends JPanel { // �����ڲ������
        public void paint(Graphics g) {     // ��дpaint()����
            Graphics2D g2 = (Graphics2D)g; // ���Graphics2D����
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, 5, 160, 10);// ������Բ����
            Random random = new Random();// �������������
            g2.translate(160, 90);// ƽ��������
            int R = random.nextInt(256);//���������ɫ��Rֵ
            int G = random.nextInt(256);//���������ɫ��Gֵ
            int B = random.nextInt(256);//���������ɫ��Bֵ
            Color color = new Color(R,G,B);//������ɫ����
            g2.setColor(color);//ָ����ɫ
            g2.draw(ellipse);// ������Բ
            int i=0;
            while (i<100){
                R = random.nextInt(256);//���������ɫ��Rֵ
                G = random.nextInt(256);//���������ɫ��Gֵ
                B = random.nextInt(256);//���������ɫ��Bֵ
                color = new Color(R,G,B);//�����µ���ɫ����
                g2.setColor(color);//ָ����ɫ
                g2.rotate(10);// ��ת����
                g2.draw(ellipse);// ������Բ
                i++;
            }
        }
    }
}
