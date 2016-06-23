package com.cdd.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BatchUpdate {
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
    
    // ��ѯ����ѧ���ɼ���Ϣ
    public List executeStu() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "select distinct dept from tb_laborage"; // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                String dept = rest.getString(1);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void updateBatch(Object[] dept, int laborage) {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����Statement����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            for (int i = 0; i < dept.length; i++) {
                cs.addBatch("update tb_laborage set laborage = laborage +"
                        + laborage + " where dept = '" + dept[i] + "'"); // �޸�����
            }
            cs.executeBatch(); // ����ִ��SQL���
            cs.close(); // ��Statement����ر�
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
