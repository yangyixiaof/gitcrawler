package com.mingrisoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteUtil {
    static Connection conn_obj = null;
    
    // ��ȡ���ݿ�����
    public static Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url_cnt = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17"; // �������ݿ�URL
        String user_name = "sa"; // �������ݿ���û���
        String pass_word = ""; // �������ݿ�����
        try {
            conn_obj = DriverManager.getConnection(url_cnt, user_name, pass_word); // ��ȡ���ݿ�����
            if (conn_obj != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn_obj; // ����Connection����
    }
    

    // �����ѯ����ͬѧ��Ϣ����
    public List selectStu() {
        conn_obj = getConn();
        Statement stmt;
        List list = new ArrayList<Stu>();
        try {
            stmt = conn_obj.createStatement();
            ResultSet res_set = stmt.executeQuery("select * from tb_stu");
            while (res_set.next()) {
                Stu stu_obj = new Stu();
                stu_obj.setId(res_set.getInt(1));
                stu_obj.setName(res_set.getString(2));
                stu_obj.setGrade(res_set.getString("grade"));
                stu_obj.setSex(res_set.getString(3));
                stu_obj.setSpecialty(res_set.getString(4));
                list.add(stu_obj);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    // ����ɾ��ָ����¼�ķ���
    public void deleteStu(int id) {
        conn_obj = getConn(); // ��ȡ���ݿ�����
        try {
            Statement stmt = conn_obj.createStatement();// �������SQL���
            stmt.executeUpdate("delete from tb_stu where id= " + id); // ִ��Ԥ�������
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Stu {
    private int id;
    private String name;
    private String sex;
    private String grade;
    private String specialty;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
