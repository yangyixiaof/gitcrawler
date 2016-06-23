package com.cdd.backUp;

import java.sql.*;
import java.util.*;

public class BackupData {
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
            list.add(rs.getString(1)); // ����ѯ������ӵ�List������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ���
}
    
    // �����ѯ�������ݿⷽ��

public void getBak(String databaseName, String databasePath) {
    Connection con = Con();           //��ȡ���ݿ�����
    Statement st;
    try {
        st = con.createStatement();     //ʵ����Statement����
        st.executeUpdate("backup database " + databaseName + " to disk='"
                + databasePath + "'");      //ָ�����ݿⱸ��SQL���
        con.close();                //�ر�����
    } catch (SQLException e) {          
        e.printStackTrace();
    }        
}
    
}
