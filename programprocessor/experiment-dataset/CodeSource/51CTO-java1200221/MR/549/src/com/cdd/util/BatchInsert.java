package com.cdd.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BatchInsert {
  Connection conn = null;
    
    // ��ȡ���ݿ�����
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            if (conn != null) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
public void insertStu(String sql){
    conn = getConn();                       //��ȡ���ݿ�����
    try {
        Statement statement = conn.createStatement();   //����Statement����
        statement.executeUpdate(sql);       //ִ�в���SQL���
    } catch (Exception e) {           
        e.printStackTrace();
    }        
}

public static void main(String[] args) {
    BatchInsert insert = new BatchInsert();     //�����������
    String sql = "insert tb_stu select '˫˫','Ů','�����ѧ','08d02' " +
    		"union all select '��ˬ','Ů','�����Ӧ��','08d02' " +
    		"union all select '����','Ů','Ӣ��','07d02'";     //��������SQL���
    insert.insertStu(sql);          //���ò������ݷ���
}
}
