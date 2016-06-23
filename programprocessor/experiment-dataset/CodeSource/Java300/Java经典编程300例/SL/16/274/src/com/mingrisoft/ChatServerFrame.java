package com.mingrisoft;

import java.awt.BorderLayout;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatServerFrame extends JFrame {
    private JTextArea ta_info;
    private ServerSocket server; // ����ServerSocket����
    private Socket socket; // ����Socket����socket
    private Hashtable<String, Socket> map = new Hashtable<String, Socket>();// ���ڴ洢���ӵ����������û��Ϳͻ����׽��ֶ���
    
    public void createSocket() {
        try {
            server = new ServerSocket(1982);// �����������׽��ֶ���
            while (true) {
                ta_info.append("�ȴ��¿ͻ�����......\n");
                socket = server.accept();// ����׽��ֶ���
                ta_info.append("�ͻ������ӳɹ���" + socket + "\n");
                new ServerThread(socket).start();// �����������̶߳���
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    class ServerThread extends Thread {
        Socket socket;
        public ServerThread(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                ObjectInputStream ins = new ObjectInputStream(socket
                        .getInputStream());
                while (true) {
                    Vector v = null;
                    try {
                        v = (Vector) ins.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (v != null && v.size() > 0) {
                        for (int i = 0; i < v.size(); i++) {
                            String info = (String) v.get(i);// ��ȡ��Ϣ
                            String key = "";
                            if (info.startsWith("�û���")) {// ��ӵ�¼�û����ͻ����б�
                                key = info.substring(3, info.length());// ����û�������Ϊ��ʹ��
                                map.put(key, socket);// ��Ӽ�ֵ��
                                Set<String> set = map.keySet();// ��ü��������м���Set��ͼ
                                Iterator<String> keyIt = set.iterator();// ������м��ĵ�����
                                while (keyIt.hasNext()) {
                                    String receiveKey = keyIt.next();// ��ñ�ʾ������Ϣ�ļ�
                                    Socket s = map.get(receiveKey);// �����ü���Ӧ���׽��ֶ���
                                    PrintWriter out = new PrintWriter(s
                                            .getOutputStream(), true);// �������������
                                    Iterator<String> keyIt1 = set.iterator();// ������м��ĵ�����
                                    while (keyIt1.hasNext()) {
                                        String receiveKey1 = keyIt1.next();// ��ü���������ͻ�������û��б�
                                        out.println(receiveKey1);// ������Ϣ
                                        out.flush();// ˢ�����������
                                    }
                                }
                            } else if (info.startsWith("�˳���")) {
                                key = info.substring(3);// ����˳��û��ļ�
                                map.remove(key);// ��Ӽ�ֵ��
                                Set<String> set = map.keySet();// ��ü��������м���Set��ͼ
                                Iterator<String> keyIt = set.iterator();// ������м��ĵ�����
                                while (keyIt.hasNext()) {
                                    String receiveKey = keyIt.next();// ��ñ�ʾ������Ϣ�ļ�
                                    Socket s = map.get(receiveKey);// �����ü���Ӧ���׽��ֶ���
                                    PrintWriter out = new PrintWriter(s
                                            .getOutputStream(), true);// �������������
                                    out.println("�˳���" + key);// ������Ϣ
                                    out.flush();// ˢ�����������
                                }
                            } else {// ת�����յ���Ϣ
                                key = info.substring(info.indexOf("�����͸���") + 5,
                                        info.indexOf("������Ϣ�ǣ�"));// ��ý��շ���keyֵ,�����շ����û���
                                String sendUser = info.substring(0, info
                                        .indexOf("�����͸���"));// ��÷��ͷ���keyֵ,�����ͷ����û���
                                Set<String> set = map.keySet();// ��ü��������м���Set��ͼ
                                Iterator<String> keyIt = set.iterator();// ������м��ĵ�����
                                while (keyIt.hasNext()) {
                                    String receiveKey = keyIt.next();// ��ñ�ʾ������Ϣ�ļ�
                                    if (key.equals(receiveKey) && !sendUser.equals(receiveKey)) {// ������û���ͬ�������Ƿ����û�
                                        Socket s = map.get(receiveKey);// �����ü���Ӧ���׽��ֶ���
                                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);// �������������
                                        out.println("MSG:" + info);// ������Ϣ
                                        out.flush();// ˢ�����������
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                ta_info.append(socket + "�Ѿ��˳���\n");
            }
        }
    }
    
    public static void main(String args[]) {
        ChatServerFrame frame = new ChatServerFrame();
        frame.setVisible(true);
        frame.createSocket();
    }
    
    /**
     * Create the frame
     */
    public ChatServerFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowIconified(final WindowEvent e) {
                setVisible(false);
            }
        });
        setTitle("�����ҷ�������");
        setBounds(100, 100, 385, 266);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        
        //����
        if (SystemTray.isSupported()){                                      // �ж��Ƿ�֧��ϵͳ����
            URL url=ChatServerFrame.class.getResource("server.png");          // ��ȡͼƬ���ڵ�URL
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
