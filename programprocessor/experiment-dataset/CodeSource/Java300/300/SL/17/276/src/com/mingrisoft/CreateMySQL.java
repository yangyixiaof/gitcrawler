package com.mingrisoft;


import java.sql.Connection;
import java.sql.DriverManager;

public class CreateMySQL {
    Connection conn = null;
    
public Connection getConnection() {
    try {
        Class.forName("com.mysql.jdbc.Driver"); // ����MySQL���ݿ�����
        System.out.println("���ݿ��������سɹ�����");
        String url = "jdbc:mysql://localhost:3306/db_database17"; // �������������ݿ��url
        String user = "root"; // �����������ݿ���û���
        String passWord = "111"; // �����������ݿ������
        conn = DriverManager.getConnection(url, user, passWord); // ��������
        System.out.println("�ѳɹ�����MySQL���ݿ⽨�����ӣ���");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return conn;
}
    
    public static void main(String[] args) {
        CreateMySQL mySQL = new CreateMySQL();
        mySQL.getConnection();
    }
}
