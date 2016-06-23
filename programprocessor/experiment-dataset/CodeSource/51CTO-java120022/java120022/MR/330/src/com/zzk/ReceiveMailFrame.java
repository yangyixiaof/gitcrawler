package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReceiveMailFrame extends JFrame {
    private JTextArea ta_receive;
    protected Session session;
    protected Store store;
    private String receiveHost = "localhost";// 接收主机
    private String receiveProtocol = "imap";// 接收协议
    private String username = "zzk";// 用户名
    private String password = "zzk";// 密码
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReceiveMailFrame frame = new ReceiveMailFrame();
                    frame.init();// 调用初始化方法
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ReceiveMailFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setTitle("接收邮件窗体");
        setBounds(100, 100, 386, 314);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_receive = new JTextArea();
        scrollPane.setViewportView(ta_receive);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    receiveMessage();// 调用接收信息的方法
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("接收邮件");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退出系统");
        panel.add(button_1);
    }
    public void init() throws Exception {
        Properties props = new Properties();// 声明Properties对象
        props.put("mail.store.protocol", receiveProtocol);// 指定接收协议
        props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");// 指定使用Store进行接收
        session = Session.getDefaultInstance(props);// 获得Session对象
        store = session.getStore(receiveProtocol);// 获得Store对象
        store.connect(receiveHost,username,password);// 连接接收服务器
    }
    public void receiveMessage() throws Exception {
        Folder folder = store.getFolder("inbox");// 获得inbox邮件夹的Folder对象
        if (folder == null) {
            throw new Exception("不存在inbox邮件夹。");
        }
        folder.open(Folder.READ_ONLY);// 以只读方式打开邮件夹
        ta_receive.append("您共收到"+folder.getMessageCount()+"个电子邮件。\n\n");
        Message[] messages = folder.getMessages();// 获得邮件夹中的所有邮件
        for (int i = 0;i<messages.length;i++){
            ta_receive.append("----第"+(i+1)+"个邮件----\n");
            ta_receive.append("主题："+folder.getMessage(i+1).getSubject()+"\n");// 主题
            ta_receive.append("正文："+folder.getMessage(i+1).getContent()+"\n");// 正文
            ta_receive.append("发送日期："+folder.getMessage(i+1).getSentDate()+"\n");// 发送日期
            Address[] ias = folder.getMessage(i+1).getFrom();// 发件人地址
            ta_receive.append("发件人："+ias[0]+"\n");
            Address[] iasTo = folder.getMessage(i+1).getAllRecipients();// 收件人地址
            ta_receive.append("收件人："+iasTo[0]+"\n\n");
        }
        folder.close(false);// 关闭邮件夹，但不删除邮件
        store.close();// 关闭Store对象
    }
}
