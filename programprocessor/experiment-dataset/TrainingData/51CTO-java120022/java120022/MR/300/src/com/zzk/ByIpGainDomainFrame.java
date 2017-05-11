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
public class ByIpGainDomainFrame extends JFrame {
    
    private JTextField tf_ip;
    private JTextField tf_canonical;
    private JTextField tf_host;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ByIpGainDomainFrame frame = new ByIpGainDomainFrame();
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
    public ByIpGainDomainFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("ͨ��IP��ַ���������������");
        setBounds(100, 100, 333, 218);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    String ip = tf_ip.getText();// IP��ַ
                    String[] ipStr = ip.split("[.]");// IPת��Ϊ�ַ�������
                    byte[] ipBytes = new byte[4];// �����洢ת����IP���ֽ�����
                    for (int i = 0; i < 4; i++) {
                        int m = Integer.parseInt(ipStr[i]);// ת��Ϊ����
                        byte b = (byte) (m & 0xff);// ת��Ϊ�ֽ�
                        ipBytes[i] = b;// ��ֵ���ֽ�����
                    }
                    InetAddress inetAddr = InetAddress.getByAddress(ipBytes);// ����InetAddress����
                    String canonical = inetAddr.getCanonicalHostName();// ��ȡ����
                    String host = inetAddr.getHostName();// ��ȡ������
                    tf_canonical.setText(canonical);// ���ı�������ʾ����
                    tf_host.setText(host);// ���ı�������ʾ������
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("��ȡ������������");
        button.setBounds(28, 136, 150, 28);
        getContentPane().add(button);
        
        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 16));
        label.setText("ͨ��IP��ַ���������������");
        label.setBounds(51, 10, 223, 35);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�� �� ����");
        label_1.setBounds(28, 110, 66, 18);
        getContentPane().add(label_1);
        
        tf_host = new JTextField();
        tf_host.setBounds(87, 108, 199, 22);
        getContentPane().add(tf_host);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�˳�ϵͳ");
        button_1.setBounds(191, 136, 95, 28);
        getContentPane().add(button_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("��    ����");
        label_2.setBounds(28, 82, 66, 18);
        getContentPane().add(label_2);
        
        tf_canonical = new JTextField();
        tf_canonical.setBounds(87, 80, 199, 22);
        getContentPane().add(tf_canonical);

        final JLabel label_3 = new JLabel();
        label_3.setText("����IP��ַ��");
        label_3.setBounds(10, 51, 84, 18);
        getContentPane().add(label_3);

        tf_ip = new JTextField();
        tf_ip.setBounds(87, 52, 199, 22);
        getContentPane().add(tf_ip);
        //
    }
    
}
