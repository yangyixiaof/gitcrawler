package com.mingrisoft.time;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class DigitalClock extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5439091024994019226L;
    private JPanel contentPane;
    private JLabel label;
    
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
                    DigitalClock frame = new DigitalClock();
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
    public DigitalClock() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setTitle("\u6570\u5B57\u65F6\u949F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 200, 100);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        label = new JLabel("");
        label.setFont(new Font("΢���ź�", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    private static String getTime() {
        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // ��õ�ǰСʱ
        int minute = calendar.get(Calendar.MINUTE); // ��õ�ǰ����
        int second = calendar.get(Calendar.SECOND); // ��õ�ǰ��
        return format(hour) + ":" + format(minute) + ":" + format(second);// ���ظ�ʽ�����ַ���
    }
    
    private static String format(int number) {
        return number < 10 ? "0" + number : "" + number;// �������С��10������ǰ���0����
    }
    
    private class ClockRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {// ��ʱ��һֱ���ڸ���״̬
                label.setText(getTime()); // ����ʱ��
                try {
                    Thread.sleep(1000); // ����һ����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        new Thread(new ClockRunnable()).start();
    }
}
