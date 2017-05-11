package com.cdd.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTrigger {
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

public void insertInfo(User user){
    conn = getConn();       //��ȡ���ݿ�����
    try {
        PreparedStatement statement = conn.prepareStatement("insert into tb_user values(?,?,?,?,?)");   //����������ݵ�SQL���
        statement.setString(1, user.getUserName());     //����Ԥ�������Ĳ���ֵ
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getAge());
        statement.setString(4,user.getSex());
        statement.setString(5, user.getJob());
        statement.executeUpdate();      //ִ��Ԥ�������
    } catch (Exception e) {           
        e.printStackTrace();
    }        
}
}
