package com.mingrisoft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ClientSocketFrame extends JFrame {
    private JTextArea ta_info;
    private File file = null;// ������ѡ����Ƶ��File����
    private JTextField tf_path;
    private DataInputStream in = null; // ����������
    private DataOutputStream out = null; // ����������
    private Socket socket; // ����Socket����
    private long lengths = -1;// ͼƬ�ļ��Ĵ�С
    private String fileName = null;
    
    private void connect() { // �����׽��ַ���
        ta_info.append("��������......\n"); // �ı�������Ϣ��Ϣ
        try { // ��׽�쳣
            socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
            ta_info.append("������ӡ�\n"); // �ı�������ʾ��Ϣ
            while (true) {
                if (socket != null && !socket.isClosed()) {
                    out = new DataOutputStream(socket.getOutputStream());// ������������
                    in = new DataInputStream(socket.getInputStream());// �������������
                    getServerInfo();// ����getServerInfo()����
                } else {
                    socket = new Socket("192.168.1.122", 1978); // ʵ����Socket����
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }
    }
    
    public static void main(String[] args) { // ������
        ClientSocketFrame clien = new ClientSocketFrame(); // ������������
        clien.setVisible(true); // ��������ʾ
        clien.connect(); // �������ӷ���
    }
    
    private void getServerInfo() {
        try {
            String name = in.readUTF();// ��ȡ�ļ���
            long lengths = in.readLong();// ��ȡ�ļ��ĳ���
            byte[] bt = new byte[(int) lengths];// �����ֽ�����
            for (int i = 0; i < bt.length; i++) {
                bt[i] = in.readByte();// ��ȡ�ֽ���Ϣ���洢���ֽ�����
            }
            FileDialog dialog = new FileDialog(ClientSocketFrame.this, "����");// �����Ի���
            dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
            dialog.setFile(name);
            dialog.setVisible(true);// ��ʾ����Ի���
            String path = dialog.getDirectory();// ����ļ��ı���·��
            String newFileName = dialog.getFile();// ��ñ�����ļ���
            if (path == null || newFileName == null) {
                return;
            }
            String pathAndName = path + "\\" + newFileName;// �ļ�������·��
            FileOutputStream fOut = new FileOutputStream(pathAndName);
            fOut.write(bt);
            fOut.flush();
            fOut.close();
            ta_info.append("�ļ�������ϡ�");
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close();// �ر���
                }
                if (socket != null) {
                    socket.close(); // �ر��׽���
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Create the frame
     */
    public ClientSocketFrame() {
        super();
        setTitle("�ͻ��˳���");
        setBounds(100, 100, 373, 257);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("·����");
        panel.add(label);

        tf_path = new JTextField();
        tf_path.setPreferredSize(new Dimension(140,25));
        panel.add(tf_path);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
                FileFilter filter = new FileNameExtensionFilter("��Ƶ�ļ���AVI/MPG/DAT/RM)", "AVI", "MPG", "DAT", "RM");// ����������
                fileChooser.setFileFilter(filter);// ���ù�����
                int flag = fileChooser.showOpenDialog(null);// ��ʾ�򿪶Ի���
                if (flag == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile(); // ��ȡѡ����Ƶ�ļ���File����
                }
                if (file != null) {
                    tf_path.setText(file.getAbsolutePath());// ��Ƶ�ļ�������·��
                    fileName = file.getName();// �����Ƶ�ļ�������
                }
            }
        });
        button.setText("ѡ����Ƶ");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    DataInputStream inStream = null;// ������������������
                    if (file != null) {
                        lengths = file.length();// �����ѡ����Ƶ�ļ��Ĵ�С
                        inStream = new DataInputStream(new FileInputStream(file));// ��������������
                    } else {
                        JOptionPane.showMessageDialog(null, "��û��ѡ����Ƶ�ļ���");
                        return;
                    }
                    out.writeUTF(fileName);// д����Ƶ�ļ���
                    out.writeLong(lengths);// ���ļ��Ĵ�Сд�������
                    byte[] bt = new byte[(int) lengths];// �����ֽ�����
                    int len = -1;
                    while ((len = inStream.read(bt)) != -1) {// ����Ƶ�ļ���ȡ���ֽ�����
                        out.write(bt);// ���ֽ�����д�������
                    }
                    out.flush();
                    out.close();
                    ta_info.append("�ļ�������ϡ�");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_1.setText("��  ��");
        panel.add(button_1);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        //
    }
}
