package com.mingrisoft;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.JScrollPane;

public class ClientSocketFrame extends JFrame { // ������̳�JFrame��
    private PrintWriter writer; // ����PrintWriter�����
    private Socket socket; // ����Socket����
    private JTextArea ta_info = new JTextArea(); // ����JtextArea����
    private JTextField tf_send = new JTextField(); // ����JtextField����
    private Container cc; // ����Container����
    
    public ClientSocketFrame() { // ���췽��
        super(); // ���ø���Ĺ��췽��
        setTitle("�ͻ��˳���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 351, 257);
        cc = this.getContentPane(); // ʵ��������
        cc.add(tf_send, "South"); // ���ı�����ڴ�����²�
        tf_send.addActionListener(new ActionListener() { // ���¼�
                    public void actionPerformed(ActionEvent e) {
                        writer.println(tf_send.getText()); // ���ı�������Ϣд����
                        ta_info.append("�ͻ��˷��͵���Ϣ�ǣ�" + tf_send.getText() + "\n"); // ���ı�������Ϣ��ʾ���ı�����
                        tf_send.setText(""); // ���ı������
                    }
                });
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(ta_info);
    }
    
    private void connect() { // �����׽��ַ���
        ta_info.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            writer = new PrintWriter(socket.getOutputStream(), true);// �������������
            ta_info.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    public static void main(String[] args) { // ������
        ClientSocketFrame clien = new ClientSocketFrame(); // ������������
        clien.setVisible(true); // ��������ʾ
        clien.connect(); // �������ӷ���
    }
}
