package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class TipOfDay extends JDialog {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6493879146336970741L;
    private final JPanel contentPanel = new JPanel();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            TipOfDay dialog = new TipOfDay();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the dialog.
     */
    public TipOfDay() {
        setTitle("\u4ECA\u65E5\u63D0\u793A");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.NORTH);
            {
                JLabel label = new JLabel("\u4ECA\u65E5\u63D0\u793A");
                label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                panel.add(label);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.SOUTH);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JCheckBox checkBox = new JCheckBox("\u4E0D\u518D\u663E\u793A");
                checkBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                panel.add(checkBox);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.WEST);
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.EAST);
        }
        {
            JScrollPane scrollPane = new JScrollPane();
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            {
                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("΢���ź�", Font.PLAIN, 14));
                textArea.setLineWrap(true);
                textArea
                        .setText("��˾��飺����ʡ���տƼ����޹�˾��һ���Լ�����������Ϊ���ĵĸ߿Ƽ�����ҵ����˾������1999��12�£���רҵ��Ӧ����������̺ͷ����ṩ�̡�������ʼ����������ҵ����������������ֻ������￪����������ҵ����������վ�����ȣ��Ⱥ�ɹ��������漰����������������Ӫ�������������Ķ�����ҵ����Ӧ�������Ӧ��ƽ̨��");
                scrollPane.setViewportView(textArea);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("\u4E0B\u6761\u4FE1\u606F");
                okButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("\u5173\u95ED\u7A97\u4F53");
                cancelButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
