package com.mingrisoft.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class QueryRunnerTest {
    // ����JDBC��ز���
    private static String URL = "jdbc:mysql://localhost:3306/db_database18";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USERNAME = "root";
    private static String PASSWORD = "111";
    private static Connection conn;
    
    public static Connection getConnection() {// ���ڻ�����ݿ����ӵĹ��߷���
        try {
            DbUtils.loadDriver(DRIVER);// ��������
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ��������
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static List<User> query(String sql) {// ��������ѯ���ת����bean�б�Ĺ��߷���
        QueryRunner qr = new QueryRunner();
        List<User> list = null;
        try {
            list = qr.query(getConnection(), sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// �ر�����
        }
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println("��users�е�ȫ���������£�");
        List<User> list = query("select * from users");// ��ѯusers����ȫ������
        for (User user : list) {
            System.out.println(user);
        }
    }
}
