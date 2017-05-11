package com.mingrisoft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProxySelectorFrame extends JFrame {
    
    private JTextArea ta_info;
    private JTextField tf_accessAddress;
    private JTextField tf_proxyAddress;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        ProxySelectorFrame frame = new ProxySelectorFrame();
        frame.setVisible(true);
    }
    
    public void setProxyInfo(String proxyAddress) {
        Properties propperties = System.getProperties();// ���ϵͳ���Զ���
        propperties.setProperty("http.proxyHost", proxyAddress);// ����HTTP����ʹ�õĴ����������ַ
        propperties.setProperty("http.proxyPort", "80");// ����HTTP����ʹ�õĴ���������˿�
        propperties.setProperty("https.proxyHost", proxyAddress);// ���ð�ȫHTTP����ʹ�õĴ����������ַ
        propperties.setProperty("https.proxyPort", "443");// ���ð�ȫHTTP����ʹ�õĴ���������˿�
        propperties.setProperty("ftp.proxyHost", proxyAddress);// ����FTP���ʵĴ������������
        propperties.setProperty("ftp.proxyPort", "21");// ����FTP���ʵĴ���������˿�
        propperties.setProperty("socks.ProxyHost", proxyAddress);// ����socks����������ĵ�ַ
        propperties.setProperty("socks.ProxyPort", "1080");// ����socks����������Ķ˿�
    }
    
    public void removeProxyInfo() {
        Properties propperties = System.getProperties();// ���ϵͳ���Զ���
        propperties.remove("http.proxyHost");// ���HTTP����ʹ�õĴ����������ַ
        propperties.remove("http.proxyPort");// ���HTTP����ʹ�õĴ���������˿�
        propperties.remove("https.proxyHost");// �����ȫHTTP����ʹ�õĴ����������ַ
        propperties.remove("https.proxyPort");// �����ȫHTTP����ʹ�õĴ���������˿�
        propperties.remove("ftp.proxyHost");// ���FTP���ʵĴ������������
        propperties.remove("ftp.proxyPort");// ���FTP���ʵĴ���������˿�
        propperties.remove("socksProxyHost");// ���socks����������ĵ�ַ
        propperties.remove("socksProxyPort");// ���socks����������Ķ˿�
    }
    
    /**
     * ʹ��HTTP����
     * @param accessAddress ��Ҫ���ʵĵ�ַ
     * @throws Exception �׳��쳣
     */
    public void useHttpAccess(String accessAddress) throws Exception{
        URL url = new URL(accessAddress);// ����URL����
        URLConnection urlConn = url.openConnection(); // �Զ�����ϵͳ���õ�HTTP�������������������
        Scanner scanner = new Scanner(urlConn.getInputStream(),"utf8");// ͨ��������ɨ�����
        StringBuffer sb = new StringBuffer();// �����ַ�������
        while (scanner.hasNextLine()) {// ���ɨ����������Ϣ
            sb.append(scanner.nextLine()+"\n");// ��ȡ����������ϵ���ҳ���ݣ�����ӵ��ַ���������
        }
        if (sb!=null) {
            ta_info.append(sb.toString());// ��ʾ��ҳ����
        }
    }
    
    /**
     * Create the frame
     */
    public ProxySelectorFrame() {
        super();
        setTitle("ʹ��ProxySelectorѡ����������");
        getContentPane().setLayout(null);
        setBounds(100, 100, 419, 309);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("����������������ַ��");
        label.setBounds(21, 21, 151, 18);
        getContentPane().add(label);
        
        tf_proxyAddress = new JTextField();
        tf_proxyAddress.setBounds(167, 19, 218, 22);
        getContentPane().add(tf_proxyAddress);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("����Ҫ���ʵ���վ��ַ��Ȼ��س�");
        label_1.setBounds(21, 45, 218, 18);
        getContentPane().add(label_1);
        
        tf_accessAddress = new JTextField();
        tf_accessAddress.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    String proxyAddress = tf_proxyAddress.getText().trim();// �����������ַ
                    setProxyInfo(proxyAddress);// ���ñ��ش���
                    String accessAddress = tf_accessAddress.getText().trim();// �����Ҫ���ʵ���ַ
                    useHttpAccess(accessAddress);// ���÷���������http����
                    removeProxyInfo();// ������ش���
                } catch (Exception ex) {
                    
                }
            }
        });
        tf_accessAddress.setBounds(21, 69, 364, 22);
        getContentPane().add(tf_accessAddress);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("���ʽ��");
        label_2.setBounds(21, 97, 75, 18);
        getContentPane().add(label_2);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 121, 358, 140);
        getContentPane().add(scrollPane);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        //
    }
    

}
