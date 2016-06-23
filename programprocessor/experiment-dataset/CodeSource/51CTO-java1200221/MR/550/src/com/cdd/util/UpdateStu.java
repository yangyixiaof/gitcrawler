package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpdateStu {
    // ��ȡ���ݿ�����
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
            if (conn != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    //�������tb_stu����

public void updateStu(Stu stu){
    conn = getConn();   //��ȡ���ݿ�����
    try {
        PreparedStatement statement = conn.prepareStatement("update tb_stu set name = ?,sex = ?,grade = ?,specialty = ? where id = ?");//�������SQL���
        statement.setString(1, stu.getName());  //����Ԥ����������
        statement.setString(2, stu.getSex());
        statement.setString(3,stu.getGrade());
        statement.setString(4, stu.getSpecialty());
        statement.setInt(5, stu.getId());
        statement.execute();    //ִ��Ԥ�������
    } catch (Exception e) {            
        e.printStackTrace();
    }        
}
    
    //�����ѯ����ͬѧ��Ϣ����
    public List selectStu(){
        conn = getConn();
        Statement statement;
        List list = new ArrayList<Stu>();
        try {
            statement = conn.createStatement();
            ResultSet rest = statement.executeQuery("select * from tb_stu");
            while(rest.next()){
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setGrade(rest.getString("grade"));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return list;        
    }
    //���尴��ָ����Ų�ѯѧ����Ϣ����
    public Stu selectStu(int id){
        conn = getConn();
        Statement statement;
        Stu stu = new Stu();
        try {         
            statement = conn.createStatement();
            ResultSet rest = statement.executeQuery("select * from tb_stu where id = "+id);
            while(rest.next()){              
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setGrade(rest.getString("grade"));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));          
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return stu;        
    }
}
