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
    
    // 获取数据库连接
    public static Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url_cnt = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17"; // 连接数据库URL
        String user_name = "sa"; // 连接数据库的用户名
        String pass_word = ""; // 连接数据库密码
        try {
            conn_obj = DriverManager.getConnection(url_cnt, user_name, pass_word); // 获取数据库连接
            if (conn_obj != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn_obj; // 返回Connection对象
    }
    

    // 定义查询所有同学信息方法
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
    
    // 定义删除指定记录的方法
    public void deleteStu(int id) {
        conn_obj = getConn(); // 获取数据库连接
        try {
            Statement stmt = conn_obj.createStatement();// 定义更新SQL语句
            stmt.executeUpdate("delete from tb_stu where id= " + id); // 执行预处理语句
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
