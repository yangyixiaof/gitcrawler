package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientSocketFrame extends JFrame {
    private PrintWriter writer; // ����PrintWriter�����
    private BufferedReader reader; // ����BufferedReader����
    private Socket socket; // ����Socket����
    private JTextArea ta_info; // ����JtextArea����
    private JTextField tf_send; // ����JtextField����
    private int index = -1;
    private void connect() { // �����׽��ַ���
        ta_info.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            while (true) {
                writer = new PrintWriter(socket.getOutputStream(), true);// �������������
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ʵ����BufferedReader����
                index = Integer.parseInt(reader.readLine());// ��ÿͻ���¼������������ֵ
                ta_info.append("���ǵ�"+(index+1)+"��������ӵ��û���\n"); // �ı�������ʾ��Ϣ
                getServerInfo();
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
    
    private void getServerInfo() {
        try {
            while (true) { // ����׽���������״̬
                if (reader != null) {
                    String line = reader.readLine();// ��ȡ���������͵���Ϣ
                    if (line != null){
                        ta_info.append("���յ����������͵���Ϣ��" + line + "\n"); // ��ÿͻ�����Ϣ
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();// �ر���
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
     * Create the frame
     */
    public ClientSocketFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                writer.println(String.valueOf(index));// ��������˷����˳��ͻ�������ֵ
            }
        });
        setTitle("�ͻ��˳���");
        setBounds(100, 100, 361, 257);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("һ�Զ�ͨ�š����ͻ��˳���");
        panel.add(label);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JLabel label_1 = new JLabel();
        label_1.setText("�ͻ��˷��͵���Ϣ��");
        panel_1.add(label_1);

        tf_send = new JTextField();
        tf_send.setPreferredSize(new Dimension(140, 25));
        panel_1.add(tf_send);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                writer.println(tf_send.getText()); // ���ı�������Ϣд����
                ta_info.append("�ͻ��˷��͵���Ϣ�ǣ�" + tf_send.getText()
                        + "\n"); // ���ı�������Ϣ��ʾ���ı�����
                tf_send.setText(""); // ���ı������
            }
        });
        button.setText("��  ��");
        panel_1.add(button);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        //
    }
    
}
