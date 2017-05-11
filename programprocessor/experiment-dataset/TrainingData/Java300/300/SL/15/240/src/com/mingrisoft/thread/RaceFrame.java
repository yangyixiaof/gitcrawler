package com.mingrisoft.thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;

public class RaceFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4941729012450153307L;
    private JPanel contentPane;
    private JTextArea rabbitTextArea;
    private JTextArea tortoiseTextArea;
    
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
                    RaceFrame frame = new RaceFrame();
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
    public RaceFrame() {
        setTitle("\u4F11\u7720\u5F53\u524D\u7EBF\u7A0B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton button = new JButton("\u6BD4\u8D5B\u5F00\u59CB");
        button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        buttonPanel.add(button);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JPanel rabbitPanel = new JPanel();
        panel.add(rabbitPanel);
        rabbitPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel rabbitLabel = new JLabel("\u5154\u5B50\u7684\u6BD4\u8D5B\u8BB0\u5F55");
        rabbitLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        rabbitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rabbitPanel.add(rabbitLabel, BorderLayout.NORTH);
        
        JScrollPane rabbitScrollPane = new JScrollPane();
        rabbitPanel.add(rabbitScrollPane, BorderLayout.CENTER);
        
        rabbitTextArea = new JTextArea();
        rabbitTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        rabbitScrollPane.setViewportView(rabbitTextArea);
        
        JPanel tortoisePanel = new JPanel();
        panel.add(tortoisePanel);
        tortoisePanel.setLayout(new BorderLayout(0, 0));
        
        JLabel tortoiseLabel = new JLabel("\u4E4C\u9F9F\u7684\u6BD4\u8D5B\u8BB0\u5F55");
        tortoiseLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tortoiseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tortoisePanel.add(tortoiseLabel, BorderLayout.NORTH);
        
        JScrollPane tortoiseScrollPane = new JScrollPane();
        tortoisePanel.add(tortoiseScrollPane, BorderLayout.CENTER);
        
        tortoiseTextArea = new JTextArea();
        tortoiseTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tortoiseScrollPane.setViewportView(tortoiseTextArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        Runnable run1 = new Rabbit();
        Runnable run2 = new Tortoise();
        Thread rabbit = new Thread(run1);
        Thread tortoise = new Thread(run2);
        rabbit.start();
        tortoise.start();
    }
    
    private class Rabbit implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 11; i++) {// ѭ��10��ģ�����ܵĹ���
                String text = rabbitTextArea.getText();// ����ı����е���Ϣ
                try {
                    Thread.sleep(1);// �߳�����0.001�룬ģ���������ܲ�
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rabbitTextArea.setText(text + "��������" + i + "0��\n");// ��ʾ���ӵ��ܲ�����
                if (i == 9) {
                    rabbitTextArea.setText(text + "������˯��\n");// ������90��ʱ��ʼ˯��
                    try {
                        Thread.sleep(10000);// �߳�����10�룬ģ��������˯��
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i == 10) {
                    try {
                        Thread.sleep(1);// �߳�����0.001�룬ģ���������ܲ�
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rabbitTextArea.setText(text + "���ӵ����յ�\n");// ��ʾ���ӵ������յ�
                }
            }
        }
    }
    
    private class Tortoise implements Runnable {
        
        @Override
        public void run() {
            for (int i = 1; i < 11; i++) {
                String text = tortoiseTextArea.getText();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tortoiseTextArea.setText(text + "�ڹ�����" + i + "0��\n");
                if (i == 10) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tortoiseTextArea.setText(text + "�ڹ굽���յ�\n");
                }
            }
            
        }
        
    }
}
