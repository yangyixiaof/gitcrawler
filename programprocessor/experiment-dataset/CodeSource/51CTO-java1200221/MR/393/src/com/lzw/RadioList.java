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

import com.sun.awt.AWTUtilities;

public class RadioList extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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
        setTitle("�б�ؼ�������ʾ�б���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 294);
        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        final String[] values = new String[15];// �����б�������
        for (int i = 0; i < values.length; i++) {
            values[i] = "ѡ��" + (i + 1);
        }
        JList list = new JList(values);// �����б�ؼ�
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
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
    }
}
