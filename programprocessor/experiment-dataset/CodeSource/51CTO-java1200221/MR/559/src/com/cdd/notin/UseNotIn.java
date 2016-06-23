package com.cdd.notin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UseNotIn {
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
    
    // �������Ӳ�ѯ��Ϊ�����ı���

public List getSubTable() {
    List list = new ArrayList<Student>(); // ����List���϶���
    conn = getConn(); // ��ȡ�����ݿ������
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select * from tb_student where college not in (select college from tb_student where name ='����' )"; // �����ѯ���
        ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while (rest.next()) { // ѭ��������ѯ�����
            Student student = new Student();        //���������ݱ��Ӧ��JavaBean����
            student.setId(rest.getInt(1));          //���ö�������
            student.setName(rest.getString(2));
            student.setCollege(rest.getString(3));
            student.setSpeciality(rest.getString(4));
            student.setAddress(rest.getString(5));
            list.add(student);                      //�򼯺������Ԫ��
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ���
}
    
    public static void main(String[] args) {
        UseNotIn useNot = new UseNotIn();
        List list = useNot.getSubTable();
        System.out.println("����ײ���ͬһ��ѧԺ��ѧ����Ϣ��");
        for(int i = 0 ;i<list.size();i++){
            Student student = (Student)list.get(i);
            System.out.println("ѧ����ţ�"+student.getId()+" ������"+student.getName()+" ѧԺ��"+student.getCollege()+"  ѧ�ƣ�"+student.getSpeciality()+
                    "   ��ַ��"+student.getAddress());
        }
    }
}
