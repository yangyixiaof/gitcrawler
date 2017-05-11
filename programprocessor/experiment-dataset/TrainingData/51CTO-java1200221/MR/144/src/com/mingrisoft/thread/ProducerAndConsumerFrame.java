package com.mingrisoft.thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class ProducerAndConsumerFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1644485036183526329L;
    private JPanel contentPane;
    private final LinkedList<String> list = new LinkedList<String>();
    private static final int MAX = 10;
    private volatile int count;
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
        setTitle("\u751F\u4EA7\u8005\u548C\u6D88\u8D39\u8005\u95EE\u9898");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton startButton = new JButton("\u5F00\u59CB\u751F\u4EA7");
        startButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
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
        producerLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        producerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        producerPanel.add(producerLabel, BorderLayout.NORTH);
        
        JScrollPane producerScrollPane = new JScrollPane();
        producerPanel.add(producerScrollPane, BorderLayout.CENTER);
        
        producerTextArea = new JTextArea();
        producerTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        producerScrollPane.setViewportView(producerTextArea);
        
        JPanel consumerPanel = new JPanel();
        resultPanel.add(consumerPanel);
        consumerPanel.setLayout(new BorderLayout(0, 0));
        
        JLabel consumerLabel = new JLabel("\u6D88\u8D39\u8005");
        consumerLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        consumerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        consumerPanel.add(consumerLabel, BorderLayout.NORTH);
        
        JScrollPane consumerScrollPane = new JScrollPane();
        consumerPanel.add(consumerScrollPane, BorderLayout.CENTER);
        
        consumerTextArea = new JTextArea();
        consumerTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        consumerScrollPane.setViewportView(consumerTextArea);
        
        JPanel storagePanel = new JPanel();
        resultPanel.add(storagePanel);
        storagePanel.setLayout(new BorderLayout(0, 0));
        
        JLabel storageLabel = new JLabel("\u4ED3\u5E93");
        storageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        storageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        storagePanel.add(storageLabel, BorderLayout.NORTH);
        
        JScrollPane storageScrollPane = new JScrollPane();
        storagePanel.add(storageScrollPane, BorderLayout.CENTER);
        
        storageTextArea = new JTextArea();
        storageTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        storageScrollPane.setViewportView(storageTextArea);
    }
    
    protected void do_startButton_actionPerformed(ActionEvent arg0) {
        
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        
    }
    
    private class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < MAX; i++) {// 向仓库中添加商品，MAX是仓库的最大容量
                synchronized (list) {// 使用同步块来解决同步问题
                    String storage = storageTextArea.getText();// 获得文本域内容
                    String text = producerTextArea.getText(); // 获得文本域内容
                    if (list.size() == MAX) {// 如果仓库装满就等待
                        storageTextArea.setText(storage + "仓库已满\n");
                        try {
                            list.wait();// 开始等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String product = "Java编程宝典";
                        list.add(product);// 向仓库中添加商品
                        list.notify();// 唤醒等待的线程
                        producerTextArea.setText(text + "生产：" + product + "\n");
                        count++;// 仓库中商品的数量加一
                        storageTextArea.setText(storage + "仓库中还有" + count + "个商品\n");
                        try {
                            Thread.sleep(100);// 当前线程休眠0.1秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    private class Consumer implements Runnable {
        
        public void run() {
            for (int i = 0; i < MAX; i++) {
                synchronized (list) {
                    String storage = storageTextArea.getText();
                    String text = consumerTextArea.getText();
                    if (list.size() == 0) {
                        storageTextArea.setText(storage + "仓库已空\n");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        consumerTextArea.setText(text + "消费：" + list.removeLast() + "\n");
                        list.notify();
                        count--;
                        storageTextArea.setText(storage + "仓库中还有" + count + "个商品\n");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    
                }
            }
        }
    }
}
