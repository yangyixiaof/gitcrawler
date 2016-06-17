package com.cdd.userView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserViewData {
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
    
    // ����ͼ�м�������
    
    public List selectView() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement cs = null; // ����CallableStatement����
        String sql = "Select * from v_ware"; // �����ѯ��ͼ��SQL���
        List list = new ArrayList(); // ���屣���ѯ�����List����
        try {
            cs = conn.createStatement(); // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
            while (rest.next()) { // ѭ��������ѯ�����
                Ware ware = new Ware();
                ware.setwName(rest.getString(1));
                ware.setProfit(rest.getString(2));
                list.add(ware);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
