package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WareUtil {
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
    public List getWare() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select id,wName,price,convert(varchar(30),price/(select sum(price) from tb_ware) * 100)+'%' as percente from tb_ware"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Ware ware = new Ware(); // ���������ݱ��Ӧ��JavaBean����
                ware.setId(rest.getInt(1)); // ���ö�������
                ware.setwName(rest.getString(2));
                ware.setPrice(rest.getFloat(3));
                ware.setPercent(rest.getString(4));
                list.add(ware); // �񼯺�����Ӷ���
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ؼ���
    }
}
