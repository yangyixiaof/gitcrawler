package com.cdd.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteGrade {
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
    
    // ��ѯ����ѧ����Ϣ
    public List executeGrade() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "select * from tb_grade"; // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade();
                grade.setId(rest.getInt(1));
                grade.setName(rest.getString(2));
                grade.setChinese(rest.getFloat("chinese"));
                grade.setEnglist(rest.getFloat("englist"));
                grade.setMath(rest.getFloat("math"));
                list.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // ��ѯ����ѧ����Ϣ
    public List executeStu() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "select * from tb_stu"; // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // ɾ��ѧ���ɼ���Ϣ
    
    public void deleteGrade(int id) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement statement = conn.createStatement(); // ����Statement����
            statement.executeUpdate("delete from tb_grade where id=" + id); // ִ��ɾ������
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
