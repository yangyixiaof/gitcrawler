package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BatchAffair {
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
    //��ȡȫ���û���ŷ���
    public List selectIds() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "Select accoutNumber from tb_transition"; // �����ѯ��ͼ��SQL���
        List list = new ArrayList(); // ���屣���ѯ�����List����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                String accoutNumber = rest.getString(1);                
                list.add(accoutNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //����ת�˷���

public void Batch(String incomeId, String goId, float money) throws SQLException {
    try {
        conn = getConn(); // ��ȡ���ݿ�����
        boolean autoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
       
        Statement cs = null; // ����Statement����            
        cs = conn.createStatement(); // ʵ����Statement����
        cs.addBatch("update tb_transition set deposit = deposit-" + money
                + " ,transition = transition-" + money
                + " where accoutNumber = " + goId);             //�����޸�ת�˱������ݷ���
        cs.addBatch("update tb_transition set deposit = deposit+" + money
                + " ,shift = shift+" + money + " where accoutNumber = "
                + incomeId);
        cs.executeBatch(); // ����ִ��SQL���
        cs.close(); // ��Statement����ر�
        conn.commit();
        conn.setAutoCommit(autoCommit);
        conn.close();
    } catch (Exception e) {
        conn.rollback();
        e.printStackTrace();        
    }
}
    
}
