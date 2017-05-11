package com.cdd.derive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DeriveTable {
    private Connection conn ;   //����Connection����
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
    //�������Ӳ�ѯ��Ϊ�����ı���
    
    public List getSubTable() {
        List list = new ArrayList<Emp>(); // ����List���϶���
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from (select id,eName,headship,laborage from tb_emp)tb"; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Emp emp = new Emp(); // ���������ݱ��Ӧ��JavaBean����
                emp.setId(rest.getInt(1)); // ���ö�������
                emp.setName(rest.getString(2));
                emp.setHeadship(rest.getString(3));
                emp.setLaborage(rest.getFloat(4));
                list.add(emp); // ��������ӵ�������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    
    //�����ѯԱ������������Ϣ����
    public List getFullMessage() {
        List list = new ArrayList<Emp>(); // ����List���϶���
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_emp"; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
               Emp emp = new Emp();
               emp.setId(rest.getInt(1));
               emp.setName(rest.getString(2));
               emp.setHeadship(rest.getString(3));
               emp.setDept(rest.getString(4));
               emp.setJoinDate(rest.getString(5));
               emp.setLaborage(rest.getFloat(6));
               list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }       
}
