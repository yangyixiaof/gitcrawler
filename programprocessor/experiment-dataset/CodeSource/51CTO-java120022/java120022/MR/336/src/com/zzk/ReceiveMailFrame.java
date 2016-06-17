package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.sun.mail.imap.IMAPFolder;

public class ReceiveMailFrame extends JFrame {
    private JTextArea ta_receive;
    protected Session session;
    protected Store store;
    private String receiveHost = "localhost";// ��������
    private String receiveProtocol = "imap";// ����Э��
    private String username = "zzk";// �û���
    private String password = "zzk";// ����
    private Vector<String> readedEmailUIDVector = new Vector<String>();
    private ObjectOutputStream objectOut = null;
    private ObjectInputStream objectIn = null;
    private int ReadedNums = 0;
    
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
        button.setText("�����ʼ������ظ���");
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
        store.connect(receiveHost, username, password);// ���ӽ��շ�����
    }
    
    public void receiveMessage() throws Exception {
        IMAPFolder folder = (IMAPFolder) store.getFolder("inbox");// ���inbox�ʼ���
        if (folder == null) {
            throw new Exception("������inbox�ʼ��С�");
        }
        folder.open(Folder.READ_ONLY);// ��ֻ����ʽ���ʼ���
        Message[] messages = folder.getMessages();// ����ʼ����е������ʼ�
        for (int i = 0; i < messages.length; i++) {
            long uid = folder.getUID(messages[i]);// ����ʼ���UID
            try {
                objectIn = new ObjectInputStream(new FileInputStream(
                        "c:/readedEmail.txt"));// ��������������
                Object readObj = objectIn.readObject();// ����������ȡ��Ϣ
                if (readObj == null) { // �ļ���δ�洢�Ѷ��ļ���UID
                    JOptionPane.showMessageDialog(null, "û���Ѷ��ʼ���");
                    return;
                } else {
                    readedEmailUIDVector = (Vector<String>) readObj;// �����ļ��ж�ȡ������ת��ΪVector����
                    for (int j = 0; j < readedEmailUIDVector.size(); j++) {// ������������
                        if (String.valueOf(uid).equals(readedEmailUIDVector.get(j))) {// ���Ѷ��ʼ�
                            ReadedNums++;// �Ѷ��ʼ�����1
                            readMessage(messages[i], folder, i);// ����readMessage()������ȡ�Ѷ��ʼ�����
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        ta_receive.append("�����յ�" + folder.getMessageCount() + "���ʼ���\n");// ��ʾ�յ����ʼ�����
        if (ReadedNums > 0) {
            if (ReadedNums == folder.getMessageCount()) {
                ta_receive.append("ȫ���Ѷ���\n\n");
            } else {
                ta_receive.append("��������" + ReadedNums + "�����Ѷ��ʼ���\n\n");
            }
        } else {
            ta_receive.append("û���Ѷ��ʼ���\n\n");
        }
        folder.close(false);// �ر��ʼ��У�����ɾ���ʼ�
        store.close();// �ر�Store����
        if (objectOut != null)
            objectOut.close();
        if (objectIn != null)
            objectIn.close();
    }
    
    public void readMessage(Message message, IMAPFolder folder, int i)
            throws Exception {
        Object content = message.getContent();// ����ʼ�����
        if (content instanceof Multipart) {// �и���ִ�еĴ���
            ta_receive.append("----��" + (i + 1) + "���ʼ�----\n");
            ta_receive.append("���⣺" + folder.getMessage(i + 1).getSubject() + "\n");// ����
            Multipart mPart = (Multipart) content;// ���Multipart����
            ta_receive.append("���ģ�" + mPart.getBodyPart(0).getContent() + "\n");
            ta_receive.append("�������ڣ�" + folder.getMessage(i + 1).getSentDate() + "\n");// ��������
            Address[] ias = folder.getMessage(i + 1).getFrom();// �����˵�ַ
            ta_receive.append("�����ˣ�" + ias[0] + "\n");
            Address[] iasTo = folder.getMessage(i + 1).getAllRecipients();// �ռ��˵�ַ
            ta_receive.append("�ռ��ˣ�" + iasTo[0] + "\n\n");
            try {
                String fileName = mPart.getBodyPart(1).getFileName();// ����ļ���
                ta_receive.append("���յ�һ����Ϊ��" + MimeUtility.decodeText(fileName) + "���ĸ���\n\n");
                InputStream in = mPart.getBodyPart(1).getInputStream();// �������������
                FileDialog dialog = new FileDialog(ReceiveMailFrame.this, "����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setFile(MimeUtility.decodeText(fileName));
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String saveFileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || saveFileName == null) {
                    return;
                }
                OutputStream out = new BufferedOutputStream(new FileOutputStream(path + "/" + saveFileName));// �������������
                int len = -1;
                while ((len = in.read()) != -1) {// ��ȡ����������Ϣ
                    out.write(len);// �������д�븽����Ϣ
                }
                out.close();
                in.close();
            } catch (Exception ex) {
                
            }
        } else {// û�и���ִ�еĴ���
            ta_receive.append("----��" + (i + 1) + "���ʼ�----\n");
            ta_receive.append("���⣺" + folder.getMessage(i + 1).getSubject() + "\n");// ����
            ta_receive.append("���ģ�" + folder.getMessage(i + 1).getContent() + "\n");// ����
            ta_receive.append("�������ڣ�" + folder.getMessage(i + 1).getSentDate() + "\n");// ��������
            Address[] ias = folder.getMessage(i + 1).getFrom();// �����˵�ַ
            ta_receive.append("�����ˣ�" + ias[0] + "\n");
            Address[] iasTo = folder.getMessage(i + 1).getAllRecipients();// �ռ��˵�ַ
            ta_receive.append("�ռ��ˣ�" + iasTo[0] + "\n\n");
        }
    }
}
