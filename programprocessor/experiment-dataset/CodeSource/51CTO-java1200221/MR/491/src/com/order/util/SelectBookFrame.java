package com.order.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class SelectBookFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable resultTable;
    private JComboBox termComboBox ;
    private LocalTableModel model = new LocalTableModel();
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
                    SelectBookFrame frame = new SelectBookFrame();
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
    public SelectBookFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("��ָ��������������");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        
        String str[] = new String[]{"ͼ���ۼ�","ͼ����"};
        termComboBox = new JComboBox(str);
        termComboBox.setBounds(135, 24, 104, 21);
        panel.add(termComboBox);
        
        JLabel label = new JLabel("��ѯ������");
        label.setBounds(44, 27, 70, 15);
        panel.add(label);
        
        JButton termButton = new JButton("��ѯ");
        termButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_termButton_actionPerformed(arg0);
            }
        });
        termButton.setBounds(273, 23, 93, 23);
        panel.add(termButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(32, 68, 368, 184);
        panel.add(scrollPane);
        resultTable = new JTable();     
        resultTable.setModel(model);
        scrollPane.setViewportView(resultTable);
        JDBCUtil util = new JDBCUtil();
        List list = util.getBook();
        for (int i = 0; i < list.size(); i++) {
            Book book = (Book) list.get(i);
            model.addRow(new Object[] { book.getId(), book.getBookName(),
                    book.getAuthor(), book.getPrice(), book.getStock() });
        }
    } 
    
    // ��ѯ��ť�ĵ����¼�
    protected void do_termButton_actionPerformed(ActionEvent arg0) {
        String term = (String) termComboBox.getSelectedItem();
        JDBCUtil util = new JDBCUtil();
        List list = new ArrayList();
        if(term.equals("ͼ����")){
            list = util.getBooKDesc("stock");
        }
        if(term.equals("ͼ���ۼ�")){
            list = util.getBooKDesc("price");
        }       
        model.setRowCount(0);  //��������
        for(int i = 0;i<list.size();i++){
            Book book = (Book) list.get(i);
            model.addRow(new Object[] { book.getId(), book.getBookName(),
                    book.getAuthor(), book.getPrice(), book.getStock() });
        }       
        
    }
}
