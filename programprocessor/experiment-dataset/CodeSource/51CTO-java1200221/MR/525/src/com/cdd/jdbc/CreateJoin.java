package com.cdd.jdbc;

import java.sql.*;

public class CreateJoin {
    Connection conn = null;
static {
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        System.out.println("���ݿ��������سɹ���");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    
public Connection getConn() {
    String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // �������ݿ�URL
    String userName = "sa"; // �������ݿ���û���
    String passWord = ""; // �������ݿ�����
    try {
        conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
        if (conn != null) {
            System.out.println("�ѳɹ�����SQLServer2000���ݿ⽨�����ӣ�");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conn; // ����Connection����
}
    
    public static void main(String[] args) {
        CreateJoin join = new CreateJoin();
        Connection conn = join.getConn();
    }
    
}
