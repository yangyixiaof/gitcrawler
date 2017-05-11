package com.mingrisoft;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InsertEmpFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField deptTextField;
    private JTextField phoneTextField;
    private JComboBox sexComboBox;
    private JTextArea remakeTextArea = new JTextArea();
    private JdbcUtil util = new JdbcUtil();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertEmpFrame frame = new InsertEmpFrame();
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
    public InsertEmpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 454, 360);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("��Ա����Ϣ��ӵ����ݿ���");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 438, 322);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel nameLabel = new JLabel("������");
        nameLabel.setBounds(63, 24, 45, 15);
        panel.add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(118, 21, 194, 21);
        panel.add(nameTextField);
        nameTextField.setColumns(10);
        
        JLabel sexLabel = new JLabel("�Ա�");
        sexLabel.setBounds(63, 55, 45, 15);
        panel.add(sexLabel);
        
        ageTextField = new JTextField();
        ageTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) { // ĳ������ʱ���õķ���
                char ch = event.getKeyChar(); // ��ȡ�û�������ַ�
                if ((ch < '0' || ch > '9')) { // ����û��������Ϣ��Ϊ����
                    event.consume(); // �������û�����
                }
                
            }
        });
        ageTextField.setBounds(118, 83, 194, 21);
        panel.add(ageTextField);
        ageTextField.setColumns(10);
        
        String[] sex = new String[] { "��", "Ů" };
        sexComboBox = new JComboBox(sex);
        sexComboBox.setBounds(118, 52, 55, 21);
        panel.add(sexComboBox);
        
        JLabel ageLabel = new JLabel("���䣺");
        ageLabel.setBounds(63, 86, 45, 15);
        panel.add(ageLabel);
        
        JLabel deptLabel = new JLabel("���ţ�");
        deptLabel.setBounds(63, 118, 54, 15);
        panel.add(deptLabel);
        
        deptTextField = new JTextField();
        deptTextField.setBounds(118, 115, 194, 21);
        panel.add(deptTextField);
        deptTextField.setColumns(10);
        
        JLabel phoneLabel = new JLabel("�绰��");
        phoneLabel.setBounds(63, 153, 54, 15);
        panel.add(phoneLabel);
        
        phoneTextField = new JTextField();
        phoneTextField.setBounds(118, 150, 194, 21);
        panel.add(phoneTextField);
        phoneTextField.setColumns(10);
        
        JLabel label = new JLabel("��ע��");
        label.setBounds(63, 187, 54, 15);
        panel.add(label);
        
        remakeTextArea.setBounds(118, 183, 194, 69);
        panel.add(remakeTextArea);
        
        JButton insertButton = new JButton("���");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_insertButton_actionPerformed(arg0);
            }
        });
        insertButton.setBounds(107, 275, 66, 23);
        panel.add(insertButton);
        
        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closeButton_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(205, 275, 66, 23);
        panel.add(closeButton);
        
        JLabel messageLabel = new JLabel("*");
        messageLabel.setBounds(322, 24, 54, 15);
        panel.add(messageLabel);
        
        JLabel label_1 = new JLabel("*");
        label_1.setBounds(322, 118, 54, 15);
        panel.add(label_1);
        
        JLabel label_2 = new JLabel("*");
        label_2.setBounds(322, 153, 54, 15);
        panel.add(label_2);
        
        JButton validateButton = new JButton("��֤");
        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_validateButton_actionPerformed(arg0);
            }
        });
        validateButton.setBounds(342, 20, 66, 23);
        panel.add(validateButton);
    }
    
    // �رհ�ť�ĵ����¼�
    protected void do_closeButton_actionPerformed(ActionEvent arg0) {
        int n = JOptionPane.showConfirmDialog(getContentPane(), "ȷ��ɾ����",
                "ȷ�϶Ի���", JOptionPane.YES_NO_CANCEL_OPTION);
        if (n == JOptionPane.YES_OPTION) { // ����û�ȷ����Ϣ
            System.exit(0);
        }
    }
    
    // ��Ӱ�ť�ĵ����¼�
    protected void do_insertButton_actionPerformed(ActionEvent arg0) {
        
        Emp emp = new Emp();
        
        emp.setName(nameTextField.getText());
        emp.setSex(sexComboBox.getSelectedItem().toString());
        emp.setAge(Integer.parseInt(ageTextField.getText()));
        emp.setDept(deptTextField.getText());
        emp.setPhone(phoneTextField.getText());
        emp.setRemark(remakeTextArea.getText());
        if (!(nameTextField.getText().equals(""))
                && (!deptTextField.getText().equals(""))
                && (!phoneTextField.getText().equals(""))) {
            util.insertEmp(emp);
            
            JOptionPane.showMessageDialog(getContentPane(), "������ӳɹ���", "��Ϣ��ʾ��",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(getContentPane(), "�뽫��Ϣ���������",
                    "��Ϣ��ʾ��", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    //��֤��ť�ĵ����¼�
    protected void do_validateButton_actionPerformed(ActionEvent arg0) {
        String name = nameTextField.getText();
        int id = util.selectEmpUseName(name);
        if (id > 0) {
            JOptionPane.showMessageDialog(getContentPane(), "��Ա���Ѵ��ڣ�", "��Ϣ��ʾ��",
                    JOptionPane.WARNING_MESSAGE);
            nameTextField.setText("");
        }
        else{
            JOptionPane.showMessageDialog(getContentPane(), "��Ա�������ڣ�", "��Ϣ��ʾ��",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
