package com.cdd.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class TransferUpdate {
  private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    //��ѯ����ѧ���ɼ���Ϣ
    public List executeTeacher() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "select * from tb_teacher"; // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Teacher teacher = new Teacher();
                teacher.setId(rest.getInt(1));
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return list;
    }
    //��ѯָ����ŵĽ�ʦ��Ϣ
    public Teacher selectTeacher(int id) {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "select * from tb_teacher where id ="+id; // ������ô�����̵�SQL���
        Teacher teacher = new Teacher();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����               
                teacher.setId(rest.getInt(1));
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));               
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return teacher;
    }

public void updateTeacher(Teacher teacher){
    conn = getConn();       //��ȡ���ݿ�����
    try {
        PreparedStatement statement = conn.prepareStatement("update tb_teacher set tName=?,course = ? where id = ?") ;  //����PreparedStatement����
        statement.setString(1, teacher.gettName());     //����Ԥ�������Ĳ���
        statement.setString(2, teacher.getCourse());
        statement.setInt(3, teacher.getId());
        statement.executeUpdate();      //ִ��ɾ������      
    } catch (SQLException e) {          
        e.printStackTrace();
    }        
}
   
}
