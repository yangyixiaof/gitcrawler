package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class AbroadUtil {
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
    
    public List getAbroad() {
        List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement staement = conn.createStatement();
            String sql = "select top 3 * from tb_abroads  order by newid()"; // �����ѯ���ݵ�SQL���
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
                Abroad abord = new Abroad(); // ���������ݱ��Ӧ��JavaBean����
                abord.setId(set.getInt(1));
                abord.setFristName(set.getString(2)); // ���ö�������
                abord.setLastName(set.getString(3));
                abord.setNationality(set.getString(4));
                abord.setSpeciality(set.getString(5));
                list.add(abord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ����List����
    }
    
}
