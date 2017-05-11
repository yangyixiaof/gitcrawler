package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClientFrame extends JFrame {
    private JTextField tf_newUser;
    private JList user_list;
    private JTextArea ta_info;
    private JTextField tf_send;
    private ObjectOutputStream out;// �������������
    private boolean loginFlag = false;// Ϊtrueʱ��ʾ�Ѿ���¼��Ϊfalseʱ��ʾδ��¼
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChatClientFrame frame = new ChatClientFrame();
                    frame.setVisible(true);
                    frame.createClientSocket();// ���÷��������׽��ֶ���
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void createClientSocket() {
        try {
            Socket socket = new Socket("192.168.1.122", 1982);// �����׽��ֶ���
            out = new ObjectOutputStream(socket.getOutputStream());// �������������
            new ClientThread(socket).start();// �����������̶߳���
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    class ClientThread extends Thread {
        Socket socket;
        public ClientThread(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));// ��������������
                DefaultComboBoxModel model = (DefaultComboBoxModel) user_list
                        .getModel();// ����б���ģ��
                while (true) {
                    String info = in.readLine().trim();// ��ȡ��Ϣ
                    if (!info.startsWith("MSG:")) {// ���յ��Ĳ�����Ϣ
                        if (info.startsWith("�˳���")) {// ���յ������˳���Ϣ
                            model.removeElement(info.substring(3));// ���û��б����Ƴ��û�
                        } else {// ���յ����ǵ�¼�û�
                            boolean itemFlag = false;// ����Ƿ�Ϊ�б������б��Ϊtrue����ӣ�Ϊfalse���
                            for (int i = 0; i < model.getSize(); i++) {// ���û��б���б���
                                if (info.equals((String) model.getElementAt(i))) {// ����û��б��д��ڸ��û���
                                    itemFlag = true;// ����Ϊtrue����ʾ����ӵ��û��б�
                                    break;// ����forѭ��
                                }
                            }
                            if (!itemFlag) {
                                    model.addElement(info);// ����¼�û���ӵ��û��б�
                            } 
                        }
                    } else {// �����õ�����Ϣ�������ı�������ʾ���յ�����Ϣ
                        DateFormat df = DateFormat.getDateInstance();// ���DateFormatʵ��
                        String dateString = df.format(new Date());         // ��ʽ��Ϊ����
                        df = DateFormat.getTimeInstance(DateFormat.MEDIUM);// ���DateFormatʵ��
                        String timeString = df.format(new Date());         // ��ʽ��Ϊʱ��
                        String sendUser = info.substring(4,info.indexOf("�����͸���"));// ��÷�����Ϣ���û�
                        String receiveInfo = info.substring(info.indexOf("������Ϣ�ǣ�")+6);// ��ý��յ�����Ϣ
                        ta_info.append("  "+sendUser + "    " +dateString+"  "+timeString+"\n  "+receiveInfo+"\n");// ���ı�������ʾ��Ϣ
                        ta_info.setSelectionStart(ta_info.getText().length()-1);// ����ѡ����ʼλ��
                        ta_info.setSelectionEnd(ta_info.getText().length());// ����ѡ��Ľ���λ��
                        tf_send.requestFocus();// ʹ������Ϣ�ı����ý���
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void send() {
        if (!loginFlag) {
            JOptionPane.showMessageDialog(null, "���ȵ�¼��");
            return;// ����û�û��¼�򷵻�
        }
        String sendUserName = tf_newUser.getText().trim();// ��õ�¼�û���
        String info = tf_send.getText();// �������ķ�����Ϣ
        if (info.equals("")) {
            return;// ���û������Ϣ�򷵻أ���������
        }
        Vector<String> v = new Vector<String>();// ���������������ڴ洢���͵���Ϣ
        Object[] receiveUserNames = user_list.getSelectedValues();// ���ѡ����û�����
        if (receiveUserNames.length <= 0) {
            return;// ���ûѡ���û��򷵻�
        }
        for (int i = 0; i < receiveUserNames.length; i++) {
            String msg = sendUserName + "�����͸���" + (String) receiveUserNames[i]
                    + "������Ϣ�ǣ� " + info;// ���巢�͵���Ϣ
            v.add(msg);// ����Ϣ��ӵ�����
        }
        try {
            out.writeObject(v);// ������д��������������Ϣ�ķ���
            out.flush();// ˢ�����������
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateFormat df = DateFormat.getDateInstance();// ���DateFormatʵ��
        String dateString = df.format(new Date());         // ��ʽ��Ϊ����
        df = DateFormat.getTimeInstance(DateFormat.MEDIUM);// ���DateFormatʵ��
        String timeString = df.format(new Date());         // ��ʽ��Ϊʱ��
        String sendUser = tf_newUser.getText().trim();// ��÷�����Ϣ���û�
        String receiveInfo = tf_send.getText().trim();// ��ʾ���͵���Ϣ
        ta_info.append("  "+sendUser + "    " +dateString+"  "+timeString+"\n  "+receiveInfo+"\n");// ���ı�������ʾ��Ϣ
        tf_send.setText(null);// ����ı���
        ta_info.setSelectionStart(ta_info.getText().length()-1);// ����ѡ�����ʼλ��
        ta_info.setSelectionEnd(ta_info.getText().length());// ����ѡ��Ľ���λ��
        tf_send.requestFocus();// ʹ������Ϣ�ı����ý���
    }
    
    /**
     * Create the frame
     */
    public ChatClientFrame() {
        super();
        setTitle("�����ҿͻ���");
        setBounds(100, 100, 385, 288);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("�����������ݣ�");
        panel.add(label);
        
        tf_send = new JTextField();
        tf_send.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                send();// ���÷���������Ϣ
            }
        });
        tf_send.setPreferredSize(new Dimension(180, 25));
        panel.add(tf_send);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                send();// ���÷���������Ϣ
            }
        });
        button.setText("��  ��");
        panel.add(button);
        
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(100);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        final JScrollPane scrollPane = new JScrollPane();
        splitPane.setRightComponent(scrollPane);
        
        ta_info = new JTextArea();
        ta_info.setFont(new Font("", Font.BOLD, 14));
        scrollPane.setViewportView(ta_info);
        
        final JScrollPane scrollPane_1 = new JScrollPane();
        splitPane.setLeftComponent(scrollPane_1);
        
        user_list = new JList();
        user_list.setModel(new DefaultComboBoxModel(new String[] { "" }));
        scrollPane_1.setViewportView(user_list);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�û����ƣ�");
        panel_1.add(label_1);
        
        tf_newUser = new JTextField();
        tf_newUser.setPreferredSize(new Dimension(140, 25));
        panel_1.add(tf_newUser);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (loginFlag) {// �ѵ�¼���Ϊtrue
                    JOptionPane.showMessageDialog(null, "��ͬһ����ֻ�ܵ�¼һ�Ρ�");
                    return;
                }
                String userName = tf_newUser.getText().trim();// ��õ�¼�û���
                Vector v = new Vector();// �������������ڴ洢��¼�û�
                v.add("�û���" + userName);// ��ӵ�¼�û�
                try {
                    out.writeObject(v);// ���û��������͵�������
                    out.flush();// ˢ�����������
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                tf_newUser.setEnabled(false);// �����û��ı���
                loginFlag = true;// ���ѵ�¼�������Ϊtrue
            }
        });
        button_1.setText("��  ¼");
        panel_1.add(button_1);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String exitUser = tf_newUser.getText().trim();
                Vector v = new Vector();
                v.add("�˳���" + exitUser);
                try {
                    out.writeObject(v);
                    out.flush();// ˢ�����������
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);                                     // �˳�ϵͳ
            }
        });
        button_2.setText("��  ��");
        panel_1.add(button_2);
        //����
        if (SystemTray.isSupported()){                                      // �ж��Ƿ�֧��ϵͳ����
            URL url=ChatClientFrame.class.getResource("client.png");          // ��ȡͼƬ���ڵ�URL
            ImageIcon icon = new ImageIcon(url);                            // ʵ����ͼ�����
            Image image=icon.getImage();                                    // ���Image����
            TrayIcon trayIcon=new TrayIcon(image);                          // ��������ͼ��
            trayIcon.addMouseListener(new MouseAdapter(){                   // Ϊ����������������
                public void mouseClicked(MouseEvent e){                     // ����¼�
                    if (e.getClickCount()==2){                              // �ж��Ƿ�˫�������
                        showFrame();                                    // ���÷�����ʾ����
                    }
                }
            });
            trayIcon.setToolTip("ϵͳ����");                                    // ��ӹ�����ʾ�ı�
            PopupMenu popupMenu=new PopupMenu();                    // ���������˵�
            MenuItem exit=new MenuItem("�˳�");                           // �����˵���
            exit.addActionListener(new ActionListener() {                   // ����¼�������
                public void actionPerformed(final ActionEvent arg0) {
                    String exitUser = tf_newUser.getText().trim();
                    Vector v = new Vector();
                    v.add("�˳���" + exitUser);
                    try {
                        out.writeObject(v);
                        out.flush();// ˢ�����������
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);                                     // �˳�ϵͳ
                }
            });
            popupMenu.add(exit);                                        // Ϊ�����˵���Ӳ˵���
            trayIcon.setPopupMenu(popupMenu);                           // Ϊ����ͼ��ӵ����˵�
            SystemTray systemTray=SystemTray.getSystemTray();           // ���ϵͳ���̶���
            try{
                systemTray.add(trayIcon);                               // Ϊϵͳ���̼�����ͼ��
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public void showFrame(){
        this.setVisible(true);                                              // ��ʾ����
        this.setState(Frame.NORMAL);
    }

}
