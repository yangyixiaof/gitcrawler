package com.mingrisoft.thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Font;

public class ProducerAndConsumerFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1644485036183526329L;
    private JPanel contentPane;
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
    private final int size = 10;
    private JTextArea producerTextArea;
    private JTextArea consumerTextArea;
    private JTextArea storageTextArea;
    
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
                    ProducerAndConsumerFrame frame = new ProducerAndConsumerFrame();
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
    public ProducerAndConsumerFrame() {
        setTitle("\u4F7F\u7528\u963B\u585E\u961F\u5217\u5B9E\u73B0\u7EBF\u7A0B\u540C\u6B65");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton startButton = new JButton("\u5F00\u59CB\u751F\u4EA7");
        startButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_startButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(startButton);
        
        JPanel resultPanel = new JPanel();
        contentPane.add(resultPanel, BorderLayout.CENTER);
        resultPanel.setLayout(new GridLayout(1, 3, 5, 5));
        
        JPanel producerPanel = new JPanel();
        resultPanel.add(producerPanel);
        producerPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel producerLabel = new JLabel("\u751F\u4EA7\u8005");
        producerLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        producerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        producerPanel.add(producerLabel, BorderLayout.NORTH);
        
        JScrollPane producerScrollPane = new JScrollPane();
        producerPanel.add(producerScrollPane, BorderLayout.CENTER);
        
        producerTextArea = new JTextArea();
        producerTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        producerScrollPane.setViewportView(producerTextArea);
        
        JPanel consumerPanel = new JPanel();
        resultPanel.add(consumerPanel);
        consumerPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel consumerLabel = new JLabel("\u6D88\u8D39\u8005");
        consumerLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        consumerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        consumerPanel.add(consumerLabel, BorderLayout.NORTH);
        
        JScrollPane consumerScrollPane = new JScrollPane();
        consumerPanel.add(consumerScrollPane, BorderLayout.CENTER);
        
        consumerTextArea = new JTextArea();
        consumerTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        consumerScrollPane.setViewportView(consumerTextArea);
        
        JPanel storagePanel = new JPanel();
        resultPanel.add(storagePanel);
        storagePanel.setLayout(new BorderLayout(0, 0));
        
        JLabel storageLabel = new JLabel("\u4ED3\u5E93");
        storageLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        storageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        storagePanel.add(storageLabel, BorderLayout.NORTH);
        
        JScrollPane storageScrollPane = new JScrollPane();
        storagePanel.add(storageScrollPane, BorderLayout.CENTER);
        
        storageTextArea = new JTextArea();
        storageTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        storageScrollPane.setViewportView(storageTextArea);
    }
    
    protected void do_startButton_actionPerformed(ActionEvent arg0) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
    
    private class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < size; i++) {// size�����������ʾ�����Ʒ�Ĵ���
                int b = new Random().nextInt(255); // ����һ�������
                String text = producerTextArea.getText();// ����������ı�����Ϣ
                producerTextArea.setText(text + "������Ʒ��" + b + "\n");// �����ı�����Ϣ
                try {
                    queue.put(b);// ����������Ԫ��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String storage = storageTextArea.getText();// ��òֿ��ı�����Ϣ
                storageTextArea.setText(storage + "�ֿ��л���" + queue.size() + "����Ʒ\n");
                try {
                    Thread.sleep(100);// ����0.1��ʵ�ֶ�̬Ч��
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
    private class Consumer implements Runnable {
        
        @Override
        public void run() {
            for (int i = 0; i < size; i++) {
                int b = 0;
                try {
                    b = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String text = consumerTextArea.getText();
                consumerTextArea.setText(text + "������Ʒ��" + b + "\n");
                String storage = storageTextArea.getText();
                storageTextArea.setText(storage + "�ֿ��л���" + queue.size() + "����Ʒ\n");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }
}
