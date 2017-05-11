package com.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class InsertDeptFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField deptNameTextField;
    private JTextField personTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertDeptFrame frame = new InsertDeptFrame();
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
    public InsertDeptFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 362, 217);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("��Ӳ�����Ϣ");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 351, 180);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel dNameLabel = new JLabel("�������ƣ�");
        dNameLabel.setBounds(52, 30, 74, 15);
        panel.add(dNameLabel);
        
        deptNameTextField = new JTextField();
        deptNameTextField.setBounds(136, 27, 148, 21);
        panel.add(deptNameTextField);
        deptNameTextField.setColumns(10);
        
        JLabel pesonLabel = new JLabel("���Ÿ����ˣ�");
        pesonLabel.setBounds(37, 73, 89, 15);
        panel.add(pesonLabel);
        
        personTextField = new JTextField();
        personTextField.setBounds(136, 70, 148, 21);
        panel.add(personTextField);
        personTextField.setColumns(10);
        
        JButton insertButton = new JButton("���");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_insertButton_actionPerformed(arg0);
            }
        });
        insertButton.setBounds(71, 122, 74, 23);
        panel.add(insertButton);
        
        JButton watchButton = new JButton("�鿴");
        watchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_watchButton_actionPerformed(arg0);
            }
        });
        watchButton.setBounds(176, 122, 74, 23);
        panel.add(watchButton);
    }
    //��Ӱ�ť�ĵ����¼�

protected void do_insertButton_actionPerformed(ActionEvent arg0) {
    String name = deptNameTextField.getText();      //��ȡ�û���ӵĲ�������
    String person = personTextField.getText();      //��ȡ�û���ӵĲ��Ÿ�����
    Random ran = new Random(System.currentTimeMillis());        //���ݵ�ǰʱ��ĺ����������������
    StringBuilder idb=new StringBuilder();          //�����ַ����������
    idb.append(String.format("%019d",Math.abs(ran.nextLong()))+String.format("%03d",(int)(Math.random()*100%9)+1));
    idb=idb.reverse();              //���ַ����������ȡ��
    String id=idb+ "" + String.format("%010d",Math.abs(ran.nextInt()));     
    Dept dept = new Dept();         //���������ݱ��Ӧ��JavaBean����
    dept.setDid(id);                //���ö�������
    dept.setdName(name);
    dept.setPriName(person);
    DeptUtil deptUtil = new DeptUtil();     //�������������
    deptUtil.insertDept(dept);      //������ӷ���
    JOptionPane.showMessageDialog(getContentPane(), 
            "������ӳɹ���", "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
}
    
    //�鿴��ť�ĵ����¼�
    protected void do_watchButton_actionPerformed(ActionEvent arg0) {
        SelectDeptFrame frame = new SelectDeptFrame();
        frame.setVisible(true);
    }
}
