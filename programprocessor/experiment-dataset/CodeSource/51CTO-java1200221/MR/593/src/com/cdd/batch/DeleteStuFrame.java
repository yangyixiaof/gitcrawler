package com.cdd.batch;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteStuFrame extends JFrame {
    
    private JPanel contentPane;
    private LocalTableModel model = new LocalTableModel();
    private BatchDelete batchDelete = new BatchDelete();
    private JTable table;
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteStuFrame frame = new DeleteStuFrame();
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
    public DeleteStuFrame() {
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
        
        JLabel messageLabel = new JLabel("����ɾ��ѧ��������");
        messageLabel.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        messageLabel.setBounds(135, 26, 149, 15);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 71, 352, 111);
        panel.add(scrollPane);
        
        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        JButton deleteButton = new JButton("ɾ��");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_deleteButton_actionPerformed(arg0);
            }
        });
        deleteButton.setBounds(97, 214, 80, 23);
        panel.add(deleteButton);
        
        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closeButton_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(232, 214, 80, 23);
        panel.add(closeButton);
        
       
        List list = batchDelete.executeStu();
        for(int i = 0;i<list.size();i++){
            Stu stu = (Stu)list.get(i);
            model.addRow(new Object[]{stu.getId(),stu.getName(),stu.getSex(),stu.getSpecialty()});
        }               
    }
    //ɾ����ť�ĵ��������¼�
protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
    int [] ids = table.getSelectedRows();           //����ѡ���е�����
    Integer values[] = new Integer[ids.length];     
    for(int i = 0;i<ids.length;i++){    //����ѡ���е�����
        values[i] = new Integer(table.getValueAt(ids[i], 0).toString());    //��ȡ�û�ѡ��ĳ��Ԫ�������            
    }
    batchDelete.deleteBatch(values);        //������������
    JOptionPane.showMessageDialog(getContentPane(), 
            "����ɾ���ɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
}
    //�رհ�ť�ĵ��������¼�
    protected void do_closeButton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
