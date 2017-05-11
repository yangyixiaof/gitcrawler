package com.cdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentUnion {
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

public List getMessageEmp() {
    conn = getConn(); // ��ȡ�����ݿ������
    List list = new ArrayList<Student>();
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select * from tb_stu2006 union select * from tb_stu2007 union select * from tb_stu2008"; // �����ѯ���
        ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while(rest.next()){              
            Student student = new Student();
            student.setId(rest.getString(1));
            student.setName(rest.getString(2));
            student.setSex(rest.getString(3));
            student.setSpciality(rest.getString(4));
            student.setAddress(rest.getString(5));
            list.add(student);
        }
        return list; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
   
    
}
