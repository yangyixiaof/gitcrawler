package com.mingrisoft;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientOneToMany_ServerFrame extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    private Vector<Socket> vector = new Vector<Socket>();// ���ڴ洢���ӵ��������Ŀͻ����׽��ֶ���
    
    public void createSocket() {
        try {
            server = new ServerSocket(1978);
            while (true) {
                ta_info.append("�ȴ��¿ͻ�����......\n");
                socket = server.accept();// �����׽��ֶ���
                vector.add(socket);// ���׽��ֶ�����ӵ�����������
                ta_info.append("�ͻ������ӳɹ���" + socket + "\n");
                new ServerThread(socket).start();// �����������̶߳���
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    class ServerThread extends Thread {
        Socket socket;
        public ServerThread(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));// ��������������
                while (true) {
                    String info = in.readLine();// ��ȡ��Ϣ
                    for (Socket s : vector) {// �������пͻ����׽��ֶ���
                        if (s != socket) {// ������Ƿ�����Ϣ���׽��ֶ���
                            PrintWriter out = new PrintWriter(s
                                    .getOutputStream(), true);// �������������
                            out.println(info);// ������Ϣ
                            out.flush();// ˢ�����������
                        }
                    }
                }
            } catch (IOException e) {
                ta_info.append(socket + "�Ѿ��˳���\n");
                vector.remove(socket);// �Ƴ��˳��Ŀͻ����׽���
            }
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        ClientOneToMany_ServerFrame frame = new ClientOneToMany_ServerFrame();
        frame.setVisible(true);
        frame.createSocket();
    }
    
    /**
     * Create the frame
     */
    public ClientOneToMany_ServerFrame() {
        super();
        setTitle("�ͻ���һ�Զ�ͨ�š����������˳���");
        setBounds(100, 100, 385, 266);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    }
}
