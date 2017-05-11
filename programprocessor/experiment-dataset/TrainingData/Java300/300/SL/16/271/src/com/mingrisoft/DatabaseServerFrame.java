package com.mingrisoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DatabaseServerFrame extends JFrame {
    
    private JTextArea ta_info;
    private PrintWriter writer; // ����PrintWriter�����
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    
    public void getserver() {
        try {
            server = new ServerSocket(1978); // ʵ����Socket����
            ta_info.append("�������׽����Ѿ������ɹ�\n"); // �����Ϣ
            while (true) { // ����׽���������״̬
                ta_info.append("�ȴ��ͻ���������......\n"); // �����Ϣ
                socket = server.accept(); // ʵ����Socket����
                writer = new PrintWriter(socket.getOutputStream(), true);// �������������
                getClientInfo(); // ����getClientInfo()����
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    private void getClientInfo() {
        try {
            BufferedReader reader; // ����BufferedReader����
            while (true) { // ����׽���������״̬
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream())); // ʵ����BufferedReader����
                String line = reader.readLine();// ��ȡ�ͻ�����Ϣ
                if (line != null) {
                    String[] value = new String[2];// ��������
                    value[0] = line.substring(0, line.indexOf(":data:"));// �������
                    value[1] = line.substring(line.indexOf(":data:") + 6);// �������
                    ta_info.append("���յ��ͻ��˵���Ϣ\n����Ϊ��"+value[0]+" ����Ϊ��"+value[1]+"��\n");
                    try {
                        Connection conn = DAO.getConn();// ������ݿ�����
                        String sql = "insert into tb_employee (name,age) values(?,?)";// ����SQL���
                        PreparedStatement ps = conn.prepareStatement(sql);// ����PreparedStatement���󣬲�����SQL���
                        ps.setString(1, value[0]); // Ϊ��1��������ֵ
                        ps.setInt(2, Integer.parseInt(value[1]));// Ϊ��2��������ֵ
                        int flag = ps.executeUpdate(); // ִ��SQL��䣬��ø��¼�¼��
                        ps.close();// �ر�PreparedStatement����
                        conn.close();// �ر�����
                        if (flag > 0) {
                            ta_info.append("���ɹ��ر��浽���ݿ��С�\n");
                            writer.println("����ɹ���");// ��ͻ����������ɹ�����Ϣ
                        } else {
                            writer.println("����ʧ�ܡ�\n");// ��ͻ����������ɹ�����Ϣ
                        }
                    } catch (SQLException ee) {
                        writer.println("����ʧ�ܡ�\n" + ee.getMessage());// ��ͻ����������ɹ�����Ϣ
                    }
                }
            }
        } catch (Exception e) {
            ta_info.append("�ͻ������˳���\n");
        } finally {
            try {
                if (socket != null) {
                    socket.close(); // �ر��׽���
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        DatabaseServerFrame frame = new DatabaseServerFrame();
        frame.setVisible(true);
        frame.getserver(); // ���÷���
    }
    
    /**
     * Create the frame
     */
    public DatabaseServerFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("�������˳���");
        setBounds(100, 100, 277, 263);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 241, 205);
        getContentPane().add(scrollPane);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        //
    }
    
}
