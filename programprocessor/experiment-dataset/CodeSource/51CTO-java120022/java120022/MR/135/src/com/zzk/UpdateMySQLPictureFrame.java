package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UpdateMySQLPictureFrame extends JFrame {
    private JTextField tf_id;
    private static final long serialVersionUID = 8339759858493972497L;
    private JTextField tf_name;
    private String picturePath = null;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateMySQLPictureFrame frame = new UpdateMySQLPictureFrame();
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
    public UpdateMySQLPictureFrame() {
        super();
        setTitle("�޸�MySQL���ݿ��д洢��ͼƬ");
        setBounds(100, 100, 369, 248);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("��    ����");
        label_1.setBounds(26, 46, 66, 18);
        panel.add(label_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("ͼ    Ƭ��");
        label_2.setBounds(26, 117, 66, 18);
        panel.add(label_2);
        
        tf_name = new JTextField();
        tf_name.setBounds(83, 44, 245, 22);
        panel.add(tf_name);
        
        final JLabel lb_picture = new JLabel();
        lb_picture.setOpaque(true);
        lb_picture.setBackground(Color.PINK);
        lb_picture.setBounds(83, 70, 139, 117);
        panel.add(lb_picture);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // �����ļ��Ի���
                // �����ļ�����
                FileFilter filter = new FileNameExtensionFilter(
                        "ͼ���ļ�(*.gif;*.jpg;*.jpeg;*.png)", "gif", "jpg", "jpeg",
                        "png");
                fileChooser.setFileFilter(filter); // Ϊ�ļ��Ի��������ļ�������
                int returnValue = fileChooser.showOpenDialog(null);// ���ļ�ѡ��Ի���
                if (returnValue == JFileChooser.APPROVE_OPTION) { // �ж��Ƿ�ѡ�����ļ�
                    File file = fileChooser.getSelectedFile(); // ����ļ�����
                    if (file.length() / 1024.0 > 50.0) {
                        JOptionPane
                                .showMessageDialog(null, "��ѡ��С�ڵ���50KB��ͼƬ�ļ���");
                        return;
                    }
                    picturePath = file.getAbsolutePath();
                    Icon icon = new ImageIcon(picturePath);
                    Dimension size = lb_picture.getSize();
                    lb_picture.setIcon(icon);
                    lb_picture.setSize(size);
                }
            }
        });
        button_2.setText("ѡ����Ƭ");
        button_2.setBounds(231, 72, 97, 28);
        panel.add(button_2);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String name = tf_name.getText().trim();
                if (picturePath == null || picturePath.equals("")) {
                    JOptionPane.showMessageDialog(null, "��ѡ��ͼƬ�ļ���");
                    return;
                }
                File file = new File(picturePath);
                try {
                    FileInputStream in = new FileInputStream(file);// ����ͼƬ������������
                    Connection conn = DAO.getConn();// ������ݿ����Ӷ���
                    String sql = "update tb_picture set name = ?,picture = ? where id = ?";// �����޸����ݼ�¼��SQL���
                    PreparedStatement ps = conn.prepareStatement(sql);// ����PreparedStatement���󣬲�����SQL���
                    ps.setString(1, name); // Ϊ��1��������ֵ
                    ps.setBinaryStream(2, in, (int) file.length());// Ϊ��2��������ֵ
                    ps.setInt(3, Integer.parseInt(tf_id.getText()));// Ϊ��3��������ֵ
                    int flag = ps.executeUpdate(); // ִ��SQL��䣬��ø��¼�¼��
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
                    } else {
                        JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
                    }
                    ps.close();// �ر�PreparedStatement����
                    conn.close(); // �ر�����
                    if (in != null) {
                        in.close();// �ر�IO��
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        });
        button.setBounds(231, 112, 97, 28);
        panel.add(button);
        button.setText("��    ��");
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setBounds(231, 154, 97, 28);
        panel.add(button_1);
        button_1.setText("��    ��");

        tf_id = new JTextField();
        tf_id.setText("1");
        tf_id.setBounds(83, 10, 122, 22);
        panel.add(tf_id);

        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int id = Integer.parseInt(tf_id.getText().trim());
                Connection conn = DAO.getConn();
                String sql = "select name,picture from tb_picture where id = ?";
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ps = conn.prepareStatement(sql);// ����PreparedStatement���󣬲�����SQL���
                    ps.setInt(1, id); // Ϊ������ֵ
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String name = rs.getString(1);
                        tf_name.setText(name);
                        Blob img = (Blob) rs.getBlob(2);
                        Icon icon = new ImageIcon(img.getBytes(1, (int) img
                                .length()));
                        Dimension size = lb_picture.getSize();
                        lb_picture.setIcon(icon);
                        lb_picture.setSize(size);
                    }
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_3.setText("����Ų�ѯ");
        button_3.setBounds(211, 7, 117, 28);
        panel.add(button_3);

        final JLabel label = new JLabel();
        label.setText("��    �ţ�");
        label.setBounds(26, 12, 66, 18);
        panel.add(label);
    }
}
