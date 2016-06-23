package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CopyDate {
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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
    public List getExcellenceStu() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_stu"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                ExcellenceStu excellenceStu = new ExcellenceStu();
                excellenceStu.setId(rest.getInt(1));
                excellenceStu.setName(rest.getString(2));
                excellenceStu.setSex(rest.getString(3));
                excellenceStu.setSpecialty(rest.getString(4));
                excellenceStu.setGrade(rest.getString(5));
                list.add(excellenceStu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ؼ���
    }
    
    public void insertStu(int id) {
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "insert into tb_excellenceStu select name,sex,specialty,grade from tb_stu where id = "
                    + id; // ����������ݵ�SQL���
            statement.executeUpdate(sql); // ִ�в������
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
