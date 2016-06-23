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
        setTitle("�����ʼ�ʱ���������֤");
        getContentPane().setLayout(null);
        setBounds(100, 100, 439, 358);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("�����ʼ�ʱ���������֤");
        label.setBounds(70, 10, 274, 24);
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
        scrollPane.setBounds(113, 128, 287, 76);
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
                    JOptionPane.showMessageDialog(null, "����ʧ��...\n\n�û��������벻��ȷ��");
                }
            }
        });
        btn_send.setText("��    ��");
        btn_send.setBounds(225, 282, 85, 28);
        getContentPane().add(btn_send);

        final JButton btn_exit = new JButton();
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        btn_exit.setText("��    ��");
        btn_exit.setBounds(316, 282, 84, 28);
        getContentPane().add(btn_exit);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // �����ļ��Ի���
                int returnValue = fileChooser.showOpenDialog(null);// ���ļ�ѡ��Ի���
                if (returnValue == JFileChooser.APPROVE_OPTION) { // �ж��Ƿ�ѡ�����ļ�
                    File file = fileChooser.getSelectedFile(); // ����ļ�����
                    if (file.length() / 1024.0 / 1024 > 50.0) {
                        JOptionPane.showMessageDialog(null, "��ѡ��С�ڵ���50MB���ļ���");
                        return;
                    }
                    filePathAndName = file.getAbsolutePath();// ����ļ�������·�����ļ���
                    ta_attachment.append(file.getName());// ��ʾ�����ļ�������
                }
            }
        });
        button.setText("��Ӹ���");
        button.setBounds(113, 282, 106, 28);
        getContentPane().add(button);

        final JLabel label_5 = new JLabel();
        label_5.setText("��    ����");
        label_5.setBounds(32, 210, 66, 18);
        getContentPane().add(label_5);

        final JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(112, 213, 287, 63);
        getContentPane().add(scrollPane_1);

        ta_attachment = new JTextArea();
        scrollPane_1.setViewportView(ta_attachment);
    }
    public void init() throws Exception {
        Properties props = new Properties();// �������Զ���
        props.put("mail.transport.protocol", sendProtocol);// ָ���ʼ�����Э��
        props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//ָ������Э��ʹ�õ���
        props.put("mail.smtp.host", sendHost);// ���巢���ʼ�������
        props.put("mail.smtp.auth", "true");// ���÷��ʼ�ʱ��Ҫ�����֤
        Authenticator check = new CheckAuthenticator();// ����Authenticatorʵ��
        session = Session.getDefaultInstance(props,check);// ����Session����
    }
    public void sendMessage(String fromAddr,String toAddr,String title,String text) throws Exception {
        Message msg = new MimeMessage(session);// ����Message����
        InternetAddress[] toAddrs = InternetAddress.parse(toAddr,false);// �������շ���InternetAddress����
        msg.setRecipients(Message.RecipientType.TO, toAddrs);// ָ�����շ�
        msg.setSentDate(new Date());// ָ���ӷ�������
        msg.setSubject(title);// ��������
        msg.setFrom(new InternetAddress(fromAddr));// ָ��������
        
        Multipart multipart = new MimeMultipart();// ������Ӹ������ݵ�Multipart����
        
        MimeBodyPart mimeBodyPartText = new MimeBodyPart();// ������ĵ�MimeBodyPart����
        mimeBodyPartText.setText(text);// ָ������
        multipart.addBodyPart(mimeBodyPartText);// ��ӵ�Multipart������
        if (filePathAndName!=null && !filePathAndName.equals("")){
            MimeBodyPart mimeBodyPartAdjunct = new MimeBodyPart();// ��Ӹ�����MimeBodyPart����
            FileDataSource fileDataSource = new FileDataSource(filePathAndName);// ����������FileDataSource����
            mimeBodyPartAdjunct.setDataHandler(new DataHandler(fileDataSource));// ָ������
            mimeBodyPartAdjunct.setDisposition(Part.ATTACHMENT);// ָ����ӵ������Ǹ���
            String name = fileDataSource.getName();
            mimeBodyPartAdjunct.setFileName(MimeUtility.encodeText(name, "GBK", null));// ָ�������ļ�������
            multipart.addBodyPart(mimeBodyPartAdjunct);// ��ӵ�Multipart������
        }
        msg.setContent(multipart);// �����ʼ�����
        Transport.send(msg);// �����ʼ�ʱ�����������֤
        filePathAndName = null;
        JOptionPane.showMessageDialog(null, "�ʼ����ͳɹ���");
    }
}
