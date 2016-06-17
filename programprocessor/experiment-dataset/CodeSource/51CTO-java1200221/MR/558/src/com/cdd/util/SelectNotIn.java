package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectNotIn {
    Connection conn = null;
    
    // ��ȡ���ݿ�����
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
            if (conn != null) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }

public List getNotIn() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_student where name not in (select name from tb_grade)"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Student student = new Student();        //���������ݿ��Ӧ��JavaBean����
            student.setId(set.getInt(1));           //���ö�������
            student.setName(set.getString(2));
            student.setCollege(set.getString(3));
            student.setSpeciality(set.getString(4));
            student.setAddress(set.getString(5));
            list.add(student);                  //��������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    
    public static void main(String[] args) {
        SelectNotIn notin = new SelectNotIn();
        List list = notin.getNotIn();
        System.out.println("��ѯû�гɼ���ѧ����Ϣ��");
        for (int i = 0; i < list.size(); i++) {
            Student student = (Student) list.get(i);
            System.out.println("��ţ�" + student.getId() +"  ������"+ student.getName()
                    +"  ѧԺ��"+ student.getCollege() +"  רҵ��"+ student.getSpeciality()
                    +"  ��ַ��"+ student.getAddress());
        }
    }
}
