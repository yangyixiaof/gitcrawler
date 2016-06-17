package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendAttachmentMailFrame extends JFrame {
    private JTextArea ta_attachment;
    private JTextArea ta_text;
    private JTextField tf_title;
    private JTextField tf_send;
    private JTextField tf_receive;
    private Session session;
    private String sendHost = "localhost";
    private String sendProtocol="smtp";
    private String filePathAndName = null;
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SendAttachmentMailFrame frame = new SendAttachmentMailFrame();
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
    public SendAttachmentMailFrame() {
        super();
        setTitle("发送邮件时进行身份验证");
        getContentPane().setLayout(null);
        setBounds(100, 100, 439, 358);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("发送邮件时进行身份验证");
        label.setBounds(70, 10, 274, 24);
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
        scrollPane.setBounds(113, 128, 287, 76);
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
                    JOptionPane.showMessageDialog(null, "发送失败...\n\n用户名或密码不正确。");
                }
            }
        });
        btn_send.setText("发    送");
        btn_send.setBounds(225, 282, 85, 28);
        getContentPane().add(btn_send);

        final JButton btn_exit = new JButton();
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        btn_exit.setText("退    出");
        btn_exit.setBounds(316, 282, 84, 28);
        getContentPane().add(btn_exit);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // 创建文件对话框
                int returnValue = fileChooser.showOpenDialog(null);// 打开文件选择对话框
                if (returnValue == JFileChooser.APPROVE_OPTION) { // 判断是否选择了文件
                    File file = fileChooser.getSelectedFile(); // 获得文件对象
                    if (file.length() / 1024.0 / 1024 > 50.0) {
                        JOptionPane.showMessageDialog(null, "请选择小于等于50MB的文件。");
                        return;
                    }
                    filePathAndName = file.getAbsolutePath();// 获得文件的完整路径和文件名
                    ta_attachment.append(file.getName());// 显示附件文件的名称
                }
            }
        });
        button.setText("添加附件");
        button.setBounds(113, 282, 106, 28);
        getContentPane().add(button);

        final JLabel label_5 = new JLabel();
        label_5.setText("附    件：");
        label_5.setBounds(32, 210, 66, 18);
        getContentPane().add(label_5);

        final JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(112, 213, 287, 63);
        getContentPane().add(scrollPane_1);

        ta_attachment = new JTextArea();
        scrollPane_1.setViewportView(ta_attachment);
    }
    public void init() throws Exception {
        Properties props = new Properties();// 创建属性对象
        props.put("mail.transport.protocol", sendProtocol);// 指定邮件传输协议
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//指定传输协议使用的类
        props.put("mail.smtp.host", sendHost);// 定义发送邮件的主机
        props.put("mail.smtp.auth", "true");// 设置发邮件时需要身份验证
        Authenticator check = new CheckAuthenticator();// 创建Authenticator实例
        session = Session.getDefaultInstance(props,check);// 创建Session对象
    }
    public void sendMessage(String fromAddr,String toAddr,String title,String text) throws Exception {
        Message msg = new MimeMessage(session);// 创建Message对象
        InternetAddress[] toAddrs = InternetAddress.parse(toAddr,false);// 创建接收方的InternetAddress对象
        msg.setRecipients(Message.RecipientType.TO, toAddrs);// 指定接收方
        msg.setSentDate(new Date());// 指定接发送日期
        msg.setSubject(title);// 设置主题
        msg.setFrom(new InternetAddress(fromAddr));// 指定发送者
        
        Multipart multipart = new MimeMultipart();// 可以添加复杂内容的Multipart对象
        
        MimeBodyPart mimeBodyPartText = new MimeBodyPart();// 添加正文的MimeBodyPart对象
        mimeBodyPartText.setText(text);// 指定正文
        multipart.addBodyPart(mimeBodyPartText);// 添加到Multipart对象上
        if (filePathAndName!=null && !filePathAndName.equals("")){
            MimeBodyPart mimeBodyPartAdjunct = new MimeBodyPart();// 添加附件的MimeBodyPart对象
            FileDataSource fileDataSource = new FileDataSource(filePathAndName);// 创建附件的FileDataSource对象
            mimeBodyPartAdjunct.setDataHandler(new DataHandler(fileDataSource));// 指定数据
            mimeBodyPartAdjunct.setDisposition(Part.ATTACHMENT);// 指定添加的内容是附件
            String name = fileDataSource.getName();
            mimeBodyPartAdjunct.setFileName(MimeUtility.encodeText(name, "GBK", null));// 指定附件文件的名称
            multipart.addBodyPart(mimeBodyPartAdjunct);// 添加到Multipart对象上
        }
        msg.setContent(multipart);// 设置邮件内容
        Transport.send(msg);// 发送邮件时，进行身份验证
        filePathAndName = null;
        JOptionPane.showMessageDialog(null, "邮件发送成功。");
    }
}
