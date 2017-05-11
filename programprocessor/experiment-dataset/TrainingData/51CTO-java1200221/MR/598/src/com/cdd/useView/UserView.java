package com.cdd.useView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserView {
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
    
    public List selectView() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "Select * from v_laborage"; // �����ѯ��ͼ��SQL���
        List list = new ArrayList(); // ���屣���ѯ�����List����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Laborage laborage = new Laborage();
                laborage.setId(rest.getInt(1));
                laborage.setName(rest.getString(2));
                laborage.setDept(rest.getString(3));
                laborage.setLaborage(rest.getString(4));
                list.add(laborage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
