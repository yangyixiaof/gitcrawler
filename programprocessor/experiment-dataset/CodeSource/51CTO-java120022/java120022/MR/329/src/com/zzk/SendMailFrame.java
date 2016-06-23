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
    private Session session;// 定义Session对象
    private String sendHost = "localhost";// 定义发送邮件的主机
    private String sendProtocol="smtp";// 定义使用的发送协议
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
        setTitle("发送邮件窗体");
        getContentPane().setLayout(null);
        setBounds(100, 100, 439, 299);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("发送电子邮件");
        label.setBounds(144, 10, 185, 24);
        getContentPane().add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("收件人地址：");
        label_1.setBounds(22, 42, 85, 18);
        getContentPane().add(label_1);

        tf_receive = new JTextField();
        tf_receive.setBounds(113, 40, 287, 22);
        getContentPane().add(tf_receive);

        final JLabel label_2 = new JLabel();
        label_2.setText("发件人地址：");
        label_2.setBounds(22, 68, 78, 18);
        getContentPane().add(label_2);

        tf_send = new JTextField();
        tf_send.setBounds(113, 66, 287, 22);
        getContentPane().add(tf_send);

        final JLabel label_3 = new JLabel();
        label_3.setText("主    题：");
        label_3.setBounds(32, 92, 66, 18);
        getContentPane().add(label_3);

        tf_title = new JTextField();
        tf_title.setBounds(113, 94, 287, 22);
        getContentPane().add(tf_title);

        final JLabel label_4 = new JLabel();
        label_4.setText("正    文：");
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
                String toAddr = tf_receive.getText().trim();// 真实存在的目标邮件地址
                String title = tf_title.getText().trim();
                String text = ta_text.getText().trim();
                try {
                    sendMessage(fromAddr, toAddr, title, text);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btn_send.setText("发    送");
        btn_send.setBounds(144, 225, 78, 28);
        getContentPane().add(btn_send);

        final JButton btn_exit = new JButton();
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        btn_exit.setText("退    出");
        btn_exit.setBounds(279, 225, 78, 28);
        getContentPane().add(btn_exit);
    }
    public void init() throws Exception {
        Properties props = new Properties();// 创建属性对象
        props.put("mail.transport.protocol", sendProtocol);// 指定邮件传输协议
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//指定传输协议使用的类
        props.put("mail.smtp.host", sendHost);// 定义发送邮件的主机
        session = Session.getDefaultInstance(props);// 创建Session对象
    }
    /**
     * @param fromAddr 发送者
     * @param toAddr 接收者
     * @param title 主题
     * @param text 内容
     * @throws Exception 异常
     */
    public void sendMessage(String fromAddr,String toAddr,String title,String text) throws Exception {
        Message msg = new MimeMessage(session);// 创建Message对象
        InternetAddress[] toAddrs = InternetAddress.parse(toAddr,false);// 创建接收方的InternetAddress对象
        msg.setRecipients(Message.RecipientType.TO, toAddrs);// 指定接收方
        msg.setSentDate(new Date());// 指定接发送日期
        msg.setSubject(title);// 设置主题
        msg.setFrom(new InternetAddress(fromAddr));// 指定发送者
        msg.setText(text);// 指定发送内容
        Transport.send(msg);// 发送邮件
        JOptionPane.showMessageDialog(null, "邮件发送成功。");
    }
}
