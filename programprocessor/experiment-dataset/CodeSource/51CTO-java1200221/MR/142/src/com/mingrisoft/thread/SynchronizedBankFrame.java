package com.mingrisoft.thread;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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

public class SynchronizedBankFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2671056183299397274L;
    private JPanel contentPane;
    private JTextArea thread1TextArea;
    private JTextArea thread2TextArea;
    private JTextArea thread3TextArea;
    
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
                    SynchronizedBankFrame frame = new SynchronizedBankFrame();
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
    public SynchronizedBankFrame() {
        setTitle("\u65B0\u5EFA\u6709\u8FD4\u56DE\u503C\u7684\u7EBF\u7A0B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton startButton = new JButton("\u5F00\u59CB\u5B58\u94B1");
        startButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        buttonPanel.add(startButton);
        
        JPanel processPanel = new JPanel();
        contentPane.add(processPanel, BorderLayout.CENTER);
        processPanel.setLayout(new GridLayout(0, 3, 5, 5));
        
        JPanel thread1Panel = new JPanel();
        processPanel.add(thread1Panel);
        thread1Panel.setLayout(new BorderLayout(0, 0));
        
        JLabel thread1Label = new JLabel("\u4E00\u53F7\u7EBF\u7A0B");
        thread1Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread1Label.setHorizontalAlignment(SwingConstants.CENTER);
        thread1Panel.add(thread1Label, BorderLayout.NORTH);
        
        JScrollPane thread1ScrollPane = new JScrollPane();
        thread1Panel.add(thread1ScrollPane, BorderLayout.CENTER);
        
        thread1TextArea = new JTextArea();
        thread1TextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread1ScrollPane.setViewportView(thread1TextArea);
        
        JPanel thread2Panel = new JPanel();
        processPanel.add(thread2Panel);
        thread2Panel.setLayout(new BorderLayout(0, 0));
        
        JLabel thread2Label = new JLabel("\u4E8C\u53F7\u7EBF\u7A0B");
        thread2Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread2Label.setHorizontalAlignment(SwingConstants.CENTER);
        thread2Panel.add(thread2Label, BorderLayout.NORTH);
        
        JScrollPane thread2ScrollPane = new JScrollPane();
        thread2Panel.add(thread2ScrollPane, BorderLayout.CENTER);
        
        thread2TextArea = new JTextArea();
        thread2TextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread2ScrollPane.setViewportView(thread2TextArea);
        
        JPanel thread3Panel = new JPanel();
        processPanel.add(thread3Panel);
        thread3Panel.setLayout(new BorderLayout(0, 0));
        
        JLabel thread3Label = new JLabel("\u4E09\u53F7\u7EBF\u7A0B");
        thread3Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread3Label.setHorizontalAlignment(SwingConstants.CENTER);
        thread3Panel.add(thread3Label, BorderLayout.NORTH);
        
        JScrollPane thread3ScrollPane = new JScrollPane();
        thread3Panel.add(thread3ScrollPane, BorderLayout.CENTER);
        
        thread3TextArea = new JTextArea();
        thread3TextArea.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        thread3ScrollPane.setViewportView(thread3TextArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        Bank bank = new Bank();
        Transfer transfer1 = new Transfer(bank, thread1TextArea);// ����Transfer����
        Transfer transfer2 = new Transfer(bank, thread2TextArea);// ����Transfer����
        FutureTask<Integer> task1 = new FutureTask<Integer>(transfer1);// ����FutureTask����
        FutureTask<Integer> task2 = new FutureTask<Integer>(transfer2);// ����FutureTask����
        Thread thread1 = new Thread(task1);// ����һ���߳�
        Thread thread2 = new Thread(task2);// ���������߳�
        thread1.start();// ����һ���߳�
        thread2.start();// ���ж����߳�
        try {
            int thread1Result = task1.get();// ���һ���̵߳ļ�����
            int thread2Result = task2.get();// ��ö����̵߳ļ�����
            thread3TextArea.setText(thread3TextArea.getText() + "һ�ż������ǣ�" + thread1Result + "\n");// ���������߳��ı�����Ϣ
            thread3TextArea.setText(thread3TextArea.getText() + "���ż������ǣ�" + thread2Result + "\n");// ���������߳��ı�����Ϣ
            thread3TextArea.setText(thread3TextArea.getText() + "ʵ�ʵĽ���ǣ�" + (thread1Result + thread2Result - 100) + "\n");// ���������߳��ı�����Ϣ
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
