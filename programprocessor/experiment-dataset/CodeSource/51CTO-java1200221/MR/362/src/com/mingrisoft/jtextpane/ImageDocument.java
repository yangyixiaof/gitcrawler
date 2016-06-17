package com.mingrisoft.jtextpane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.Font;

public class ImageDocument extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2805667115903449650L;
    private JPanel contentPane;
    private JTextPane textPane;
    
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
                    ImageDocument frame = new ImageDocument();
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
    public ImageDocument() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5305\u542B\u56FE\u7247\u7684\u6587\u6863");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textPane = new JTextPane();
        textPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(textPane);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        String heading = "����ʡ���տƼ����޹�˾\n";
        String content = "����ʡ���տƼ����޹�˾��һ���Լ�����������Ϊ���ĵĸ߿Ƽ�����ҵ����˾������1999��12�£���רҵ��Ӧ����������̺ͷ����ṩ�̡�������ʼ����������ҵ����������������ֻ������￪����������ҵ����������վ�����ȣ��Ⱥ�ɹ��������漰����������������Ӫ�������������Ķ�����ҵ����Ӧ�������Ӧ��ƽ̨��Ŀǰ�ѳ�Ϊ�����������ҵ��֪��Ʒ�ơ�";
        Style imageStyle = new StyleContext().addStyle("Image", null);
        StyleConstants.setIcon(imageStyle, new ImageIcon("src/images/logo.jpg"));
        DefaultStyledDocument document = new DefaultStyledDocument();
        try {
            document.insertString(0, "image", imageStyle);
            document.insertString(document.getLength(), heading + content, null);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        textPane.setDocument(document);
    }
}
