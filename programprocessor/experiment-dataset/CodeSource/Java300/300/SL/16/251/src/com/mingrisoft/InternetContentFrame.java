package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InternetContentFrame extends JFrame {
    
    private JTextArea ta_content;
    private JTextField tf_address;
    /**
     * Launch the application
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InternetContentFrame frame = new InternetContentFrame();
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
    public InternetContentFrame() {
        super();
        setTitle("������ҳ�е�����");
        setBounds(100, 100, 484, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("������ַ��");
        panel.add(label);

        tf_address = new JTextField();
        tf_address.setPreferredSize(new Dimension(260,25));
        panel.add(tf_address);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String address = tf_address.getText().trim();// ����������ַ
                Collection urlCollection = getURLCollection(address);// ���÷����������ҳ���ݵļ��϶���
                Iterator it = urlCollection.iterator();  // ��ü��ϵĵ���������
                while(it.hasNext()){
                    ta_content.append((String)it.next()+"\n");       // ���ı�������ʾ����������
                }
            }
        });
        button.setText("������ҳ");
        panel.add(button);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_content = new JTextArea();
        ta_content.setFont(new Font("", Font.BOLD, 14));
        scrollPane.setViewportView(ta_content);
        //
    }
    public Collection<String> getURLCollection(String urlString){
        URL url = null;                             // ����URL
        URLConnection conn = null;                  // ����URLConnection
        Collection<String> urlCollection = new ArrayList<String>(); // �������϶���
        try{
            url = new URL(urlString);               // ����URL����
            conn = url.openConnection();            // ������Ӷ���
            conn.connect();                         // �򿪵�url������Դ��ͨ������
            InputStream is = conn.getInputStream(); // ��ȡ������
            InputStreamReader in = new InputStreamReader(is,"UTF-8"); // ת��Ϊ�ַ���
            BufferedReader br = new BufferedReader(in); // ��������������
            String nextLine = br.readLine();            // ��ȡ��Ϣ��������ҳ
            while (nextLine !=null){
                urlCollection.add(nextLine);   // ������ҳ��ȫ�����ݣ���ӵ�������
                nextLine = br.readLine();      // ��ȡ��Ϣ��������ҳ
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return urlCollection;
    }

}
