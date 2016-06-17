package com.cdd.triiger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTrigger {
    
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
    
    public int insertGrade(Grade grade) {
        conn = getConn(); // ��ȡ���ݿ�����
        PreparedStatement cs = null; // ����PreparedStatement����
        int count = 0;
        try {
            String sql = "insert into tb_grade values(?,?,?,?)"; // �������SQL���
            cs = conn.prepareStatement(sql);
            cs.setString(1, grade.getName()); // ����Ԥ����������
            cs.setFloat(2, grade.getMath());
            cs.setFloat(3, grade.getEnglist());
            cs.setFloat(4, grade.getChinese());
            count = cs.executeUpdate(); // ִ��Ԥ������䣬ʵ�ֲ������
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
}
