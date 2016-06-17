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
    private String receiveHost = "localhost";// ��������
    private String receiveProtocol = "imap";// ����Э��
    private String username = "zzk";// �û���
    private String password = "zzk";// ����
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReceiveMailFrame frame = new ReceiveMailFrame();
                    frame.init();// ���ó�ʼ������
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
        setTitle("�����ʼ�����");
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
                    receiveMessage();// ���ý�����Ϣ�ķ���
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("�����ʼ�");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�˳�ϵͳ");
        panel.add(button_1);
    }
    public void init() throws Exception {
        Properties props = new Properties();// ����Properties����
        props.put("mail.store.protocol", receiveProtocol);// ָ������Э��
        props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");// ָ��ʹ��Store���н���
        session = Session.getDefaultInstance(props);// ���Session����
        store = session.getStore(receiveProtocol);// ���Store����
        store.connect(receiveHost,username,password);// ���ӽ��շ�����
    }
    public void receiveMessage() throws Exception {
        Folder folder = store.getFolder("inbox");// ���inbox�ʼ��е�Folder����
        if (folder == null) {
            throw new Exception("������inbox�ʼ��С�");
        }
        folder.open(Folder.READ_ONLY);// ��ֻ����ʽ���ʼ���
        ta_receive.append("�����յ�"+folder.getMessageCount()+"�������ʼ���\n\n");
        Message[] messages = folder.getMessages();// ����ʼ����е������ʼ�
        for (int i = 0;i<messages.length;i++){
            ta_receive.append("----��"+(i+1)+"���ʼ�----\n");
            ta_receive.append("���⣺"+folder.getMessage(i+1).getSubject()+"\n");// ����
            ta_receive.append("���ģ�"+folder.getMessage(i+1).getContent()+"\n");// ����
            ta_receive.append("�������ڣ�"+folder.getMessage(i+1).getSentDate()+"\n");// ��������
            Address[] ias = folder.getMessage(i+1).getFrom();// �����˵�ַ
            ta_receive.append("�����ˣ�"+ias[0]+"\n");
            Address[] iasTo = folder.getMessage(i+1).getAllRecipients();// �ռ��˵�ַ
            ta_receive.append("�ռ��ˣ�"+iasTo[0]+"\n\n");
        }
        folder.close(false);// �ر��ʼ��У�����ɾ���ʼ�
        store.close();// �ر�Store����
    }
}
