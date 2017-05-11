package com.mingrisoft.exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CatchExceptions {
    private static String URL = "jdbc:mysql://localhost:3306/db_database18";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USERNAME = "mr";
    private static String PASSWORD = "mingri";
    private static Connection conn;
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);// ������������
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ��������
            return conn;
        } catch (ClassNotFoundException e) {// ������Ϊ�����쳣
            e.printStackTrace();
        } catch (SQLException e) {// ����SQL�쳣
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        CatchExceptions.getConnection();
    }
}
