package com.cdd.deleteProcedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteProcedure {
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
    
    public List executeGain() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null;
        String sql = "select name from sysobjects where xtype = 'p' and status > 0"; // ������ô�����̵�SQL���
        List list = new ArrayList(); // �������ڷ���ֵ�ļ��϶���
        try {
            cs = conn.createStatement();
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                String name = rest.getString(1); // ��ȡ��ѯ�����������
                list.add(name); // ��������ӵ�������
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ�����
    }
    
    public boolean executeUpdate(String[] sql) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement stmt = conn.createStatement(); // ʵ����Statement����
            for (int i = 0; i < sql.length; i++) {
                stmt.executeUpdate("DROP PROCEDURE " + sql[i]); // ִ��ɾ������
            }
            conn.close(); // �ر�����
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
