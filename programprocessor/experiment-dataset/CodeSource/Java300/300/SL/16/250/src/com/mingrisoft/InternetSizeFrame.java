package com.mingrisoft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InternetSizeFrame extends JFrame {
    
    private JTextField tf_size;
    private JTextField tf_address;
    /**
     * Launch the application
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InternetSizeFrame frame = new InternetSizeFrame();
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
    public InternetSizeFrame() {
        super();
        setTitle("��ȡ������Դ�Ĵ�С");
        getContentPane().setLayout(null);
        setBounds(100, 100, 391, 223);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setText("������ַ��");
        label.setBounds(10, 77, 79, 18);
        getContentPane().add(label);

        tf_address = new JTextField();
        tf_address.setBounds(82, 75, 286, 22);
        getContentPane().add(tf_address);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String address = tf_address.getText().trim();// ����������ַ
                try{
                    long len = netSourceSize(address);// ���÷�����ȡ������Դ�Ĵ�С
                    tf_size.setText(String.valueOf(len)+" �ֽ�");// ���ı�������ʾ������Դ�Ĵ�С
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        button.setText("��ô�С");
        button.setBounds(282, 123, 86, 28);
        getContentPane().add(button);

        final JLabel label_1 = new JLabel();
        label_1.setText("������Դ�Ĵ�СΪ��");
        label_1.setBounds(10, 128, 127, 18);
        getContentPane().add(label_1);

        tf_size = new JTextField();
        tf_size.setBounds(131, 126, 145, 22);
        getContentPane().add(tf_size);

        final JLabel label_2 = new JLabel();
        label_2.setForeground(new Color(0, 0, 255));
        label_2.setFont(new Font("", Font.BOLD, 22));
        label_2.setText("��ȡ������Դ�Ĵ�С");
        label_2.setBounds(82, 8, 239, 44);
        getContentPane().add(label_2);
    }
    public long netSourceSize(String sUrl) throws Exception{
        URL url = new URL(sUrl);  // ����URL����
        URLConnection urlConn = url.openConnection();          // ����������Ӷ���
        urlConn.connect();                                     // �򿪵�url������Դ��ͨ������
        return urlConn.getContentLength();                     // ���ֽ�Ϊ��λ������Դ�Ĵ�С
    }
}
