package com.mingrisoft;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Phonebook extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3041722856251346473L;
    private JPanel contentPane;
    private JTable table;
    
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
                    Phonebook frame = new Phonebook();
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
    public Phonebook() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u6211\u7684\u7535\u8BDD\u7C3F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        Map<String, String> directory = new HashMap<String, String>();// ��������
        directory.put("��һ", "33265****");// �򼯺�������Ԫ��
        directory.put("����", "66150****");// �򼯺�������Ԫ��
        directory.put("����", "20427****");// �򼯺�������Ԫ��
        directory.put("����", "98823****");// �򼯺�������Ԫ��
        directory.put("����", "91364****");// �򼯺�������Ԫ��
        directory.put("����", "89259****");// �򼯺�������Ԫ��
        directory.put("����", "12441****");// �򼯺�������Ԫ��
        directory.put("����", "79920****");// �򼯺�������Ԫ��
        directory.put("����", "22721****");// �򼯺�������Ԫ��
        directory.put("��ʮ", "89383****");// �򼯺�������Ԫ��
        DefaultTableModel model = (DefaultTableModel) table.getModel();// ��ñ��ģ��
        model.setColumnIdentifiers(new Object[] { "����", "�ֻ�" });// ���ñ�ͷ
        Set<String> names = directory.keySet();// ��ü�����
        for (Iterator<String> it = names.iterator(); it.hasNext();) {
            String name = it.next();// ��ü�
            model.addRow(new Object[] { name, directory.get(name) });// ����������Ԫ��
        }
        table.setModel(model);// ���±��ģ��
    }
}
