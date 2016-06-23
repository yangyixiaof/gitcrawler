package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrendsSelect {
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
    
    public List getGrade(String operator, String denotation, int mark) {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_grade where " + operator
                    + denotation + mark; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade(); // ���������ݱ��Ӧ��JavaBean����
                grade.setId(rest.getInt(1)); // ���ö�������ֵ
                grade.setName(rest.getString(2));
                grade.setSex(rest.getString(3));
                grade.setEnglish(rest.getInt(4));
                grade.setChinese(rest.getInt(5));
                grade.setMath(rest.getInt(6));
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ؼ���
    }
    
}
