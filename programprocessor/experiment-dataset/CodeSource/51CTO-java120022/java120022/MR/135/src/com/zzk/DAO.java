package com.zzk;

import java.sql.*;
import javax.swing.JOptionPane;

public class DAO {
    private static DAO dao = new DAO(); // ����DAO��ľ�̬ʵ��
    
    /**
     * ���췽�����������ݿ�����
     */
    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "���ݿ���������ʧ�ܣ��뽫MySQL�������õ�����·���С�\n" + e.getMessage());
        }
    }
    
    /**
     * ������ݿ����ӵķ���
     * @return Connection
     */
    public static Connection getConn() {
        try {
            Connection conn = null; // �������ݿ�����
            String url = "jdbc:mysql://localhost:3306/db_database?useUnicode=true&characterEncoding=utf-8"; // ���ݿ�db_database��URL
            String username = "root"; // ���ݿ���û���
            String password = "111"; // ���ݿ�����
            conn = DriverManager.getConnection(url, username, password); // ��������
            return conn; // ��������
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܡ�" + e.getMessage());
            return null;
        }
    }
}