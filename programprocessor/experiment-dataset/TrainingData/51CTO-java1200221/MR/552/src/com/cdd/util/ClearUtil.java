package com.cdd.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClearUtil {
    // ��ȡ���ݿ�����
    private Connection conn;
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

public void deleteDate(String dataName){
    conn = getConn();   //��ȡ���ݿ�����
    try {
        Statement statement = conn.createStatement();   //��ȡStatement����
        statement.executeUpdate("TRUNCATE TABLE  "+dataName);               //ָ��ɾ�����
    } catch (Exception e) {       
        e.printStackTrace();
    }
    
}
    //�����ѯ���ݿ����������ݱ���
    public List GetRs() {
        List list = new ArrayList<String>();
        try {            
            String[] tableType = { "TABLE" }; // ָ��Ҫ���в�ѯ�ı�����
            Connection conn = getConn(); // ���������ݿ⽨�����ӷ���
            DatabaseMetaData databaseMetaData = conn.getMetaData(); // ��ȡDatabaseMetaDataʵ��
            ResultSet resultSet = databaseMetaData.getTables(null, null, "%",
                    tableType);// ��ȡ���ݿ����������ݱ���
            while(resultSet.next()){
                String tableName = resultSet.getString("TABLE_NAME");
                list.add(tableName);
            }          
        } catch (SQLException e) {
            System.out.println("��¼������ȡʧ�ܣ�");
          
        }
        return list;
    }
    
    public static void main(String[] args) {
        ClearUtil util = new ClearUtil();
        util.deleteDate("tb_empess");
    }
    
}
