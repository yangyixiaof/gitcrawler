package com.cdd.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AchievementUtil {
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

public float getBooKDesc() {
    conn = getConn(); // ��ȡ���ݿ�����
    float sum = 0;
    try {
        Statement staement = conn.createStatement();
        String sql = "select sum(achievement)as sum from tb_achievement where monthCount <= 3"; // ������ҵ�һ���ȶ����ܺ͵�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            sum = set.getFloat(1);         //��ȡ��ѯ���       
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sum;
}
    
    public static void main(String[] args) {
        AchievementUtil util = new AchievementUtil();
        float ft = util.getBooKDesc();
        System.out.println("�������е�һ���ȵ��ܶ������Ϊ�� "+ft);
    }
}
