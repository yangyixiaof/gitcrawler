package com.mingrisoft;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BubbleSort extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5417254398243107506L;
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BubbleSort frame = new BubbleSort();
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
    public BubbleSort() {
        setTitle("ʹ��ð�����򷨶���������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 306, 364);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 5, 280, 46);
        contentPane.add(scrollPane);
        
        textArea1 = new JTextArea();
        scrollPane.setViewportView(textArea1);
        
        JButton button = new JButton("�������������");
        button.setBounds(85, 60, 125, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        contentPane.add(button);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(5, 101, 280, 173);
        contentPane.add(scrollPane_1);
        
        textArea2 = new JTextArea();
        scrollPane_1.setViewportView(textArea2);
        
        JButton button_1 = new JButton("����");
        button_1.setBounds(120, 285, 60, 28);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        contentPane.add(button_1);
    }
    
    private int[] array = new int[10];
    private JTextArea textArea1;
    private JTextArea textArea2;
    
    protected void do_button_actionPerformed(ActionEvent e) {
        Random random = new Random();// �������������
        textArea1.setText("");// ����ı���
        for (int i = 0; i < array.length; i++) {// ��ʼ������Ԫ��
            array[i] = random.nextInt(50);// ����50���ڵ������
            textArea1.append(array[i] + "  ");// ������Ԫ����ʾ���ı���ؼ���
        }
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        textArea2.setText("");// ����ı���
        for (int i = 1; i < array.length; i++) {
            // �Ƚ���������Ԫ�أ��ϴ��������ð��
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];// �ѵ�һ��Ԫ��ֵ���ֵ���ʱ������
                    array[j] = array[j + 1];// �ѵڶ���Ԫ��ֵ���浽��һ��Ԫ�ص�Ԫ��
                    array[j + 1] = temp;// ����ʱ����Ҳ���ǵ�һ��Ԫ��ԭֵ���ֵ��ڶ���Ԫ����
                }
                textArea2.append(array[j] + "  ");// ������������Ԫ����ʾ���ı�����
            }
            textArea2.append("��");
            for (int j = array.length - i; j < array.length; j++) {
                textArea2.append(array[j] + "  ");// ������������Ԫ����ʾ���ı�����
            }
            textArea2.append("��\n");
        }
    }
}
