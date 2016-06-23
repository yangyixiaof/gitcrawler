package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
    Connection conn = null;
    
    // ��ȡ���ݿ�����
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �������ݿ�URL
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
    // ���尴ָ����ָ�����������ѯ���ݷ���    
public List getMINStudent() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select name,sex,age,specialty from tb_student where age = (select min(age) from  tb_student)"; // �����ѯѧ������������С��ѧ����ϢSQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
          Student student = new Student();  //����ѧ������
          student.setName(set.getString("name"));   //����ѧ���������������
          student.setSex(set.getString("sex"));
          student.setAge(set.getInt("age"));
          student.setSpecialty(set.getString("specialty"));
          list.add(student);                //��ѧ��������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;        //����List����
}
    public static void main(String[] args) {
        StudentUtil util = new StudentUtil();
        List list = util.getMINStudent();
        System.out.println("��ѯ�����С��ѧ����Ϣ��");
        for(int i = 0;i<list.size();i++){
            Student student = (Student)list.get(i);
            System.out.println("ѧ��������"+student.getName()+
                    " ��ѧ���Ա�"+student.getSex()+" ��ѧ�����䣺"+student.getAge()+" ����ѧרҵ��"
                    +student.getSpecialty());
        }
    }
}
