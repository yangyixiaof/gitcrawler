package com.cdd.substring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FindStaffer {

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

public List getBirthday() {
    conn = getConn(); // ��ȡ�����ݿ������
    ResultSet rest;
    List list = new ArrayList<Staffer>();
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select id,sName,substring(code,7,8) as birthday ,code,degree,job from tb_staffer"; // �����ѯ���
        rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while (rest.next()) { // ѭ��������ѯ�����
            Staffer staffer = new Staffer();    //���������ݿ��Ӧ��JavaBean����
            staffer.setId(rest.getInt(1));      //���ö�������
            staffer.setsName(rest.getString(2));
            staffer.setBirthday(rest.getString(3));
            staffer.setCode(rest.getString(4));
            staffer.setDegree(rest.getString(5));
            staffer.setJob(rest.getString(6));
            list.add(staffer);                  //��������ӵ�������
        }
        return list; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
