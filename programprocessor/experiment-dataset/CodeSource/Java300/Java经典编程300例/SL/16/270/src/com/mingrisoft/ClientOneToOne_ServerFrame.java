package com.mingrisoft;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientOneToOne_ServerFrame extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    private Hashtable<String, Socket> map = new Hashtable<String, Socket>();// ���ڴ洢���ӵ����������û��Ϳͻ����׽��ֶ���
    
    public void createSocket() {
        try {
            server = new ServerSocket(1978);
            while (true) {
                ta_info.append("�ȴ��¿ͻ�����......\n");
                socket = server.accept();// �����׽��ֶ���
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
                    String key = "";
                    if (info.startsWith("�û���")) {// ��ӵ�¼�û����ͻ����б�
                        key = info.substring(3, info.length());// ����û�������Ϊ��ʹ��
                        map.put(key, socket);// ��Ӽ�ֵ��
                        Set<String> set = map.keySet();// ��ü��������м���Set��ͼ
                        Iterator<String> keyIt = set.iterator();// ������м��ĵ�����
                        while (keyIt.hasNext()) {
                            String receiveKey = keyIt.next();// ��ñ�ʾ������Ϣ�ļ�
                            Socket s = map.get(receiveKey);// �����ü���Ӧ���׽��ֶ���
                            PrintWriter out = new PrintWriter(s
                                    .getOutputStream(), true);// �������������
                            Iterator<String> keyIt1 = set.iterator();// ������м��ĵ�����
                            while (keyIt1.hasNext()) {
                                String receiveKey1 = keyIt1.next();// ��ü���������ͻ�������û��б�
                                out.println(receiveKey1);// ������Ϣ
                                out.flush();// ˢ�����������
                            }
                        }
                        
                    } else {// ת�����յ���Ϣ
                        key = info.substring(info.indexOf("�����͸���") + 5, info
                                .indexOf("������Ϣ�ǣ�"));// ��ý��շ���keyֵ,�����շ����û���
                        String sendUser = info.substring(0, info
                                .indexOf("�����͸���"));// ��÷��ͷ���keyֵ,�����ͷ����û���
                        Set<String> set = map.keySet();// ��ü��������м���Set��ͼ
                        Iterator<String> keyIt = set.iterator();// ������м��ĵ�����
                        while (keyIt.hasNext()) {
                            String receiveKey = keyIt.next();// ��ñ�ʾ������Ϣ�ļ�
                            if (key.equals(receiveKey)
                                    && !sendUser.equals(receiveKey)) {// ����Ƿ��ͷ����������û�����
                                Socket s = map.get(receiveKey);// �����ü���Ӧ���׽��ֶ���
                                PrintWriter out = new PrintWriter(s
                                        .getOutputStream(), true);// �������������
                                
                                out.println("MSG:"+info);// ������Ϣ
                                out.flush();// ˢ�����������
                            }
                        }
                    }
                }
            } catch (IOException e) {
                ta_info.append(socket + "�Ѿ��˳���\n");
            }
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        ClientOneToOne_ServerFrame frame = new ClientOneToOne_ServerFrame();
        frame.setVisible(true);
        frame.createSocket();
    }
    
    /**
     * Create the frame
     */
    public ClientOneToOne_ServerFrame() {
        super();
        setTitle("�ͻ���һ��һͨ�š����������˳���");
        setBounds(100, 100, 385, 266);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    }
}
