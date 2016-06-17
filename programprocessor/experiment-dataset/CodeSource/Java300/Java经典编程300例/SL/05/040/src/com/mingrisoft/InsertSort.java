package com.mingrisoft;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class InsertSort extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6620166811183406036L;
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertSort frame = new InsertSort();
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
    public InsertSort() {
        setTitle("ʹ��ֱ�Ӳ��뷨����������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 335, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textArea1 = new JTextArea();
        textArea1.setBounds(6, 6, 86, 250);
        contentPane.add(textArea1);
        
        JButton button = new JButton("�����������");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(104, 49, 114, 30);
        contentPane.add(button);
        
        JButton button_1 = new JButton("����");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(104, 161, 114, 30);
        contentPane.add(button_1);
        
        textArea2 = new JTextArea();
        textArea2.setBounds(230, 6, 86, 250);
        contentPane.add(textArea2);
    }
    
    private int[] array = new int[10];
    private JTextArea textArea1;
    private JTextArea textArea2;
    
protected void do_button_actionPerformed(ActionEvent e) {
    Random random = new Random();// �������������
    textArea1.setText("");
    for (int i = 0; i < array.length; i++) {// ��ʼ������Ԫ��
        array[i] = random.nextInt(90);// ����50���ڵ������
        textArea1.append(array[i] + "\n");// ������Ԫ����ʾ���ı���ؼ���
    }
}
    
protected void do_button_1_actionPerformed(ActionEvent e) {
    int tmp;// ������ʱ����
    int j;
    for (int i = 1; i < array.length; i++) {
        tmp = array[i];// ������ʱ����
        for (j = i - 1; j >= 0 && array[j] > tmp; j--) {
            array[j + 1] = array[j];// ����Ԫ�ؽ���
        }
        array[j + 1] = tmp;// ������λ�ò�������
    }
    textArea2.setText("");
    for (int i = 0; i < array.length; i++) {// ��ʼ������Ԫ��
        textArea2.append(array[i] + "\n");// ������Ԫ����ʾ���ı���ؼ���
    }
}
}
