package com.mingrisoft.exceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExceptionTest {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/db_database";// MySQL���ݿ��URL
        String DRIVER = "com.mysql.jdbc.Driver";// MySQL���ݿ������
        String USERNAME = "mr";// ���ݿ���û���
        Connection connection = null;
        try {
            Class.forName(DRIVER);// ��������
            connection = DriverManager.getConnection(URL, USERNAME, "");// ��������
        } catch (SQLException e) {// ����SQLException
            e.printStackTrace();
        } catch (ClassNotFoundException e) {// ����ClassNotFoundException
            e.printStackTrace();
        } finally {
            try {
                connection.close();// �ͷ���Դ
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
