package com.cdd.patindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatePatindex {
    private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database23"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }

public List getPatindex(String address) {
    conn = getConn(); // ��ȡ�����ݿ������
    ResultSet rest;
    List list = new ArrayList<Order>();
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select * from tb_order where patindex('" + address
                + "%',address)>0"; // �����ѯ���
        rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while (rest.next()) {           //ѭ��������ѯ�����
            Order order = new Order();      //���������ݱ��Ӧ��JavaBean����
            order.setId(rest.getInt(1));        //���ö�������
            order.setName(rest.getString(2));
            order.setAddress(rest.getString(3));
            order.setPhone(rest.getString(4));
            list.add(order);
        }
        return list; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    
}
