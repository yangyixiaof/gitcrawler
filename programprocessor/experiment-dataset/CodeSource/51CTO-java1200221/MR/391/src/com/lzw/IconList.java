package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class IconList extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IconList frame = new IconList();
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
    public IconList() {
        setTitle("֧��ͼ����б�ؼ�");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 294);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        final String[] values = new String[] { "����", "��ʣ��ƻ��", "�㽶", "����", "����",
                "����", "������" };// �����б�������
        final ImageIcon[] icons = new ImageIcon[values.length];// ����ͼ������
        for (int i = 0; i < icons.length; i++) {// ����ͼ������
            icons[i] = new ImageIcon(getClass().getResource(
                    "/res/" + i + ".png"));// ��ʼ��ÿһ������Ԫ��
        }
        JList list = new JList(values);// �����б�ؼ�
        list.setSelectedIndex(0);
        ListCellRenderer renderer = new ListCellRenderer() {// ������Ⱦ��ʵ��
            JLabel label = new JLabel();// ������ǩ�ؼ�
            Color background = new Color(0, 0, 0, 0);// ����͸���ı���ɫ
            
            @Override
            public Component getListCellRendererComponent(final JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                label.setBackground(background);// ���ñ�ǩ�ؼ��ı���ɫ
                label.setOpaque(true);// ʹ��ǩ��͸��
                if (value.equals(values[index])) {
                    label.setText(value + "");// ���ñ�ǩ�ı�
                    label.setIcon(icons[index]);// ���ñ�ǩͼ��
                }
                if (isSelected) {
                    label.setBackground(Color.PINK);// ����ѡ��ʱ�ı���ɫ
                } else {
                    label.setBackground(background);// ����δѡ��ʱ�ı���ɫ
                }
                return label;// ���ر�ǩ�ؼ���Ϊ��Ⱦ�ؼ�
            }
        };
        list.setCellRenderer(renderer);// �����б�ؼ�����Ⱦ��
        scrollPane.setViewportView(list);// ���б�ؼ���ӵ��������
    }
}
