package com.mingrisoft;

import java.awt.BorderLayout;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerSocketFrame extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    
    public void getserver() {
        try {
            server = new ServerSocket(1978); // ʵ����Socket����
            ta_info.append("�������׽����Ѿ������ɹ�\n"); // �����Ϣ
            while (true) { // ����׽���������״̬
                ta_info.append("�ȴ��ͻ���������......\n"); // �����Ϣ
                socket = server.accept(); // ʵ����Socket����
                ta_info.append("���ӳɹ�......\n");
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    public static void main(String[] args) { // ������
        ServerSocketFrame frame = new ServerSocketFrame(); // �����������
        frame.setVisible(true);
        frame.getserver(); // ���÷���
    }
    
    public ServerSocketFrame() {
        super();
        setTitle("�������˳���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 336, 257);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    }
}
