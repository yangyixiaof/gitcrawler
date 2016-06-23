package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendMailFrame extends JFrame {
    private JTextArea ta_text;
    private JTextField tf_title;
    private JTextField tf_send;
    private JTextField tf_receive;
    private Session session;// ����Session����
    private String sendHost = "localhost";// ���巢���ʼ�������
    private String sendProtocol="smtp";// ����ʹ�õķ���Э��
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SendMailFrame frame = new SendMailFrame();
                    frame.init();
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
    public SendMailFrame() {
        super();
        setTitle("�����ʼ�����");
        getContentPane().setLayout(null);
        setBounds(100, 100, 439, 299);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("���͵����ʼ�");
        label.setBounds(144, 10, 185, 24);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("�ռ��˵�ַ��");
        label_1.setBounds(22, 42, 85, 18);
        getContentPane().add(label_1);

        tf_receive = new JTextField();
        tf_receive.setBounds(113, 40, 287, 22);
        getContentPane().add(tf_receive);

        final JLabel label_2 = new JLabel();
        label_2.setText("�����˵�ַ��");
        label_2.setBounds(22, 68, 78, 18);
        getContentPane().add(label_2);

        tf_send = new JTextField();
        tf_send.setBounds(113, 66, 287, 22);
        getContentPane().add(tf_send);

        final JLabel label_3 = new JLabel();
        label_3.setText("��    �⣺");
        label_3.setBounds(32, 92, 66, 18);
        getContentPane().add(label_3);

        tf_title = new JTextField();
        tf_title.setBounds(113, 94, 287, 22);
        getContentPane().add(tf_title);

        final JLabel label_4 = new JLabel();
        label_4.setText("��    �ģ�");
        label_4.setBounds(34, 128, 66, 18);
        getContentPane().add(label_4);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(113, 128, 287, 91);
        getContentPane().add(scrollPane);

        ta_text = new JTextArea();
        scrollPane.setViewportView(ta_text);

        final JButton btn_send = new JButton();
        btn_send.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String fromAddr = tf_send.getText().trim();
                String toAddr = tf_receive.getText().trim();// ��ʵ���ڵ�Ŀ���ʼ���ַ
                String title = tf_title.getText().trim();
                String text = ta_text.getText().trim();
                try {
                    sendMessage(fromAddr, toAddr, title, text);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btn_send.setText("��    ��");
        btn_send.setBounds(144, 225, 78, 28);
        getContentPane().add(btn_send);

        final JButton btn_exit = new JButton();
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        btn_exit.setText("��    ��");
        btn_exit.setBounds(279, 225, 78, 28);
        getContentPane().add(btn_exit);
    }
    public void init() throws Exception {
        Properties props = new Properties();// �������Զ���
        props.put("mail.transport.protocol", sendProtocol);// ָ���ʼ�����Э��
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//ָ������Э��ʹ�õ���
        props.put("mail.smtp.host", sendHost);// ���巢���ʼ�������
        session = Session.getDefaultInstance(props);// ����Session����
    }
    /**
     * @param fromAddr ������
     * @param toAddr ������
     * @param title ����
     * @param text ����
     * @throws Exception �쳣
     */
    public void sendMessage(String fromAddr,String toAddr,String title,String text) throws Exception {
        Message msg = new MimeMessage(session);// ����Message����
        InternetAddress[] toAddrs = InternetAddress.parse(toAddr,false);// �������շ���InternetAddress����
        msg.setRecipients(Message.RecipientType.TO, toAddrs);// ָ�����շ�
        msg.setSentDate(new Date());// ָ���ӷ�������
        msg.setSubject(title);// ��������
        msg.setFrom(new InternetAddress(fromAddr));// ָ��������
        msg.setText(text);// ָ����������
        Transport.send(msg);// �����ʼ�
        JOptionPane.showMessageDialog(null, "�ʼ����ͳɹ���");
    }
}
