package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GetLocalHostIpFrame extends JFrame {
    
    private JTextField tf_ip;
    /**
     * Launch the application
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GetLocalHostIpFrame frame = new GetLocalHostIpFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public GetLocalHostIpFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("��ȡ����������Ip��ַ");
        setBounds(100, 100, 340, 211);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    InetAddress inetAddr = InetAddress.getLocalHost();// ��������������InetAddress����
                    String ip = inetAddr.getHostAddress();// ��ñ���������IP��ַ
                    tf_ip.setText(ip);// ���ı�������ʾIP��ַ
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("��ȡIP��ַ");
        button.setBounds(29, 113, 106, 28);
        getContentPane().add(button);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 16));
        label.setText("��ȡ����������Ip��ַ");
        label.setBounds(73, 22, 171, 35);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("Ip��ַ��");
        label_1.setBounds(29, 75, 66, 18);
        getContentPane().add(label_1);

        tf_ip = new JTextField();
        tf_ip.setBounds(88, 73, 199, 22);
        getContentPane().add(tf_ip);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�˳�ϵͳ");
        button_1.setBounds(181, 113, 106, 28);
        getContentPane().add(button_1);
        //
    }
    
}
