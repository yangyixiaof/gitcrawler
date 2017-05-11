package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetTables {
    Connection conn = null;
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // ����MySQL���ݿ�����
            String url = "jdbc:mysql://localhost:3306/db_database21"; // �������������ݿ��url
            String user = "root"; // �����������ݿ���û���
            String passWord = "111"; // �����������ݿ������
            conn = DriverManager.getConnection(url, user, passWord); // ��������
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    // ��ʾ���ݿ�
public ResultSet listDB() {
    String sql = "show tables;"; // �����ѯ����SQL���
    try {
        conn = getConnection(); // ��ȡ���ݿ�����
        Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); // ʵ����Statement����
        ResultSet rs = stmt.executeQuery(sql); // ִ�в�ѯSQL���
        return rs;              //���ز�ѯ���
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}
    
    public static void main(String[] args) {
        GetTables tables = new GetTables();
        ResultSet rest = tables.listDB();
        System.out.println("���ݿ�db_database21�µ����ݱ��У�");
        try {
            while (rest.next()) {
                System.out.println(rest.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
