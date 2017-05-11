package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SuperFun extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6787592245621788484L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SuperFun frame = new SuperFun();
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
    public SuperFun() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setTitle("����͸����������");
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JLabel label = new JLabel("���������������");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        
        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        panel.add(textField);
        textField.setColumns(10);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton button = new JButton("���ɺ���");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int times = Integer.parseInt(textField.getText());// ����û��������Ҫ���ɵ��н��������
                // ʡ����ʾ��������̫��Ĵ���
                StringBuilder sb = new StringBuilder();// �����ַ�������������
                for (int i = 0; i < times; i++) {
                    List<String> startList = getStartNumber();// ���ǰ�κ���ļ���
                    List<String> endList = getEndNumber();// ��ú�κ���ļ���
                    for (int m = 0; m < startList.size(); m++) {
                        sb.append(startList.get(m));// ���ַ��������������ǰ�κ���
                    }
                    sb.append("    ");
                    for (int n = 0; n < endList.size(); n++) {
                        sb.append(endList.get(n));// ���ַ�������������Ӻ�κ���
                    }
                    sb.append("\n");
                }
                textArea.setText(sb.toString());// ���ı�������ʾ����
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        buttonPanel.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        scrollPane.setViewportView(textArea);
    }
    
    /**
     * �������ǰ��5������ķ���
     * 
     * @return
     */
    public List<String> getStartNumber() {
        List<String> list = new ArrayList<String>(); // ����ǰ�κ��뼯��
        String luckyNumber = "";
        for (int i = 1; i < 36; i++) { // ��ʼ��ǰ�κ��뼯��
            if (i < 10) {
                list.add("0" + i + "  ");// ���0~9�ĺ���
            } else {
                list.add("" + i + "  ");// ��Ӵ���9�ĺ���
            }
        }
        int roundIndex = 0;
        List<String> luckylist = new ArrayList<String>(); // ����ǰ�κ����List����
        for (int j = 0; j < 5; j++) {
            int amount = list.size(); // ��ȡǰ�κ���ĸ���
            Random r = new Random(); // ������ʵ����Random�Ķ���
            roundIndex = r.nextInt(amount); // ��ȡһ��0��amount-1�������
            luckyNumber = list.get(roundIndex); // ��ȡ��������
            luckylist.add(luckyNumber); // ���luckylist��
            list.remove(roundIndex); // �Ƴ��ող����ĺ���
        }
        Collections.sort(luckylist); // ��ǰ�κ����������
        return luckylist;
    }
    
    /**
     * ������ɺ��2������ķ���
     * 
     * @return
     */
    public List<String> getEndNumber() {
        List<String> list = new ArrayList<String>(); // ������κ��뼯��
        String luckyNumber = "";
        for (int i = 1; i < 13; i++) { // ��ʼ����κ��뼯��
            if (i < 10) {
                list.add("0" + i + "  ");// ���0~9�ĺ���
            } else {
                list.add("" + i + "  ");// ��Ӵ���9�ĺ���
            }
        }
        int roundIndex = 0;
        List<String> luckylist = new ArrayList<String>(); // �����κ����List����
        for (int j = 0; j < 2; j++) {
            int amount = list.size(); // ��ȡ��κ���ĸ���
            Random r = new Random(); // ������ʵ����Random�Ķ���
            roundIndex = r.nextInt(amount); // ��ȡһ��0��amount-1�������
            luckyNumber = list.get(roundIndex); // ��ȡ��������
            luckylist.add(luckyNumber); // ���luckylist��
            list.remove(roundIndex); // �Ƴ��ող����ĺ���
        }
        Collections.sort(luckylist); // �Ժ�κ����������
        return luckylist;
    }
}