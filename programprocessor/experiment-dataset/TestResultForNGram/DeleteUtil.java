package com.mingrisoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteUtil {
    static Connection conn = null;
    
    public static Connection getConn() {
        try {
        	// right 2
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
        	// right 1
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17"; // �������ݿ�URL
        String userName = "sa";
        // wrong of not generalizing.
        String passWord = "";
        try {
            conn = DriverManager.getConnection(url, userName, passWord);
            if (conn != null) {
            }
        } catch (SQLException e) {
        	// right 1
            e.printStackTrace();
        }
        // wrong of not generalizing.
        return conn;
    }
    
    public List selectStu() {
        conn = getConn();
        Statement statement;
        List list = new ArrayList<Stu>();
        try {
            statement = conn.createStatement();
            ResultSet rest = statement.executeQuery("select * from tb_stu");
            while (rest.next()) {
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setGrade(rest.getString("grade"));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (Exception e) {
        	// right 1
            e.printStackTrace();
        }
        return list;
    }
    
    public void deleteStu(int id) {
        conn = getConn();
        try {
            Statement statement = conn.createStatement();
            // not handling variables.
            statement.executeUpdate("delete from tb_stu where id= " + id);
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
    	// right 1
        this.id = id;
    }
    public String getName() {
    	// right 1
        return name;
    }
    public void setName(String name) {
    	// right 1
    	this.name = name;
    }
    public String getSex() {
    	// right 1
        return sex;
    }
    public void setSex(String sex) {
    	// right 1
    	this.sex = sex;
    }
    public String getGrade() {
    	// right 1
    	return grade;
    }
    public void setGrade(String grade) {
    	// right 1
    	this.grade = grade;
    }
    public String getSpecialty() {
    	// right 1
    	return specialty;
    }
    public void setSpecialty(String specialty) {
    	// right 1
    	this.specialty = specialty;
    }
}
