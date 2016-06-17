package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class DynamicArray extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8515266110392261888L;
    private JPanel contentPane;
    private final JPanel panel = new JPanel();
    
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
                    DynamicArray frame = new DynamicArray();
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
    public DynamicArray() {
        setTitle("\u7528\u52A8\u6001\u6570\u7EC4\u4FDD\u5B58\u5B66\u751F\u59D3\u540D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 269);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        list = new JList();
        scrollPane.setViewportView(list);
        
        JPanel panel_1 = new JPanel();
        scrollPane.setColumnHeaderView(panel_1);
        
        JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
        panel_1.add(label);
        
        textField = new JTextField();
        panel_1.add(textField);
        textField.setColumns(10);
        panel.setPreferredSize(new Dimension(100, 10));
        contentPane.add(panel, BorderLayout.EAST);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton button = new JButton("\u6DFB\u52A0\u5B66\u751F");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button);
        
        JButton button_1 = new JButton("\u5220\u9664\u5B66\u751F");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        panel.add(button_1);
    }
    
    private ArrayList<String> arraylist = new ArrayList<String>();
    private JTextField textField;
    private JList list;
    
    protected void do_button_actionPerformed(ActionEvent e) {
        textField.requestFocusInWindow();
        textField.selectAll();// ѡ���ı����ı�׼���´�����
        String text = textField.getText();// ��ȡ�û���������
        if (text.isEmpty())// ����Ϊ�������������
            return;
        arraylist.add(text);// ��������ӵ����鼯����
        replaceModel();// �����鼯����������ʾ�������б�ؼ���
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        Object value = list.getSelectedValue();// ��ȡ�б�ؼ���ѡ����
        arraylist.remove(value);// �����鼯�����Ƴ��û���ѡ����
        replaceModel();// �����鼯����������ʾ�������б�ؼ���
    }
    
    private void replaceModel() {
        // Ϊ�б�ؼ���������ģ����ʾ���鼯���е�����
        list.setModel(new AbstractListModel() {
            /**
             * 
             */
            private static final long serialVersionUID = -3406879889366471211L;

            @Override
            public int getSize() {// ��ȡ�����С
                return arraylist.size();
            }
            
            @Override
            public Object getElementAt(int index) {// ��ȡָ������Ԫ��
                return arraylist.get(index);
            }
        });
    }
}
