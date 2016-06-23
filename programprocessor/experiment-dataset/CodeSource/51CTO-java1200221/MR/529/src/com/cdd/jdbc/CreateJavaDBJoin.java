package com.cdd.jdbc;

import java.sql.*;
import java.io.*;

public class CreateJavaDBJoin {
    
    private static final String DRIVERCLASS = "org.apache.derby.jdbc.EmbeddedDriver"; // ���ݿ�����
    private static final String URL = "jdbc:derby:db_database22";// ���ݿ�URL
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); // ���������������ݿ����ӵ��߳�
    private static Connection conn = null;// ���ݿ�����
    
    static { // ͨ����̬�����������ݿ����������������ݿⲻ���ڵ�����´������ݿ�
        try {
            Class.forName(DRIVERCLASS); // �������ݿ�����
            System.out.println("���ݿ��������سɹ�����");
            File albumF = new File("db_database22");// �������ݿ��ļ�����
            if (!albumF.exists()) {// �ж����ݿ��ļ��Ƿ����
                String[] sqls = new String[1];// ���崴�����ݿ��SQL���
                sqls[0] = "create table tb_album (name varchar(200) not null)";
            } else {
                conn = DriverManager.getConnection(URL + ";create=true");// �������ݿ�����
                System.out.println("�ѳɹ�����JavaDB���ݿ⽨�����ӣ���");
                threadLocal.set(conn);// �������ݿ�����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected Connection getConnection() { // �������ݿ����ӵķ���
        conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����
        if (conn == null) { // û�п��õ����ݿ�����
            try {
                conn = DriverManager.getConnection(URL);// �����µ����ݿ�����
                threadLocal.set(conn); // �����ݿ����ӱ��浽�߳���
                
            } catch (Exception e) {
                String[] infos = { "δ�ܳɹ��������ݿ⣡", "��ȷ�ϱ�����Ƿ��Ѿ����У�" };
                
                e.printStackTrace();
            }
        }
        
        return conn;
    }
    
    protected boolean closeConnection() { // �ر����ݿ����ӵķ���
        boolean isClosed = true; // Ĭ�Ϲرճɹ�
        conn = (Connection) threadLocal.get(); // ���߳��л�����ݿ�����
        threadLocal.set(null); // ����߳��е����ݿ�����
        if (conn != null) { // ���ݿ����ӿ���
            try {
                conn.close(); // �ر����ݿ�����
            } catch (SQLException e) {
                isClosed = false; // �ر�ʧ��
                e.printStackTrace();
            }
        }
        return isClosed;
    }
    
    public static void main(String[] args) {
        CreateJavaDBJoin javaDBJoin = new CreateJavaDBJoin();
        javaDBJoin.getConnection();
    }
    
}
