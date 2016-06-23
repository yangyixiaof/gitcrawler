package com.mingrisoft.time;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class WorldTimeViewer extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5988076597643890566L;
    private JPanel contentPane;
    private JComboBox TZComboBox;
    private JLabel resultLabel;
    
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
                    WorldTimeViewer frame = new WorldTimeViewer();
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
    public WorldTimeViewer() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4E16\u754C\u65F6\u95F4\u67E5\u770B\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 140);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
        
        JPanel TZPanel = new JPanel();
        contentPane.add(TZPanel);
        TZPanel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JLabel TZLabel = new JLabel("\u9009\u62E9\u76EE\u6807\u65F6\u533A\uFF1A");
        TZLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        TZPanel.add(TZLabel);
        
        TZComboBox = new JComboBox();
        TZComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_foramtterComboBox_actionPerformed(e);
            }
        });
        TZComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        TZPanel.add(TZComboBox);
        
        JPanel resultPanel = new JPanel();
        contentPane.add(resultPanel);
        resultPanel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JLabel tipLabel = new JLabel("\u76EE\u6807\u65F6\u533A\u65E5\u671F\uFF1A");
        tipLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        resultPanel.add(tipLabel);
        
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        resultPanel.add(resultLabel);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        String[] timezones = TimeZone.getAvailableIDs();// ������п��õ�ʱ��ID
        for (String patten : timezones) {
            TZComboBox.addItem(patten);// ������ID��ӵ���Ͽ���
        }
    }
    
    protected void do_foramtterComboBox_actionPerformed(ActionEvent e) {
        String currentTimezone = (String) TZComboBox.getSelectedItem();// ���ѡ���ID
        Calendar calendar = Calendar.getInstance();// �����������
        calendar.setTimeZone(TimeZone.getTimeZone(currentTimezone));// ������������ʱ��
        StringBuilder result = new StringBuilder();// ����StringBuilder��������
        result.append(calendar.getTimeZone().getDisplayName() + " ");// ��õ�ǰʱ����������
        result.append(calendar.get(Calendar.HOUR_OF_DAY) + ":");// ��õ�ǰʱ�ӵ�Сʱ
        result.append(calendar.get(Calendar.MINUTE));// ��õ�ǰʱ���ķ���
        resultLabel.setText(result.toString());// ���½��
    }
    
}
