package com.lzw.widget;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ButtonPanel extends JPanel {
    
    /**
     * Create the panel.
     */
    public ButtonPanel() {
        setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton button = new JButton("��¼ģ��");
        add(button);
        
        JButton button_1 = new JButton("�ʼ�����");
        add(button_1);
        
        JButton button_2 = new JButton("��վά��");
        add(button_2);
        
        JButton button_3 = new JButton("����ģ��");
        add(button_3);
        
        JButton button_4 = new JButton("���ݱ���");
        add(button_4);
        
        JButton button_5 = new JButton("ϵͳ����");
        add(button_5);
        
        JButton button_6 = new JButton("���ݹ���");
        add(button_6);
        
        JButton button_7 = new JButton("��������");
        add(button_7);
        
        JButton button_8 = new JButton("�������");
        add(button_8);
        
    }
    
}
