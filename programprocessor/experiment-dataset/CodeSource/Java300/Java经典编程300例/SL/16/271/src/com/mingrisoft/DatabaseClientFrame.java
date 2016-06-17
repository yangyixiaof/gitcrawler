package com.mingrisoft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DatabaseClientFrame extends JFrame {
    
    private JTextArea ta_result;
    private JTextField tf_age;
    private JTextField tf_name;
    private PrintWriter writer; // ����PrintWriter�����
    private BufferedReader reader; // ����BufferedReader����
    private Socket socket; // ����Socket����
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        DatabaseClientFrame frame = new DatabaseClientFrame();
        frame.setVisible(true);
        frame.connect();
    }
    
    private void connect() { // �����׽��ַ���
        ta_result.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            while (true) {
                writer = new PrintWriter(socket.getOutputStream(), true);// ʵ����PrintWriter����
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream())); // ʵ����BufferedReader����
                ta_result.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
                getServerInfo();// ���÷�����ȡ��������Ϣ
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    private void getServerInfo() {
        try {
            while (true) { // ����׽���������״̬
                if (reader != null) {
                    String line = reader.readLine();// ��ȡ��������Ϣ
                    if (line != null) {
                        ta_result.append("���յ����������͵���Ϣ�� " + line + "\n"); // ��÷�������Ϣ
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
     * Create the frame
     */
    public DatabaseClientFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("�ͻ��˳���");
        setBounds(100, 100, 277, 263);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("��  �ƣ�");
        label.setBounds(10, 12, 66, 18);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("��  �䣺");
        label_1.setBounds(10, 38, 66, 18);
        getContentPane().add(label_1);
        
        tf_name = new JTextField();
        tf_name.setBounds(56, 10, 194, 22);
        getContentPane().add(tf_name);
        
        tf_age = new JTextField();
        tf_age.setBounds(56, 36, 194, 22);
        getContentPane().add(tf_age);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "��ʾ�������˵ķ�����Ϣ",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        panel.setBounds(10, 91, 240, 124);
        getContentPane().add(panel);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 21, 220, 93);
        panel.add(scrollPane);
        
        ta_result = new JTextArea();
        scrollPane.setViewportView(ta_result);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String name = tf_name.getText().trim();// �������
                String age = tf_age.getText().trim();// �������
                if (name == null || name.equals("") || age == null || age.equals("")) {
                    JOptionPane.showMessageDialog(null, "���������䲻��Ϊ�ա�");
                    return;
                }
                try {
                    Integer.parseInt(age);// ������䲻�����־ͻᷢ���쳣
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������Ϊ���֡�");
                    return;
                }
                String info = name + ":data:" + age;// ʹ���ַ���":data:"��������������
                writer.println(info);// �������������Ϣ
            }
        });
        button.setText("��    ��");
        button.setBounds(41, 62, 72, 23);
        getContentPane().add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        button_1.setBounds(148, 62, 72, 23);
        getContentPane().add(button_1);
        //
    }
    
}
