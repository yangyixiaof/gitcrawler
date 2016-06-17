package com.mingrisoft;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConnectionTimeoutSetFrame extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server; // ����ServerSocket����
    public void getserver() {
        try {
            server = new ServerSocket(1978); // ʵ����Socket����
            server.setSoTimeout(10000);// �������ӳ�ʱʱ��Ϊ10��
            ta_info.append("�������׽����Ѿ������ɹ�\n"); // �����Ϣ
            while (true) { // ����׽���������״̬
                ta_info.append("�ȴ��ͻ���������......\n"); // �����Ϣ
                server.accept();// �ȴ��ͻ�������
            }
        } catch (SocketTimeoutException e) {
            ta_info.append("���ӳ�ʱ......");
            JOptionPane.showMessageDialog(null, "���ӳ�ʱ......");
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public static void main(String[] args) { // ������
        ConnectionTimeoutSetFrame frame = new ConnectionTimeoutSetFrame(); // �����������
        frame.setVisible(true);
        frame.getserver(); // ���÷���
    }
    public ConnectionTimeoutSetFrame() {
        super();
        setTitle("���õȴ����ӵĳ�ʱʱ��");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 336, 257);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    }
}
