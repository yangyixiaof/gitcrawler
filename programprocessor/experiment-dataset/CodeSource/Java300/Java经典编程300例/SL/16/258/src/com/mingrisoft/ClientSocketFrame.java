package com.mingrisoft;

import java.awt.BorderLayout;
import java.net.*;
import javax.swing.*;
import javax.swing.JScrollPane;

public class ClientSocketFrame extends JFrame { // ������̳�JFrame��
    private Socket socket; // ����Socket����
    private JTextArea ta = new JTextArea(); // ����JtextArea����
    
    public ClientSocketFrame() { // ���췽��
        super(); // ���ø���Ĺ��췽��
        setTitle("��ȡSocket��Ϣ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 351, 257);
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(ta);
    }
    
    private void connect() { // �����׽��ַ���
        ta.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            ta.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
            InetAddress netAddress = socket.getInetAddress();// ���Զ�̷������ĵ�ַ
            String netIp = netAddress.getHostAddress();// ���Զ�̷�������IP��ַ 
            int netPort = socket.getPort();// ���Զ�̷������Ķ˿ں�
            InetAddress localAddress = socket.getLocalAddress();// ��ÿͻ��˵ĵ�ַ
            String localIp = localAddress.getHostAddress();// ��ÿͻ��˵�IP��ַ
            int localPort = socket.getLocalPort();// ��ÿͻ��˵Ķ˿ں�
            ta.append("Զ�̷�������IP��ַ��" + netIp + "\n");
            ta.append("Զ�̷������Ķ˿ںţ�" + netPort + "\n");
            ta.append("�ͻ������ص�IP��ַ��" + localIp + "\n");
            ta.append("�ͻ������صĶ˿ںţ�" + localPort + "\n");
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
