package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;

public class RadioList extends JFrame {
    
    private JPanel contentPane;
    
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
                    RadioList frame = new RadioList();
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
    public RadioList() {
        setTitle("���б�ؼ�����ʾ��ѡ��ť");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 294);
        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        final String[] values = new String[] { "Java", "Visual C++", "C/C++",
                "C#", "Asp.net", "Visual Basic", "PHP", "Java Web" };// �����б�������
        JList list = new JList(values);// �����б�ؼ�
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// �б��ѡ
        list.setSelectedIndex(0);// ����Ĭ��ѡ��״̬��ѡ��
        list.setFixedCellHeight(30);// �����б���Ĺ̶��߶�
        ListCellRenderer renderer = new ListCellRenderer() {// ������Ⱦ��ʵ��
            JRadioButton radio = new JRadioButton();// ������ѡ��ť�ؼ�
            Color background = new Color(0, 0, 0, 0);// ����͸���ı���ɫ
            
            @Override
            public Component getListCellRendererComponent(final JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                radio.setBackground(background);// ���õ�ѡ��ť�ؼ��ı���ɫ
                radio.setOpaque(true);// ʹ��ѡ��ť��͸��
                if (value.equals(values[index])) {
                    radio.setText(value + "");// ���õ�ѡ��ť�ı�
                }
                radio.setSelected(isSelected);
                return radio;// ���ص�ѡ��ť�ؼ���Ϊ��Ⱦ�ؼ�
            }
        };
        list.setCellRenderer(renderer);// �����б�ؼ�����Ⱦ��
        scrollPane.setViewportView(list);// ���б�ؼ���ӵ��������
        JLabel label = new JLabel("��ѡ�������ڵĿ������ţ�");
        scrollPane.setColumnHeaderView(label);
    }
}
