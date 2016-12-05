package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Resume {
    public Connection Con() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection Con = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=master",
                    "sa", "");
            return Con;
        } catch (Exception e) {
            return null;
        }
    }
    
    // ��ȡ�������ݿ�����
    
    public List getDatabase() {
        List list = new ArrayList(); // ����List���϶���
        Connection con = Con(); // ��ȡ���ݿ�����
        Statement st; // ����Statement����
        try {
            st = con.createStatement(); // ʵ����Statement����
            ResultSet rs = st.executeQuery("select name from dbo.sysdatabases"); // ָ����ѯ�������ݿⷽ��
            while (rs.next()) { // ѭ��������ѯ�����
                list.add(rs.getString(1)); // ����ѯ�������ӵ�List������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    
    // �����ѯ�������ݿⷽ��

public void getBak(String databaseName, String databasePath) {
    Connection con = Con(); // ��ȡ���ݿ�����
    Statement st;
    try {          
        st = con.createStatement(); // ʵ����Statement����
        st.executeUpdate("restore database " + databaseName
                + " from disk='" + databasePath + "'"); // ָ�����ݿⱸ��SQL���
        con.close(); // �ر�����
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public static void main(String[] args) {
        Resume resume = new Resume();
        resume.getBak("master", "c://master.bak");
    }
}