package com.cdd.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserUtil {
    
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

public boolean executeUpdate(User user) {
    conn = getConn(); // ��ȡ���ݿ�����
    CallableStatement cs = null; // ����CallableStatement����
    String sql = "{call insertUser('" + user.getUserName() + "','"
            + user.getPassword() + "','" + user.getAge() + "','"
            + user.getSex() + "','" + user.getJob() + "')}";    //������ô�����̵�SQL���
    try {
        cs = conn.prepareCall(sql);     //ʵ����CallableStatement����
        cs.executeUpdate();             //ִ��SQL���
        return true;
    } catch (SQLException e) {   
        e.printStackTrace();
        return false;
    }        
}
}
