package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseUtil {
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
                System.out.println("���ݿ����ӳɹ���");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
    // ��ѯͼ��������м�¼����
    
public List getMerchandise() {
    conn = getConn(); // ��ȡ���ݿ�����
    List list = new ArrayList(); // ���屣�淵��ֵ��List����
    try {
        Statement staement = conn.createStatement(); // ����Statement����
        String sql = "select * from tb_merchandise"; // �����ѯ��SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䣬���ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Merchandise merchandise = new Merchandise();
            merchandise.setId(set.getInt(1));
            merchandise.setWareName(set.getString(2));
            merchandise.setWareDate(set.getString(3));
            list.add(merchandise);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ���
}
    
    // ���尴ָ����ָ�����������ѯ���ݷ���

public String getgetMerchandiseDate(String term) {
    String date = "";
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select  datediff(mm,(select wareDate from tb_merchandise where wareName ='"
                + term + "'),getDate())"; // ���彫ͼ�������Ϣ���������SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            date = set.getString(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return date;
}
    public static void main(String[] args) {
        MerchandiseUtil util = new MerchandiseUtil();
        String date = util.getgetMerchandiseDate("A���ֻ�");
        System.out.println(date);
    }
}
