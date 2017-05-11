package com.cdd.getTriiger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetTrriger {
    
    private Connection conn;
    
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
    
    // ��ѯ���ݿ��е����д���������
    
    public List executeTeacher() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "Select name From sysobjects Where xtype = 'TR'"; // �����ȡ���д�������SQL���
        List list = new ArrayList(); // ���屣���ѯ�����List����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                String name = rest.getString(1);
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        GetTrriger getTriiger = new GetTrriger();
        List list = getTriiger.executeTeacher();
        System.out.println("db_database24���ݿ��µĴ������У�");
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).toString();
            System.out.println(name);
        }
    }
}
