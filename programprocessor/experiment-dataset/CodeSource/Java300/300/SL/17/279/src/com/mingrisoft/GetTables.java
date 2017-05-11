package com.mingrisoft;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTables {
    static Connection conn = null;
    
    // ��ȡ���ݿ�����
    public static Connection getConn() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_database17"; // �������ݿ�URL
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
    
    public static ResultSet GetRs() {
        try {
            String[] tableType = { "TABLE" }; // ָ��Ҫ���в�ѯ�ı�����
            Connection conn = getConn(); // ���������ݿ⽨�����ӷ���
            DatabaseMetaData databaseMetaData = conn.getMetaData(); // ��ȡDatabaseMetaDataʵ��
            ResultSet resultSet = databaseMetaData.getTables(null, null, "%",
                    tableType);// ��ȡ���ݿ����������ݱ���
            return resultSet;
        } catch (SQLException e) {
            System.out.println("��¼������ȡʧ�ܣ�");
            return null;
        }
    }
    
    public static void main(String[] args) {
        ResultSet rst = GetRs();
        System.out.println("���ݿ��еı��У�");
        try {
            while (rst.next()) { // ��������
                String tableName = rst.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
