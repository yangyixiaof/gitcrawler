package com.mingrisoft.runtime;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.UIManager;

public class MemoryStatus extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3225753245258184286L;
    private JPanel contentPane;
    private JPanel statusPanel;
    private JLabel totalLabel;
    private JLabel freeLabel;
    private JScrollPane scrollPane;
    private JProgressBar progressBar;
    
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
                    MemoryStatus frame = new MemoryStatus();
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
    public MemoryStatus() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5185\u5B58\u72B6\u6001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        statusPanel = new JPanel();
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setLayout(new GridLayout(2, 1, 5, 5));
        
        freeLabel = new JLabel("");
        freeLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        statusPanel.add(freeLabel);
        
        totalLabel = new JLabel("");
        totalLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        statusPanel.add(totalLabel);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        progressBar.setOrientation(SwingConstants.VERTICAL);
        scrollPane.setViewportView(progressBar);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        new Thread(new Memory()).start();
    }
    
    private class Memory implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.gc();// ǿ����������������������ͷ��ڴ�
                int free = (int) Runtime.getRuntime().freeMemory() / 1024;// ��ÿ����ڴ�
                int total = (int) Runtime.getRuntime().totalMemory() / 1024;// ����ܹ��ڴ�
                int status = free * 100 / total;// ����ڴ�ʹ����
                freeLabel.setText("�����ڴ棺" + free + "Kb"); // ��ʾ�����ڴ�
                totalLabel.setText("�ܹ��ڴ棺" + total + "Kb"); // ��ʾ�ܹ��ڴ�
                progressBar.setValue(status); // ��ʾ�ڴ��ʹ����
                progressBar.setString("�����ڴ棺" + status + "%");
                try {
                    Thread.sleep(1000);// �߳�����1���ӽ��ж�̬����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
