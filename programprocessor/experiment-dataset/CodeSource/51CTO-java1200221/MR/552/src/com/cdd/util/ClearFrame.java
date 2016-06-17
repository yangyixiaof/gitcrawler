package com.cdd.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ClearFrame extends JFrame {
    
    private JPanel contentPane;
    private ClearUtil util = new ClearUtil();
    private JComboBox dateNameComboBox ;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClearFrame frame = new ClearFrame();
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
    public ClearFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        setTitle("����ɾ�����ݱ�������");
        JLabel messageLabel = new JLabel("���ݱ�");
        messageLabel.setBounds(67, 59, 63, 15);
        panel.add(messageLabel);
        List list = util.GetRs();
        String[] str = new String[list.size()];
        for(int i = 0;i<list.size();i++){
            str[i] = list.get(i).toString();
        }
        dateNameComboBox = new JComboBox(str);
        dateNameComboBox.setBounds(140, 56, 169, 21);
        panel.add(dateNameComboBox);
        
        JButton clearButton = new JButton("ɾ��");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_clearButton_actionPerformed(arg0);
            }
        });
        clearButton.setBounds(124, 172, 63, 23);
        panel.add(clearButton);
        
        JButton annulButton = new JButton("�ر�");
        annulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_annulButton_actionPerformed(arg0);
            }
        });
        annulButton.setBounds(215, 172, 69, 23);
        panel.add(annulButton);
    }
    //ɾ����ť�ĵ����¼�
    protected void do_clearButton_actionPerformed(ActionEvent arg0) {
        String dateName = dateNameComboBox.getSelectedItem().toString();
        util.deleteDate(dateName);
        JOptionPane.showMessageDialog(getContentPane(), 
                "�����˷Ƿ��ַ���", "������ʾ��", JOptionPane.WARNING_MESSAGE);

    }
    //ȡ����ť�ĵ����¼�
    protected void do_annulButton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
