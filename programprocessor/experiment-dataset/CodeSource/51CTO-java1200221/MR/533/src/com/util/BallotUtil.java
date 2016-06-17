package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BallotUtil {
    static Connection conn = null;
    
    // ��ȡ���ݿ�����
    public static Connection getConn() {
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
    
    // �������ѡƱ�����ֶη���
    
    public void addField(String fieldName, String type) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "alter table tb_ballot add " + fieldName + " " + type; // �����ݱ�������ֶ�
            statement.executeUpdate(sql); // ִ�и������ݱ�SQL���
            conn.close(); // �ر����ݿ�����
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ����ɾ��ѡƱ�����ֶη���
    
    public void deleteField(String fieldName) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement statement = conn.createStatement();
            String sql = "alter table tb_ballot drop column " + fieldName; // ��������ݿ���ɾ���ֶ�SLQ���
            statement.executeUpdate(sql); // ִ��ɾ������
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // �����ȡ���ݱ��������ֶη���
    public List getField() {
        List list = new ArrayList();
        conn = getConn();
        try {
            Statement ps = conn.createStatement();
            ResultSet rest = ps.executeQuery("select * from tb_ballot");
            ResultSetMetaData rsme = rest.getMetaData();
            int columnCount = rsme.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String cName = rsme.getColumnName(i);
                list.add(cName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
}
