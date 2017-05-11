package com.mingrisoft.jlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ImageList extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8093988259425885523L;
    private JPanel contentPane;
    private JList list;
    
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
                    ImageList frame = new ImageList();
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
    public ImageList() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5305\u542B\u56FE\u7247\u7684\u5217\u8868\u5143\u7D20");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        list = new JList();
        list.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        scrollPane.setViewportView(list);
       
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        Object[][] data = new Object[4][2];
        data[0][0] = new ImageIcon("src/images/1.png");
        data[1][0] = new ImageIcon("src/images/2.png");
        data[2][0] = new ImageIcon("src/images/3.png");
        data[3][0] = new ImageIcon("src/images/4.png");
        data[0][1] = "��Java�����ŵ���ͨ����2�棩��";
        data[1][1] = "��PHP�����ŵ���ͨ����2�棩��";
        data[2][1] = "��Visual Basic�����ŵ���ͨ����2�棩��";
        data[3][1] = "��Visual C++�����ŵ���ͨ����2�棩��";
        list.setListData(data);
        ListCellRenderer renderer = new ImageListCellRenderer();
        list.setCellRenderer(renderer);
        
    }
}
