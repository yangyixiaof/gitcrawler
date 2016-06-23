package com.mingrisoft.jlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class SelectEventTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7020163320919418696L;
    private JPanel contentPane;
    private JList list;
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
                    SelectEventTest frame = new SelectEventTest();
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
    public SelectEventTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5217\u8868\u9879\u53CC\u51FB\u4E8B\u4EF6\u6D4B\u8BD5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        label = new JLabel("\u56FE\u4E66\u8D2D\u4E70\u4FE1\u606F");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(label);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                do_list_mouseClicked(e);
            }
        });
        list.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        scrollPane.setViewportView(list);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        String[] listData = new String[7];
        listData[0] = "��Java�����ŵ���ͨ����2�棩��";
        listData[1] = "��PHP�����ŵ���ͨ����2�棩��";
        listData[2] = "��Visual Basic�����ŵ���ͨ����2�棩��";
        listData[3] = "��Visual C++�����ŵ���ͨ����2�棩��";
        listData[4] = "��Java��̴ʵ䡷";
        listData[5] = "��ϸ˵Java��";
        listData[6] = "����ƵѧJava��";
        list.setListData(listData);
    }
    
    protected void do_list_mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JList source = (JList) e.getSource();
            label.setText("��л������" + source.getSelectedValue());
        }
    }
}
