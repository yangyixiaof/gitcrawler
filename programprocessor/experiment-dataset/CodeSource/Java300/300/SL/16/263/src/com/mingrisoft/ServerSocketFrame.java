package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerSocketFrame extends JFrame {
    
    private JTextField tf_name;
    private JTextField tf_id;
    private JTextArea ta_info;
    private ObjectOutputStream out = null; // ����������
    private ObjectInputStream in = null; // ����������
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    
    public void getserver() {
        try {
            server = new ServerSocket(1978); // ʵ����Socket����
            ta_info.append("�������׽����Ѿ������ɹ�\n"); // �����Ϣ
            while (true) { // ����׽���������״̬
                ta_info.append("�ȴ��ͻ���������......\n"); // �����Ϣ
                socket = server.accept(); // ʵ����Socket����
                ta_info.append("�ͻ������ӳɹ�\n"); // �����Ϣ
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                getClientInfo(); // ����getClientInfo()����
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    private void getClientInfo() {
        try {
            while (true) { // ����׽���������״̬
                Student stud = (Student)in.readObject();
                if (stud!=null)
                ta_info.append("���յ��ͻ������͵�  ���Ϊ��" + stud.getId() + "  ����Ϊ��" +stud.getName() + "\n"); // ��ÿͻ�����Ϣ
            }
        } catch (Exception e) {
            ta_info.append("�ͻ������˳���\n"); // ����쳣��Ϣ
        } finally {
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
    
    public static void main(String[] args) { // ������
        ServerSocketFrame frame = new ServerSocketFrame(); // �����������
        frame.setVisible(true);
        frame.getserver(); // ���÷���
    }
    
    public ServerSocketFrame() {
        super();
        setTitle("�������˳���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 379, 260);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("��ţ�");
        panel.add(label);

        tf_id = new JTextField();
        tf_id.setPreferredSize(new Dimension(70,25));
        panel.add(tf_id);

        final JLabel label_1 = new JLabel();
        label_1.setText("���ƣ�");
        panel.add(label_1);

        tf_name = new JTextField();
        tf_name.setPreferredSize(new Dimension(100,25));
        panel.add(tf_name);

        final JButton button = new JButton();
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
                ta_info.append("���������͵�  ����ǣ�" + tf_id.getText() + "  �����ǣ�"+tf_name.getText()+"\n"); // ���ı�������Ϣ��ʾ���ı�����
                tf_id.setText(null); // ���ı������
                tf_name.setText(null);
            }
        });
        button.setText("��  ��");
        panel.add(button);
    }
}
