package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientOneToMany_ClientFrame extends JFrame {
    private JTextArea ta_info;
    private JTextField tf_send;
    PrintWriter out;// �������������
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientOneToMany_ClientFrame frame = new ClientOneToMany_ClientFrame();
                    frame.setVisible(true);
                    frame.createClientSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void createClientSocket() {
        try {
            Socket socket = new Socket("192.168.1.122", 1978);// �����׽��ֶ���
            out = new PrintWriter(socket.getOutputStream(), true);// �������������
            new ClientThread(socket).start();// �����������̶߳���
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    class ClientThread extends Thread {
        Socket socket;
        
        public ClientThread(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));// ��������������
                while (true) {
                    String info = in.readLine();// ��ȡ��Ϣ
                    ta_info.append(info + "\n");// ���ı�������ʾ��Ϣ
                    if (info.equals("88")) {
                        break;// �����߳�
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void send() {
        String info = tf_send.getText();// ����������Ϣ
        if (info.equals("")) {
            return;// ���û������Ϣ�򷵻أ���������
        }
        if (info.equals("88")) {
            System.exit(0);// ���û������Ϣ��88�����˳�
        }
        out.println(info);// ������Ϣ
        out.flush();// ˢ�����������
        tf_send.setText(null);// ����ı���
    }
    /**
     * Create the frame
     */
    public ClientOneToMany_ClientFrame() {
        super();
        setTitle("�ͻ���һ�Զ�ͨ�š����ͻ��˳���");
        setBounds(100, 100, 385, 266);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("�����������ݣ�");
        panel.add(label);
        
        tf_send = new JTextField();
        tf_send.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                send();// ���÷���������Ϣ
            }
        });
        tf_send.setPreferredSize(new Dimension(180, 25));
        panel.add(tf_send);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                send();// ���÷���������Ϣ
            }
        });
        button.setText("��  ��");
        panel.add(button);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        //
    }
    
}
