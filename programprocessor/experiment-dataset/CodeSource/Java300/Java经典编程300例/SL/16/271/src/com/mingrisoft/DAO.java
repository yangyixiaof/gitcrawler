package com.mingrisoft;
import java.sql.*;
import javax.swing.JOptionPane;
public class DAO {
    private static DAO dao = new DAO(); // ����DAO��ľ�̬ʵ��
    /**
     * ���췽�����������ݿ�����
     */
    public DAO() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "���ݿ���������ʧ�ܡ�\n"
                    + e.getMessage());
        }
    }
    
    /**
     * ������ݿ����ӵķ���
     * 
     * @return Connection
     */
    public static Connection getConn() {
        try {
            Connection conn = null; // �������ݿ�����
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=database/db_picture.mdb"; // ���ݿ�db_picture.mdb��URL
            String username = ""; // ���ݿ���û���
            String password = ""; // ���ݿ�����
            conn = DriverManager.getConnection(url, username, password); // ��������
            return conn; // ��������
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "���ݿ�����ʧ�ܡ�\n" + e.getMessage());
            return null;
        }
    }
}