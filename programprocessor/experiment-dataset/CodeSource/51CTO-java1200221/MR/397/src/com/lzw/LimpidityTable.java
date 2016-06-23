package com.lzw;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class LimpidityTable extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    
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
                    LimpidityTable frame = new LimpidityTable();
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
    public LimpidityTable() {
        setTitle("ʵ��͸��Ч���ı��ؼ�");// ���ô������
        setResizable(false);// ��ֹ������С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ImgPanel imgPanel = new ImgPanel();// ����ͼƬ���
        contentPane.add(imgPanel, BorderLayout.CENTER);
        imgPanel.setLayout(null);// ȡ�����ֹ�����
        table = new JTable() {// �����Զ�����
            {
                setOpaque(false);// ��ʼ�����Ϊ͸��
                setGridColor(Color.MAGENTA);// ���ñ��������ɫ
                setShowVerticalLines(true);// ��ʾ��������
                setShowHorizontalLines(true);// ��ʾ�������
                setRowHeight(20);// ���ñ���и�
                setBorder(new LineBorder(Color.PINK));// ���ñ߿�
                setForeground(Color.BLACK);// ���ñ��������ɫ
                setFont(new Font("SansSerif", Font.PLAIN, 18));// ���ñ��Ԫ������
            }
            
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int column) {// ��д��Ⱦ����
                // ��ȡ��Ⱦ��Ŀؼ�
                Component component = super.prepareRenderer(renderer, row,
                        column);
                ((JComponent) component).setOpaque(false);// ���ÿؼ�͸��
                return component;// ���ؿؼ�
            }
        };
        table.setModel(new DefaultTableModel(
                new Object[][] {// ��ʼ���������������
                { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" } },
                new String[] { "����1", "����2", "����3", "����4", "����5" }));
        table.setBounds(40, 161, 421, 254);// ���ñ���С
        imgPanel.add(table);
        JPanel panel = new JPanel();// ������ͷ���
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(table.getTableHeader(), BorderLayout.CENTER);// ��ӱ�ͷ
        panel.setBounds(40, 126, 421, 34);
        imgPanel.add(panel);
    }
}
