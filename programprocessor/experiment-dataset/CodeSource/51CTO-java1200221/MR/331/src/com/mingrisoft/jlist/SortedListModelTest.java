package com.mingrisoft.jlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JList;
import java.awt.Font;

public class SortedListModelTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8253911300573828115L;
    private JPanel contentPane;
    
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
                    SortedListModelTest frame = new SortedListModelTest();
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
    public SortedListModelTest() {
        setTitle("\u81EA\u52A8\u6392\u5E8F\u7684\u5217\u8868");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JList list = new JList();
        list.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(list);
        
        SortedListModel model = new SortedListModel();
        model.add("��Java�����ŵ���ͨ����2�棩��");
        model.add("��PHP�����ŵ���ͨ����2�棩��");
        model.add("��Visual Basic�����ŵ���ͨ����2�棩��");
        model.add("��Visual C++�����ŵ���ͨ����2�棩��");
        model.add("��Java��̴ʵ䡷");
        model.add("��ϸ˵Java��");
        model.add("����ƵѧJava��");
        
        list.setModel(model);
    }
    
}
