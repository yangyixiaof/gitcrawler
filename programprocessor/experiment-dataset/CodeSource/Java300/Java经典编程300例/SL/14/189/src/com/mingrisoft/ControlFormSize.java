
package com.mingrisoft;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlFormSize extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2085588912441845548L;
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ControlFormSize frame = new ControlFormSize();
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
    public ControlFormSize() {
        setTitle("���ô����С");// ���ô������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲرշ�ʽ
        setSize(400, 300);// ���ô����С
        contentPane = new JPanel();// �����������
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);// �����������
        JLabel label = new JLabel("��ȣ�400���߶ȣ�300");// ������ǩ�ؼ�
        contentPane.add(label, BorderLayout.CENTER);// ��ӱ�ǩ�ؼ�������
    }
    
}
