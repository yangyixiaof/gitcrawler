package com.mingrisoft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserProxyFrame extends JFrame {
    
    private JTextArea ta_show;
    private JTextField tf_accessAddress;
    private JTextField tf_proxyPort;
    private JTextField tf_proxyAddress;
    private Proxy proxy;// �������
    private URL url;// ����URL����
    private URLConnection urlConn;// �������Ӷ���
    private Scanner scanner;// ��������ͨ�������ȡ���ݵ�ɨ����
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        UserProxyFrame frame = new UserProxyFrame();
        frame.setVisible(true);
    }
    
    public void accessUrl(String proxyAddress, int proxyPort,
            String accessAddress) throws Exception {
        url = new URL(accessAddress);// ����URL����
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress,
                proxyPort));// ��������
        urlConn = url.openConnection(proxy);// ͨ�����������
        scanner = new Scanner(urlConn.getInputStream(), "UTF8");// ͨ��������ɨ����
        ta_show.setText(null);// ����ı��������
        StringBuffer sb = new StringBuffer();// �����ַ�������
        while (scanner.hasNextLine()) {// �ж�ɨ�����Ƿ�������
            String line = scanner.nextLine();// ��ɨ�������һ������
            sb.append(line + "\n");// ���ַ������������Ϣ
        }
        if (sb != null) {
            ta_show.append(sb.toString());// ���ı�������ʾ��Ϣ
        }
    }
    
    /**
     * Create the frame
     */
    public UserProxyFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("ʹ��Proxy�������������");
        setBounds(100, 100, 448, 334);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("����������ĵ�ַ��");
        label.setBounds(10, 10, 129, 18);
        getContentPane().add(label);
        
        tf_proxyAddress = new JTextField();
        tf_proxyAddress.setBounds(145, 8, 277, 22);
        getContentPane().add(tf_proxyAddress);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("����������Ķ˿ںţ�");
        label_1.setBounds(10, 38, 136, 18);
        getContentPane().add(label_1);
        
        tf_proxyPort = new JTextField();
        tf_proxyPort.setBounds(145, 36, 277, 22);
        getContentPane().add(tf_proxyPort);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("����Ҫ���ʵ���վ��ַ��Ȼ��س�");
        label_2.setBounds(10, 65, 231, 18);
        getContentPane().add(label_2);
        
        tf_accessAddress = new JTextField();
        tf_accessAddress.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    String proxyAddress = tf_proxyAddress.getText().trim();// ����������ĵ�ַ
                    int proxyPort = Integer.parseInt(tf_proxyPort.getText().trim());// ����������Ķ˿�
                    String accessAddress = tf_accessAddress.getText().trim();// ��Ҫ�򿪵���վ��ַ
                    accessUrl(proxyAddress, proxyPort, accessAddress);// ���÷�����ʹ�ô��������վ
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������Ϣ����");
                }
            }
        });
        tf_accessAddress.setBounds(10, 85, 412, 22);
        getContentPane().add(tf_accessAddress);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 137, 412, 149);
        getContentPane().add(scrollPane);
        
        ta_show = new JTextArea();
        scrollPane.setViewportView(ta_show);
        
        final JLabel label_3 = new JLabel();
        label_3.setText("���ʽ��");
        label_3.setBounds(10, 113, 193, 18);
        getContentPane().add(label_3);
    }
    
}
