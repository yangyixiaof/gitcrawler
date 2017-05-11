package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class TitledBorderPanel extends JFrame {
    
    private JPanel contentPane;
    private TitledBorder titledBorder;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TitledBorderPanel frame = new TitledBorderPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public TitledBorderPanel() {
        setTitle("ʵ�ִ�����߿���������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 448, 389);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_1 = new JPanel();// �������
        panel_1.setBorder(new TitledBorder(null, "ˮƽ���еı���",
                TitledBorder.CENTER, TitledBorder.TOP));// ��ӱ���߿�
        contentPane.add(panel_1);
        
        JPanel panel_2 = new JPanel();// �����ö�����ı߿����
        titledBorder = new TitledBorder("�ö�����");
        panel_2.setBorder(titledBorder);
        contentPane.add(panel_2);
        
        JPanel panel_3 = new JPanel();// �������б߿����ö���������
        titledBorder = new TitledBorder(null, "�߿����ö�����", TitledBorder.LEADING,
                TitledBorder.BELOW_TOP);
        panel_3.setBorder(titledBorder);
        contentPane.add(panel_3);
        
        JPanel panel_4 = new JPanel();// �������б߿��ڵױ߱�������
        titledBorder = new TitledBorder(null, "�߿��ڵױ߱���", TitledBorder.LEADING,
                TitledBorder.ABOVE_BOTTOM);
        panel_4.setBorder(titledBorder);
        contentPane.add(panel_4);
        
        JPanel panel_5 = new JPanel();// �������еױ߱���߿�����
        titledBorder = new TitledBorder(null, "�ױ߱���", TitledBorder.LEADING,
                TitledBorder.BOTTOM);
        panel_5.setBorder(titledBorder);
        contentPane.add(panel_5);
        
        JPanel panel_6 = new JPanel();// ���������õױ���߿�����
        titledBorder = new TitledBorder(null, "�õױ���", TitledBorder.LEADING,
                TitledBorder.BELOW_BOTTOM);
        panel_6.setBorder(titledBorder);
        contentPane.add(panel_6);
        
        JPanel panel_7 = new JPanel();// ��������ˮƽ���еİ�ɫ��������
        titledBorder = new TitledBorder(null, "", TitledBorder.CENTER,
                TitledBorder.TOP);
        panel_7.setBorder(titledBorder);
        contentPane.add(panel_7);
        
    }
    
}
