package com.mingrisoft.jeditorpane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class HighLightKeyWord extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5747183545714998015L;
    private JPanel contentPane;
    private JTextField textField;
    private JEditorPane editorPane;
    
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
                    HighLightKeyWord frame = new HighLightKeyWord();
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
    public HighLightKeyWord() {
        setTitle("\u9AD8\u4EAE\u6307\u5B9A\u7684\u5173\u952E\u5B57");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JLabel label = new JLabel("\u5173\u952E\u5B57\uFF1A");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(label);
        
        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(textField);
        textField.setColumns(15);
        
        JButton button = new JButton("\u9AD8\u4EAE");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        editorPane = new JEditorPane();
        editorPane
                .setText("       ����ʡ���տƼ����޹�˾��һ���Լ�����������Ϊ���ĵĸ߿Ƽ�����ҵ����˾������1999��12�£���רҵ��Ӧ����������̺ͷ����ṩ�̡�������ʼ����������ҵ����������������ֻ������￪����������ҵ����������վ�����ȣ��Ⱥ�ɹ��������漰����������������Ӫ�������������Ķ�����ҵ����Ӧ�������Ӧ��ƽ̨��Ŀǰ�ѳ�Ϊ�����������ҵ��֪��Ʒ�ơ�");
        editorPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(editorPane);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String key = textField.getText();
        String content = editorPane.getText();
        Highlighter highlighter = editorPane.getHighlighter();
        highlighter.removeAllHighlights();
        if (content.contains(key)) {
            int index = content.indexOf(key);
            while (true) {
                if (index != -1) {
                    try {
                        highlighter.addHighlight(index, index + key.length(), DefaultHighlighter.DefaultPainter);
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                    index = content.indexOf(key, ++index);
                    
                } else {
                    break;
                }
            }
        }
    }
}
