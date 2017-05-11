package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BreakPointSuperveneFrame extends JFrame {
    private JTextField tf_totalLength;
    private JTextField tf_residuaryLength;
    private JTextField tf_readToPos;
    private JTextField tf_address;
    private JTextField tf_endPos;
    private JTextField tf_startPos;
    private String urlAddress = "";// ���ڴ洢������Դ�ĵ�ַ
    private long totalLength = 0;// �洢������Դ�Ĵ�С�����ֽ�Ϊ��λ
    private long readToPos = 0;// �洢�ϴζ�ȡ����λ��
    private long residuaryLength = 0;// �洢δ�����ݵĴ�С
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BreakPointSuperveneFrame frame = new BreakPointSuperveneFrame();
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
    public BreakPointSuperveneFrame() {
        super();
        getContentPane().setLayout(null);
        setTitle("����������Դ�Ķϵ�����");
        setBounds(100, 100, 514, 238);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tf_startPos = new JTextField();
        tf_startPos.setBounds(80, 165, 113, 22);
        getContentPane().add(tf_startPos);
        
        final JLabel label = new JLabel();
        label.setText("��ʼλ�ã�");
        label.setBounds(10, 167, 74, 18);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("����λ�ã�");
        label_1.setBounds(199, 167, 74, 18);
        getContentPane().add(label_1);
        
        tf_endPos = new JTextField();
        tf_endPos.setBounds(267, 165, 117, 22);
        getContentPane().add(tf_endPos);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("������Դ�ĵ�ַ��");
        label_2.setBounds(10, 52, 113, 18);
        getContentPane().add(label_2);
        
        tf_address = new JTextField();
        tf_address.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    urlAddress = tf_address.getText().trim();
                    URL url = new URL(urlAddress);// ���������Դ��URL
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();// ������Ӷ���
                    connection.connect();// ����������Դ
                    totalLength = connection.getContentLength();// ���������Դ�ĳ���
                    connection.disconnect();// �Ͽ�����
                    tf_totalLength.setText(String.valueOf(totalLength));// ��ʾ�ܳ���
                    tf_readToPos.setText("0");// ��ʾ�ϴζ�ȡ����λ��
                    residuaryLength = totalLength;// δ������Ϊ�ļ��ܳ���
                    tf_residuaryLength.setText(String.valueOf(residuaryLength));// ��ʾδ������
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                
            }
        });
        tf_address.setBounds(119, 50, 365, 22);
        getContentPane().add(tf_address);
        
        final JLabel label_3 = new JLabel();
        label_3.setForeground(new Color(0, 0, 255));
        label_3.setFont(new Font("", Font.BOLD, 14));
        label_3.setText("����������Դ�ĵ�ַ���س������Ի��������Դ�Ĵ�С��");
        label_3.setBounds(10, 10, 384, 22);
        getContentPane().add(label_3);
        
        final JLabel label_4 = new JLabel();
        label_4.setForeground(new Color(128, 0, 0));
        label_4.setText("������Դ�Ĵ�СΪ");
        label_4.setBounds(10, 76, 113, 38);
        getContentPane().add(label_4);
        
        final JLabel label_5 = new JLabel();
        label_5.setText("�ϴζ�ȡ��");
        label_5.setBounds(10, 123, 74, 18);
        getContentPane().add(label_5);
        
        tf_readToPos = new JTextField();
        tf_readToPos.setBounds(80, 121, 113, 22);
        tf_readToPos.setEnabled(false);
        getContentPane().add(tf_readToPos);
        
        final JLabel label_6 = new JLabel();
        label_6.setText("�ֽڴ�����ʣ");
        label_6.setBounds(202, 123, 87, 18);
        getContentPane().add(label_6);
        
        tf_residuaryLength = new JTextField();
        tf_residuaryLength.setBounds(285, 120, 117, 22);
        tf_residuaryLength.setEnabled(false);
        getContentPane().add(tf_residuaryLength);
        
        final JLabel label_7 = new JLabel();
        label_7.setText("�ֽ�δ����");
        label_7.setBounds(404, 123, 80, 18);
        getContentPane().add(label_7);
        
        final JLabel label_4_1 = new JLabel();
        label_4_1.setForeground(new Color(128, 0, 0));
        label_4_1.setText("���ֽڡ�");
        label_4_1.setBounds(404, 76, 80, 38);
        getContentPane().add(label_4_1);
        
        tf_totalLength = new JTextField();
        tf_totalLength.setBounds(119, 84, 283, 22);
        tf_totalLength.setEnabled(false);
        getContentPane().add(tf_totalLength);
        
        final JButton button = new JButton();
        button.setBounds(395, 162, 89, 28);
        getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (totalLength == 0) {
                    JOptionPane.showMessageDialog(null,
                            "û��������Դ��\n\n��������ȷ����ַ��Ȼ��س���");
                    return;
                }
                long startPos = 0;// ��ʼλ��
                long endPos = 0;// ����λ��
                try {
                    startPos = Long.parseLong(tf_startPos.getText().trim());// ��ʼλ��
                    endPos = Long.parseLong(tf_endPos.getText().trim());// ����λ��
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "�������ʼλ�û����λ�ò���ȷ��");
                    return;
                }
                readToPos = endPos;// ��¼��ȡ����λ��
                residuaryLength = totalLength - readToPos;// ��¼δ�����ݵĴ�С
                tf_readToPos.setText(String.valueOf(readToPos));// ��ʾ��ȡ����λ��
                tf_residuaryLength.setText(String.valueOf(residuaryLength));// ��ʾδ���ֽ���
                tf_startPos.setText(String.valueOf(readToPos));// ������һ����ȡ��Ŀ�ʼλ��
                tf_endPos.setText(String.valueOf(totalLength));// ������һ����ȡ��Ľ���λ��
                tf_endPos.requestFocus();// ʹ����λ���ı����ý���
                tf_endPos.selectAll();// ѡ�����λ���ı����е�ȫ�����ݣ������������λ��ֵ
                download(startPos, endPos);// ���÷�����������
            }
        });
        button.setText("��ʼ����");
    }
    
    public void download(long startPosition, long endPosition) {
        try {
            URL url = new URL(urlAddress);// ���������Դ��URL
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();// ������Ӷ���
            connection.setRequestProperty("User-Agent", "NetFox");// ������������
            String rangeProperty = "bytes=" + startPosition + "-";// ��������Χ����
            if (endPosition > 0) {
                rangeProperty += endPosition;// ��������Χ����
            }
            connection.setRequestProperty("RANGE", rangeProperty);// ��������Χ����
            connection.connect();// ����������Դ
            InputStream in = connection.getInputStream();// �������������
            String file = url.getFile();// ����ļ�����
            String name = file.substring(file.lastIndexOf('/') + 1);// ����ļ���
            FileOutputStream out = new FileOutputStream("c:/" + name, true);// �������������,�������ص���Դ
            byte[] buff = new byte[2048];// �����ֽ�����
            int len = 0;// ����洢��ȡ���ݳ��ȵı���
            len = in.read(buff);// ��ȡ����
            while (len != -1) {
                out.write(buff, 0, len);// д�����
                len = in.read(buff);// ��ȡ����
            }
            out.close();// �ر���
            in.close();// �ر���
            connection.disconnect();// �Ͽ�����
            if (readToPos > 0 && readToPos == totalLength) {
                JOptionPane.showMessageDialog(null, "���������Դ�����ء�\n������ȷ������ť�˳�����");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
