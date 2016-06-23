package com.mingrisoft.time;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class DateValidator extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1397183659288755891L;
    private JPanel contentPane;
    private JTextField dateTextField;
    private JTextField formatTextField;
    
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
                    DateValidator frame = new DateValidator();
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
    public DateValidator() {
        setTitle("\u65E5\u671F\u683C\u5F0F\u6821\u9A8C\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 5, 5));
        
        JPanel datePanel = new JPanel();
        contentPane.add(datePanel);
        
        JLabel dateLabel = new JLabel("\u8F93\u5165\u65E5\u671F\uFF1A");
        dateLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        datePanel.add(dateLabel);
        
        dateTextField = new JTextField();
        dateTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        datePanel.add(dateTextField);
        dateTextField.setColumns(15);
        
        JPanel formatPanel = new JPanel();
        contentPane.add(formatPanel);
        
        JLabel formatLabel = new JLabel("\u8F93\u5165\u683C\u5F0F\uFF1A");
        formatLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        formatPanel.add(formatLabel);
        
        formatTextField = new JTextField();
        formatTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        formatPanel.add(formatTextField);
        formatTextField.setColumns(15);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        
        JButton button = new JButton("\u6821\u9A8C");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        buttonPanel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String date = dateTextField.getText();// �������
        String format = formatTextField.getText();// ��ø�ʽ
        if (date.length() == 0 || format.length() == 0) {
            JOptionPane.showMessageDialog(this, "���ڻ��ʽ����Ϊ��", "", JOptionPane.WARNING_MESSAGE);// ������ڻ��ʽΪ������ʾ�û�����
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);// ����ָ����ʽ��formatter
        try {
            formatter.parse(date);// ����ָ���ĸ�ʽ����date����
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "���ڸ�ʽ����ƥ��", "", JOptionPane.WARNING_MESSAGE);// �����ƥ������ʾ�û�����ƥ��
            return;
        }
        JOptionPane.showMessageDialog(this, "���ڸ�ʽ�໥ƥ��", "", JOptionPane.WARNING_MESSAGE);// ���ƥ������ʾ�û���ƥ��
        return;
    }
    
}
