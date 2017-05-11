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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientOneToOne_ClientFrame extends JFrame {
    private JTextField tf_newUser;
    private JList user_list;
    private JTextArea ta_info;
    private JTextField tf_send;
    PrintWriter out;// �������������
    private boolean loginFlag = false;// Ϊtrueʱ��ʾ�Ѿ���¼��Ϊfalseʱ��ʾδ��¼
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientOneToOne_ClientFrame frame = new ClientOneToOne_ClientFrame();
                    frame.setVisible(true);
                    frame.createClientSocket();// ���÷��������׽��ֶ���
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
                DefaultComboBoxModel model = (DefaultComboBoxModel) user_list
                                .getModel();// ����б���ģ��
                while (true) {
                    String info = in.readLine().trim();// ��ȡ��Ϣ
                    
                    if (!info.startsWith("MSG:")) {
                        boolean itemFlag = false;// ����Ƿ�Ϊ�б������б��Ϊtrue����ӣ�Ϊfalse���
                        for (int i = 0; i < model.getSize(); i++) {
                            if (info.equals((String) model.getElementAt(i))) {
                                itemFlag = true;
                            }
                        }
                        if (!itemFlag) {
                            model.addElement(info);// ����б���
                        } else {
                            itemFlag = false;
                        }
                    } else {
                        ta_info.append(info + "\n");// ���ı�������ʾ��Ϣ
                        if (info.equals("88")) {
                            break;// �����߳�
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void send() {
        if (!loginFlag) {
            JOptionPane.showMessageDialog(null, "���ȵ�¼��");
            return;
        }
        String sendUserName = tf_newUser.getText().trim();
        String info = tf_send.getText();// ����������Ϣ
        if (info.equals("")) {
            return;// ���û������Ϣ�򷵻أ���������
        }
        String receiveUserName = (String) user_list.getSelectedValue();// ��ý�����Ϣ���û�
        String msg = sendUserName + "�����͸���" + receiveUserName + "������Ϣ�ǣ� "
                + info;// ���巢�͵���Ϣ
        if (info.equals("88")) {
            System.exit(0);// ���û������Ϣ��88�����˳�
        }
        out.println(msg);// ������Ϣ
        out.flush();// ˢ�����������
        tf_send.setText(null);// ����ı���
    }
    
    /**
     * Create the frame
     */
    public ClientOneToOne_ClientFrame() {
        super();
        setTitle("�ͻ���һ��һͨ�š����ͻ��˳���");
        setBounds(100, 100, 385, 288);
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
        
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(100);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        final JScrollPane scrollPane = new JScrollPane();
        splitPane.setRightComponent(scrollPane);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        
        final JScrollPane scrollPane_1 = new JScrollPane();
        splitPane.setLeftComponent(scrollPane_1);
        
        user_list = new JList();
        user_list.setModel(new DefaultComboBoxModel(new String[] { "" }));
        scrollPane_1.setViewportView(user_list);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�����û����ƣ�");
        panel_1.add(label_1);
        
        tf_newUser = new JTextField();
        tf_newUser.setPreferredSize(new Dimension(180, 25));
        panel_1.add(tf_newUser);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (loginFlag) {
                    JOptionPane.showMessageDialog(null, "��ͬһ����ֻ�ܵ�¼һ�Ρ�");
                    return;
                }
                String userName = tf_newUser.getText().trim();// ��õ�¼�û���
                out.println("�û���" + userName);// ���͵�¼�û�������
                out.flush();// ˢ�����������
                tf_newUser.setEnabled(false);
                loginFlag = true;
            }
        });
        button_1.setText("��  ¼");
        panel_1.add(button_1);
    }
}
