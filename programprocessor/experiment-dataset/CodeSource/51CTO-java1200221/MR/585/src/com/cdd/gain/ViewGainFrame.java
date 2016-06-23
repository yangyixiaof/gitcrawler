package com.cdd.gain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewGainFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewGainFrame frame = new ViewGainFrame();
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
    public ViewGainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("��ȡ���ݿ������д洢����");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel massageLabel = new JLabel("��ȡdb_database24���ݿ��µ����д洢��������");
        massageLabel.setFont(new Font("��������", Font.PLAIN, 13));
        massageLabel.setBounds(65, 20, 297, 27);
        panel.add(massageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(54, 71, 316, 159);
        panel.add(scrollPane);
        
        JTextArea textArea = new JTextArea();        
        scrollPane.setViewportView(textArea);
        GainProcedure procedure = new GainProcedure(); // �������������
        List list = procedure.executeGain(); // ���û�ȡ���д洢���̷���
        for (int i = 0; i < list.size(); i++) { // ѭ��������ѯ���
            String name = list.get(i).toString(); // ��ȡ�����������
            textArea.append(name + "\n"); // ���ı����������Ϣ
        }
    }
}
