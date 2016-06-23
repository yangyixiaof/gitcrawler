package com.cdd.createView;

import java.io.ObjectInputStream.GetField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateView {
    private static Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
    // �������ݿ�
    
    public boolean executeUpdate(String sql) {
        if (conn == null) {
            getConn(); // ��ȡ���ݿ�����
        }
        try {
            Statement stmt = conn.createStatement(); // ����Statementʵ��
            int iCount = stmt.executeUpdate(sql); // ִ��SQL���
            System.out.println("�����ɹ�����Ӱ��ļ�¼��Ϊ" + String.valueOf(iCount));
            conn.close(); // �ر�����
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
}
