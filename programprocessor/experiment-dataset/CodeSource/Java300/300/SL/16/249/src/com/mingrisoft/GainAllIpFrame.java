package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author ������
 * �������������IP��ַ
 */
@SuppressWarnings("serial")
public class GainAllIpFrame extends JFrame {
    private JTextArea ta_allIp;
    static public Hashtable<String, String> pingMap; // ���ڴ洢��ping��IP�Ƿ�Ϊ����IP�ļ���
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        GainAllIpFrame frame = new GainAllIpFrame();
        frame.setVisible(true);
    }
    
    public void gainAllIp() throws Exception {// �������IP������ʾ���ı����еķ���
        InetAddress host = InetAddress.getLocalHost();// ��ñ�����InetAddress����
        String hostAddress = host.getHostAddress();// ��ñ�����IP��ַ
        int pos = hostAddress.lastIndexOf(".");// ���IP��ַ�����һ�����λ��
        String wd = hostAddress.substring(0, pos + 1);// �Ա�����IP���н�ȡ���������
        for (int i = 1; i <= 255; i++) { // �Ծ�������IP��ַ���б���
            String ip = wd + i;// ����IP��ַ
            PingIpThread thread = new PingIpThread(ip);// �����̶߳���
            thread.start();// �����̶߳���
        }
        Set<String> set = pingMap.keySet();// ��ü����м���Set��ͼ
        Iterator<String> it = set.iterator();// ��õ���������
        while (it.hasNext()) { // ����������Ԫ�أ���ִ��ѭ����
            String key = it.next(); // �����һ����������
            String value = pingMap.get(key);// ���ָ������ֵ
            if (value.equals("true")) {
                ta_allIp.append(key + "\n");// ׷����ʾIP��ַ
            }
        }
    }
    
    /**
     * Create the frame
     */
    public GainAllIpFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                try {
                    gainAllIp();
                    ta_allIp.setText(null);
                } catch (Exception e1) {
                    ta_allIp.setText(null);
                }
            }
        });
        setTitle("�������������IP��ַ");
        setBounds(400, 200, 270, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_allIp = new JTextArea();
        scrollPane.setViewportView(ta_allIp);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    ta_allIp.setText(null);
                    gainAllIp();
                } catch (Exception e1) {
                    ta_allIp.setText(null);
                    JOptionPane.showMessageDialog(null, "Ӧ�ó����쳣��������һ�Ρ�");
                }
            }
        });
        button_2.setText("��ʾ����IP");
        panel.add(button_2);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button.setText("��    ��");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.setText("New JButton");
        panel.add(button_1);
        pingMap = new Hashtable<String, String>();
    }
    
    class PingIpThread extends Thread {// �жϸ���IP�Ƿ�Ϊ����IP���̶߳���
        public String ip; // ��ʾIP��ַ�ĳ�Ա����
        public PingIpThread(String ip) {// ����Ϊ��Ҫ�жϵ�IP��ַ
            this.ip = ip;
        }
        public void run() {
            try {
                // �����ping��IP���̣�-w 280�ǵȴ�ÿ�λظ��ĳ�ʱʱ�䣬-n 1��Ҫ���͵Ļ���������
                Process process = Runtime.getRuntime().exec(
                        "ping " + ip + " -w 280 -n 1");
                InputStream is = process.getInputStream();// ��ý��̵�����������
                InputStreamReader isr = new InputStreamReader(is);// ����InputStreamReader����
                BufferedReader in = new BufferedReader(isr);// ���������ַ�������
                String line = in.readLine();// ��ȡ��Ϣ
                while (line != null) {
                    if (line != null && !line.equals("")) {
                        if (line.substring(0, 2).equals("����")
                                || (line.length() > 10 && line.substring(0, 10)
                                        .equals("Reply from"))) {// �ж���pingͨ����IP��ַ
                            pingMap.put(ip, "true");// �򼯺������IP
                        }
                    }
                    line = in.readLine();// �ٶ�ȡ��Ϣ
                }
            } catch (IOException e) {
            }
        }
    }
}
