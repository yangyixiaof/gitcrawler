package com.mingrisoft;


import java.sql.*;

public class CreateOracle {

public Connection getConnection() {
    Connection conn = null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");   //�������ݿ�����
        System.out.println("���ݿ��������سɹ���");           //�������Ϣ
        String url = "jdbc:oracle:thin:@localhost:1521:orcl3";  //��ȡ����URL
        String user = "system";                     //�����û���
        String password = "aaa";                    //��������
        Connection con = DriverManager.getConnection(url, user, password);  //��ȡ���ݿ�����
        if (con != null) {
            System.out.println("�ɹ�����Oracle���ݿ⽨�����ӣ���");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }        
    return conn;        //����Connectionʵ��        
}

    public static void main(String[] args) {
        CreateOracle oracle = new CreateOracle();
        oracle.getConnection();
    }
}
