package com.cdd.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchDelete {
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
        String sql = "select * from tb_stu"; // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void deleteBatch(Integer[] id) {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����Statement����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            for (int i = 0; i < id.length; i++) { // ѭ��������������
                cs.addBatch("delete from tb_stu  where id =" + id[i]); // ɾ������
            }
            cs.executeBatch(); // ����ִ��SQL���
            cs.close(); // ��Statement����ر�
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
