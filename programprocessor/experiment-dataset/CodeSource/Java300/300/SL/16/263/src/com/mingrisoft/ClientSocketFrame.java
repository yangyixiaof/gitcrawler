package com.mingrisoft;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientSocketFrame extends JFrame { // ������̳�JFrame��
    private JButton button;
    private JTextField tf_name;
    private JLabel label_1;
    private JLabel label;
    private JPanel panel;
    private ObjectInputStream in = null;// ����������
    private ObjectOutputStream out = null;// ����������
    private Socket socket;// ����Socket����
    private JTextArea ta_info = new JTextArea();// ����JtextArea����
    private JTextField tf_id = new JTextField();// ����JtextField����
    private Container cc;// ����Container����
    
    public ClientSocketFrame() { // ���췽��
        super(); // ���ø���Ĺ��췽��
        setTitle("�ͻ��˳���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 373, 257);
        cc = this.getContentPane(); // ʵ��������
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(ta_info);
        getContentPane().add(getPanel(), BorderLayout.NORTH);
    }
    
    private void connect() { // �����׽��ַ���
        ta_info.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            while (true){
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            ta_info.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
                getClientInfo();
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    public static void main(String[] args) { // ������
        ClientSocketFrame clien = new ClientSocketFrame(); // ������������
        clien.setVisible(true); // ��������ʾ
        clien.connect(); // �������ӷ���
    }
    private void getClientInfo() {
        try {
            while (true) { // ����׽���������״̬
                Student stud = (Student)in.readObject();
                if (stud!=null)
                ta_info.append("���յ����������͵�  ���Ϊ��" + stud.getId() + "  ����Ϊ��" +stud.getName() + "\n"); // ��÷�������Ϣ
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (in != null) {
                    in.close();// �ر���
                }
                if (socket != null) {
                    socket.close(); // �ر��׽���
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }
    /**
     * @return
     */
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getLabel());
            tf_id.setPreferredSize(new Dimension(70, 25));
            panel.add(tf_id);
            panel.add(getLabel_1());
            panel.add(getTf_name());
            panel.add(getButton());
        }
        return panel;
    }
    
    /**
     * @return
     */
    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setText("��ţ�");
        }
        return label;
    }
    /**
     * @return
     */
    protected JLabel getLabel_1() {
        if (label_1 == null) {
            label_1 = new JLabel();
            label_1.setText("���ƣ�");
        }
        return label_1;
    }
    /**
     * @return
     */
    protected JTextField getTf_name() {
        if (tf_name == null) {
            tf_name = new JTextField();
            tf_name.setPreferredSize(new Dimension(100, 25));
        }
        return tf_name;
    }
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    Student stud = new Student();
                    stud.setId(tf_id.getText());
                    stud.setName(tf_name.getText());
                    try {
                        out.writeObject(stud);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    ta_info.append("�ͻ��˷��͵�  ����ǣ�" + tf_id.getText() + "  �����ǣ�"+tf_name.getText()+"\n"); // ���ı�������Ϣ��ʾ���ı�����
                    tf_id.setText(null); // ���ı������
                    tf_name.setText(null);
                }
            });
            button.setText("��  ��");
        }
        return button;
    }
}
